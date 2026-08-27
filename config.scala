val rootElement = "root"

val repoPath = "repos"

val config = Map(
  "wasmports-demo" -> (
    deps = List("cats-effect"),
    republishJS = "run",
    republish = "run"
  ),

  "fs2" -> (
    deps = List(
      "cats-effect",
      "cats-core", 
      "cats-mtl",
      "discipline-munit",
      "munit-cats-effect",
      "scalacheck-effect",
      "ip4s",
      "wasi4s"
    ),
    republishJS = "rootJS/publishLocal",
    republish = "root/publishLocal"
  ),

  "scalacheck-effect" -> (
    deps = List("munit", "cats", "cats-effect"),
    republishJS = "rootJS/publishLocal",
    republish = "root/publishLocal"
  ),

  "munit-cats-effect" -> (
    deps = List("munit", "cats-effect"),
    republishJS = "rootJS/publishLocal",
    republish = "root/publishLocal"
  ),

  "cats" -> (
    deps = List(
      "discipline-munit",
      "munit",
      //"scala-wasm"
    ),
    republishJS = "catsJS/publishLocal",
    republish = "cats/publishLocal"
  ),

  "cats-effect" -> (
    deps = List(
      "cats",
      "cats-mtl",
      "discipline-munit",
      "munit-scalacheck",
      "munit",
      "wasi4s"
      //"scala-wasm"
    ),
    republishJS = "rootJS/publishLocal",
    republish = "root/publishLocal"
  ),

  "cats-mtl" -> (
    deps = List(
      "cats",
      "munit",
      "discipline-munit",
      //"scala-wasm"
    ),
    republishJS = "rootJS/publishLocal",
    republish = "root/publishLocal"
  ),

  "discipline-munit" -> (
    deps = List(
      "munit",
      "munit-scalacheck",
      "discipline",
      //"scala-wasm"
    ),
    republishJS = "rootJS/publishLocal",
    republish = "root/publishLocal"
  ),

  "munit" -> (
    deps = List(
      "wasi4s"
      //"scala-wasm"
    ),
    republishJS = "munitJS/publishLocal",
    republish = "munit-root/publishLocal"
  ),

  "munit-scalacheck" -> (
    deps = List(
      "munit",
      //"scala-wasm"
    ),
    republishJS = "munitScalacheckJS/publishLocal",
    republish = "munit-scalacheck-root/publishLocal"
  ),

  "ip4s" -> (
    deps = List(
      "cats",
      "cats-effect",
      "munit-scalacheck",
      "munit-cats-effect",
      "discipline"
    ),
    republishJS = "rootJS/publishLocal",
    republish = "publishLocal"
  ),

  "wasi4s" -> (
    deps = List.empty,
    republishJS = "publishLocal",
    republish = "publishLocal",
  ),

  "discipline" -> (
    deps = List.empty,
    republishJS = "disciplineJS/publishLocal",
    republish = "discipline/publishLocal",
  )

  /*
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
  */
)
