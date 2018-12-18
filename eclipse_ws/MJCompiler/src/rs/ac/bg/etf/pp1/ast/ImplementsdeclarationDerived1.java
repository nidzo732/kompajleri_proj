// generated with ast extension for cup
// version 0.8
// 18/11/2018 13:27:0


package rs.ac.bg.etf.pp1.ast;

public class ImplementsdeclarationDerived1 extends Implementsdeclaration {

    private Nonemptyimplementsdeclaration nonemptyimplementsdeclaration;

    public ImplementsdeclarationDerived1 (Nonemptyimplementsdeclaration nonemptyimplementsdeclaration) {
        this.nonemptyimplementsdeclaration=nonemptyimplementsdeclaration;
        if(nonemptyimplementsdeclaration!=null) nonemptyimplementsdeclaration.setParent(this);
    }

    public Nonemptyimplementsdeclaration getNonemptyimplementsdeclaration() {
        return nonemptyimplementsdeclaration;
    }

    public void setNonemptyimplementsdeclaration(Nonemptyimplementsdeclaration nonemptyimplementsdeclaration) {
        this.nonemptyimplementsdeclaration=nonemptyimplementsdeclaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(nonemptyimplementsdeclaration!=null) nonemptyimplementsdeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(nonemptyimplementsdeclaration!=null) nonemptyimplementsdeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(nonemptyimplementsdeclaration!=null) nonemptyimplementsdeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ImplementsdeclarationDerived1(\n");

        if(nonemptyimplementsdeclaration!=null)
            buffer.append(nonemptyimplementsdeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ImplementsdeclarationDerived1]");
        return buffer.toString();
    }
}
