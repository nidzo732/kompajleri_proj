// generated with ast extension for cup
// version 0.8
// 28/11/2018 0:12:52


package rs.ac.bg.etf.pp1.ast;

public class ExtendsDecl extends Extendsdeclaration {

    private String base;

    public ExtendsDecl (String base) {
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
        buffer.append("ExtendsDecl(\n");

        buffer.append(" "+tab+base);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExtendsDecl]");
        return buffer.toString();
    }
}
