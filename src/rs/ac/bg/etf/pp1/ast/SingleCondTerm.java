// generated with ast extension for cup
// version 0.8
// 9/9/2018 22:58:35


package rs.ac.bg.etf.pp1.ast;

public class SingleCondTerm extends Condterm {

    private Condfactor condfactor;

    public SingleCondTerm (Condfactor condfactor) {
        this.condfactor=condfactor;
        if(condfactor!=null) condfactor.setParent(this);
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
        if(condfactor!=null) condfactor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(condfactor!=null) condfactor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(condfactor!=null) condfactor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleCondTerm(\n");

        if(condfactor!=null)
            buffer.append(condfactor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleCondTerm]");
        return buffer.toString();
    }
}
