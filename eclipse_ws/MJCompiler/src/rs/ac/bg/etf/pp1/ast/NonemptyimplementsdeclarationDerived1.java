// generated with ast extension for cup
// version 0.8
// 20/11/2018 13:38:8


package rs.ac.bg.etf.pp1.ast;

public class NonemptyimplementsdeclarationDerived1 extends Nonemptyimplementsdeclaration {

    private Implementedname implementedname;

    public NonemptyimplementsdeclarationDerived1 (Implementedname implementedname) {
        this.implementedname=implementedname;
        if(implementedname!=null) implementedname.setParent(this);
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
        if(implementedname!=null) implementedname.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(implementedname!=null) implementedname.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(implementedname!=null) implementedname.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NonemptyimplementsdeclarationDerived1(\n");

        if(implementedname!=null)
            buffer.append(implementedname.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NonemptyimplementsdeclarationDerived1]");
        return buffer.toString();
    }
}
