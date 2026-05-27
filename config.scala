val repoPath = "repos"

val config = Map(

  "cats-effect" -> (
    deps = List(
      "munit",
      "scala-wasm"
    ),
    republishJS = "++2.13.18; update; coreJS/publishLocal",
    republish = "++2.13.18; update; core/publishLocal"
  ),


  "munit" -> (
    deps = List(
      "scala-wasm"
    ),
    republishJS = "++2.13.18; update; munitJS/publishLocal",
    republish = "++2.13.18; update; munit/publishLocal"
  ),

  "scala-wasm" -> (
    deps = List.empty[String],
    republishJS = """
      |ir2_12/publishLocal;
      |linkerInterface2_12/publishLocal;
      |linker2_12/publishLocal;
      |testAdapter2_12/publishLocal;
      |sbtPlugin2_12/publishLocal;
      |javalib/publishLocal;
      |javalibintf/publishLocal;


      |library2_13/publishLocal;
      |testInterface2_13/publishLocal;
      |testBridge2_13/publishLocal;
      |jUnitRuntime2_13/publishLocal;
      |jUnitPlugin2_13/publishLocal;
      |scalalib2_13/publishLocal;


      |ir3/publishLocal;
      |linkerInterface3/publishLocal;
      |testAdapter3/publishLocal;
      |sbtPlugin3/publishLocal;

      |++2.13.18 compiler2_13/publishLocal;
    """.stripMargin,

    republish = """
      |ir2_12/publishLocal;
      |linkerInterface2_12/publishLocal;
      |linker2_12/publishLocal;
      |testAdapter2_12/publishLocal;
      |sbtPlugin2_12/publishLocal;
      |javalib/publishLocal;
      |javalibintf/publishLocal;


      |library2_13/publishLocal;
      |testInterface2_13/publishLocal;
      |testBridge2_13/publishLocal;
      |jUnitRuntime2_13/publishLocal;
      |jUnitPlugin2_13/publishLocal;
      |scalalib2_13/publishLocal;


      |ir3/publishLocal;
      |linkerInterface3/publishLocal;
      |testAdapter3/publishLocal;
      |sbtPlugin3/publishLocal;

      |++2.13.18 compiler2_13/publishLocal;
    """.stripMargin
  )
)
