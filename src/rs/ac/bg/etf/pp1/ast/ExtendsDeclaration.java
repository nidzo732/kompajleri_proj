// generated with ast extension for cup
// version 0.8
// 15/11/2018 5:57:14


package rs.ac.bg.etf.pp1.ast;

public class ExtendsDeclaration extends Extendsdeclaration {

    private String base;

    public ExtendsDeclaration (String base) {
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
        buffer.append("ExtendsDeclaration(\n");

        buffer.append(" "+tab+base);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExtendsDeclaration]");
        return buffer.toString();
    }
}
