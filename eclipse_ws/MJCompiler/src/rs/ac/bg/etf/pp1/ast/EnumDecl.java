// generated with ast extension for cup
// version 0.8
// 28/11/2018 0:12:52


package rs.ac.bg.etf.pp1.ast;

public class EnumDecl extends Enumdeclaration {

    private Enumname enumname;
    private Enuminner enuminner;

    public EnumDecl (Enumname enumname, Enuminner enuminner) {
        this.enumname=enumname;
        if(enumname!=null) enumname.setParent(this);
        this.enuminner=enuminner;
        if(enuminner!=null) enuminner.setParent(this);
    }

    public Enumname getEnumname() {
        return enumname;
    }

    public void setEnumname(Enumname enumname) {
        this.enumname=enumname;
    }

    public Enuminner getEnuminner() {
        return enuminner;
    }

    public void setEnuminner(Enuminner enuminner) {
        this.enuminner=enuminner;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(enumname!=null) enumname.accept(visitor);
        if(enuminner!=null) enuminner.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(enumname!=null) enumname.traverseTopDown(visitor);
        if(enuminner!=null) enuminner.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(enumname!=null) enumname.traverseBottomUp(visitor);
        if(enuminner!=null) enuminner.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnumDecl(\n");

        if(enumname!=null)
            buffer.append(enumname.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(enuminner!=null)
            buffer.append(enuminner.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumDecl]");
        return buffer.toString();
    }
}
