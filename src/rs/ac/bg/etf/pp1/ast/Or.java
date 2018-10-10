// generated with ast extension for cup
// version 0.8
// 10/9/2018 20:35:43


package rs.ac.bg.etf.pp1.ast;

public class Or extends Condexpr {

    private Condexpr condexpr;
    private Condterm condterm;

    public Or (Condexpr condexpr, Condterm condterm) {
        this.condexpr=condexpr;
        if(condexpr!=null) condexpr.setParent(this);
        this.condterm=condterm;
        if(condterm!=null) condterm.setParent(this);
    }

    public Condexpr getCondexpr() {
        return condexpr;
    }

    public void setCondexpr(Condexpr condexpr) {
        this.condexpr=condexpr;
    }

    public Condterm getCondterm() {
        return condterm;
    }

    public void setCondterm(Condterm condterm) {
        this.condterm=condterm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(condexpr!=null) condexpr.accept(visitor);
        if(condterm!=null) condterm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(condexpr!=null) condexpr.traverseTopDown(visitor);
        if(condterm!=null) condterm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(condexpr!=null) condexpr.traverseBottomUp(visitor);
        if(condterm!=null) condterm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Or(\n");

        if(condexpr!=null)
            buffer.append(condexpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(condterm!=null)
            buffer.append(condterm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Or]");
        return buffer.toString();
    }
}
