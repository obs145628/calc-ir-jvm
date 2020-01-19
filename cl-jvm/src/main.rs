use jni::objects::JClass;
use jni::sys::jint;
use jni::AttachGuard;
use jni::InitArgsBuilder;
use jni::JNIVersion;
use jni::JavaVM;

struct MyAPI<'a> {
    jvm: &'a JavaVM,
    env: AttachGuard<'a>,
    class_api: JClass<'a>,
}

impl<'a> MyAPI<'a> {
    /*
    fn new(env: AttachGuard<'a>) -> Self {
        let class_api = env.find_class("api").expect("Failed to find class API");
        MyAPI { env, class_api }
    }
     */

    fn new(jvm: &'a JavaVM) -> Self {
        let env: AttachGuard = jvm
            .attach_current_thread()
            .expect("Failed to create JNI env");

        let class_api = env.find_class("api").expect("Failed to find class API");
        MyAPI {
            jvm,
            env,
            class_api,
        }
    }

    fn foo(&self) {}
}

fn run_exec(x: &MyAPI) {
    x.foo();
}

/*
fn build_api<'a>(env: AttachGuard<'a>) -> MyAPI<'a> {
    let class_api = env.find_class("api").expect("Failed to find class API");
    MyAPI { env, class_api }
}
*/

/*
fn build2<'a>(jvm: &'a JavaVM) -> MyAPI<'a> {
    let env: AttachGuard = jvm
        .attach_current_thread()
        .expect("Failed to create JNI env");
    let res = MyAPI::new(env);
    res
}
*/

fn main() {
    println!("Hello, world!");
}
