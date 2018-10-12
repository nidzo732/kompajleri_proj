// generated with ast extension for cup
// version 0.8
// 12/9/2018 12:24:49


package rs.ac.bg.etf.pp1.ast;

public class ExtendsdeclarationDerived2 extends Extendsdeclaration {

    private String base;

    public ExtendsdeclarationDerived2 (String base) {
        this.base=base;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base=base;
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
        buffer.append("ExtendsdeclarationDerived2(\n");

        buffer.append(" "+tab+base);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExtendsdeclarationDerived2]");
        return buffer.toString();
    }
}
