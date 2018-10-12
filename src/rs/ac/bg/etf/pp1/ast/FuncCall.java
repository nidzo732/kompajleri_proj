// generated with ast extension for cup
// version 0.8
// 12/9/2018 12:24:49


package rs.ac.bg.etf.pp1.ast;

public class FuncCall extends Call {

    private Designator designator;
    private Actpars actpars;

    public FuncCall (Designator designator, Actpars actpars) {
        this.designator=designator;
        if(designator!=null) designator.setParent(this);
        this.actpars=actpars;
        if(actpars!=null) actpars.setParent(this);
    }

    public Designator getDesignator() {
        return designator;
    }

    public void setDesignator(Designator designator) {
        this.designator=designator;
    }

    public Actpars getActpars() {
        return actpars;
    }

    public void setActpars(Actpars actpars) {
        this.actpars=actpars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(designator!=null) designator.accept(visitor);
        if(actpars!=null) actpars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(designator!=null) designator.traverseTopDown(visitor);
        if(actpars!=null) actpars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(designator!=null) designator.traverseBottomUp(visitor);
        if(actpars!=null) actpars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FuncCall(\n");

        if(designator!=null)
            buffer.append(designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(actpars!=null)
            buffer.append(actpars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FuncCall]");
        return buffer.toString();
    }
}
