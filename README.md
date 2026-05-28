# Cats-Effect & FS2, now on Component Model
[Google Summer of Code project page](https://summerofcode.withgoogle.com/programs/2026/projects/vibpm0UF)


## General information
This repo serves as the central hub for the [`cats-effect`](https://typelevel.org/cats-effect/) and [`fs2`](https://fs2.io) Wasm/WASI porting efforts.

## Getting the code to run
The Component Model backend is developed as a fork of `scala-js` called [`scala-wasm`](https://github.com/scala-wasm/scala-wasm).

You will need to compile some of your dependencies with `scala-wasm` because otherwise the IR will not contain JS-free Wasm code and the linking phase will fail. The `rebuild.scala` script helps with mass-rebuilds:
```
    ./rebuild.scala (list|publishJS|publishAll) packageName
```
It walks through the packages depending on `packageName` and publishes them to the local ivy2 repository in the dependency order. Forked dependencies have their version hardcoded so that it doesn't change between rebuilds.

In general, any package compiled with scala-js <=1.20.1 will probably need to be rebuilt with something newer.
