// generated with ast extension for cup
// version 0.8
// 12/9/2018 12:24:49


package rs.ac.bg.etf.pp1.ast;

public class Proc extends Function {

    private Functionname functionname;
    private Formparswrapper formparswrapper;
    private Declarationblock declarationblock;
    private Statementblock statementblock;

    public Proc (Functionname functionname, Formparswrapper formparswrapper, Declarationblock declarationblock, Statementblock statementblock) {
        this.functionname=functionname;
        if(functionname!=null) functionname.setParent(this);
        this.formparswrapper=formparswrapper;
        if(formparswrapper!=null) formparswrapper.setParent(this);
        this.declarationblock=declarationblock;
        if(declarationblock!=null) declarationblock.setParent(this);
        this.statementblock=statementblock;
        if(statementblock!=null) statementblock.setParent(this);
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
        if(functionname!=null) functionname.accept(visitor);
        if(formparswrapper!=null) formparswrapper.accept(visitor);
        if(declarationblock!=null) declarationblock.accept(visitor);
        if(statementblock!=null) statementblock.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(functionname!=null) functionname.traverseTopDown(visitor);
        if(formparswrapper!=null) formparswrapper.traverseTopDown(visitor);
        if(declarationblock!=null) declarationblock.traverseTopDown(visitor);
        if(statementblock!=null) statementblock.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(functionname!=null) functionname.traverseBottomUp(visitor);
        if(formparswrapper!=null) formparswrapper.traverseBottomUp(visitor);
        if(declarationblock!=null) declarationblock.traverseBottomUp(visitor);
        if(statementblock!=null) statementblock.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Proc(\n");

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
        buffer.append(") [Proc]");
        return buffer.toString();
    }
}
