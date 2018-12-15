// generated with ast extension for cup
// version 0.8
// 15/11/2018 5:57:14


package rs.ac.bg.etf.pp1.ast;

public class CallStatement extends Statement {

    private Call call;

    public CallStatement (Call call) {
        this.call=call;
        if(call!=null) call.setParent(this);
    }

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call=call;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(call!=null) call.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(call!=null) call.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(call!=null) call.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CallStatement(\n");

        if(call!=null)
            buffer.append(call.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CallStatement]");
        return buffer.toString();
    }
}
