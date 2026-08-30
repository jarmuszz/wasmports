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
            rev = "190fb717a2260bc24d57347cf9349f729504118c";
            hash = "sha256-RalMNdqJr9aH8JSGG8rasu3Dcm8njFPHrE1uE8mZF5Q=";
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
