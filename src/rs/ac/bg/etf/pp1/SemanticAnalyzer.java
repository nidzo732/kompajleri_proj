package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class SemanticAnalyzer extends VisitorAdaptor
{

    private List<String> references = new ArrayList<>();

    @Override
    public void visit(ProgramName programName)
    {
        TableWrapper.openScope(Obj.Prog, programName.getName(), Tab.noType);
    }

    @Override
    public void visit(ProgramDerived1 program)
    {
        TableWrapper.closeScope();
    }

    @Override
    public void visit(ClassName className)
    {
        if(className.getParent() instanceof DerivedClass)
        {
            DerivedClass cls = ((DerivedClass)className.getParent());
            Extendsdeclaration edc = cls.getExtendsdeclaration();
            String baseName;
            if(edc instanceof ExtendsdeclarationDerived1)
            {
                baseName=((ExtendsdeclarationDerived1)edc).getBase();
            }
            else
            {
                baseName=((ExtendsdeclarationDerived2)edc).getBase();
            }
            Struct baseClass = TableWrapper.getType(baseName);
            if(baseClass==null)
            {
                CompilerError.raise("Unknown type: " + baseName, className);
            }
            if(baseClass.getKind()!=Struct.Class)
            {
                CompilerError.raise("Cannot inherit from a non-class type", className);
            }
        }
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
        if(!TableWrapper.declareFunction(functionName.getName(), functionName.getParent() instanceof Proc))
        {
            CompilerError.raise("Redeclaration of name: "+functionName.getName(), functionName);
        };
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
        if (!TableWrapper.setActiveType(type.getName()))
        {
            CompilerError.raise("Unknown type: " + type.getName(), type);
        }
        type.typeannotation=new TypeAnnotation();
        type.typeannotation.type=TableWrapper.getType(type.getName());
    }

    @Override
    public void visit(Array array)
    {
        if(!TableWrapper.declareVariable(array.getName(), true))
        {
            CompilerError.raise("Redeclaration of name: "+array.getName(), array);
        }
    }

    @Override
    public void visit(Variable variable)
    {
        if(!TableWrapper.declareVariable(variable.getName(), false))
        {
            CompilerError.raise("Redeclaration of name: "+variable.getName(), variable);
        }
    }

    @Override
    public void visit(FormParsWrapper fpw)
    {
        TableWrapper.methodHeaderDone();
    }

    public void visit(FormParsWrapper2 fpw)
    {
        TableWrapper.methodHeaderDone();
    }

    public void visit(FormParsWrapper3 fpw)
    {
        TableWrapper.methodHeaderDone();
    }

    private Struct activeDesignatorType=null;
    private String pathBaseName="";

    public void visit(BaseDesignator bd)
    {
        Obj obj = TableWrapper.getSymbol(bd.getName());
        if(obj==null)
        {
            CompilerError.raise("Unknown symbol: "+bd.getName(), bd);
        }
        else
        {
            references.add("Symbol referenced: "+bd.getName()+" on Line:"+bd.getLine());
            activeDesignatorType=obj.getType();
            pathBaseName = bd.getName();
            bd.typeannotation=new TypeAnnotation();
            bd.typeannotation.obj=obj;
            bd.typeannotation.type=activeDesignatorType;
            bd.typeannotation.path=pathBaseName;
        }
    }

    public void visit(PathDesignator pd)
    {
        Obj obj=null;
        if(activeDesignatorType!=null && activeDesignatorType.getMembers()!=null)
        {
            for(Obj member:activeDesignatorType.getMembers())
            {
                if(member.getName().equals(pd.getName()))
                {
                    obj=member;
                    break;
                }
            }
        }
        if(obj==null)
        {
            CompilerError.raise("Object "+pathBaseName+" does not have a field named: "+pd.getName(), pd);
        }
        else
        {
            references.add("Field: "+pd.getName()+" of object: "+pathBaseName+" referenced on Line:"+pd.getLine());
            activeDesignatorType=obj.getType();
            pathBaseName += "."+pd.getName();
            pd.typeannotation=new TypeAnnotation();
            pd.typeannotation.obj=obj;
            pd.typeannotation.type=activeDesignatorType;
            pd.typeannotation.path=pathBaseName;
        }
    }

    public void visit(IndexDesignator id)
    {
        if(!id.getExpr().typeannotation.type.compatibleWith(TableWrapper.getType("int")))
        {
            CompilerError.raise("Array index must be an integer", id);
        }
        activeDesignatorType=id.getDesignator().typeannotation.type;
        pathBaseName=id.getDesignator().typeannotation.path;
        if(activeDesignatorType!=null && activeDesignatorType.getKind()==Struct.Array)
        {
            references.add("Array member of object: "+pathBaseName+" referenced on Line:"+id.getLine());
            activeDesignatorType=activeDesignatorType.getElemType();
            pathBaseName += "[...]";
            id.typeannotation=new TypeAnnotation();
            id.typeannotation.type=activeDesignatorType;
            id.typeannotation.path=pathBaseName;
        }
        else
        {
            CompilerError.raise(pathBaseName+" is not an array", id);
        }
    }

    @Override
    public void visit(DeclarationBlockWrapper1 dbw)
    {
        if(dbw.getParent() instanceof DerivedClass)
        {
            DerivedClass cls = ((DerivedClass)dbw.getParent());
            Extendsdeclaration edc = cls.getExtendsdeclaration();
            String baseName;
            if(edc instanceof ExtendsdeclarationDerived1)
            {
                baseName=((ExtendsdeclarationDerived1)edc).getBase();
            }
            else
            {
                baseName=((ExtendsdeclarationDerived2)edc).getBase();
            }
            Struct baseClass = TableWrapper.getType(baseName);
            if(baseClass==null)
            {
                CompilerError.raise("Unknown type: " + baseName, dbw);
            }
            TableWrapper.closeClassDeclarationBlock(baseClass.getMembers());
        }
    }

    @Override
    public void visit(DeclarationBlockWrapper2 dbw)
    {
        if(dbw.getParent() instanceof DerivedClass)
        {
            DerivedClass cls = ((DerivedClass)dbw.getParent());
            Extendsdeclaration edc = cls.getExtendsdeclaration();
            String baseName;
            if(edc instanceof ExtendsdeclarationDerived1)
            {
                baseName=((ExtendsdeclarationDerived1)edc).getBase();
            }
            else
            {
                baseName=((ExtendsdeclarationDerived2)edc).getBase();
            }
            Struct baseClass = TableWrapper.getType(baseName);
            if(baseClass==null)
            {
                CompilerError.raise("Unknown type: " + baseName, dbw);
            }
            TableWrapper.closeClassDeclarationBlock(baseClass.getMembers());
        }
    }

    @Override
    public void visit(NumericConstant c)
    {
        c.typeannotation=new TypeAnnotation();
        c.typeannotation.type=TableWrapper.getType("int");
    }

    @Override
    public void visit(BooleanConstant c)
    {
        c.typeannotation=new TypeAnnotation();
        c.typeannotation.type=TableWrapper.getType("bool");
    }

    @Override
    public void visit(CharacterConstant c)
    {
        c.typeannotation=new TypeAnnotation();
        c.typeannotation.type=TableWrapper.getType("char");
    }

    @Override
    public void visit(SingleFactor c)
    {
        c.typeannotation=new TypeAnnotation();
        c.typeannotation.type = c.getDesignator().typeannotation.type;
    }

    @Override
    public void visit(NewObject c)
    {
        c.typeannotation=new TypeAnnotation();
        c.typeannotation.type=c.getType().typeannotation.type;
    }

    @Override
    public void visit(NewArray c)
    {
        enforceTypes(c, "int", "Array size must be an integer", c.getExpr().typeannotation);
        c.typeannotation=new TypeAnnotation();
        c.typeannotation.type= new Struct(Struct.Array, c.getType().typeannotation.type);
    }

    @Override
    public void visit(Parenthesized p)
    {
        p.typeannotation=new TypeAnnotation();
        p.typeannotation.type=p.getExpr().typeannotation.type;
    }

    @Override
    public void visit(CallResult cr)
    {
        cr.typeannotation=new TypeAnnotation();
        cr.typeannotation.type=cr.getCall().typeannotation.type;
    }

    @Override
    public void visit(FuncCall fc)
    {
        fc.typeannotation=new TypeAnnotation();
        if(fc.getDesignator().typeannotation.obj.getKind()!=Obj.Meth)
        {
            CompilerError.raise("Attempting to call a non-callable object", fc);
        }
        fc.typeannotation.type=fc.getDesignator().typeannotation.type;
    }

    @Override
    public void visit(SingleTerm st)
    {
        st.typeannotation=new TypeAnnotation();
        st.typeannotation.type=st.getFactor().typeannotation.type;
    }

    @Override
    public void visit(Negated ne)
    {
        enforceTypes(ne, "int","Only integers can be negated", ne.getFactor().typeannotation);
        ne.typeannotation=new TypeAnnotation();
        ne.typeannotation.type=ne.getFactor().typeannotation.type;
    }

    @Override
    public void visit(Multiplication mu)
    {
        enforceTypes(mu, "int", "Only integers can be multiplied", mu.getTerm().typeannotation, mu.getFactor().typeannotation);
        mu.typeannotation=new TypeAnnotation();
        mu.typeannotation.type = TableWrapper.getType("int");
    }

    @Override
    public void visit(Division di)
    {
        enforceTypes(di, "int", "Only integers can be divided", di.getTerm().typeannotation, di.getFactor().typeannotation);
        di.typeannotation=new TypeAnnotation();
        di.typeannotation.type = TableWrapper.getType("int");
    }

    @Override
    public void visit(Modulus mo)
    {
        enforceTypes(mo, "int", "Only integers can be divided", mo.getTerm().typeannotation, mo.getFactor().typeannotation);
        mo.typeannotation=new TypeAnnotation();
        mo.typeannotation.type = TableWrapper.getType("int");
    }

    @Override
    public void visit(SingleExpr se)
    {
        se.typeannotation=new TypeAnnotation();
        se.typeannotation.type=se.getTerm().typeannotation.type;
    }

    @Override
    public void visit(Addition ad)
    {
        enforceTypes(ad, "int", "Only integers can be added", ad.getExpr().typeannotation, ad.getTerm().typeannotation);
        ad.typeannotation=new TypeAnnotation();
        ad.typeannotation.type = TableWrapper.getType("int");
    }

    @Override
    public void visit(Substraction su)
    {
        enforceTypes(su, "int", "Only integers can be substracted", su.getExpr().typeannotation, su.getTerm().typeannotation);
        su.typeannotation=new TypeAnnotation();
        su.typeannotation.type = TableWrapper.getType("int");
    }

    @Override
    public void visit(SingleCondFactor scf)
    {
        scf.typeannotation=new TypeAnnotation();
        scf.typeannotation.type=scf.getExpr().typeannotation.type;
    }

    @Override
    public void visit(Equals eq)
    {
        if((eq.getExpr().typeannotation.type.getKind()==Struct.Array)
                && (eq.getExpr1().typeannotation.type.getKind()==Struct.Array)
                && !(eq.getExpr().typeannotation.type.getElemType().compatibleWith(eq.getExpr1().typeannotation.type.getElemType())))
        {
            CompilerError.raise("Equality test between incompatible types", eq);
        }
        else if(!eq.getExpr().typeannotation.type.compatibleWith(eq.getExpr1().typeannotation.type))
        {
            CompilerError.raise("Equality test between incompatible types", eq);
        }
        eq.typeannotation=new TypeAnnotation();
        eq.typeannotation.type=TableWrapper.getType("bool");
    }

    @Override
    public void visit(NotEquals neq)
    {
        if((neq.getExpr().typeannotation.type.getKind()==Struct.Array)
                && (neq.getExpr1().typeannotation.type.getKind()==Struct.Array)
                && !(neq.getExpr().typeannotation.type.getElemType().compatibleWith(neq.getExpr1().typeannotation.type.getElemType())))
        {
            CompilerError.raise("Inequality test between incompatible types", neq);
        }
        else if(!neq.getExpr().typeannotation.type.compatibleWith(neq.getExpr1().typeannotation.type))
        {
            CompilerError.raise("Inequality test between incompatible types", neq);
        }
        neq.typeannotation=new TypeAnnotation();
        neq.typeannotation.type=TableWrapper.getType("bool");
    }

    @Override
    public void visit(Greater gr)
    {
        enforceTypes(gr, "int", "Only integers can be compared", gr.getExpr().typeannotation, gr.getExpr1().typeannotation);
        gr.typeannotation=new TypeAnnotation();
        gr.typeannotation.type=TableWrapper.getType("bool");
    }

    @Override
    public void visit(GreaterEqual gre)
    {
        enforceTypes(gre, "int", "Only integers can be compared", gre.getExpr().typeannotation, gre.getExpr1().typeannotation);
        gre.typeannotation=new TypeAnnotation();
        gre.typeannotation.type=TableWrapper.getType("bool");
    }

    @Override
    public void visit(Less le)
    {
        enforceTypes(le, "int", "Only integers can be compared", le.getExpr().typeannotation, le.getExpr1().typeannotation);
        le.typeannotation=new TypeAnnotation();
        le.typeannotation.type=TableWrapper.getType("bool");
    }

    @Override
    public void visit(LessEqual leq)
    {
        enforceTypes(leq, "int", "Only integers can be compared", leq.getExpr().typeannotation, leq.getExpr1().typeannotation);
        leq.typeannotation=new TypeAnnotation();
        leq.typeannotation.type=TableWrapper.getType("bool");
    }

    @Override
    public void visit(SingleCondTerm sct)
    {
        sct.typeannotation=new TypeAnnotation();
        sct.typeannotation.type=sct.getCondfactor().typeannotation.type;
    }

    @Override
    public void visit(CondTermError sct)
    {
        sct.typeannotation=new TypeAnnotation();
        sct.typeannotation.type=sct.getCondterm().typeannotation.type;
    }

    @Override
    public void visit(And and)
    {
        enforceTypes(and, "bool","AND can only be done on bool", and.getCondfactor().typeannotation, and.getCondterm().typeannotation);
        and.typeannotation=new TypeAnnotation();
        and.typeannotation.type=TableWrapper.getType("bool");
    }

    @Override
    public void visit(SingleCondExpr sce)
    {
        sce.typeannotation=new TypeAnnotation();
        sce.typeannotation.type=sce.getCondterm().typeannotation.type;
    }

    @Override
    public void visit(CondExprError cee)
    {
        cee.typeannotation=new TypeAnnotation();
        cee.typeannotation.type=cee.getCondexpr().typeannotation.type;
    }

    @Override
    public void visit(Or or)
    {
        enforceTypes(or, "bool","OR can only be done on bool", or.getCondexpr().typeannotation, or.getCondterm().typeannotation);
        or.typeannotation=new TypeAnnotation();
        or.typeannotation.type=TableWrapper.getType("bool");
    }

    @Override
    public void visit(If ifstmt)
    {
        enforceTypes(ifstmt, "bool", "IF condition must be a bool", ifstmt.getCondexpr().typeannotation);
    }

    @Override
    public void visit(Else els)
    {
        StatementBlock parent;
        if(els.getParent() instanceof StatementBlock)
        {
            parent=(StatementBlock)els.getParent();
            if(parent.getStatementblock() instanceof StatementBlock)
            {
                StatementBlock sibling=(StatementBlock)parent.getStatementblock();
                if(sibling.getStatement() instanceof If)
                {
                    return;
                }
                if(sibling.getStatement() instanceof Else)
                {
                    Statement siblingStatement = ((Else)sibling.getStatement()).getStatement();
                    if(siblingStatement instanceof If)
                    {
                        return;
                    }

                }
            }
        }
        CompilerError.raise("ELSE must be preceded by an IF or ELSE IF", els);
    }

    @Override
    public void visit(Continue cnt)
    {
        SyntaxNode node=cnt;
        while (node.getParent()!=null)
        {
            if(node.getParent() instanceof DoWhile)
            {
                return;
            }
            node=node.getParent();
        }
        CompilerError.raise("CONTINUE must be called in a loop", cnt);
    }

    @Override
    public void visit(Break brk)
    {
        SyntaxNode node=brk;
        while (node.getParent()!=null)
        {
            if(node.getParent() instanceof DoWhile)
            {
                return;
            }
            node=node.getParent();
        }
        CompilerError.raise("CONTINUE must be called in a loop", brk);
    }

    @Override
    public void visit(DoWhile dw)
    {
        enforceTypes(dw, "bool", "WHILE condition must be bool", dw.getCondexpr().typeannotation);
    }

    private void enforceTypes(SyntaxNode node, String type, String message, TypeAnnotation ...tas)
    {
        for (TypeAnnotation ta:tas)
        {
            if(!ta.type.compatibleWith(TableWrapper.getType(type)))
            {
                CompilerError.raise(message, node);
            }
        }
    }

    void dumpReferences(PrintStream stream)
    {
        for(String reference: references)
        {
            stream.println(reference);
        }
    }
}
