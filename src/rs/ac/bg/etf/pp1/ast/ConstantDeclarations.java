// generated with ast extension for cup
// version 0.8
// 15/11/2018 5:57:14


package rs.ac.bg.etf.pp1.ast;

public class ConstantDeclarations extends Constantdeclaration {

    private Constantdeclaration constantdeclaration;
    private Constnamedeclaration constnamedeclaration;

    public ConstantDeclarations (Constantdeclaration constantdeclaration, Constnamedeclaration constnamedeclaration) {
        this.constantdeclaration=constantdeclaration;
        if(constantdeclaration!=null) constantdeclaration.setParent(this);
        this.constnamedeclaration=constnamedeclaration;
        if(constnamedeclaration!=null) constnamedeclaration.setParent(this);
    }

    public Constantdeclaration getConstantdeclaration() {
        return constantdeclaration;
    }

    public void setConstantdeclaration(Constantdeclaration constantdeclaration) {
        this.constantdeclaration=constantdeclaration;
    }

    public Constnamedeclaration getConstnamedeclaration() {
        return constnamedeclaration;
    }

    public void setConstnamedeclaration(Constnamedeclaration constnamedeclaration) {
        this.constnamedeclaration=constnamedeclaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(constantdeclaration!=null) constantdeclaration.accept(visitor);
        if(constnamedeclaration!=null) constnamedeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(constantdeclaration!=null) constantdeclaration.traverseTopDown(visitor);
        if(constnamedeclaration!=null) constnamedeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(constantdeclaration!=null) constantdeclaration.traverseBottomUp(visitor);
        if(constnamedeclaration!=null) constnamedeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstantDeclarations(\n");

        if(constantdeclaration!=null)
            buffer.append(constantdeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(constnamedeclaration!=null)
            buffer.append(constnamedeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstantDeclarations]");
        return buffer.toString();
    }
}
