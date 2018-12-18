// generated with ast extension for cup
// version 0.8
// 18/11/2018 13:27:0


package rs.ac.bg.etf.pp1.ast;

public class DesignatorLineStmt extends Statement {

    private Designatorline designatorline;

    public DesignatorLineStmt (Designatorline designatorline) {
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
        buffer.append("DesignatorLineStmt(\n");

        if(designatorline!=null)
            buffer.append(designatorline.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorLineStmt]");
        return buffer.toString();
    }
}
