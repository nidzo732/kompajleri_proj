package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemanticAnalyzer extends VisitorAdaptor {
    @Override
    public void visit(ProgramName programName) {
        programName.tableref=new TableRef(Tab.insert(Obj.Prog, programName.getName(), Tab.noType));
        Tab.openScope();
    }

    @Override
    public void visit(ProgramDerived1 program) {
        program.getProgramname().tableref.obj.setLocals(Tab.currentScope().getLocals());
        Tab.closeScope();
    }

    @Override
    public void visit(ClassName className)
    {
        className.tableref=new TableRef(Tab.insert(Obj.Type, className.getName(), Tab.noType));
        Tab.openScope();
    }

    @Override
    public void visit(NonDerivedClass ndc)
    {
        ndc.getClassname().tableref.obj.setLocals(Tab.currentScope().getLocals());
        Tab.closeScope();
    }

    @Override
    public void visit(DerivedClass dc)
    {
        dc.getClassname().tableref.obj.setLocals(Tab.currentScope().getLocals());
        Tab.closeScope();
    }

    @Override
    public void visit(FunctionName functionName)
    {
        functionName.tableref=new TableRef(Tab.insert(Obj.Meth, functionName.getName()), Tab.intType);
        Tab.openScope();
    }

    @Override
    public void visit(Func func)
    {
        func.getFunctionname().tableref.obj.setLocals(Tab.currentScope().getLocals());
        Tab.closeScope();
    }

    @Override
    public void visit(Proc func)
    {
        func.getFunctionname().tableref.obj.setLocals(Tab.currentScope().getLocals());
        Tab.closeScope();
    }
}
