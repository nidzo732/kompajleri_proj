// generated with ast extension for cup
// version 0.8
// 9/9/2018 22:58:35


package rs.ac.bg.etf.pp1.ast;

public class BooleanConstant extends Factor {

    private boolean val;

    public BooleanConstant (boolean val) {
        this.val=val;
    }

    public boolean getVal() {
        return val;
    }

    public void setVal(boolean val) {
        this.val=val;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BooleanConstant(\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BooleanConstant]");
        return buffer.toString();
    }
}
