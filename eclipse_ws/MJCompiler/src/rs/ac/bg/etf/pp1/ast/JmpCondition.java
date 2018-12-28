// generated with ast extension for cup
// version 0.8
// 28/11/2018 16:9:49


package rs.ac.bg.etf.pp1.ast;

public class JmpCondition extends Jmpcond {

    private Condexpr condexpr;

    public JmpCondition (Condexpr condexpr) {
        this.condexpr=condexpr;
        if(condexpr!=null) condexpr.setParent(this);
    }

    public Condexpr getCondexpr() {
        return condexpr;
    }

    public void setCondexpr(Condexpr condexpr) {
        this.condexpr=condexpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(condexpr!=null) condexpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(condexpr!=null) condexpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(condexpr!=null) condexpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("JmpCondition(\n");

        if(condexpr!=null)
            buffer.append(condexpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [JmpCondition]");
        return buffer.toString();
    }
}
