// generated with ast extension for cup
// version 0.8
// 15/11/2018 5:57:14


package rs.ac.bg.etf.pp1.ast;

public class FormparsDerived3 extends Formpars {

    public FormparsDerived3 () {
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
        buffer.append("FormparsDerived3(\n");

        buffer.append(tab);
        buffer.append(") [FormparsDerived3]");
        return buffer.toString();
    }
}
