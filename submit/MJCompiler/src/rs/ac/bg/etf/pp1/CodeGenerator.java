package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CodeGenerator extends VisitorAdaptor
{
    private Stack<List<Integer>> continueLocations = new Stack<List<Integer>>();
    private Stack<List<Integer>> breakLocations = new Stack<List<Integer>>();
    private Stack<Integer> jmpCondBackpatch = new Stack<>();

    private static FuncCall getFunctionCallForNode(SyntaxNode node)
    {
        while (!(node instanceof FuncCall))
        {
            node = node.getParent();
            if (node == null) return null;
        }
        return ((FuncCall) node);
    }

    private static boolean doStore(SyntaxNode node)
    {
        if (node.getParent() instanceof Assign || node.getParent() instanceof Increment || node.getParent() instanceof Decrement
                || node.getParent() instanceof ReadCallS)
        {
            return true;
        }
        return false;
    }

    private static boolean doCall(SyntaxNode node)
    {
        return node.getParent() instanceof FuncCall;
    }
    
    @Override
    public void visit(FuncName fn)
    {
        Obj obj = fn.compilerannotation.obj;
        int level = obj.getLevel();
        if (TableWrapper.isClassMethod(obj)) level++;
        fn.compilerannotation.start = Code.pc;
        obj.setAdr(Code.pc);
        Code.put(Code.enter);
        Code.put(level);
        Code.put(obj.getLocalSymbols().size());
    }

    @Override
    public void visit(ProcedureName pn)
    {
        Obj obj = pn.compilerannotation.obj;
        int level = obj.getLevel();
        if (TableWrapper.isClassMethod(obj)) level++;
        pn.compilerannotation.start = Code.pc;
        obj.setAdr(Code.pc);
        Code.put(Code.enter);
        Code.put(level);
        Code.put(obj.getLocalSymbols().size());
    }

    @Override
    public void visit(Function function)
    {
    	if(function.compilerannotation.obj.getType()==Tab.noType)
    	{
    		Code.put(Code.exit);
            Code.put(Code.return_);
    	}
    	else
    	{
    		Code.put(Code.trap);
    		Code.put(42);
    	}
        if (function.compilerannotation.obj == TableWrapper.main)
        {
            Code.mainPc = Code.pc;
            Obj program = null;
            for (Obj obj : Tab.currentScope().getLocals().symbols())
            {
                if (obj.getKind() == Obj.Prog)
                {
                    program = obj;
                    break;
                }
            }
            for (Obj clsObject : program.getLocalSymbols())
            {
                if (clsObject.getKind() == Obj.Type && clsObject.getType().getKind() == Struct.Class)
                {
                    int dataaddr = clsObject.getAdr();
                    for (String methodName : TableWrapper.VTPData.get(clsObject.getName()))
                    {
                        Obj methodObject = clsObject.getType().getMembersTable().searchKey(methodName);
                        for (char c : methodName.toCharArray())
                        {
                            Code.loadConst(c);
                            Code.put(Code.putstatic);
                            Code.put2(dataaddr++);
                        }
                        Code.loadConst(-1);
                        Code.put(Code.putstatic);
                        Code.put2(dataaddr++);
                        Code.loadConst(methodObject.getAdr());
                        Code.put(Code.putstatic);
                        Code.put2(dataaddr++);
                    }
                    Code.loadConst(-2);
                    Code.put(Code.putstatic);
                    Code.put2(dataaddr++);
                }
            }
            Code.dataSize = TableWrapper.staticMemorySize;
            Code.put(Code.call);
            Code.put2(TableWrapper.main.getAdr() - Code.pc + 1);
            Code.put(Code.return_);
        }
    }

    @Override
    public void visit(EmptyBlock block)
    {
        block.compilerannotation = new CompilerAnnotation();
        block.compilerannotation.end = Code.pc;
    }

    @Override
    public void visit(StmtBlock block)
    {
        block.compilerannotation = new CompilerAnnotation();
        block.compilerannotation.end = Code.pc;
    }

    public void visit(DesignatorLineStmt statement)
    {
        statement.compilerannotation = new CompilerAnnotation();
        statement.compilerannotation.end = Code.pc;
    }

    public void visit(Block statement)
    {
        statement.compilerannotation = new CompilerAnnotation();
        statement.compilerannotation.end = Code.pc;
    }

    public void visit(If statement)
    {
        statement.compilerannotation = new CompilerAnnotation();
        statement.compilerannotation.end = Code.pc;
        Code.fixup(jmpCondBackpatch.pop());
    }

    public void visit(ElseWrap ew)
    {
        Code.put(Code.jmp);
        Code.put2(0);
        ew.compilerannotation = new CompilerAnnotation();
        ew.compilerannotation.end = Code.pc;
    }

    public void visit(IfElse statement)
    {
        statement.compilerannotation = new CompilerAnnotation();
        statement.compilerannotation.end = Code.pc;
        Code.fixup(statement.getElsewrapper().compilerannotation.end - 2);
        int backpatchAddr = jmpCondBackpatch.pop();
        Code.put2(backpatchAddr, statement.getElsewrapper().compilerannotation.end - backpatchAddr + 1);
    }

    public void visit(While statement)
    {
        List<Integer> breaks = breakLocations.pop();
        List<Integer> continues = continueLocations.pop();
        Code.put(Code.jmp);
        Code.put2(statement.getWhilewrapper().compilerannotation.start - Code.pc + 1);
        statement.compilerannotation = new CompilerAnnotation();
        statement.compilerannotation.end = Code.pc;
        Code.fixup(jmpCondBackpatch.pop());
        for (Integer breakLocation : breaks)
        {
            Code.fixup(breakLocation);
        }
        for (Integer continueLocation : continues)
        {
            Code.put2(continueLocation, statement.getWhilewrapper().compilerannotation.start - continueLocation + 1);
        }
    }

    public void visit(ForLoop statement)
    {
        List<Integer> breaks = breakLocations.pop();
        List<Integer> continues = continueLocations.pop();
        Code.put(Code.jmp);
        Code.put2(statement.getFor2().compilerannotation.end - Code.pc + 1);
        int cndPatch = jmpCondBackpatch.pop();
        if (cndPatch != -1) Code.fixup(cndPatch);
        int incrPatch = statement.getFor3().compilerannotation.end - 2;
        Code.put2(incrPatch, statement.getFor1().compilerannotation.end - incrPatch + 1);
        int cndPatch2 = statement.getFor2().compilerannotation.end - 2;
        Code.put2(cndPatch2, statement.getFor3().compilerannotation.end - cndPatch2 + 1);
        statement.compilerannotation = new CompilerAnnotation();
        statement.compilerannotation.end = Code.pc;
        for (Integer breakLocation : breaks)
        {
            Code.fixup(breakLocation);
        }
        for (Integer continueLocation : continues)
        {
            Code.put2(continueLocation, statement.getFor2().compilerannotation.end - continueLocation + 1);
        }
    }

    public void visit(ReadStatement statement)
    {
        statement.compilerannotation = new CompilerAnnotation();
        statement.compilerannotation.end = Code.pc;
    }

    public void visit(PrintStatement statement)
    {
        statement.compilerannotation = new CompilerAnnotation();
        statement.compilerannotation.end = Code.pc;
    }

    public void visit(PrintStatementW statement)
    {
        statement.compilerannotation = new CompilerAnnotation();
        statement.compilerannotation.end = Code.pc;
    }

    public void visit(Break statement)
    {
        Code.put(Code.jmp);
        Code.put2(0);
        breakLocations.peek().add(Code.pc - 2);
        statement.compilerannotation = new CompilerAnnotation();
        statement.compilerannotation.end = Code.pc;
    }

    public void visit(Continue statement)
    {
        Code.put(Code.jmp);
        Code.put2(0);
        continueLocations.peek().add(Code.pc - 2);
        statement.compilerannotation = new CompilerAnnotation();
        statement.compilerannotation.end = Code.pc;
    }

    public void visit(Return statement)
    {
        Code.put(Code.exit);
        Code.put(Code.return_);
        statement.compilerannotation = new CompilerAnnotation();
        statement.compilerannotation.end = Code.pc;
    }

    public void visit(EmptyReturn statement)
    {
        Code.put(Code.exit);
        Code.put(Code.return_);
        statement.compilerannotation = new CompilerAnnotation();
        statement.compilerannotation.end = Code.pc;
    }

    public void visit(CallStatement statement)
    {
    	if(statement.getCall().compilerannotation.type!=Tab.noType)
    	{
    		Code.put(Code.pop);
    	}
        statement.compilerannotation = new CompilerAnnotation();
        statement.compilerannotation.end = Code.pc;
    }

    public void visit(ForInit statement)
    {
        breakLocations.push(new ArrayList<>());
        continueLocations.push(new ArrayList<>());
        statement.compilerannotation = new CompilerAnnotation();
        statement.compilerannotation.end = Code.pc;
    }

    public void visit(ForInitEmpty statement)
    {
        breakLocations.push(new ArrayList<>());
        continueLocations.push(new ArrayList<>());
        statement.compilerannotation = new CompilerAnnotation();
        statement.compilerannotation.end = Code.pc;
    }

    public void visit(ForCond statement)
    {
    	while(Code.pc>0 && Code.buf[Code.pc-1]==Code.neg)
    	{
    		Code.pc--;
    	}
        Code.loadConst(0);
        Code.put(Code.jcc + Code.eq);
        Code.put2(0);
        jmpCondBackpatch.push(Code.pc - 2);
        Code.put(Code.jmp);
        Code.put2(0);
        statement.compilerannotation = new CompilerAnnotation();
        statement.compilerannotation.end = Code.pc;
    }

    public void visit(ForCondEmpty statement)
    {
        Code.put(Code.jmp);
        Code.put2(0);
        statement.compilerannotation = new CompilerAnnotation();
        statement.compilerannotation.end = Code.pc;
        jmpCondBackpatch.push(-1);
    }

    public void visit(ForIncrement statement)
    {
        Code.put(Code.jmp);
        Code.put2(0);
        statement.compilerannotation = new CompilerAnnotation();
        statement.compilerannotation.end = Code.pc;
    }

    public void visit(ForIncrementEmpty statement)
    {
        Code.put(Code.jmp);
        Code.put2(0);
        statement.compilerannotation = new CompilerAnnotation();
        statement.compilerannotation.end = Code.pc;
    }

    public void visit(WhileWrap statement)
    {
        breakLocations.push(new ArrayList<>());
        continueLocations.push(new ArrayList<>());
        statement.compilerannotation = new CompilerAnnotation();
        statement.compilerannotation.start = Code.pc;
    }

    public void visit(JmpCondition statement)
    {
    	while(Code.pc>0 && Code.buf[Code.pc-1]==Code.neg)
    	{
    		Code.pc--;
    	}
        statement.compilerannotation = new CompilerAnnotation();
        Code.loadConst(0);
        Code.put(Code.jcc + Code.eq);
        Code.put2(0);
        statement.compilerannotation.end = Code.pc;
        jmpCondBackpatch.push(Code.pc - 2);
    }

    @Override
    public void visit(NumericConstant constant)
    {
        Code.load(constant.compilerannotation.obj);
    }

    @Override
    public void visit(CharacterConstant constant)
    {
        Code.load(constant.compilerannotation.obj);
    }

    @Override
    public void visit(BooleanConstant constant)
    {
        Code.load(constant.compilerannotation.obj);
    }

    @Override
    public void visit(NewObject newObj)
    {
        Code.put(Code.new_);
        Code.put2(TableWrapper.getTypeSize(newObj.getType().compilerannotation.type) * 4);
        Code.put(Code.dup);
        Code.loadConst(newObj.getType().compilerannotation.obj.getAdr());
        Code.put(Code.putfield);
        Code.put2(0);
    }

    @Override
    public void visit(Negated neg)
    {
        Code.put(Code.neg);
    }

    @Override
    public void visit(Multiplication mul)
    {
        Code.put(Code.mul);
    }

    @Override
    public void visit(Division div)
    {
        Code.put(Code.div);
    }

    @Override
    public void visit(Modulus mod)
    {
        Code.put(Code.rem);
    }

    @Override
    public void visit(Addition add)
    {
        Code.put(Code.add);
    }

    @Override
    public void visit(Substraction sub)
    {
        Code.put(Code.sub);
    }

    private void testCondition(int condition, CompilerAnnotation annotation)
    {
        Code.put(Code.jcc + condition);
        Code.put2(7);
        Code.loadConst(0);
        Code.put(Code.jmp);
        Code.put2(4);
        Code.loadConst(1);
        putShortCircuitPatch(annotation);
    }
    
    private void putShortCircuitPatch(CompilerAnnotation annotation)
    {
    	annotation.shortCircuitPatchJmp=Code.pc;
    	Code.put(Code.neg);
    	Code.put(Code.neg);
    	Code.put(Code.neg);
    	Code.put(Code.neg);
    	/*Code.loadConst(0);
        Code.put(Code.jmp);
        Code.put2(3);*/
    }

    @Override
    public void visit(Equals eq)
    {
        testCondition(Code.eq, eq.compilerannotation);
    }

    @Override
    public void visit(NotEquals neq)
    {
        testCondition(Code.ne, neq.compilerannotation);
    }

    @Override
    public void visit(Greater gr)
    {
        testCondition(Code.gt, gr.compilerannotation);
    }

    @Override
    public void visit(GreaterEqual ge)
    {
        testCondition(Code.ge, ge.compilerannotation);
    }

    @Override
    public void visit(Less lt)
    {
        testCondition(Code.lt, lt.compilerannotation);
    }

    @Override
    public void visit(LessEqual le)
    {
        testCondition(Code.le, le.compilerannotation);
    }
    
    public void visit(SingleCondTerm ct)
    {
    	ct.compilerannotation.shortCircuitPatchJmp=ct.getCondfactor().compilerannotation.shortCircuitPatchJmp;
    }
    public void visit(SingleCondExpr ce)
    {
    	ce.compilerannotation.shortCircuitPatchJmp=ce.getCondterm().compilerannotation.shortCircuitPatchJmp;
    }
    
    private void jmpPatch(int addr, int jmpKind, int target)
    {
    	int tpc=Code.pc;
    	Code.pc=addr;
    	Code.loadConst(0);
    	Code.put(jmpKind);
    	Code.put2(target-addr-1);
    	Code.pc=tpc;
    }
    
    @Override
    public void visit(Or or)
    {
    	int jmpAddr = or.getCondexpr().compilerannotation.shortCircuitPatchJmp;
    	jmpPatch(jmpAddr, Code.jcc+Code.ne, Code.pc);
    	jmpAddr=or.getCondterm().compilerannotation.shortCircuitPatchJmp;
    	jmpPatch(jmpAddr, Code.jcc+Code.eq, Code.pc+4);
    	Code.loadConst(1);
    	Code.put(Code.jmp);
    	Code.put2(4);
    	Code.loadConst(0);
    	putShortCircuitPatch(or.compilerannotation);
    }

    @Override
    public void visit(And and)
    {
    	int jmpAddr = and.getCondterm().compilerannotation.shortCircuitPatchJmp;
    	jmpPatch(jmpAddr, Code.jcc+Code.eq, Code.pc);
    	jmpAddr=and.getCondfactor().compilerannotation.shortCircuitPatchJmp;
    	jmpPatch(jmpAddr, Code.jcc+Code.ne, Code.pc+4);
    	Code.loadConst(0);
    	Code.put(Code.jmp);
    	Code.put2(4);
    	Code.loadConst(1);
    	putShortCircuitPatch(and.compilerannotation);
    }

    @Override
    public void visit(NewArray newArray)
    {
        Code.put(Code.newarray);
        Code.put(newArray.getType().compilerannotation.type == TableWrapper.getType("char") ? 0 : 1);
    }

    @Override
    public void visit(Assign assign)
    {
        Code.store(assign.getDesignator().compilerannotation.obj);
    }

    @Override
    public void visit(BaseDesignator baseDesignator)
    {
        if (baseDesignator.compilerannotation.obj.getKind() == Obj.Type && baseDesignator.compilerannotation.type.getKind() == Struct.Enum)
            return;
        if (baseDesignator.compilerannotation.obj.getKind() == Obj.Fld)
        {
            Code.put(Code.load_n);
        }
        if (baseDesignator.compilerannotation.obj.getKind() == Obj.Meth && TableWrapper.isClassMethod(baseDesignator.compilerannotation.obj))
        {
            Code.put(Code.load_n);
        }
        if (!doStore(baseDesignator) && !doCall(baseDesignator))
        {
            Code.load(baseDesignator.compilerannotation.obj);
        }
        if (doCall(baseDesignator))
        {
            visitFunctionDesignator((FuncCall) (baseDesignator.getParent()), baseDesignator);
        }
        if (baseDesignator.getParent() instanceof Increment || baseDesignator.getParent() instanceof Decrement)
        {
            if (baseDesignator.compilerannotation.obj.getKind() == Obj.Fld) Code.put(Code.dup);
            Code.load(baseDesignator.compilerannotation.obj);
            Code.loadConst(1);
            if (baseDesignator.getParent() instanceof Increment) Code.put(Code.add);
            else Code.put(Code.sub);
            Code.store(baseDesignator.compilerannotation.obj);
        }
    }

    @Override
    public void visit(IndexDesignator indexDesignator)
    {
        if (!doStore(indexDesignator) && !doCall(indexDesignator))
        {
            Code.load(indexDesignator.compilerannotation.obj);
        }
        if (doCall(indexDesignator))
        {
            visitFunctionDesignator((FuncCall) (indexDesignator.getParent()), indexDesignator);
        }
        if (indexDesignator.getParent() instanceof Increment || indexDesignator.getParent() instanceof Decrement)
        {
            Code.put(Code.dup2);
            Code.load(indexDesignator.compilerannotation.obj);
            Code.loadConst(1);
            if (indexDesignator.getParent() instanceof Increment) Code.put(Code.add);
            else Code.put(Code.sub);
            Code.store(indexDesignator.compilerannotation.obj);
        }
    }

    public void visit(PathDesignator pathDesignator)
    {
        if (!doStore(pathDesignator) && !doCall(pathDesignator))
        {
            Code.load(pathDesignator.compilerannotation.obj);
        }
        if (doCall(pathDesignator))
        {
            visitFunctionDesignator((FuncCall) (pathDesignator.getParent()), pathDesignator);
        }
        if (pathDesignator.getParent() instanceof Increment || pathDesignator.getParent() instanceof Decrement)
        {
            Code.put(Code.dup);
            Code.load(pathDesignator.compilerannotation.obj);
            Code.loadConst(1);
            if (pathDesignator.getParent() instanceof Increment) Code.put(Code.add);
            else Code.put(Code.sub);
            Code.store(pathDesignator.compilerannotation.obj);
        }
    }

    private void visitFunctionDesignator(FuncCall node, Designator designator)
    {
        Obj functionObject = node.compilerannotation.obj;
        if (TableWrapper.isClassMethod(functionObject))
        {
            Code.put(Code.dup);
        }
    }

    public void visit(FuncCall call)
    {
        Obj obj = call.getDesignator().compilerannotation.obj;

        if (TableWrapper.isClassMethod(obj))
        {
            Code.put(Code.getfield);
            Code.put2(0);
            Code.put(Code.invokevirtual);
            for (char c : obj.getName().toCharArray())
            {
                Code.put4(c);
            }
            Code.put4(-1);
        }
        else
        {
            if (obj == Tab.chrObj)
            {

            }
            else if (obj == Tab.ordObj)
            {

            }
            else if (obj == Tab.lenObj)
            {
                Code.put(Code.arraylength);
            }
            else
            {
                Code.put(Code.call);
                Code.put2(obj.getAdr() - Code.pc + 1);
            }
        }
    }

    public void visit(ReadCallS call)
    {
        if (call.getDesignator().compilerannotation.type == Tab.charType) Code.put(Code.bread);
        else Code.put(Code.read);
        Code.store(call.getDesignator().compilerannotation.obj);
    }

    public void visit(PrintCallS call)
    {
        Code.loadConst(0);
        if (call.getExpr().compilerannotation.type == Tab.charType) Code.put(Code.bprint);
        else Code.put(Code.print);
    }

    public void visit(PrintCallWidth call)
    {
        Code.loadConst(call.getWidth());
        if (call.getExpr().compilerannotation.type == Tab.charType) Code.put(Code.bprint);
        else Code.put(Code.print);
    }

    public void visit(Parameter p)
    {
        if (TableWrapper.isClassMethod(getFunctionCallForNode(p).compilerannotation.obj))
        {
            Code.put(Code.dup_x1);
            Code.put(Code.pop);
        }
    }

    public void visit(Parameters p)
    {
        if (TableWrapper.isClassMethod(getFunctionCallForNode(p).compilerannotation.obj))
        {
            Code.put(Code.dup_x1);
            Code.put(Code.pop);
        }
    }
}
