// generated with ast extension for cup
// version 0.8
// 18/11/2018 13:27:0


package rs.ac.bg.etf.pp1.ast;

public class NumberConstantDeclaration extends Constnamedeclaration {

    private String name;
    private int val;

    public NumberConstantDeclaration (String name, int val) {
        this.name=name;
        this.val=val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
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
        buffer.append("NumberConstantDeclaration(\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NumberConstantDeclaration]");
        return buffer.toString();
    }
}
