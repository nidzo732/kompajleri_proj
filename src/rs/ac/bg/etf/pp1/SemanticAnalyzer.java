package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class SemanticAnalyzer extends VisitorAdaptor {
    @Override
    public void visit(ProgramName programName) {
        TableWrapper.openScope(Obj.Prog, programName.getName(), Tab.noType);
    }

    @Override
    public void visit(ProgramDerived1 program) {
        TableWrapper.closeScope();
    }

    @Override
    public void visit(ClassName className)
    {
        TableWrapper.openClassScope(className.getName());
    }

    @Override
    public void visit(NonDerivedClass ndc)
    {
        TableWrapper.closeScope();
    }

    @Override
    public void visit(DerivedClass dc)
    {
        TableWrapper.closeScope();
    }

    @Override
    public void visit(FunctionName functionName)
    {
        TableWrapper.declareFunction(functionName.getName(), functionName.getParent() instanceof Proc);
    }

    @Override
    public void visit(Func func)
    {
        TableWrapper.closeScope();
    }

    @Override
    public void visit(Proc func)
    {
        TableWrapper.closeScope();
    }

    @Override
    public void visit(Type type)
    {
        TableWrapper.setActiveType(type.getName());
    }

    @Override
    public void visit(Array array)
    {
        TableWrapper.declareVariable(array.getName(), true);
    }

    @Override
    public void visit(Variable variable)
    {
        TableWrapper.declareVariable(variable.getName(), false);
    }

    @Override
    public void visit(FormParsWrapper fpw)
    {
        TableWrapper.methodHeaderDone();
    }
}
