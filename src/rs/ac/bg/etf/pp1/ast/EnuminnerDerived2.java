// generated with ast extension for cup
// version 0.8
// 16/11/2018 17:36:48


package rs.ac.bg.etf.pp1.ast;

public class EnuminnerDerived2 extends Enuminner {

    private Enuminner enuminner;
    private Enumconstant enumconstant;

    public EnuminnerDerived2 (Enuminner enuminner, Enumconstant enumconstant) {
        this.enuminner=enuminner;
        if(enuminner!=null) enuminner.setParent(this);
        this.enumconstant=enumconstant;
        if(enumconstant!=null) enumconstant.setParent(this);
    }

    public Enuminner getEnuminner() {
        return enuminner;
    }

    public void setEnuminner(Enuminner enuminner) {
        this.enuminner=enuminner;
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
        if(enuminner!=null) enuminner.accept(visitor);
        if(enumconstant!=null) enumconstant.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(enuminner!=null) enuminner.traverseTopDown(visitor);
        if(enumconstant!=null) enumconstant.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(enuminner!=null) enuminner.traverseBottomUp(visitor);
        if(enumconstant!=null) enumconstant.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnuminnerDerived2(\n");

        if(enuminner!=null)
            buffer.append(enuminner.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(enumconstant!=null)
            buffer.append(enumconstant.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnuminnerDerived2]");
        return buffer.toString();
    }
}
