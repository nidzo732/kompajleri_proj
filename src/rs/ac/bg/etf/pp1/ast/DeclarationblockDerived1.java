// generated with ast extension for cup
// version 0.8
// 9/9/2018 22:58:35


package rs.ac.bg.etf.pp1.ast;

public class DeclarationblockDerived1 extends Declarationblock {

    private Declarationblock declarationblock;
    private Classdeclaration classdeclaration;

    public DeclarationblockDerived1 (Declarationblock declarationblock, Classdeclaration classdeclaration) {
        this.declarationblock=declarationblock;
        if(declarationblock!=null) declarationblock.setParent(this);
        this.classdeclaration=classdeclaration;
        if(classdeclaration!=null) classdeclaration.setParent(this);
    }

    public Declarationblock getDeclarationblock() {
        return declarationblock;
    }

    public void setDeclarationblock(Declarationblock declarationblock) {
        this.declarationblock=declarationblock;
    }

    public Classdeclaration getClassdeclaration() {
        return classdeclaration;
    }

    public void setClassdeclaration(Classdeclaration classdeclaration) {
        this.classdeclaration=classdeclaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(declarationblock!=null) declarationblock.accept(visitor);
        if(classdeclaration!=null) classdeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(declarationblock!=null) declarationblock.traverseTopDown(visitor);
        if(classdeclaration!=null) classdeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(declarationblock!=null) declarationblock.traverseBottomUp(visitor);
        if(classdeclaration!=null) classdeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DeclarationblockDerived1(\n");

        if(declarationblock!=null)
            buffer.append(declarationblock.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(classdeclaration!=null)
            buffer.append(classdeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DeclarationblockDerived1]");
        return buffer.toString();
    }
}
