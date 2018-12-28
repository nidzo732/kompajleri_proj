// generated with ast extension for cup
// version 0.8
// 28/11/2018 16:9:49


package rs.ac.bg.etf.pp1.ast;

public class FormparsDerived1 extends Formpars {

    private Formpars formpars;
    private Formpar formpar;

    public FormparsDerived1 (Formpars formpars, Formpar formpar) {
        this.formpars=formpars;
        if(formpars!=null) formpars.setParent(this);
        this.formpar=formpar;
        if(formpar!=null) formpar.setParent(this);
    }

    public Formpars getFormpars() {
        return formpars;
    }

    public void setFormpars(Formpars formpars) {
        this.formpars=formpars;
    }

    public Formpar getFormpar() {
        return formpar;
    }

    public void setFormpar(Formpar formpar) {
        this.formpar=formpar;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(formpars!=null) formpars.accept(visitor);
        if(formpar!=null) formpar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(formpars!=null) formpars.traverseTopDown(visitor);
        if(formpar!=null) formpar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(formpars!=null) formpars.traverseBottomUp(visitor);
        if(formpar!=null) formpar.traverseBottomUp(visitor);
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

        if(formpar!=null)
            buffer.append(formpar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormparsDerived1]");
        return buffer.toString();
    }
}
