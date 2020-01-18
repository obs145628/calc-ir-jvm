
Simple tests, calls an external static Java method from rust, and print the result

# Build

```shell
javac App.java
cargo build
```

# Run

```shell
export LD_LIBRARY_PATH=/usr/lib/jvm/java-13-oracle/lib/server
cargo run
```
