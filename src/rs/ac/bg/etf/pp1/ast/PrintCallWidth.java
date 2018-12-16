// generated with ast extension for cup
// version 0.8
// 16/11/2018 17:36:48


package rs.ac.bg.etf.pp1.ast;

public class PrintCallWidth extends Printcallw {

    private Expr expr;
    private int width;

    public PrintCallWidth (Expr expr, int width) {
        this.expr=expr;
        if(expr!=null) expr.setParent(this);
        this.width=width;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr=expr;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width=width;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(expr!=null) expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(expr!=null) expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(expr!=null) expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintCallWidth(\n");

        if(expr!=null)
            buffer.append(expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+width);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintCallWidth]");
        return buffer.toString();
    }
}
