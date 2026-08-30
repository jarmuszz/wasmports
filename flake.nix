{
  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { nixpkgs, flake-utils, ... }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = import nixpkgs { inherit system; };
        wit-bindgen-scala = pkgs.rustPlatform.buildRustPackage {
          pname = "wit-bindgen-scala";
          name = "wit-bindgen-scala";
          doCheck = false;
          src = pkgs.fetchFromGitHub {
            owner = "scala-wasm";
            repo = "wit-bindgen-scala";
            rev = "v0.1.0-rc.1";
            hash = "sha256-RC/1hr/XNnInXfHX8MLaM5L3M/4meYcBbJ41ixvieU8=";
          };
         cargoHash = "sha256-0SFCdV1Bj+rYa0WCzqPGlZNTzhEx0u0orHdNe8+z7kc=";
        };

      in {
        devShells.default = pkgs.mkShell {
          nativeBuildInputs = with pkgs; [
            sbt
            scala-cli
            nodejs
            wasm-tools
            wasmtime
            wkg
            wac-cli
            wit-bindgen-scala
          ];
        };
      }
    );
}
