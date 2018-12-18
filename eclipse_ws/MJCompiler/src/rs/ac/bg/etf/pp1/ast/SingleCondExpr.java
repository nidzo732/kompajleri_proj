// generated with ast extension for cup
// version 0.8
// 18/11/2018 13:27:0


package rs.ac.bg.etf.pp1.ast;

public class SingleCondExpr extends Condexpr {

    private Condterm condterm;

    public SingleCondExpr (Condterm condterm) {
        this.condterm=condterm;
        if(condterm!=null) condterm.setParent(this);
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
        if(condterm!=null) condterm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(condterm!=null) condterm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(condterm!=null) condterm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleCondExpr(\n");

        if(condterm!=null)
            buffer.append(condterm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleCondExpr]");
        return buffer.toString();
    }
}
