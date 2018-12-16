// generated with ast extension for cup
// version 0.8
// 16/11/2018 17:36:48


package rs.ac.bg.etf.pp1.ast;

public class ForIncrement extends For3 {

    private Designatorstmt designatorstmt;

    public ForIncrement (Designatorstmt designatorstmt) {
        this.designatorstmt=designatorstmt;
        if(designatorstmt!=null) designatorstmt.setParent(this);
    }

    public Designatorstmt getDesignatorstmt() {
        return designatorstmt;
    }

    public void setDesignatorstmt(Designatorstmt designatorstmt) {
        this.designatorstmt=designatorstmt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(designatorstmt!=null) designatorstmt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(designatorstmt!=null) designatorstmt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(designatorstmt!=null) designatorstmt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForIncrement(\n");

        if(designatorstmt!=null)
            buffer.append(designatorstmt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForIncrement]");
        return buffer.toString();
    }
}
