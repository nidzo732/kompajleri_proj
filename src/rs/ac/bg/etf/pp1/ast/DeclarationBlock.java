// generated with ast extension for cup
// version 0.8
// 12/9/2018 12:24:49


package rs.ac.bg.etf.pp1.ast;

public class DeclarationBlock extends Declarationblock {

    private Declarationblock declarationblock;
    private Variabledeclaration variabledeclaration;

    public DeclarationBlock (Declarationblock declarationblock, Variabledeclaration variabledeclaration) {
        this.declarationblock=declarationblock;
        if(declarationblock!=null) declarationblock.setParent(this);
        this.variabledeclaration=variabledeclaration;
        if(variabledeclaration!=null) variabledeclaration.setParent(this);
    }

    public Declarationblock getDeclarationblock() {
        return declarationblock;
    }

    public void setDeclarationblock(Declarationblock declarationblock) {
        this.declarationblock=declarationblock;
    }

    public Variabledeclaration getVariabledeclaration() {
        return variabledeclaration;
    }

    public void setVariabledeclaration(Variabledeclaration variabledeclaration) {
        this.variabledeclaration=variabledeclaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(declarationblock!=null) declarationblock.accept(visitor);
        if(variabledeclaration!=null) variabledeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(declarationblock!=null) declarationblock.traverseTopDown(visitor);
        if(variabledeclaration!=null) variabledeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(declarationblock!=null) declarationblock.traverseBottomUp(visitor);
        if(variabledeclaration!=null) variabledeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DeclarationBlock(\n");

        if(declarationblock!=null)
            buffer.append(declarationblock.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(variabledeclaration!=null)
            buffer.append(variabledeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DeclarationBlock]");
        return buffer.toString();
    }
}
