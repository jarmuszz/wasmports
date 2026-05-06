val repoPath = "repos"

val config = Map(
  "cats" -> (
    deps = List("scalacheck", "discipline", "discipline-munit", "munit", "scala-wasm"),
    republishJS = "++2.13.18; update; catsJS/publishLocal",
    republish = "++2.13.18; update; cats/publishLocal"
  ),

  "cats-effect" -> (
    deps = List(
      "cats",
      "scalacheck",
      "coop",
      "cats-mtl",
      "discipline-munit",
      //"scala-js-macrotask-executro",
      "munit-scalacheck",
      "munit",
      "scala-wasm"
    ),
    republishJS = "++2.13.18; update; wasmTests/publishLocal",
    republish = "++2.13.18; update; wasmTests/publishLocal"
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
      //"specs2-core",
      "scala-wasm"
    ),
    republishJS = "++2.13.18; update; rootJS/publishLocal",
    republish = "++2.13.18; update; root/publishLocal"
  ),

  "discipline" -> (
    deps = List("scalacheck", "scala-wasm"),
    republishJS = "++2.13.18; update; disciplineJS/publishLocal",
    republish = "++2.13.18; update; discipline/publishLocal"
  ),

  "discipline-munit" -> (
    deps = List("munit", "munit-scalacheck", "discipline", "scala-wasm"),
    republishJS = "++2.13.18; update; rootJS/publishLocal",
    republish = "++2.13.18; update; root/publishLocal"
  ),

  "munit" -> (
    deps = List(
      //"junit",
      //"scala-reflect",
      //"scalajs-test-interface",
      //"scalajs-junit-test-runtime",
      //"google-cloud-storage"
      "scala-wasm"
    ),
    republishJS = "++2.13.18; update; munitJS/publishLocal",
    republish = "++2.13.18; update; munit/publishLocal"
  ),

  "munit-scalacheck" -> (
    deps = List("scalacheck", "munit", "scala-wasm"),
    republishJS = "++2.13.18; update; munitScalacheckJS/publishLocal",
    republish = "++2.13.18; update; munit-scalacheck/publishLocal"
  ),

  "scalacheck" -> (
    deps = List(
      //"scalajs-test-interface"
      "scala-wasm"
    ),
    republishJS = "++2.13.18; update; rootJS/publishLocal",
    republish = "++2.13.18; update; root/publishLocal"
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
