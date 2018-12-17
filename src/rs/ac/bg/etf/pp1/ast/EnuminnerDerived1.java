// generated with ast extension for cup
// version 0.8
// 17/11/2018 21:40:55


package rs.ac.bg.etf.pp1.ast;

public class EnuminnerDerived1 extends Enuminner {

    private Enumconstant enumconstant;

    public EnuminnerDerived1 (Enumconstant enumconstant) {
        this.enumconstant=enumconstant;
        if(enumconstant!=null) enumconstant.setParent(this);
    }

    public Enumconstant getEnumconstant() {
        return enumconstant;
    }

    public void setEnumconstant(Enumconstant enumconstant) {
        this.enumconstant=enumconstant;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(enumconstant!=null) enumconstant.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(enumconstant!=null) enumconstant.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(enumconstant!=null) enumconstant.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnuminnerDerived1(\n");

        if(enumconstant!=null)
            buffer.append(enumconstant.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnuminnerDerived1]");
        return buffer.toString();
    }
}
