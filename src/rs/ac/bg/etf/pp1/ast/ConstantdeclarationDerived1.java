// generated with ast extension for cup
// version 0.8
// 9/9/2018 22:58:35


package rs.ac.bg.etf.pp1.ast;

public class ConstantdeclarationDerived1 extends Constantdeclaration {

    private Constantdeclaration constantdeclaration;

    public ConstantdeclarationDerived1 (Constantdeclaration constantdeclaration) {
        this.constantdeclaration=constantdeclaration;
        if(constantdeclaration!=null) constantdeclaration.setParent(this);
    }

    public Constantdeclaration getConstantdeclaration() {
        return constantdeclaration;
    }

    public void setConstantdeclaration(Constantdeclaration constantdeclaration) {
        this.constantdeclaration=constantdeclaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(constantdeclaration!=null) constantdeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(constantdeclaration!=null) constantdeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(constantdeclaration!=null) constantdeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstantdeclarationDerived1(\n");

        if(constantdeclaration!=null)
            buffer.append(constantdeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstantdeclarationDerived1]");
        return buffer.toString();
    }
}
