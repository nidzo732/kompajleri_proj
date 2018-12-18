// generated with ast extension for cup
// version 0.8
// 18/11/2018 13:27:0


package rs.ac.bg.etf.pp1.ast;

public class InterfaceMethodDeclaration extends Interfaceinner {

    private Interfaceinner interfaceinner;
    private Functionheader functionheader;

    public InterfaceMethodDeclaration (Interfaceinner interfaceinner, Functionheader functionheader) {
        this.interfaceinner=interfaceinner;
        if(interfaceinner!=null) interfaceinner.setParent(this);
        this.functionheader=functionheader;
        if(functionheader!=null) functionheader.setParent(this);
    }

    public Interfaceinner getInterfaceinner() {
        return interfaceinner;
    }

    public void setInterfaceinner(Interfaceinner interfaceinner) {
        this.interfaceinner=interfaceinner;
    }

    public Functionheader getFunctionheader() {
        return functionheader;
    }

    public void setFunctionheader(Functionheader functionheader) {
        this.functionheader=functionheader;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(interfaceinner!=null) interfaceinner.accept(visitor);
        if(functionheader!=null) functionheader.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(interfaceinner!=null) interfaceinner.traverseTopDown(visitor);
        if(functionheader!=null) functionheader.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(interfaceinner!=null) interfaceinner.traverseBottomUp(visitor);
        if(functionheader!=null) functionheader.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("InterfaceMethodDeclaration(\n");

        if(interfaceinner!=null)
            buffer.append(interfaceinner.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(functionheader!=null)
            buffer.append(functionheader.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [InterfaceMethodDeclaration]");
        return buffer.toString();
    }
}
