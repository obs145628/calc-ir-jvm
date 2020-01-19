mod bytecodegen;
mod ir;
mod jvmcompiler;
mod parser;

use std::env;

use jvmcompiler::JVMCompiler;

fn main() {
    let file_path = env::args().nth(1).expect("Usage: ./cl-jvm <input-file>");
    let jvm = JVMCompiler::build_jvm();
    let cl = JVMCompiler::new(&jvm);
    let ir_code = parser::parse_file(file_path.as_str());
    bytecodegen::gen_code(&ir_code, &cl);
}
