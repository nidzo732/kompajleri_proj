// generated with ast extension for cup
// version 0.8
// 16/11/2018 17:36:48


package rs.ac.bg.etf.pp1.ast;

public class PrintStatement extends Statement {

    private Printcall printcall;

    public PrintStatement (Printcall printcall) {
        this.printcall=printcall;
        if(printcall!=null) printcall.setParent(this);
    }

    public Printcall getPrintcall() {
        return printcall;
    }

    public void setPrintcall(Printcall printcall) {
        this.printcall=printcall;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(printcall!=null) printcall.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(printcall!=null) printcall.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(printcall!=null) printcall.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintStatement(\n");

        if(printcall!=null)
            buffer.append(printcall.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintStatement]");
        return buffer.toString();
    }
}
