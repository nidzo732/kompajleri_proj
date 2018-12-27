// generated with ast extension for cup
// version 0.8
// 28/11/2018 0:12:52


package rs.ac.bg.etf.pp1.ast;

public class FuncHeader extends Functionheader {

    private Functionname functionname;
    private Formparswrapper formparswrapper;

    public FuncHeader (Functionname functionname, Formparswrapper formparswrapper) {
        this.functionname=functionname;
        if(functionname!=null) functionname.setParent(this);
        this.formparswrapper=formparswrapper;
        if(formparswrapper!=null) formparswrapper.setParent(this);
    }

    public Functionname getFunctionname() {
        return functionname;
    }

    public void setFunctionname(Functionname functionname) {
        this.functionname=functionname;
    }

    public Formparswrapper getFormparswrapper() {
        return formparswrapper;
    }

    public void setFormparswrapper(Formparswrapper formparswrapper) {
        this.formparswrapper=formparswrapper;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(functionname!=null) functionname.accept(visitor);
        if(formparswrapper!=null) formparswrapper.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(functionname!=null) functionname.traverseTopDown(visitor);
        if(formparswrapper!=null) formparswrapper.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(functionname!=null) functionname.traverseBottomUp(visitor);
        if(formparswrapper!=null) formparswrapper.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FuncHeader(\n");

        if(functionname!=null)
            buffer.append(functionname.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(formparswrapper!=null)
            buffer.append(formparswrapper.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FuncHeader]");
        return buffer.toString();
    }
}
