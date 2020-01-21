use crate::ir;
use crate::jvmcompiler::JVMCompiler;

trait CodeGen {
    fn gen(&self, cl: &JVMCompiler);
}

impl CodeGen for ir::InsMovr {
    fn gen(&self, cl: &JVMCompiler) {
        cl.i_iload(self.src as i32);
        cl.i_istore(self.dst as i32);
    }
}

impl CodeGen for ir::InsMovi {
    fn gen(&self, cl: &JVMCompiler) {
        cl.i_bipush(self.val);
        cl.i_istore(self.dst as i32);
    }
}

impl CodeGen for ir::InsAdd {
    fn gen(&self, cl: &JVMCompiler) {
        cl.i_iload(self.src_l as i32);
        cl.i_iload(self.src_r as i32);
        cl.i_iadd();
        cl.i_istore(self.dst as i32);
    }
}

impl CodeGen for ir::InsSub {
    fn gen(&self, cl: &JVMCompiler) {
        cl.i_iload(self.src_l as i32);
        cl.i_iload(self.src_r as i32);
        cl.i_isub();
        cl.i_istore(self.dst as i32);
    }
}

impl CodeGen for ir::InsMul {
    fn gen(&self, cl: &JVMCompiler) {
        cl.i_iload(self.src_l as i32);
        cl.i_iload(self.src_r as i32);
        cl.i_imul();
        cl.i_istore(self.dst as i32);
    }
}

impl CodeGen for ir::InsDiv {
    fn gen(&self, cl: &JVMCompiler) {
        cl.i_iload(self.src_l as i32);
        cl.i_iload(self.src_r as i32);
        cl.i_idiv();
        cl.i_istore(self.dst as i32);
    }
}

impl CodeGen for ir::InsMod {
    fn gen(&self, cl: &JVMCompiler) {
        cl.i_iload(self.src_l as i32);
        cl.i_iload(self.src_r as i32);
        cl.i_irem();
        cl.i_istore(self.dst as i32);
    }
}

impl CodeGen for ir::Ins {
    fn gen(&self, cl: &JVMCompiler) {
        match self {
            ir::Ins::Movr(ins) => ins.gen(cl),
            ir::Ins::Movi(ins) => ins.gen(cl),
            ir::Ins::Add(ins) => ins.gen(cl),
            ir::Ins::Sub(ins) => ins.gen(cl),
            ir::Ins::Mul(ins) => ins.gen(cl),
            ir::Ins::Div(ins) => ins.gen(cl),
            ir::Ins::Mod(ins) => ins.gen(cl),
        }
    }
}

pub fn gen_code(code: &Vec<ir::Ins>, cl: &JVMCompiler) {
    cl.set_class_name("App");
    cl.begin();

    for ins in code {
        ins.gen(cl);
    }
    cl.i_iload(0);
    cl.i_ireturn();

    cl.set_maxs(2, 16);
    cl.end();
    cl.save_to_file();
}
