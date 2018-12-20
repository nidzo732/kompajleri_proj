package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.Class;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SemanticAnalyzer extends VisitorAdaptor
{
    private static Struct activeType = Tab.noType;
    private List<String> references = new ArrayList<>();
    private String pathBaseName = "";
    private boolean returnMade;
    private int enumV = 0;

    @Override
    public void visit(Type type)
    {
        Struct tableType = TableWrapper.getType(type.getName());
        if (tableType == null)
        {
            CompilerError.raise("Unknown type: " + type.getName(), type);
            tableType = Tab.noType;
        }
        type.compilerannotation = new CompilerAnnotation();
        type.compilerannotation.type = tableType;
        type.compilerannotation.obj = TableWrapper.getSymbol(type.getName());
        activeType = tableType;
    }

    @Override
	public void visit(ProgName pn)
    {
        TableWrapper.openProgramScope(pn.getName());
    }

    @Override
    public void visit(ProgramDerived1 program)
    {
        TableWrapper.closeScope();
    }

    @Override
    public void visit(ClsName cls)
    {
        Obj obj = TableWrapper.openClassScope(cls.getName(), cls);
        ((Class) cls.getParent()).compilerannotation = new CompilerAnnotation();
        ((Class) cls.getParent()).compilerannotation.obj = obj;
    }

    @Override
    public void visit(Class cls)
    {
        if (!TableWrapper.validateImplementation())
        {
            CompilerError.raise("Class does not implement all interfaces", cls);
        }
        Obj classObject = TableWrapper.closeScope();
        TableWrapper.dumpVtpData(classObject);

    }

    @Override
    public void visit(InterfName ifName)
    {
        TableWrapper.openInterfaceScope(ifName.getName(), ifName);
    }

    @Override
    public void visit(InterfaceDecl ifDecl)
    {
        TableWrapper.closeScope();
    }

    @Override
    public void visit(InterfaceMethodDeclaration imd)
    {
        TableWrapper.closeScope();
    }

    @Override
    public void visit(FuncName fn)
    {
        Obj obj = TableWrapper.openFunctionScope(fn.getName(), fn.getType().compilerannotation.type, fn);
        if (obj == null)
        {
            CompilerError.raise("Redeclaration of name " + fn.getName(), fn);
        }
        fn.compilerannotation = new CompilerAnnotation();
        fn.compilerannotation.obj = obj;
        fn.compilerannotation.type = obj.getType();
        setFunctionObject(fn, obj);
        returnMade = false;
    }

    @Override
    public void visit(ProcedureName pn)
    {
        Obj obj = TableWrapper.openFunctionScope(pn.getName(), Tab.noType, pn);
        if (obj == null)
        {
            CompilerError.raise("Redeclaration of name " + pn.getName(), pn);
            obj = Tab.noObj;
        }
        pn.compilerannotation = new CompilerAnnotation();
        pn.compilerannotation.obj = obj;
        pn.compilerannotation.type = obj.getType();
        setFunctionObject(pn, obj);
    }

    private void setFunctionObject(SyntaxNode sn, Obj obj)
    {
        SyntaxNode fn = sn.getParent().getParent();
        if (fn instanceof Function)
        {
            Function function = (Function) fn;
            function.compilerannotation = new CompilerAnnotation();
            function.compilerannotation.obj = obj;
        }
    }

    @Override
    public void visit(FormParsWrap fpw)
    {
        TableWrapper.functionHeaderDone();
    }

    @Override
    public void visit(Function fun)
    {
        if (!returnMade && TableWrapper.getCurrentFunction().getType() != Tab.noType)
        {
            CompilerError.raise("No return in a method returning non-void", fun);
        }
        if (!TableWrapper.validateAgainstBase())
        {
            CompilerError.raise("Incompatible redefinition", fun);
        }
        if (fun.compilerannotation.obj != Tab.noObj) TableWrapper.closeScope();
    }

    @Override
    public void visit(EnumNm enumName)
    {
        enumV = 0;
        TableWrapper.openEnumScope(enumName.getName(), enumName);
    }

    @Override
    public void visit(EnumDecl eDecl)
    {
        TableWrapper.closeScope();
    }

    @Override
    public void visit(NumberedEnumConst nec)
    {
        TableWrapper.declareEnumConstant(nec.getName(), nec.getValue(), nec);
        enumV = nec.getValue() + 1;
    }

    @Override
    public void visit(EnumConst ec)
    {
        TableWrapper.declareEnumConstant(ec.getName(), enumV++, ec);
    }

    @Override
    public void visit(Variable varDecl)
    {
        TableWrapper.declareVariable(varDecl.getName(), activeType, varDecl);
    }

    @Override
    public void visit(Array array)
    {
        TableWrapper.declareVariable(array.getName(), new Struct(Struct.Array, activeType), array);
    }

    @Override
    public void visit(BoolConstantDeclaration bcd)
    {
        if (!activeType.compatibleWith(TableWrapper.getType("bool")))
        {
            CompilerError.raise("Mismatch between constant type and value", bcd);
        }
        TableWrapper.declareConstant(bcd.getName(), activeType, bcd.getVal() ? 1 : 0, bcd);
    }

    @Override
    public void visit(NumberConstantDeclaration ncd)
    {
        if (!activeType.compatibleWith(TableWrapper.getType("int")))
        {
            CompilerError.raise("Mismatch between constant type and value", ncd);
        }
        TableWrapper.declareConstant(ncd.getName(), activeType, ncd.getVal(), ncd);
    }

    @Override
    public void visit(CharConstantDeclaration ccd)
    {
        if (!activeType.compatibleWith(TableWrapper.getType("char")))
        {
            CompilerError.raise("Mismatch between constant type and value", ccd);
        }
        TableWrapper.declareConstant(ccd.getName(), activeType, ccd.getVal(), ccd);
    }

    @Override
    public void visit(ExtendsDecl ed)
    {
        Struct base = TableWrapper.getType(ed.getBase());
        if (base == null)
        {
            CompilerError.raise("Unknown type " + ed.getBase(), ed);
        }
        if (base.getKind() != Struct.Class)
        {
            CompilerError.raise("Extending from a non-class type " + ed.getBase(), ed);
        }
        TableWrapper.setBaseClass(base, ed);
    }

    @Override
    public void visit(ImplementedNm impName)
    {
        Struct base = impName.getType().compilerannotation.type;
        if (base.getKind() != Struct.Interface)
        {
            CompilerError.raise("Implementing from a non-interface type " + impName.getType().getName(), impName);
        }
        TableWrapper.addImplementedInterface(base, impName);
    }
    public void visit(BaseDesignator bd)
    {
        Obj obj = TableWrapper.getSymbol(bd.getName());
        if (obj == null)
        {
            CompilerError.raise("Unknown symbol: " + bd.getName(), bd);
            obj = Tab.noObj;
        }
        if (obj.getKind() == Obj.Type && obj.getType().getKind() != Struct.Enum)
        {
        	CompilerError.raise("Cannot use a type here", bd);
        }
        if(obj.getKind()==Obj.Var)
        {
            references.add("Variable referenced: " + bd.getName() + " on Line:" + bd.getLine() + " " + TableWrapper.nodeToString(obj));
        }
        else if(obj.getKind()==Obj.Fld)
        {
            references.add("Field referenced: " + bd.getName() + " on Line:" + bd.getLine() + " " + TableWrapper.nodeToString(obj));
        }
        else if(obj.getKind()==Obj.Meth)
        {
            references.add("Method called: " + bd.getName() + " on Line:" + bd.getLine() + " " + TableWrapper.nodeToString(obj));
        }
        else if(obj.getKind()==Obj.Type && obj.getType().getKind()==Struct.Enum)
        {
            references.add("Enum referenced: " + bd.getName() + " on Line:" + bd.getLine() + " " + TableWrapper.nodeToString(obj));
        }
        else if(obj.getKind()==Obj.Con)
        {
            if(obj.getType().getKind()==Struct.Enum)
            {
                references.add("Enum constant referenced: " + bd.getName() + " on Line:" + bd.getLine() + " " + TableWrapper.nodeToString(obj));
            }
            else
            {
                references.add("Constant referenced: " + bd.getName() + " on Line:" + bd.getLine() + " " + TableWrapper.nodeToString(obj));
            }
        }
        else
        {
            references.add("Symbol referenced: " + bd.getName() + " on Line:" + bd.getLine() + " " + TableWrapper.nodeToString(obj));
        }
        pathBaseName = bd.getName();
        bd.compilerannotation = new CompilerAnnotation();
        bd.compilerannotation.obj = obj;
        bd.compilerannotation.type = obj.getType();
        bd.compilerannotation.path = pathBaseName;
    }

    public void visit(PathDesignator pd)
    {
        Obj obj = null;
        Struct activeDesignatorType = pd.getDesignator().compilerannotation.type;
        if (activeDesignatorType != null && activeDesignatorType.getMembers() != null)
        {
            for (Obj member : activeDesignatorType.getMembers())
            {
                if (member.getName().equals(pd.getName()))
                {
                    obj = member;
                    break;
                }
            }
        }
        if (obj == null)
        {
            CompilerError.raise("Object " + pathBaseName + " does not have a field named: " + pd.getName(), pd);
            obj = Tab.noObj;
        }
        if(obj.getKind()==Obj.Fld)
        {
            references.add("Field: " + pd.getName() + " of object: " + pathBaseName + " referenced on Line:" + pd.getLine() + " " + TableWrapper.nodeToString(obj));
        }
        else if(obj.getKind()==Obj.Meth)
        {
            references.add("Method: " + pd.getName() + " of object: " + pathBaseName + " called on Line:" + pd.getLine() + " " + TableWrapper.nodeToString(obj));
        }
        else if(obj.getKind()==Obj.Con)
        {
            references.add("Enum constant: " + pd.getName() + " of : " + pathBaseName + " called on Line:" + pd.getLine() + " " + TableWrapper.nodeToString(obj));
        }
        pathBaseName += "." + pd.getName();
        pd.compilerannotation = new CompilerAnnotation();
        pd.compilerannotation.obj = obj;
        pd.compilerannotation.type = obj.getType();
        pd.compilerannotation.path = pathBaseName;
    }

    public void visit(IndexDesignator id)
    {
        Struct activeDesignatorType = null;
        if (!TableWrapper.assignmentCompatible("int", id.getExpr().compilerannotation.type))
        {
            CompilerError.raise("Array index must be an integer", id);
        }
        activeDesignatorType = id.getDesignator().compilerannotation.type;
        pathBaseName = id.getDesignator().compilerannotation.path;
        if (activeDesignatorType == null || activeDesignatorType.getKind() != Struct.Array)
        {
            activeDesignatorType = Tab.noType;
            CompilerError.raise(pathBaseName + " is not an array", id);
        }
        references.add("Array member of object: " + pathBaseName + " referenced on Line:" + id.getLine() + " " + TableWrapper.nodeToString(id.getDesignator().compilerannotation.obj));
        activeDesignatorType = activeDesignatorType.getElemType();
        pathBaseName += "[...]";
        id.compilerannotation = new CompilerAnnotation();
        id.compilerannotation.type = activeDesignatorType;
        id.compilerannotation.path = pathBaseName;
        id.compilerannotation.obj = new Obj(Obj.Elem, "", activeDesignatorType);
    }

    @Override
    public void visit(NumericConstant c)
    {
        c.compilerannotation = new CompilerAnnotation();
        c.compilerannotation.type = TableWrapper.getType("int");
        c.compilerannotation.obj = new Obj(Obj.Con, "", TableWrapper.getType("int"));
        c.compilerannotation.obj.setAdr(c.getVal());
    }

    @Override
    public void visit(BooleanConstant c)
    {
        c.compilerannotation = new CompilerAnnotation();
        c.compilerannotation.type = TableWrapper.getType("bool");
        c.compilerannotation.obj = new Obj(Obj.Con, "", TableWrapper.getType("bool"));
        c.compilerannotation.obj.setAdr(c.getVal() ? 1 : 0);
    }

    @Override
    public void visit(CharacterConstant c)
    {
        c.compilerannotation = new CompilerAnnotation();
        c.compilerannotation.type = TableWrapper.getType("char");
        c.compilerannotation.obj = new Obj(Obj.Con, "", TableWrapper.getType("char"));
        c.compilerannotation.obj.setAdr((int) c.getVal());
    }

    @Override
    public void visit(SingleFactor c)
    {
        c.compilerannotation = new CompilerAnnotation();
        c.compilerannotation.type = c.getDesignator().compilerannotation.type;
        c.compilerannotation.obj = c.getDesignator().compilerannotation.obj;
        if(c.compilerannotation.obj.getKind()==Obj.Meth)
        {
        	CompilerError.raise("Cannot use a method in an expression", c);
        }
        else if(c.compilerannotation.obj.getKind()==Obj.Prog)
        {
        	CompilerError.raise("Cannot use a program in an expression", c);
        }
        else if(c.compilerannotation.obj.getKind()==Obj.Type)
        {
        	CompilerError.raise("Cannot use a type in an expression", c);
        }
    }

    @Override
    public void visit(NewObject c)
    {
        if (!c.getType().compilerannotation.type.isRefType() || c.getType().compilerannotation.type.getKind() == Struct.Interface)
        {
            CompilerError.raise("Cannot dynamically instantiate " + c.getType().getName(), c);
        }
        c.compilerannotation = new CompilerAnnotation();
        c.compilerannotation.type = c.getType().compilerannotation.type;
    }

    @Override
    public void visit(NewArray c)
    {
        enforceTypes(c, "int", "Array size must be an integer", c.getExpr().compilerannotation);
        c.compilerannotation = new CompilerAnnotation();
        c.compilerannotation.type = new Struct(Struct.Array, c.getType().compilerannotation.type);
    }

    @Override
    public void visit(Parenthesized p)
    {
        p.compilerannotation = new CompilerAnnotation();
        p.compilerannotation.type = p.getExpr().compilerannotation.type;
    }

    @Override
    public void visit(CallResult cr)
    {
        cr.compilerannotation = new CompilerAnnotation();
        cr.compilerannotation.type = cr.getCall().compilerannotation.type;
    }

    @Override
    public void visit(ReadCallS call)
    {
        if (!TableWrapper.assignmentCompatible("int", call.getDesignator().compilerannotation.type) &&
                !TableWrapper.assignmentCompatible("bool", call.getDesignator().compilerannotation.type) &&
                !TableWrapper.assignmentCompatible("char", call.getDesignator().compilerannotation.type))
        {
            CompilerError.raise("Read can only handle builtin types", call);
        }
    }

    @Override
    public void visit(PrintCallS call)
    {
        if (!TableWrapper.assignmentCompatible("int", call.getExpr().compilerannotation.type) &&
                !TableWrapper.assignmentCompatible("bool", call.getExpr().compilerannotation.type) &&
                !TableWrapper.assignmentCompatible("char", call.getExpr().compilerannotation.type))
        {
            CompilerError.raise("Print can only handle builtin types", call);
        }
    }

    @Override
    public void visit(PrintCallWidth call)
    {
        if (!TableWrapper.assignmentCompatible("int", call.getExpr().compilerannotation.type) &&
                !TableWrapper.assignmentCompatible("bool", call.getExpr().compilerannotation.type) &&
                !TableWrapper.assignmentCompatible("char", call.getExpr().compilerannotation.type))
        {
            CompilerError.raise("Print can only handle builtin types", call);
        }
    }

    @Override
    public void visit(FuncCall fc)
    {
        fc.compilerannotation = new CompilerAnnotation();
        Obj function = fc.getDesignator().compilerannotation.obj;
        if (function.getKind() != Obj.Meth)
        {
            CompilerError.raise("Attempting to call a non-callable object", fc);
        }
        fc.compilerannotation.type = fc.getDesignator().compilerannotation.type;
        fc.compilerannotation.obj = fc.getDesignator().compilerannotation.obj;
        Actpars pars = fc.getActpars();
        int argc = function.getLevel();
        List<CompilerAnnotation> taList = new LinkedList<>();
        if (pars.compilerannotation != null)
        {
            taList = pars.compilerannotation.arglist;
        }
        fc.compilerannotation.arglist = taList;
        List<Struct> argTypes = new ArrayList<>();
        for (CompilerAnnotation ta : taList)
        {
            argTypes.add(ta.type);
        }
        if (taList.size() < argc)
        {
            CompilerError.raise("Too few arguments for call, " + argc + " required, got " + taList.size(), fc);
        }
        if (taList.size() > argc)
        {
            CompilerError.raise("Too many arguments for call, " + argc + " required, got " + taList.size(), fc);
        }
        if (argc > 0)
        {
            List<Integer> errors = TableWrapper.callCompatible(function, argTypes);
            if (errors.size() > 0)
            {
                if (function != Tab.ordObj && function != Tab.chrObj && function != Tab.lenObj)
                {
                    List<String> args = new ArrayList<>();
                    for (Integer i : errors)
                    {
                        if (taList.get(i).obj != null) args.add(taList.get(i).obj.getName());
                        else args.add("UNKNOWN SYMBOL");
                    }
                    CompilerError.raise("Type mismatch on arguments " + String.join(",", args), fc);
                }
                else
                {
                    CompilerError.raise("Type mismatch on argument", fc);
                }
            }
        }
    }

    @Override
    public void visit(Parameter param)
    {
        param.compilerannotation = new CompilerAnnotation();
        param.compilerannotation.type = param.getExpr().compilerannotation.type;
        param.compilerannotation.arglist = new LinkedList<>();
        param.compilerannotation.arglist.add(param.compilerannotation);
    }

    @Override
    public void visit(Parameters param)
    {
        param.compilerannotation = new CompilerAnnotation();
        param.compilerannotation.type = param.getExpr().compilerannotation.type;
        param.compilerannotation.arglist = param.getActpars().compilerannotation.arglist;
        param.compilerannotation.arglist.add(param.compilerannotation);
    }

    @Override
    public void visit(SingleTerm st)
    {
        st.compilerannotation = new CompilerAnnotation();
        st.compilerannotation.type = st.getFactor().compilerannotation.type;
    }

    @Override
    public void visit(Negated ne)
    {
        enforceTypes(ne, "int", "Only integers can be negated", ne.getFactor().compilerannotation);
        ne.compilerannotation = new CompilerAnnotation();
        ne.compilerannotation.type = ne.getFactor().compilerannotation.type;
    }

    @Override
    public void visit(Multiplication mu)
    {
        enforceTypes(mu, "int", "Only integers can be multiplied", mu.getTerm().compilerannotation, mu.getFactor().compilerannotation);
        mu.compilerannotation = new CompilerAnnotation();
        mu.compilerannotation.type = TableWrapper.getType("int");
    }

    @Override
    public void visit(Division di)
    {
        enforceTypes(di, "int", "Only integers can be divided", di.getTerm().compilerannotation, di.getFactor().compilerannotation);
        di.compilerannotation = new CompilerAnnotation();
        di.compilerannotation.type = TableWrapper.getType("int");
    }

    @Override
    public void visit(Modulus mo)
    {
        enforceTypes(mo, "int", "Only integers can be divided", mo.getTerm().compilerannotation, mo.getFactor().compilerannotation);
        mo.compilerannotation = new CompilerAnnotation();
        mo.compilerannotation.type = TableWrapper.getType("int");
    }

    @Override
    public void visit(SingleExpr se)
    {
        se.compilerannotation = new CompilerAnnotation();
        se.compilerannotation.type = se.getTerm().compilerannotation.type;
    }

    @Override
    public void visit(Addition ad)
    {
        enforceTypes(ad, "int", "Only integers can be added", ad.getExpr().compilerannotation, ad.getTerm().compilerannotation);
        ad.compilerannotation = new CompilerAnnotation();
        ad.compilerannotation.type = TableWrapper.getType("int");
    }

    @Override
    public void visit(Substraction su)
    {
        enforceTypes(su, "int", "Only integers can be substracted", su.getExpr().compilerannotation, su.getTerm().compilerannotation);
        su.compilerannotation = new CompilerAnnotation();
        su.compilerannotation.type = TableWrapper.getType("int");
    }

    @Override
    public void visit(SingleCondFactor scf)
    {
        scf.compilerannotation = new CompilerAnnotation();
        scf.compilerannotation.type = scf.getExpr().compilerannotation.type;
    }

    @Override
    public void visit(Equals eq)
    {
        eq.compilerannotation = new CompilerAnnotation();
        eq.compilerannotation.type = TableWrapper.getType("bool");
        if (!TableWrapper.assignmentCompatible(eq.getExpr().compilerannotation.type, eq.getExpr1().compilerannotation.type))
        {
            CompilerError.raise("Equality test between incompatible types", eq);
        }
    }

    @Override
    public void visit(NotEquals neq)
    {
        neq.compilerannotation = new CompilerAnnotation();
        neq.compilerannotation.type = TableWrapper.getType("bool");
        if (!TableWrapper.assignmentCompatible(neq.getExpr().compilerannotation.type, neq.getExpr1().compilerannotation.type))
        {
            CompilerError.raise("Inequality test between incompatible types", neq);
        }
    }

    @Override
    public void visit(Greater gr)
    {
        enforceTypes(gr, "int", "Only integers can be compared", gr.getExpr().compilerannotation, gr.getExpr1().compilerannotation);
        gr.compilerannotation = new CompilerAnnotation();
        gr.compilerannotation.type = TableWrapper.getType("bool");
    }

    @Override
    public void visit(GreaterEqual gre)
    {
        enforceTypes(gre, "int", "Only integers can be compared", gre.getExpr().compilerannotation, gre.getExpr1().compilerannotation);
        gre.compilerannotation = new CompilerAnnotation();
        gre.compilerannotation.type = TableWrapper.getType("bool");
    }

    @Override
    public void visit(Less le)
    {
        enforceTypes(le, "int", "Only integers can be compared", le.getExpr().compilerannotation, le.getExpr1().compilerannotation);
        le.compilerannotation = new CompilerAnnotation();
        le.compilerannotation.type = TableWrapper.getType("bool");
    }

    @Override
    public void visit(LessEqual leq)
    {
        enforceTypes(leq, "int", "Only integers can be compared", leq.getExpr().compilerannotation, leq.getExpr1().compilerannotation);
        leq.compilerannotation = new CompilerAnnotation();
        leq.compilerannotation.type = TableWrapper.getType("bool");
    }

    @Override
    public void visit(SingleCondTerm sct)
    {
        sct.compilerannotation = new CompilerAnnotation();
        sct.compilerannotation.type = sct.getCondfactor().compilerannotation.type;
    }

    @Override
    public void visit(And and)
    {
        enforceTypes(and, "bool", "AND can only be done on bool", and.getCondfactor().compilerannotation, and.getCondterm().compilerannotation);
        and.compilerannotation = new CompilerAnnotation();
        and.compilerannotation.type = TableWrapper.getType("bool");
    }

    @Override
    public void visit(SingleCondExpr sce)
    {
        sce.compilerannotation = new CompilerAnnotation();
        sce.compilerannotation.type = sce.getCondterm().compilerannotation.type;
    }

    @Override
    public void visit(Or or)
    {
        enforceTypes(or, "bool", "OR can only be done on bool", or.getCondexpr().compilerannotation, or.getCondterm().compilerannotation);
        or.compilerannotation = new CompilerAnnotation();
        or.compilerannotation.type = TableWrapper.getType("bool");
    }

    @Override
    public void visit(JmpCondition cond)
    {
        enforceTypes(cond, "bool", "condition must be a bool", cond.getCondexpr().compilerannotation);
    }

    @Override
    public void visit(Assign assign)
    {
    	if(assign.getDesignator().compilerannotation.obj.getKind()==Obj.Meth)
        {
        	CompilerError.raise("Cannot assign to a method", assign);
        }
        else if(assign.getDesignator().compilerannotation.obj.getKind()==Obj.Prog)
        {
        	CompilerError.raise("Cannot assign to a program", assign);
        }
        else if(assign.getDesignator().compilerannotation.obj.getKind()==Obj.Type)
        {
        	CompilerError.raise("Cannot assign to a type", assign);
        }
        else if(assign.getDesignator().compilerannotation.obj.getKind()==Obj.Con)
        {
        	CompilerError.raise("Cannot assign to a constant", assign);
        }
        if (!TableWrapper.assignmentCompatible(assign.getDesignator().compilerannotation.type, assign.getExpr().compilerannotation.type))
        {
            CompilerError.raise("Assignment between incompatible types", assign);
        }
    }

    public void visit(Continue cnt)
    {
        SyntaxNode node = cnt;
        while (node.getParent() != null)
        {
            if (node.getParent() instanceof ForLoop)
            {
                return;
            }
            if (node.getParent() instanceof While)
            {
                return;
            }
            node = node.getParent();
        }
        CompilerError.raise("CONTINUE must be called in a loop", cnt);
    }

    @Override
    public void visit(Break brk)
    {
        SyntaxNode node = brk;
        while (node.getParent() != null)
        {
            if (node.getParent() instanceof ForLoop)
            {
                return;
            }
            if (node.getParent() instanceof While)
            {
                return;
            }
            node = node.getParent();
        }
        CompilerError.raise("BREAK must be called in a loop", brk);
    }

    @Override
    public void visit(Return rs)
    {
        returnMade = true;
        if (!TableWrapper.inFunction())
        {
            CompilerError.raise("Return called outside of function", rs);
        }
        else if (TableWrapper.getCurrentFunction().getType().getKind() == Struct.None)
        {
            CompilerError.raise("Void function can not return a value", rs);
        }
        else if (!TableWrapper.assignmentCompatible(TableWrapper.getCurrentFunction().getType(), rs.getExpr().compilerannotation.type))
        {
            CompilerError.raise("Returned value does not match function type", rs);
        }
    }

    @Override
    public void visit(EmptyReturn rs)
    {
        if (!TableWrapper.inFunction())
        {
            CompilerError.raise("Return called outside of function", rs);
        }
        else if (TableWrapper.getCurrentFunction().getType().getKind() != Struct.None)
        {
            CompilerError.raise("Empty return statement in a non-void function", rs);
        }
    }

    @Override
    public void visit(Increment increment)
    {
    	if(increment.getDesignator().compilerannotation.obj.getKind()==Obj.Meth)
        {
        	CompilerError.raise("Cannot assign to a method", increment);
        }
        else if(increment.getDesignator().compilerannotation.obj.getKind()==Obj.Prog)
        {
        	CompilerError.raise("Cannot assign to a program", increment);
        }
        else if(increment.getDesignator().compilerannotation.obj.getKind()==Obj.Type)
        {
        	CompilerError.raise("Cannot assign to a type", increment);
        }
        else if(increment.getDesignator().compilerannotation.obj.getKind()==Obj.Con)
        {
        	CompilerError.raise("Cannot assign to a constant", increment);
        }
        enforceTypes(increment, "int", "Only integers can be incremented", increment.getDesignator().compilerannotation);
    }

    @Override
    public void visit(Decrement decrement)
    {
    	if(decrement.getDesignator().compilerannotation.obj.getKind()==Obj.Meth)
        {
        	CompilerError.raise("Cannot assign to a method", decrement);
        }
        else if(decrement.getDesignator().compilerannotation.obj.getKind()==Obj.Prog)
        {
        	CompilerError.raise("Cannot assign to a program", decrement);
        }
        else if(decrement.getDesignator().compilerannotation.obj.getKind()==Obj.Type)
        {
        	CompilerError.raise("Cannot assign to a type", decrement);
        }
        else if(decrement.getDesignator().compilerannotation.obj.getKind()==Obj.Con)
        {
        	CompilerError.raise("Cannot assign to a constant", decrement);
        }
        enforceTypes(decrement, "int", "Only integers can be decremented", decrement.getDesignator().compilerannotation);
    }

    private void enforceTypes(SyntaxNode node, String type, String message, CompilerAnnotation... tas)
    {
        for (CompilerAnnotation ta : tas)
        {
            if (!TableWrapper.assignmentCompatible(type, ta.type))
            {
                CompilerError.raise(message, node);
            }
        }
    }

    void dumpReferences(PrintStream stream)
    {
        for (String reference : references)
        {
            stream.println(reference);
        }
    }
}
