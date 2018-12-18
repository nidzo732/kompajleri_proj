// generated with ast extension for cup
// version 0.8
// 18/11/2018 13:27:0


package rs.ac.bg.etf.pp1.ast;

public class ProgramdeclarationsDerived7 extends Programdeclarations {

    private Programdeclarations programdeclarations;

    public ProgramdeclarationsDerived7 (Programdeclarations programdeclarations) {
        this.programdeclarations=programdeclarations;
        if(programdeclarations!=null) programdeclarations.setParent(this);
    }

    public Programdeclarations getProgramdeclarations() {
        return programdeclarations;
    }

    public void setProgramdeclarations(Programdeclarations programdeclarations) {
        this.programdeclarations=programdeclarations;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(programdeclarations!=null) programdeclarations.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(programdeclarations!=null) programdeclarations.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(programdeclarations!=null) programdeclarations.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramdeclarationsDerived7(\n");

        if(programdeclarations!=null)
            buffer.append(programdeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramdeclarationsDerived7]");
        return buffer.toString();
    }
}
