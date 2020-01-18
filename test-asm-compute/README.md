


# Dependencies

Download `asm.jar` and `asm-util.jar` from `https://search.maven.org/search?q=g:org.ow2.asm%20AND%20a:asm`.  
Put the files in this folder.

# Build compiler

```shell
javac Compute.java
java --class-path "asm.jar:asm-util.jar" org.objectweb.asm.util.ASMifier Compute.class
```

# Exec Without compiler

```shell
javac RunCompute.java
java RunCompute
```

# Running compiler exec

```shell
javac RunCompute.java
javac --class-path "asm.jar"  Compiler.java
java --class-path ".:asm.jar"  Compiler
java RunCompute
```

