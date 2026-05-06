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
          pname = "wit-bindgen";
          name = "wit-bindgen";
          src = pkgs.fetchFromGitHub {
            owner = "scala-wasm";
            repo = "wit-bindgen";
            rev = "4f740e9e767e0e1f50f87c708aa50a3970a519de";
            hash = "sha256-WfAgCFkAoPwi+SDZZYhXzh5UKahwWT902KL+7Qi6yBo=";
          };
         cargoHash = "sha256-bAs+j5HJkJ5j6ZZBzfPDbMeu96c92VDbrIt+QKBPofU=";
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
