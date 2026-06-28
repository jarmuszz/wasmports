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

  def republish(node: String, publishCommand: String => String): IO[Unit] = {
    val sorted = if (node == rootElement) topsort(node).tail else topsort(node)

    Stream
      .emits[IO, String](sorted)
      .evalMap { node => 
        ProcessBuilder("sbt", publishCommand(node))
          .withWorkingDirectory(Path(repoPath) / Path(node))
          .spawn[IO]
          .use { proc =>
            val printStdout = proc.stdout.map(_.toChar.toString).evalTapChunk(IO.print).compile.drain
            val exit = proc.exitValue.ensure(java.io.IOException("Exit code non-zero"))(_ == 0)
            printStdout >> exit
          }
      }
      .compile
      .drain
  }


  def run(args: List[String]): IO[ExitCode] = {
    args match {
      case List("publishRootAll") => republish("root", config(_).republish).as(ExitCode.Success)
      case List("publishJS", pkg) => republish(pkg, config(_).republishJS).as(ExitCode.Success)
      case List("publishAll", pkg) => republish(pkg, config(_).republish).as(ExitCode.Success)
      case List("list", pkg) => IO.println(topsort(pkg)).as(ExitCode.Success)
      case _ => IO.raiseError(IllegalArgumentException(s"Invalid args: ${args}"))
    }
  }
}
