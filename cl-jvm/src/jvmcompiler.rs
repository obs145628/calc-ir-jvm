use jni::objects::JClass;
use jni::objects::JValue;
use jni::AttachGuard;
use jni::InitArgsBuilder;
use jni::JNIVersion;
use jni::JavaVM;

pub struct JVMCompiler<'a> {
    jvm: &'a JavaVM,
    env: AttachGuard<'a>,
    class_cl: JClass<'a>,
}

impl<'a> JVMCompiler<'a> {
    pub fn build_jvm() -> JavaVM {
        let jvm_args = InitArgsBuilder::new()
            .version(JNIVersion::V8)
            .option("-Xcheck:jni")
            .option("-Djava.class.path=.:asm.jar")
            .build()
            .expect("Failed to create JVM arguments");

        JavaVM::new(jvm_args).expect("Failed to create JVM")
    }

    pub fn new(jvm: &'a JavaVM) -> Self {
        let env: AttachGuard = jvm
            .attach_current_thread()
            .expect("Failed to create JNI env");

        let class_cl = env
            .find_class("CompilerAPI")
            .expect("Failed to find class API");
        JVMCompiler { jvm, env, class_cl }
    }

    pub fn set_class_name(&self, name: &str) {
        let a_name = self
            .env
            .new_string(name)
            .expect("Failed to create className string argument");
        self.env
            .call_static_method(
                self.class_cl,
                "setClassName",
                "(Ljava/lang/String;)V",
                &[JValue::Object(a_name.into())],
            )
            .expect("Fail to call method CompilerAPI.setClassName()");
    }

    pub fn begin(&self) {
        self.env
            .call_static_method(self.class_cl, "begin", "()V", &[])
            .expect("Fail to call method CompilerAPI.begin()");
    }

    pub fn end(&self) {
        self.env
            .call_static_method(self.class_cl, "end", "()V", &[])
            .expect("Fail to call method CompilerAPI.end()");
    }

    pub fn save_to_file(&self) {
        self.env
            .call_static_method(self.class_cl, "saveToFile", "()V", &[])
            .expect("Fail to call method CompilerAPI.saveToFile()");
    }

    pub fn i_bipush(&self, val: i32) {
        self.env
            .call_static_method(self.class_cl, "i_bipush", "(I)V", &[JValue::from(val)])
            .expect("Fail to call method CompilerAPI.i_bipush()");
    }

    pub fn i_istore(&self, val: i32) {
        self.env
            .call_static_method(self.class_cl, "i_istore", "(I)V", &[JValue::from(val)])
            .expect("Fail to call method CompilerAPI.i_istore()");
    }

    pub fn i_iload(&self, val: i32) {
        self.env
            .call_static_method(self.class_cl, "i_iload", "(I)V", &[JValue::from(val)])
            .expect("Fail to call method CompilerAPI.i_iload()");
    }

    pub fn i_iadd(&self) {
        self.env
            .call_static_method(self.class_cl, "i_iadd", "()V", &[])
            .expect("Fail to call method CompilerAPI.i_iadd()");
    }

    pub fn i_isub(&self) {
        self.env
            .call_static_method(self.class_cl, "i_isub", "()V", &[])
            .expect("Fail to call method CompilerAPI.i_isub()");
    }

    pub fn i_imul(&self) {
        self.env
            .call_static_method(self.class_cl, "i_imul", "()V", &[])
            .expect("Fail to call method CompilerAPI.i_imul()");
    }

    pub fn i_idiv(&self) {
        self.env
            .call_static_method(self.class_cl, "i_idiv", "()V", &[])
            .expect("Fail to call method CompilerAPI.i_idiv()");
    }

    pub fn i_irem(&self) {
        self.env
            .call_static_method(self.class_cl, "i_irem", "()V", &[])
            .expect("Fail to call method CompilerAPI.i_irem()");
    }

    pub fn i_ireturn(&self) {
        self.env
            .call_static_method(self.class_cl, "i_ireturn", "()V", &[])
            .expect("Fail to call method CompilerAPI.i_ireturn()");
    }

    pub fn set_maxs(&self, max_stack: i32, max_locals: i32) {
        self.env
            .call_static_method(
                self.class_cl,
                "setMaxs",
                "(II)V",
                &[JValue::from(max_stack), JValue::from(max_locals)],
            )
            .expect("Fail to call method CompilerAPI.setMaxs()");
    }
}
