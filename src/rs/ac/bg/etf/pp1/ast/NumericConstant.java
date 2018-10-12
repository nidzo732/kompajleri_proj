// generated with ast extension for cup
// version 0.8
// 12/9/2018 12:24:49


package rs.ac.bg.etf.pp1.ast;

public class NumericConstant extends Factor {

    private int val;

    public NumericConstant (int val) {
        this.val=val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
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
        buffer.append("NumericConstant(\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NumericConstant]");
        return buffer.toString();
    }
}
