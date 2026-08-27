#!/usr/bin/env -S scala-cli shebang
//> using scala 3.8.3
//> using toolkit typelevel:0.2.0
//> using file config.scala

import cats.effect.*
import fs2.*
import fs2.io.process.*
import fs2.io.file.Path
import cats.implicits.*
import scala.annotation.implicitNotFound
import cats.syntax.all.*

object Main extends IOApp {
  val reversed: Map[String, List[String]] = {
    val mb = scala.collection.mutable.Map.empty[String, List[String]]

    mb(rootElement) = List.empty

    config.foreach { (node, _) =>
      mb(node) = List.empty
      mb.updateWith(rootElement)(_.map(_ :+ node))
    }

    config.foreach { (node, v) => 
      v.deps.foreach { dep => 
        mb.updateWith(dep)(_.map(node +: _)) 
      }
    }

    mb.toMap
  }

  def topsort(node: String): List[String] = {
    type Acc = (lst: List[String], visited: List[String])

    def go(acc: Acc, node: String): Acc =
      if (acc.visited.contains(node)) acc
      else
        reversed(node) match {
          case Nil =>
            (node +: acc.lst, node +: acc.visited)
          case next =>
            val (newLst, newVisited) = next.foldLeft(acc)(go)

            (node +: newLst, node +: newVisited)
        }

    go((List.empty, List.empty), node).lst
  }

  def republish(node: String, publishCommand: String => String, ver: ScalaVer): IO[Unit] = {
    val sorted = if (node == rootElement) topsort(node).tail else topsort(node)
    Stream
      .emits[IO, String](sorted)
      .evalMap { node => 
         republishOne(node, publishCommand, ver)
      }
      .compile
      .drain
  }

  def republishOne(node: String, publishCommand: String => String, ver: ScalaVer): IO[Unit] = {
    val action = publishCommand(node)
    val cmd = "update; " + (ver match {
      case ScalaVer.all => s"++3.3; $action; ++2.13; $action; ++2.12; $action"
      case v => s"++$v ; $action"
    })
    println(cmd)
    ProcessBuilder("sbt", cmd)
      .withWorkingDirectory(Path(repoPath) / Path(node))
      .spawn[IO]
      .use { proc =>
        val printStdout = proc.stdout.map(_.toChar.toString).evalTapChunk(IO.print).compile.drain
        val exit = proc.exitValue.ensure(java.io.IOException("Exit code non-zero"))(_ == 0)
        printStdout >> exit
      }.void
  }

  sealed trait ScalaVer
  object ScalaVer {
    object all extends ScalaVer
    object _3_3 extends ScalaVer { override def toString() = "3.3" }
    object _2_13 extends ScalaVer { override def toString() = "2.13" }
    object _2_12 extends ScalaVer { override def toString() = "2.12" }
  }
  
  enum PublishScope {
    case All, One
  }

  enum BuildTarget {
    case All, JS
  }

  enum ArgOpt {
    case ScalaVersion(ver: ScalaVer)
    case Scope(s: PublishScope)
    case Target(s: BuildTarget)
  }

  def parseArgs(args: List[String]): (List[ArgOpt], List[String]) = {
    @scala.annotation.tailrec
    def go(acc: List[ArgOpt], left: List[String]): (List[ArgOpt], List[String]) =
      if (left.isEmpty) (acc, left)
      else {
        val pair = left match {
          case "-V" :: "2.12" :: tail => Option(ArgOpt.ScalaVersion(ScalaVer._2_12) -> tail)
          case "-V" :: "2.13" :: tail => Option(ArgOpt.ScalaVersion(ScalaVer._2_13) -> tail)
          case "-V" :: "3.3" :: tail  => Option(ArgOpt.ScalaVersion(ScalaVer._3_3) -> tail)
          case "-V" :: "all" :: tail  => Option(ArgOpt.ScalaVersion(ScalaVer.all) -> tail)
          case "-s" :: "all" :: tail  => Option(ArgOpt.Scope(PublishScope.All) -> tail)
          case "-s" :: "one" :: tail  => Option(ArgOpt.Scope(PublishScope.One) -> tail)
          case "-t" :: "all" :: tail  => Option(ArgOpt.Target(BuildTarget.All) -> tail)
          case "-t" :: "js" :: tail   => Option(ArgOpt.Target(BuildTarget.JS) -> tail)
          case _ => None
        }

        pair match {
          case Some(opt, tail) => go(acc :+ opt, tail)
          case None => (acc, left)
        }
      }

    go(List.empty, args)
  }

  def run(args: List[String]): IO[ExitCode] = {
    args match {
      case "publish" :: tail =>
        val (opts, rest) = parseArgs(tail)
        rest.headOption.liftTo[IO] {
          new IllegalArgumentException("You need to specify package name to publish")
        }.flatMap { pkg =>
          val fun = if (opts.contains(ArgOpt.Scope(PublishScope.One))) republishOne else republish
          val transform = (pkg: String) =>
            if (opts.contains(ArgOpt.Target(BuildTarget.JS)))
              config(pkg).republishJS
            else
              config(pkg).republish

          val ver = opts.collectFirst {
            case ArgOpt.ScalaVersion(v) => v
          }.getOrElse(ScalaVer.all)
          println(ver)

          fun(pkg, transform, ver).as(ExitCode.Success)
        }
      case List("list", pkg) =>
        IO.println(topsort(pkg)).as(ExitCode.Success)
      case _ => IO.println("Invalid argument list").as(ExitCode.Error)
    }
  }
}
