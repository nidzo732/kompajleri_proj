// generated with ast extension for cup
// version 0.8
// 17/11/2018 21:40:55


package rs.ac.bg.etf.pp1.ast;

public class NumberedEnumConstant extends Enumconstant {

    private String name;
    private int value;

    public NumberedEnumConstant (String name, int value) {
        this.name=name;
        this.value=value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value=value;
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
        buffer.append("NumberedEnumConstant(\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        buffer.append(" "+tab+value);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NumberedEnumConstant]");
        return buffer.toString();
    }
}
