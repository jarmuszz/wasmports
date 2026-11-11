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
            rev = "v0.1.0";
            hash = "sha256-ipQFTer7ul+v7lKv0SZMS67XV0gZ3Q2H3vFekEsOh+w=";
          };
         cargoHash = "sha256-bwByPgXbLmWFAoitk82IqwZKakL4AO2ntv6ry5TPuZ4=";
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
