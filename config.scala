val repoPath = "repos"

val config = Map(
  "cats" -> (
    deps = List("scalacheck", "discipline", "discipline-munit", "munit"),
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
      "munit"
    ),
    republishJS = "++2.13.18; update; wasmTests/publishLocal",
    republish = "++2.13.18; update; wasmTests/publishLocal"
  ),

  "cats-mtl" -> (
    deps = List("cats", "munit", "discipline-munit"),
    republishJS = "++2.13.18; update; rootJS/publishLocal",
    republish = "++2.13.18; update; root/publishLocal"
  ),

  "coop" -> (
    deps = List(
      "cats-mtl",
      "cats",
      //"specs2-core"
    ),
    republishJS = "++2.13.18; update; rootJS/publishLocal",
    republish = "++2.13.18; update; root/publishLocal"
  ),

  "discipline" -> (
    deps = List("scalacheck"),
    republishJS = "++2.13.18; update; disciplineJS/publishLocal",
    republish = "++2.13.18; update; discipline/publishLocal"
  ),

  "discipline-munit" -> (
    deps = List("munit", "munit-scalacheck", "discipline"),
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
    ),
    republishJS = "++2.13.18; update; munitJS/publishLocal",
    republish = "++2.13.18; update; munit/publishLocal"
  ),

  "munit-scalacheck" -> (
    deps = List("scalacheck", "munit"),
    republishJS = "++2.13.18; update; munitScalacheckJS/publishLocal",
    republish = "++2.13.18; update; munit-scalacheck/publishLocal"
  ),

  "scalacheck" -> (
    deps = List(
      //"scalajs-test-interface"
    ),
    republishJS = "++2.13.18; update; rootJS/publishLocal",
    republish = "++2.13.18; update; root/publishLocal"
  )
)
