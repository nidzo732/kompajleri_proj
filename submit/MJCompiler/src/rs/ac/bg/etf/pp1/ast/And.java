// generated with ast extension for cup
// version 0.8
// 28/11/2018 17:15:5


package rs.ac.bg.etf.pp1.ast;

public class And extends Condterm {

    private Condterm condterm;
    private Condfactor condfactor;

    public And (Condterm condterm, Condfactor condfactor) {
        this.condterm=condterm;
        if(condterm!=null) condterm.setParent(this);
        this.condfactor=condfactor;
        if(condfactor!=null) condfactor.setParent(this);
    }

    public Condterm getCondterm() {
        return condterm;
    }

    public void setCondterm(Condterm condterm) {
        this.condterm=condterm;
    }

    public Condfactor getCondfactor() {
        return condfactor;
    }

    public void setCondfactor(Condfactor condfactor) {
        this.condfactor=condfactor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(condterm!=null) condterm.accept(visitor);
        if(condfactor!=null) condfactor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(condterm!=null) condterm.traverseTopDown(visitor);
        if(condfactor!=null) condfactor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(condterm!=null) condterm.traverseBottomUp(visitor);
        if(condfactor!=null) condfactor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("And(\n");

        if(condterm!=null)
            buffer.append(condterm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(condfactor!=null)
            buffer.append(condfactor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [And]");
        return buffer.toString();
    }
}
