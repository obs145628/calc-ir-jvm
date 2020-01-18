use jni::sys::jint;
use jni::InitArgsBuilder;
use jni::JNIVersion;
use jni::JavaVM;

fn main() {
    let jvm_args = InitArgsBuilder::new()
        .version(JNIVersion::V8)
        .option("-Xcheck:jni")
        .build()
        .expect("Failed to create JVM arguments");

    let jvm = JavaVM::new(jvm_args).expect("Failed to create JVM");
    let env = jvm
        .attach_current_thread()
        .expect("Failed to create JNI env");
    let app_class = env.find_class("App").expect("Failed to find App class");
    let val: jint = env
        .call_static_method(app_class, "run", "()I", &[])
        .expect("Fail to call method App.run()")
        .i()
        .expect("Fail to convert return value of App.run() to int");

    println!("val = {}", val);
}
