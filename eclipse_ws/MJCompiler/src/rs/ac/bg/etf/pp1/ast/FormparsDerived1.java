// generated with ast extension for cup
// version 0.8
// 18/11/2018 13:27:0


package rs.ac.bg.etf.pp1.ast;

public class FormparsDerived1 extends Formpars {

    private Formpars formpars;
    private Type type;
    private Namedeclaration namedeclaration;

    public FormparsDerived1 (Formpars formpars, Type type, Namedeclaration namedeclaration) {
        this.formpars=formpars;
        if(formpars!=null) formpars.setParent(this);
        this.type=type;
        if(type!=null) type.setParent(this);
        this.namedeclaration=namedeclaration;
        if(namedeclaration!=null) namedeclaration.setParent(this);
    }

    public Formpars getFormpars() {
        return formpars;
    }

    public void setFormpars(Formpars formpars) {
        this.formpars=formpars;
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
        if(formpars!=null) formpars.accept(visitor);
        if(type!=null) type.accept(visitor);
        if(namedeclaration!=null) namedeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(formpars!=null) formpars.traverseTopDown(visitor);
        if(type!=null) type.traverseTopDown(visitor);
        if(namedeclaration!=null) namedeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(formpars!=null) formpars.traverseBottomUp(visitor);
        if(type!=null) type.traverseBottomUp(visitor);
        if(namedeclaration!=null) namedeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormparsDerived1(\n");

        if(formpars!=null)
            buffer.append(formpars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

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
        buffer.append(") [FormparsDerived1]");
        return buffer.toString();
    }
}
