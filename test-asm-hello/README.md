# Dependencies

Download `asm.jar` and `asm-util.jar` from `https://search.maven.org/search?q=g:org.ow2.asm%20AND%20a:asm`.  
Put the files in this folder.

# Building compiler

```shell
javac HelloWorld.java
java --class-path "asm.jar:asm-util.jar" org.objectweb.asm.util.ASMifier HelloWorld.class
```

# Running compiler exec

```shell
javac --class-path "asm.jar"  Compiler.java
java --class-path ".:asm.jar"  Compiler
java HelloWorld
```

