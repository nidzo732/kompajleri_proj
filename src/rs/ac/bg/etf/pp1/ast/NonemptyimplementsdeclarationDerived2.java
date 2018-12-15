// generated with ast extension for cup
// version 0.8
// 15/11/2018 5:57:14


package rs.ac.bg.etf.pp1.ast;

public class NonemptyimplementsdeclarationDerived2 extends Nonemptyimplementsdeclaration {

    private Nonemptyimplementsdeclaration nonemptyimplementsdeclaration;
    private Implementedname implementedname;

    public NonemptyimplementsdeclarationDerived2 (Nonemptyimplementsdeclaration nonemptyimplementsdeclaration, Implementedname implementedname) {
        this.nonemptyimplementsdeclaration=nonemptyimplementsdeclaration;
        if(nonemptyimplementsdeclaration!=null) nonemptyimplementsdeclaration.setParent(this);
        this.implementedname=implementedname;
        if(implementedname!=null) implementedname.setParent(this);
    }

    public Nonemptyimplementsdeclaration getNonemptyimplementsdeclaration() {
        return nonemptyimplementsdeclaration;
    }

    public void setNonemptyimplementsdeclaration(Nonemptyimplementsdeclaration nonemptyimplementsdeclaration) {
        this.nonemptyimplementsdeclaration=nonemptyimplementsdeclaration;
    }

    public Implementedname getImplementedname() {
        return implementedname;
    }

    public void setImplementedname(Implementedname implementedname) {
        this.implementedname=implementedname;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(nonemptyimplementsdeclaration!=null) nonemptyimplementsdeclaration.accept(visitor);
        if(implementedname!=null) implementedname.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(nonemptyimplementsdeclaration!=null) nonemptyimplementsdeclaration.traverseTopDown(visitor);
        if(implementedname!=null) implementedname.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(nonemptyimplementsdeclaration!=null) nonemptyimplementsdeclaration.traverseBottomUp(visitor);
        if(implementedname!=null) implementedname.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NonemptyimplementsdeclarationDerived2(\n");

        if(nonemptyimplementsdeclaration!=null)
            buffer.append(nonemptyimplementsdeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(implementedname!=null)
            buffer.append(implementedname.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NonemptyimplementsdeclarationDerived2]");
        return buffer.toString();
    }
}
