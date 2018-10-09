// generated with ast extension for cup
// version 0.8
// 4/9/2018 17:42:56


package qq;

public class ProgramDerived2 extends Program {

    private Program program;
    private Dplus dplus;

    public ProgramDerived2 (Program program, Dplus dplus) {
        this.program=program;
        if(program!=null) program.setParent(this);
        this.dplus=dplus;
        if(dplus!=null) dplus.setParent(this);
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program=program;
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
        if(program!=null) program.accept(visitor);
        if(dplus!=null) dplus.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(program!=null) program.traverseTopDown(visitor);
        if(dplus!=null) dplus.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(program!=null) program.traverseBottomUp(visitor);
        if(dplus!=null) dplus.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramDerived2(\n");

        if(program!=null)
            buffer.append(program.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(dplus!=null)
            buffer.append(dplus.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramDerived2]");
        return buffer.toString();
    }
}
