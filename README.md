# Cats-Effect & FS2, now on Component Model
[Google Summer of Code project page](https://summerofcode.withgoogle.com/programs/2026/projects/vibpm0UF)


## General information
This repo serves as the central hub for the [`cats-effect`](https://typelevel.org/cats-effect/) and [`fs2`](https://fs2.io) Wasm/WASI porting efforts.

## Getting the code to run
The Component Model backend is developed as a fork of `scala-js` called [`scala-wasm`](https://github.com/scala-wasm/scala-wasm).

In order to run the code, you first have to compile and publish locally the fork of `munit`. After that, you should be free to compile `cats-effect`.
In theory, running `./rebuild.scala publishAll munit` should do the thing.
