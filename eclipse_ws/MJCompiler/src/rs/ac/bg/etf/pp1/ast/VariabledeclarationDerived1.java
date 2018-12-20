// generated with ast extension for cup
// version 0.8
// 20/11/2018 13:38:8


package rs.ac.bg.etf.pp1.ast;

public class VariabledeclarationDerived1 extends Variabledeclaration {

    private Type type;
    private Namedeclaration namedeclaration;

    public VariabledeclarationDerived1 (Type type, Namedeclaration namedeclaration) {
        this.type=type;
        if(type!=null) type.setParent(this);
        this.namedeclaration=namedeclaration;
        if(namedeclaration!=null) namedeclaration.setParent(this);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type=type;
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
        if(type!=null) type.accept(visitor);
        if(namedeclaration!=null) namedeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(type!=null) type.traverseTopDown(visitor);
        if(namedeclaration!=null) namedeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(type!=null) type.traverseBottomUp(visitor);
        if(namedeclaration!=null) namedeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VariabledeclarationDerived1(\n");

        if(type!=null)
            buffer.append(type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(namedeclaration!=null)
            buffer.append(namedeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VariabledeclarationDerived1]");
        return buffer.toString();
    }
}
