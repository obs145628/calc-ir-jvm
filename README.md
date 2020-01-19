# calc-ir-jvm

This is a basic demo to demonstrate how to generate JVM bytecode in Rust.  
It takes as input a basic IR language with 5 operators on integers, and generate a Java class file that runs the computation.

## Dependencies

This was tested with the following:
- Ubuntu 18.04
- Oracle JDK 13.0.2
- [org.ow2.asm](https://asm.ow2.io/) 7.3.1
- Crate [jni](https://crates.io/crates/jni) 0.14.0

## cl-jvm

The main rust project.  
Compiles LIR files to bytecode .class files

## test-asm-hello

Basic test of ASM lib to do an hello world

## test-asm-compute

Basic test of ASM lib to create a compute function with all needed instructions for the compiler, and print the result.

## test-rust-jni

Basic test to run Java code from Rust
