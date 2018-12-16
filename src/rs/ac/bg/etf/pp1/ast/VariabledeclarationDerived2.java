// generated with ast extension for cup
// version 0.8
// 16/11/2018 17:36:48


package rs.ac.bg.etf.pp1.ast;

public class VariabledeclarationDerived2 extends Variabledeclaration {

    private Variabledeclaration variabledeclaration;
    private Namedeclaration namedeclaration;

    public VariabledeclarationDerived2 (Variabledeclaration variabledeclaration, Namedeclaration namedeclaration) {
        this.variabledeclaration=variabledeclaration;
        if(variabledeclaration!=null) variabledeclaration.setParent(this);
        this.namedeclaration=namedeclaration;
        if(namedeclaration!=null) namedeclaration.setParent(this);
    }

    public Variabledeclaration getVariabledeclaration() {
        return variabledeclaration;
    }

    public void setVariabledeclaration(Variabledeclaration variabledeclaration) {
        this.variabledeclaration=variabledeclaration;
    }

    public Namedeclaration getNamedeclaration() {
        return namedeclaration;
    }

    public void setNamedeclaration(Namedeclaration namedeclaration) {
        this.namedeclaration=namedeclaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(variabledeclaration!=null) variabledeclaration.accept(visitor);
        if(namedeclaration!=null) namedeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(variabledeclaration!=null) variabledeclaration.traverseTopDown(visitor);
        if(namedeclaration!=null) namedeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(variabledeclaration!=null) variabledeclaration.traverseBottomUp(visitor);
        if(namedeclaration!=null) namedeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VariabledeclarationDerived2(\n");

        if(variabledeclaration!=null)
            buffer.append(variabledeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(namedeclaration!=null)
            buffer.append(namedeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VariabledeclarationDerived2]");
        return buffer.toString();
    }
}
