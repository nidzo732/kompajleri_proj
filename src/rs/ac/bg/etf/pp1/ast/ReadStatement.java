// generated with ast extension for cup
// version 0.8
// 16/11/2018 17:36:48


package rs.ac.bg.etf.pp1.ast;

public class ReadStatement extends Statement {

    private Readcall readcall;

    public ReadStatement (Readcall readcall) {
        this.readcall=readcall;
        if(readcall!=null) readcall.setParent(this);
    }

    public Readcall getReadcall() {
        return readcall;
    }

    public void setReadcall(Readcall readcall) {
        this.readcall=readcall;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(readcall!=null) readcall.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(readcall!=null) readcall.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(readcall!=null) readcall.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ReadStatement(\n");

        if(readcall!=null)
            buffer.append(readcall.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ReadStatement]");
        return buffer.toString();
    }
}
