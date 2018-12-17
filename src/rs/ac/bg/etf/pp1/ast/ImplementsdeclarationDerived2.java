// generated with ast extension for cup
// version 0.8
// 17/11/2018 21:40:55


package rs.ac.bg.etf.pp1.ast;

public class ImplementsdeclarationDerived2 extends Implementsdeclaration {

    private Emptyimplementsdeclaration emptyimplementsdeclaration;

    public ImplementsdeclarationDerived2 (Emptyimplementsdeclaration emptyimplementsdeclaration) {
        this.emptyimplementsdeclaration=emptyimplementsdeclaration;
        if(emptyimplementsdeclaration!=null) emptyimplementsdeclaration.setParent(this);
    }

    public Emptyimplementsdeclaration getEmptyimplementsdeclaration() {
        return emptyimplementsdeclaration;
    }

    public void setEmptyimplementsdeclaration(Emptyimplementsdeclaration emptyimplementsdeclaration) {
        this.emptyimplementsdeclaration=emptyimplementsdeclaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(emptyimplementsdeclaration!=null) emptyimplementsdeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(emptyimplementsdeclaration!=null) emptyimplementsdeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(emptyimplementsdeclaration!=null) emptyimplementsdeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ImplementsdeclarationDerived2(\n");

        if(emptyimplementsdeclaration!=null)
            buffer.append(emptyimplementsdeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ImplementsdeclarationDerived2]");
        return buffer.toString();
    }
}
