// generated with ast extension for cup
// version 0.8
// 15/11/2018 5:57:14


package rs.ac.bg.etf.pp1.ast;

public class StatementDerived1 extends Statement {

    private Designatorline designatorline;

    public StatementDerived1 (Designatorline designatorline) {
        this.designatorline=designatorline;
        if(designatorline!=null) designatorline.setParent(this);
    }

    public Designatorline getDesignatorline() {
        return designatorline;
    }

    public void setDesignatorline(Designatorline designatorline) {
        this.designatorline=designatorline;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(designatorline!=null) designatorline.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(designatorline!=null) designatorline.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(designatorline!=null) designatorline.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementDerived1(\n");

        if(designatorline!=null)
            buffer.append(designatorline.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementDerived1]");
        return buffer.toString();
    }
}
