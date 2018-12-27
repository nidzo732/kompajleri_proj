// generated with ast extension for cup
// version 0.8
// 28/11/2018 0:12:52


package rs.ac.bg.etf.pp1.ast;

public class FunctionDeclBlock extends Functiondeclarationblock {

    private Declarationblock declarationblock;

    public FunctionDeclBlock (Declarationblock declarationblock) {
        this.declarationblock=declarationblock;
        if(declarationblock!=null) declarationblock.setParent(this);
    }

    public Declarationblock getDeclarationblock() {
        return declarationblock;
    }

    public void setDeclarationblock(Declarationblock declarationblock) {
        this.declarationblock=declarationblock;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(declarationblock!=null) declarationblock.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(declarationblock!=null) declarationblock.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(declarationblock!=null) declarationblock.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FunctionDeclBlock(\n");

        if(declarationblock!=null)
            buffer.append(declarationblock.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FunctionDeclBlock]");
        return buffer.toString();
    }
}
