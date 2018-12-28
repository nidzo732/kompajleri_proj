// generated with ast extension for cup
// version 0.8
// 28/11/2018 16:9:49


package rs.ac.bg.etf.pp1.ast;

public class FormparsDerived2 extends Formpars {

    private Formpar formpar;

    public FormparsDerived2 (Formpar formpar) {
        this.formpar=formpar;
        if(formpar!=null) formpar.setParent(this);
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
        if(formpar!=null) formpar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(formpar!=null) formpar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(formpar!=null) formpar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormparsDerived2(\n");

        if(formpar!=null)
            buffer.append(formpar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormparsDerived2]");
        return buffer.toString();
    }
}
