// generated with ast extension for cup
// version 0.8
// 4/9/2018 17:42:56


package qq;

public class ProgramDerived1 extends Program {

    private Dplus dplus;

    public ProgramDerived1 (Dplus dplus) {
        this.dplus=dplus;
        if(dplus!=null) dplus.setParent(this);
    }

    public Dplus getDplus() {
        return dplus;
    }

    public void setDplus(Dplus dplus) {
        this.dplus=dplus;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(dplus!=null) dplus.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(dplus!=null) dplus.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(dplus!=null) dplus.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramDerived1(\n");

        if(dplus!=null)
            buffer.append(dplus.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramDerived1]");
        return buffer.toString();
    }
}
