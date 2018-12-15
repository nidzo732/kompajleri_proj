package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor
{
    public void visit(NumericConstant constant)
    {
        Code.loadConst(constant.getVal());
    }

    public void visit(BaseDesignator designator)
    {
        Obj obj = Tab.find(designator.getName());
        Code.load(obj);
    }

    @Override
    public void visit(ProgramName name)
    {
        Tab.currentScope=((Program)name.getParent()).typeannotation.scope;
    }

    @Override
    public void visit(FunctionName name)
    {
        Tab.currentScope=name.typeannotation.scope;
    }
}
