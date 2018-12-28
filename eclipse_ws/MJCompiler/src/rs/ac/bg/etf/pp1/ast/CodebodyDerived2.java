// generated with ast extension for cup
// version 0.8
// 28/11/2018 16:9:49


package rs.ac.bg.etf.pp1.ast;

public class CodebodyDerived2 extends Codebody {

    private Codebody codebody;
    private Function function;

    public CodebodyDerived2 (Codebody codebody, Function function) {
        this.codebody=codebody;
        if(codebody!=null) codebody.setParent(this);
        this.function=function;
        if(function!=null) function.setParent(this);
    }

    public Codebody getCodebody() {
        return codebody;
    }

    public void setCodebody(Codebody codebody) {
        this.codebody=codebody;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function=function;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(codebody!=null) codebody.accept(visitor);
        if(function!=null) function.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(codebody!=null) codebody.traverseTopDown(visitor);
        if(function!=null) function.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(codebody!=null) codebody.traverseBottomUp(visitor);
        if(function!=null) function.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CodebodyDerived2(\n");

        if(codebody!=null)
            buffer.append(codebody.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(function!=null)
            buffer.append(function.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CodebodyDerived2]");
        return buffer.toString();
    }
}
