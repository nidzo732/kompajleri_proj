// generated with ast extension for cup
// version 0.8
// 18/11/2018 13:27:0


package rs.ac.bg.etf.pp1.ast;

public class FormparswrapperDerived1 extends Formparswrapper {

    private Formpars formpars;

    public FormparswrapperDerived1 (Formpars formpars) {
        this.formpars=formpars;
        if(formpars!=null) formpars.setParent(this);
    }

    public Formpars getFormpars() {
        return formpars;
    }

    public void setFormpars(Formpars formpars) {
        this.formpars=formpars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(formpars!=null) formpars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(formpars!=null) formpars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(formpars!=null) formpars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormparswrapperDerived1(\n");

        if(formpars!=null)
            buffer.append(formpars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormparswrapperDerived1]");
        return buffer.toString();
    }
}
