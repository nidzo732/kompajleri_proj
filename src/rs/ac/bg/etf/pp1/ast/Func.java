// generated with ast extension for cup
// version 0.8
// 17/9/2018 23:25:43


package rs.ac.bg.etf.pp1.ast;

public class Func extends Function {

    private Type type;
    private Functionname functionname;
    private Formparswrapper formparswrapper;
    private Declarationblock declarationblock;
    private Statementblock statementblock;

    public Func (Type type, Functionname functionname, Formparswrapper formparswrapper, Declarationblock declarationblock, Statementblock statementblock) {
        this.type=type;
        if(type!=null) type.setParent(this);
        this.functionname=functionname;
        if(functionname!=null) functionname.setParent(this);
        this.formparswrapper=formparswrapper;
        if(formparswrapper!=null) formparswrapper.setParent(this);
        this.declarationblock=declarationblock;
        if(declarationblock!=null) declarationblock.setParent(this);
        this.statementblock=statementblock;
        if(statementblock!=null) statementblock.setParent(this);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type=type;
    }

    public Functionname getFunctionname() {
        return functionname;
    }

    public void setFunctionname(Functionname functionname) {
        this.functionname=functionname;
    }

    public Formparswrapper getFormparswrapper() {
        return formparswrapper;
    }

    public void setFormparswrapper(Formparswrapper formparswrapper) {
        this.formparswrapper=formparswrapper;
    }

    public Declarationblock getDeclarationblock() {
        return declarationblock;
    }

    public void setDeclarationblock(Declarationblock declarationblock) {
        this.declarationblock=declarationblock;
    }

    public Statementblock getStatementblock() {
        return statementblock;
    }

    public void setStatementblock(Statementblock statementblock) {
        this.statementblock=statementblock;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(type!=null) type.accept(visitor);
        if(functionname!=null) functionname.accept(visitor);
        if(formparswrapper!=null) formparswrapper.accept(visitor);
        if(declarationblock!=null) declarationblock.accept(visitor);
        if(statementblock!=null) statementblock.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(type!=null) type.traverseTopDown(visitor);
        if(functionname!=null) functionname.traverseTopDown(visitor);
        if(formparswrapper!=null) formparswrapper.traverseTopDown(visitor);
        if(declarationblock!=null) declarationblock.traverseTopDown(visitor);
        if(statementblock!=null) statementblock.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(type!=null) type.traverseBottomUp(visitor);
        if(functionname!=null) functionname.traverseBottomUp(visitor);
        if(formparswrapper!=null) formparswrapper.traverseBottomUp(visitor);
        if(declarationblock!=null) declarationblock.traverseBottomUp(visitor);
        if(statementblock!=null) statementblock.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Func(\n");

        if(type!=null)
            buffer.append(type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(functionname!=null)
            buffer.append(functionname.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(formparswrapper!=null)
            buffer.append(formparswrapper.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(declarationblock!=null)
            buffer.append(declarationblock.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(statementblock!=null)
            buffer.append(statementblock.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Func]");
        return buffer.toString();
    }
}
