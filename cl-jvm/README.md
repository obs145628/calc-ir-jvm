# Dependencies

Download `asm.jar` from `https://search.maven.org/search?q=g:org.ow2.asm%20AND%20a:asm`.  
Put the file in this folder.

# JavaAPI Test

Baic test to make sure the API is running correctly.  
It creates and run a Java Method created with the JVMCompiler API in Java.

```shell
javac --class-path ".:asm.jar"  TestAPI.java
java --class-path ".:asm.jar"  TestAPI
javac RunApp.java
java RunApp
```
The output should be `-2`

# Build Compiler

```shell
javac --class-path ".:asm.jar"  CompilerAPI.java
cargo build
```

# Run compiler

```shell
cargo run ./tests/compute.ir
javac RunApp.java
java RunApp
```

The output should be `-2`
