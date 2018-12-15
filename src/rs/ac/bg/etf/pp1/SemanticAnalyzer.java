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
    private static Struct activeType=Tab.noType;
    private List<String> references = new ArrayList<>();
    private String pathBaseName = "";
    @Override
    public void visit(Type type)
    {
        Struct tableType=TableWrapper.getType(type.getName());
        if(tableType==null)
        {
            CompilerError.raise("Unknown type: "+type.getName(), type);
        }
        type.typeannotation=new TypeAnnotation();
        type.typeannotation.type=tableType;
        activeType=tableType;
    }
    @Override
    public void visit(ProgramName pn)
    {
        TableWrapper.openProgramScope(pn.getName());
    }

    @Override
    public void visit(ProgramDerived1 program)
    {
        TableWrapper.closeScope();
    }

    @Override
    public void visit(ClassName cls)
    {
        if(TableWrapper.openClassScope(cls.getName())==null)
        {
            CompilerError.raise("Redeclaration of name "+cls.getName(), cls);
        }
    }

    @Override
    public void visit(Class cls)
    {
        if(!TableWrapper.validateImplementation())
        {
            CompilerError.raise("Class does not implement all interfaces", cls);
        }
        TableWrapper.closeScope();
    }

    @Override
    public void visit(InterfaceName ifName)
    {
        if(TableWrapper.openInterfaceScope(ifName.getName())==null)
        {
            CompilerError.raise("Redeclaration of name "+ifName.getName(), ifName);
        }
    }
    @Override
    public void visit(InterfaceDeclaration ifDecl)
    {
        TableWrapper.closeScope();
    }
    @Override
    public void visit(InterfaceMethodDeclaration imd)
    {
        TableWrapper.closeScope();
    }

    @Override
    public void visit(FunctionName fn)
    {
        if(TableWrapper.openFunctionScope(fn.getName(), fn.getType().typeannotation.type)==null)
        {
            CompilerError.raise("Redeclaration of name "+fn.getName(), fn);
        }
    }
    @Override
    public void visit(ProcedureName pn)
    {
        if(TableWrapper.openFunctionScope(pn.getName(), Tab.noType)==null)
        {
            CompilerError.raise("Redeclaration of name "+pn.getName(), pn);
        }
    }

    @Override
    public void visit(FormParsWrapper fpw)
    {
        TableWrapper.functionHeaderDone();
    }

    @Override
    public void visit(Function fun)
    {
        if(!TableWrapper.validateAgainstBase())
        {
            CompilerError.raise("Incompatible redefinition", fun);
        }
        TableWrapper.closeScope();
    }

    private int enumV=0;
    @Override
    public void visit(EnumName enumName)
    {
        enumV=0;
        if(TableWrapper.openEnumScope(enumName.getName())==null)
        {
            CompilerError.raise("Redeclaration of name "+enumName.getName(), enumName);
        }
    }

    @Override
    public void visit(EnumDeclaration eDecl)
    {
        TableWrapper.closeScope();
    }
    @Override
    public void visit(NumberedEnumConstant nec)
    {
        if(TableWrapper.declareEnumConstant(nec.getName(), nec.getValue())==null)
        {
            CompilerError.raise("Redeclaration of constant "+nec.getName(), nec);
        }
        enumV=nec.getValue()+1;
    }
    @Override
    public void visit(EnumConstant ec)
    {
        if(TableWrapper.declareEnumConstant(ec.getName(), enumV++)==null)
        {
            CompilerError.raise("Redeclaration of constant "+ec.getName(), ec);
        }
    }

    @Override
    public void visit(Variable varDecl)
    {
        if(TableWrapper.declareVariable(varDecl.getName(), activeType)==null)
        {
            CompilerError.raise("Redeclaration of variable "+varDecl.getName(), varDecl);
        }
    }

    @Override
    public void visit(Array array)
    {
        if(TableWrapper.declareVariable(array.getName(), new Struct(Struct.Array, activeType))==null)
        {
            CompilerError.raise("Redeclaration of variable "+array.getName(), array);
        }
    }

    @Override
    public void visit(BoolConstantDeclaration bcd)
    {
        if (!activeType.compatibleWith(TableWrapper.getType("bool")))
        {
            CompilerError.raise("Mismatch between constant type and value", bcd);
        }
        if(TableWrapper.declareConstant(bcd.getName(), activeType, bcd.getVal()?1:0)==null)
        {
            CompilerError.raise("Redeclaration of constant "+bcd.getName(), bcd);
        }
    }

    @Override
    public void visit(NumberConstantDeclaration ncd)
    {
        if (!activeType.compatibleWith(TableWrapper.getType("int")))
        {
            CompilerError.raise("Mismatch between constant type and value", ncd);
        }
        if(TableWrapper.declareConstant(ncd.getName(), activeType, ncd.getVal())==null)
        {
            CompilerError.raise("Redeclaration of constant "+ncd.getName(), ncd);
        }
    }

    @Override
    public void visit(CharConstantDeclaration ccd)
    {
        if (!activeType.compatibleWith(TableWrapper.getType("char")))
        {
            CompilerError.raise("Mismatch between constant type and value", ccd);
        }
        if(TableWrapper.declareConstant(ccd.getName(), activeType, ccd.getVal())==null)
        {
            CompilerError.raise("Redeclaration of constant "+ccd.getName(), ccd);
        }
    }

    @Override
    public void visit(ExtendsDeclaration ed)
    {
        Struct base = TableWrapper.getType(ed.getBase());
        if(base==null)
        {
            CompilerError.raise("Unknown type "+ed.getBase(), ed);
        }
        if(base.getKind()!=Struct.Class)
        {
            CompilerError.raise("Extending from a non-class type "+ed.getBase(), ed);
        }
        TableWrapper.setBaseClass(base);
    }

    @Override
    public void visit(ImplementedName impName)
    {
        Struct base = impName.getType().typeannotation.type;
        if(base.getKind()!=Struct.Interface)
        {
            CompilerError.raise("Implementing from a non-interface type "+impName.getType().getName(), impName);
        }
        TableWrapper.addImplementedInterface(base);
    }

    public void visit(BaseDesignator bd)
    {
        Obj obj = TableWrapper.getSymbol(bd.getName());
        if (obj == null)
        {
            CompilerError.raise("Unknown symbol: " + bd.getName(), bd);
        }
        else
        {
            references.add("Symbol referenced: " + bd.getName() + " on Line:" + bd.getLine());
            pathBaseName = bd.getName();
            bd.typeannotation = new TypeAnnotation();
            bd.typeannotation.obj = obj;
            bd.typeannotation.type = obj.getType();
            bd.typeannotation.path = pathBaseName;
        }
    }

    public void visit(PathDesignator pd)
    {
        Obj obj = null;
        Struct activeDesignatorType = pd.getDesignator().typeannotation.type;
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
        }
        else
        {
            references.add("Field: " + pd.getName() + " of object: " + pathBaseName + " referenced on Line:" + pd.getLine());
            pathBaseName += "." + pd.getName();
            pd.typeannotation = new TypeAnnotation();
            pd.typeannotation.obj = obj;
            pd.typeannotation.type = obj.getType();
            pd.typeannotation.path = pathBaseName;
        }
    }

    public void visit(IndexDesignator id)
    {
        if (!TableWrapper.assignmentCompatible("int", id.getExpr().typeannotation.type))
        {
            CompilerError.raise("Array index must be an integer", id);
        }
        Struct activeDesignatorType = id.getDesignator().typeannotation.type;
        pathBaseName = id.getDesignator().typeannotation.path;
        if (activeDesignatorType != null && activeDesignatorType.getKind() == Struct.Array)
        {
            references.add("Array member of object: " + pathBaseName + " referenced on Line:" + id.getLine());
            activeDesignatorType = activeDesignatorType.getElemType();
            pathBaseName += "[...]";
            id.typeannotation = new TypeAnnotation();
            id.typeannotation.type = activeDesignatorType;
            id.typeannotation.path = pathBaseName;
        }
        else
        {
            CompilerError.raise(pathBaseName + " is not an array", id);
        }
    }

    @Override
    public void visit(NumericConstant c)
    {
        c.typeannotation = new TypeAnnotation();
        c.typeannotation.type = TableWrapper.getType("int");
    }

    @Override
    public void visit(BooleanConstant c)
    {
        c.typeannotation = new TypeAnnotation();
        c.typeannotation.type = TableWrapper.getType("bool");
    }

    @Override
    public void visit(CharacterConstant c)
    {
        c.typeannotation = new TypeAnnotation();
        c.typeannotation.type = TableWrapper.getType("char");
    }

    @Override
    public void visit(SingleFactor c)
    {
        c.typeannotation = new TypeAnnotation();
        c.typeannotation.type = c.getDesignator().typeannotation.type;
    }

    @Override
    public void visit(NewObject c)
    {
        if(!c.getType().typeannotation.type.isRefType())
        {
            CompilerError.raise("Cannot dynamically instantiate "+c.getType().getName(), c);
        }
        c.typeannotation = new TypeAnnotation();
        c.typeannotation.type = c.getType().typeannotation.type;
    }

    @Override
    public void visit(NewArray c)
    {
        enforceTypes(c, "int", "Array size must be an integer", c.getExpr().typeannotation);
        c.typeannotation = new TypeAnnotation();
        c.typeannotation.type = new Struct(Struct.Array, c.getType().typeannotation.type);
    }

    @Override
    public void visit(Parenthesized p)
    {
        p.typeannotation = new TypeAnnotation();
        p.typeannotation.type = p.getExpr().typeannotation.type;
    }

    @Override
    public void visit(CallResult cr)
    {
        cr.typeannotation = new TypeAnnotation();
        cr.typeannotation.type = cr.getCall().typeannotation.type;
    }

    @Override
    public void visit(FuncCall fc)
    {
        fc.typeannotation = new TypeAnnotation();
        Obj function= fc.getDesignator().typeannotation.obj;
        if (function.getKind() != Obj.Meth)
        {
            CompilerError.raise("Attempting to call a non-callable object", fc);
        }
        fc.typeannotation.type = fc.getDesignator().typeannotation.type;
        Actpars pars = fc.getActpars();
        int argc = function.getLevel();
        List<TypeAnnotation> taList=new LinkedList<>();
        if(pars.typeannotation!=null)
        {
            taList=pars.typeannotation.arglist;
        }
        List<Struct> argTypes=new ArrayList<>();
        for(TypeAnnotation ta:taList)
        {
            argTypes.add(ta.type);
        }
        if(taList.size()<argc)
        {
            CompilerError.raise("Too few arguments for call, "+argc+" required, got "+taList.size(), fc);
        }
        if(taList.size()>argc)
        {
            CompilerError.raise("Too many arguments for call, "+argc+" required, got "+taList.size(), fc);
        }
        if(argc>0)
        {
            if(function==TableWrapper.read || function==TableWrapper.print)
            {
                if(!TableWrapper.assignmentCompatible("int", taList.get(0).type) &&
                        !TableWrapper.assignmentCompatible("bool", taList.get(0).type) &&
                        !TableWrapper.assignmentCompatible("char", taList.get(0).type))
                {
                    CompilerError.raise(function.getName()+" can only handle builtin types", fc);
                }
                return;
            }
            List<Integer> errors=TableWrapper.callCompatible(function, argTypes);
            if(errors.size()>0)
            {
                List<String> args=new ArrayList<>();
                for (Integer i:errors)
                {
                    args.add(taList.get(i).obj.getName());
                }
                CompilerError.raise("Type mismatch on arguments "+String.join(",", args), fc);
            }
        }
    }

    @Override
    public void visit(Parameter param)
    {
        param.typeannotation=new TypeAnnotation();
        param.typeannotation.type=param.getExpr().typeannotation.type;
        param.typeannotation.arglist = new LinkedList<>();
        param.typeannotation.arglist.add(param.typeannotation);
    }

    @Override
    public void visit(Parameters param)
    {
        param.typeannotation=new TypeAnnotation();
        param.typeannotation.type=param.getExpr().typeannotation.type;
        param.typeannotation.arglist=param.getActpars().typeannotation.arglist;
        param.typeannotation.arglist.add(param.typeannotation);
    }

    @Override
    public void visit(SingleTerm st)
    {
        st.typeannotation = new TypeAnnotation();
        st.typeannotation.type = st.getFactor().typeannotation.type;
    }

    @Override
    public void visit(Negated ne)
    {
        enforceTypes(ne, "int", "Only integers can be negated", ne.getFactor().typeannotation);
        ne.typeannotation = new TypeAnnotation();
        ne.typeannotation.type = ne.getFactor().typeannotation.type;
    }

    @Override
    public void visit(Multiplication mu)
    {
        enforceTypes(mu, "int", "Only integers can be multiplied", mu.getTerm().typeannotation, mu.getFactor().typeannotation);
        mu.typeannotation = new TypeAnnotation();
        mu.typeannotation.type = TableWrapper.getType("int");
    }

    @Override
    public void visit(Division di)
    {
        enforceTypes(di, "int", "Only integers can be divided", di.getTerm().typeannotation, di.getFactor().typeannotation);
        di.typeannotation = new TypeAnnotation();
        di.typeannotation.type = TableWrapper.getType("int");
    }

    @Override
    public void visit(Modulus mo)
    {
        enforceTypes(mo, "int", "Only integers can be divided", mo.getTerm().typeannotation, mo.getFactor().typeannotation);
        mo.typeannotation = new TypeAnnotation();
        mo.typeannotation.type = TableWrapper.getType("int");
    }

    @Override
    public void visit(SingleExpr se)
    {
        se.typeannotation = new TypeAnnotation();
        se.typeannotation.type = se.getTerm().typeannotation.type;
    }

    @Override
    public void visit(Addition ad)
    {
        enforceTypes(ad, "int", "Only integers can be added", ad.getExpr().typeannotation, ad.getTerm().typeannotation);
        ad.typeannotation = new TypeAnnotation();
        ad.typeannotation.type = TableWrapper.getType("int");
    }

    @Override
    public void visit(Substraction su)
    {
        enforceTypes(su, "int", "Only integers can be substracted", su.getExpr().typeannotation, su.getTerm().typeannotation);
        su.typeannotation = new TypeAnnotation();
        su.typeannotation.type = TableWrapper.getType("int");
    }

    @Override
    public void visit(SingleCondFactor scf)
    {
        scf.typeannotation = new TypeAnnotation();
        scf.typeannotation.type = scf.getExpr().typeannotation.type;
    }

    @Override
    public void visit(Equals eq)
    {
        eq.typeannotation = new TypeAnnotation();
        eq.typeannotation.type = TableWrapper.getType("bool");
        if(!TableWrapper.assignmentCompatible(eq.getExpr().typeannotation.type, eq.getExpr1().typeannotation.type))
        {
            CompilerError.raise("Equality test between incompatible types", eq);
        }
    }

    @Override
    public void visit(NotEquals neq)
    {
        neq.typeannotation = new TypeAnnotation();
        neq.typeannotation.type = TableWrapper.getType("bool");
        if(!TableWrapper.assignmentCompatible(neq.getExpr().typeannotation.type, neq.getExpr1().typeannotation.type))
        {
            CompilerError.raise("Inequality test between incompatible types", neq);
        }
    }

    @Override
    public void visit(Greater gr)
    {
        enforceTypes(gr, "int", "Only integers can be compared", gr.getExpr().typeannotation, gr.getExpr1().typeannotation);
        gr.typeannotation = new TypeAnnotation();
        gr.typeannotation.type = TableWrapper.getType("bool");
    }

    @Override
    public void visit(GreaterEqual gre)
    {
        enforceTypes(gre, "int", "Only integers can be compared", gre.getExpr().typeannotation, gre.getExpr1().typeannotation);
        gre.typeannotation = new TypeAnnotation();
        gre.typeannotation.type = TableWrapper.getType("bool");
    }

    @Override
    public void visit(Less le)
    {
        enforceTypes(le, "int", "Only integers can be compared", le.getExpr().typeannotation, le.getExpr1().typeannotation);
        le.typeannotation = new TypeAnnotation();
        le.typeannotation.type = TableWrapper.getType("bool");
    }

    @Override
    public void visit(LessEqual leq)
    {
        enforceTypes(leq, "int", "Only integers can be compared", leq.getExpr().typeannotation, leq.getExpr1().typeannotation);
        leq.typeannotation = new TypeAnnotation();
        leq.typeannotation.type = TableWrapper.getType("bool");
    }

    @Override
    public void visit(SingleCondTerm sct)
    {
        sct.typeannotation = new TypeAnnotation();
        sct.typeannotation.type = sct.getCondfactor().typeannotation.type;
    }

    @Override
    public void visit(And and)
    {
        enforceTypes(and, "bool", "AND can only be done on bool", and.getCondfactor().typeannotation, and.getCondterm().typeannotation);
        and.typeannotation = new TypeAnnotation();
        and.typeannotation.type = TableWrapper.getType("bool");
    }

    @Override
    public void visit(SingleCondExpr sce)
    {
        sce.typeannotation = new TypeAnnotation();
        sce.typeannotation.type = sce.getCondterm().typeannotation.type;
    }

    @Override
    public void visit(Or or)
    {
        enforceTypes(or, "bool", "OR can only be done on bool", or.getCondexpr().typeannotation, or.getCondterm().typeannotation);
        or.typeannotation = new TypeAnnotation();
        or.typeannotation.type = TableWrapper.getType("bool");
    }

    @Override
    public void visit(If ifstmt)
    {
        enforceTypes(ifstmt, "bool", "IF condition must be a bool", ifstmt.getCondexpr().typeannotation);
        System.out.println("IF(cnd on line "+ifstmt.getLine()+") THEN COMMAND ON LINE "+ifstmt.getStatement().getLine());
    }

    @Override
    public void visit(IfElse ifstmt)
    {
        enforceTypes(ifstmt, "bool", "IF condition must be a bool", ifstmt.getCondexpr().typeannotation);
        System.out.println("IF(cnd on line "+ifstmt.getLine()+") THEN COMMAND ON LINE "+ifstmt.getStatement().getLine()+" ELSE COMMAND ON LINE "+ifstmt.getStatement1().getLine());
    }

    @Override
    public void visit(Assign assign)
    {
        if(!TableWrapper.assignmentCompatible(assign.getDesignator().typeannotation.type, assign.getExpr().typeannotation.type))
        {
            CompilerError.raise("Assignment between incompatible types", assign);
        }
    }

    /*@Override
    public void visit(Continue cnt)
    {
        SyntaxNode node = cnt;
        while (node.getParent() != null)
        {
            if (node.getParent() instanceof DoWhile)
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
            if (node.getParent() instanceof DoWhile)
            {
                return;
            }
            node = node.getParent();
        }
        CompilerError.raise("CONTINUE must be called in a loop", brk);
    }

    @Override
    public void visit(DoWhile dw)
    {
        enforceTypes(dw, "bool", "WHILE condition must be bool", dw.getCondexpr().typeannotation);
    }*/

    private void enforceTypes(SyntaxNode node, String type, String message, TypeAnnotation... tas)
    {
        for (TypeAnnotation ta : tas)
        {
            if (!TableWrapper.assignmentCompatible(type, ta.type))
            {
                CompilerError.raise(message, node);
            }
        }
    }
    /*private List<String> references = new ArrayList<>();
    private Struct activeDesignatorType = null;
    private String pathBaseName = "";
    private String programName;



    @Override
    public void visit(Return rs)
    {
        if (TableWrapper.getCurrentFunction() == null)
        {
            CompilerError.raise("Return called outside of function", rs);
        }
        if (TableWrapper.getCurrentFunction().getType().getKind() == Struct.None)
        {
            CompilerError.raise("Void function can not return a value", rs);
        }
        if (!TableWrapper.getCurrentFunction().getType().compatibleWith(rs.getExpr().typeannotation.type))
        {
            CompilerError.raise("Returned value does not match function type", rs);
        }
    }

    @Override
    public void visit(EmptyReturn rs)
    {
        if (TableWrapper.getCurrentFunction() == null)
        {
            CompilerError.raise("Return called outside of function", rs);
        }
        if (TableWrapper.getCurrentFunction().getType().getKind() != Struct.None)
        {
            CompilerError.raise("Empty return statement in a non-void function", rs);
        }
    }

    @Override
    public void visit(BoolConstantDeclaration bcd)
    {
        if (!TableWrapper.getActiveType().compatibleWith(TableWrapper.getType("bool")))
        {
            CompilerError.raise("Mismatch between constant type and value", bcd);
        }
        if (!TableWrapper.declareConstant(bcd.getName(), bcd.getVal()))
        {
            CompilerError.raise("Redeclaration of name: " + bcd.getName(), bcd);
        }
    }

    @Override
    public void visit(NumberConstantDeclaration ncd)
    {
        if (!TableWrapper.getActiveType().compatibleWith(TableWrapper.getType("int")))
        {
            CompilerError.raise("Mismatch between constant type and value", ncd);
        }
        if (!TableWrapper.declareConstant(ncd.getName(), ncd.getVal()))
        {
            CompilerError.raise("Redeclaration of name: " + ncd.getName(), ncd);
        }
    }

    @Override
    public void visit(CharConstantDeclaration ccd)
    {
        if (!TableWrapper.getActiveType().compatibleWith(TableWrapper.getType("char")))
        {
            CompilerError.raise("Mismatch between constant type and value", ccd);
        }
        if (!TableWrapper.declareConstant(ccd.getName(), ccd.getVal()))
        {
            CompilerError.raise("Redeclaration of name: " + ccd.getName(), ccd);
        }
    }



    void dumpReferences(PrintStream stream)
    {
        for (String reference : references)
        {
            stream.println(reference);
        }
    }*/
}
