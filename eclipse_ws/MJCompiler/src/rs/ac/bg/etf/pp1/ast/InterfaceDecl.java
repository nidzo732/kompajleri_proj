// generated with ast extension for cup
// version 0.8
// 28/11/2018 16:9:49


package rs.ac.bg.etf.pp1.ast;

public class InterfaceDecl extends Interfacedeclaration {

    private Interfacename interfacename;
    private Interfaceinner interfaceinner;

    public InterfaceDecl (Interfacename interfacename, Interfaceinner interfaceinner) {
        this.interfacename=interfacename;
        if(interfacename!=null) interfacename.setParent(this);
        this.interfaceinner=interfaceinner;
        if(interfaceinner!=null) interfaceinner.setParent(this);
    }

    public Interfacename getInterfacename() {
        return interfacename;
    }

    public void setInterfacename(Interfacename interfacename) {
        this.interfacename=interfacename;
    }

    public Interfaceinner getInterfaceinner() {
        return interfaceinner;
    }

    public void setInterfaceinner(Interfaceinner interfaceinner) {
        this.interfaceinner=interfaceinner;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(interfacename!=null) interfacename.accept(visitor);
        if(interfaceinner!=null) interfaceinner.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(interfacename!=null) interfacename.traverseTopDown(visitor);
        if(interfaceinner!=null) interfaceinner.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(interfacename!=null) interfacename.traverseBottomUp(visitor);
        if(interfaceinner!=null) interfaceinner.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("InterfaceDecl(\n");

        if(interfacename!=null)
            buffer.append(interfacename.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(interfaceinner!=null)
            buffer.append(interfaceinner.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [InterfaceDecl]");
        return buffer.toString();
    }
}
