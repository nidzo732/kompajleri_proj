// generated with ast extension for cup
// version 0.8
// 18/11/2018 13:27:0


package rs.ac.bg.etf.pp1.ast;

public class PrintStatementW extends Statement {

    private Printcallw printcallw;

    public PrintStatementW (Printcallw printcallw) {
        this.printcallw=printcallw;
        if(printcallw!=null) printcallw.setParent(this);
    }

    public Printcallw getPrintcallw() {
        return printcallw;
    }

    public void setPrintcallw(Printcallw printcallw) {
        this.printcallw=printcallw;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(printcallw!=null) printcallw.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(printcallw!=null) printcallw.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(printcallw!=null) printcallw.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintStatementW(\n");

        if(printcallw!=null)
            buffer.append(printcallw.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintStatementW]");
        return buffer.toString();
    }
}
