// generated with ast extension for cup
// version 0.8
// 20/11/2018 13:38:8


package rs.ac.bg.etf.pp1.ast;

public class ConstantDeclarationTyped extends Constantdeclaration {

    private Type type;
    private Constnamedeclaration constnamedeclaration;

    public ConstantDeclarationTyped (Type type, Constnamedeclaration constnamedeclaration) {
        this.type=type;
        if(type!=null) type.setParent(this);
        this.constnamedeclaration=constnamedeclaration;
        if(constnamedeclaration!=null) constnamedeclaration.setParent(this);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type=type;
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
        if(type!=null) type.accept(visitor);
        if(constnamedeclaration!=null) constnamedeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(type!=null) type.traverseTopDown(visitor);
        if(constnamedeclaration!=null) constnamedeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(type!=null) type.traverseBottomUp(visitor);
        if(constnamedeclaration!=null) constnamedeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstantDeclarationTyped(\n");

        if(type!=null)
            buffer.append(type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(constnamedeclaration!=null)
            buffer.append(constnamedeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstantDeclarationTyped]");
        return buffer.toString();
    }
}
