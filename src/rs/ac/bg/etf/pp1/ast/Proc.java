// generated with ast extension for cup
// version 0.8
// 9/9/2018 22:58:35


package rs.ac.bg.etf.pp1.ast;

public class Proc extends Function {

    private Functionname functionname;
    private Formpars formpars;
    private Declarationblock declarationblock;
    private Statementblock statementblock;

    public Proc (Functionname functionname, Formpars formpars, Declarationblock declarationblock, Statementblock statementblock) {
        this.functionname=functionname;
        if(functionname!=null) functionname.setParent(this);
        this.formpars=formpars;
        if(formpars!=null) formpars.setParent(this);
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

    public Formpars getFormpars() {
        return formpars;
    }

    public void setFormpars(Formpars formpars) {
        this.formpars=formpars;
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
        if(formpars!=null) formpars.accept(visitor);
        if(declarationblock!=null) declarationblock.accept(visitor);
        if(statementblock!=null) statementblock.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(functionname!=null) functionname.traverseTopDown(visitor);
        if(formpars!=null) formpars.traverseTopDown(visitor);
        if(declarationblock!=null) declarationblock.traverseTopDown(visitor);
        if(statementblock!=null) statementblock.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(functionname!=null) functionname.traverseBottomUp(visitor);
        if(formpars!=null) formpars.traverseBottomUp(visitor);
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

        if(formpars!=null)
            buffer.append(formpars.toString("  "+tab));
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
