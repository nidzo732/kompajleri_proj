// generated with ast extension for cup
// version 0.8
// 15/11/2018 5:57:14


package rs.ac.bg.etf.pp1.ast;

public class Parameters extends Actpars {

    private Actpars actpars;
    private Expr expr;

    public Parameters (Actpars actpars, Expr expr) {
        this.actpars=actpars;
        if(actpars!=null) actpars.setParent(this);
        this.expr=expr;
        if(expr!=null) expr.setParent(this);
    }

    public Actpars getActpars() {
        return actpars;
    }

    public void setActpars(Actpars actpars) {
        this.actpars=actpars;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr=expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(actpars!=null) actpars.accept(visitor);
        if(expr!=null) expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(actpars!=null) actpars.traverseTopDown(visitor);
        if(expr!=null) expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(actpars!=null) actpars.traverseBottomUp(visitor);
        if(expr!=null) expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Parameters(\n");

        if(actpars!=null)
            buffer.append(actpars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(expr!=null)
            buffer.append(expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Parameters]");
        return buffer.toString();
    }
}
