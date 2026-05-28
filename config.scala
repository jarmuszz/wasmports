val repoPath = "repos"

val config = Map(
  "cats" -> (
    deps = List("discipline", "discipline-munit", "munit", "scala-wasm"),
    republishJS = "++2.13.18; update; catsJS/publishLocal",
    republish = "++2.13.18; update; cats/publishLocal"
  ),

  "cats-effect" -> (
    deps = List(
      "cats",
      "coop",
      "cats-mtl",
      "discipline-munit",
      "munit-scalacheck",
      "munit",
      "scala-wasm"
    ),
    republishJS = "++2.13.18; update; coreJS/publishLocal",
    republish = "++2.13.18; update; core/publishLocal"
  ),

  "cats-mtl" -> (
    deps = List("cats", "munit", "discipline-munit", "scala-wasm"),
    republishJS = "++2.13.18; update; rootJS/publishLocal",
    republish = "++2.13.18; update; root/publishLocal"
  ),

  "coop" -> (
    deps = List(
      "cats-mtl",
      "cats",
      "scala-wasm"
    ),
    republishJS = "++2.13.18; update; rootJS/publishLocal",
    republish = "++2.13.18; update; root/publishLocal"
  ),

  "discipline" -> (
    deps = List("scala-wasm"),
    republishJS = "++2.13.18; update; disciplineJS/publishLocal",
    republish = "++2.13.18; update; discipline/publishLocal"
  ),

  "discipline-munit" -> (
    deps = List("munit", "discipline", "scala-wasm"),
    republishJS = "++2.13.18; update; rootJS/publishLocal",
    republish = "++2.13.18; update; root/publishLocal"
  ),

  "munit" -> (
    deps = List(
      "scala-wasm"
    ),
    republishJS = "++2.13.18; update; munitJS/publishLocal",
    republish = "++2.13.18; update; munit/publishLocal"
  ),

  "munit-scalacheck" -> (
    deps = List("munit", "scala-wasm"),
    republishJS = "++2.13.18; update; munitScalacheckJS/publishLocal",
    republish = "++2.13.18; update; munit-scalacheck/publishLocal"
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
