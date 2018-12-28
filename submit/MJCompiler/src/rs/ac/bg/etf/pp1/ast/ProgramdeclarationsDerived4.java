// generated with ast extension for cup
// version 0.8
// 28/11/2018 17:15:5


package rs.ac.bg.etf.pp1.ast;

public class ProgramdeclarationsDerived4 extends Programdeclarations {

    private Programdeclarations programdeclarations;
    private Enumdeclaration enumdeclaration;

    public ProgramdeclarationsDerived4 (Programdeclarations programdeclarations, Enumdeclaration enumdeclaration) {
        this.programdeclarations=programdeclarations;
        if(programdeclarations!=null) programdeclarations.setParent(this);
        this.enumdeclaration=enumdeclaration;
        if(enumdeclaration!=null) enumdeclaration.setParent(this);
    }

    public Programdeclarations getProgramdeclarations() {
        return programdeclarations;
    }

    public void setProgramdeclarations(Programdeclarations programdeclarations) {
        this.programdeclarations=programdeclarations;
    }

    public Enumdeclaration getEnumdeclaration() {
        return enumdeclaration;
    }

    public void setEnumdeclaration(Enumdeclaration enumdeclaration) {
        this.enumdeclaration=enumdeclaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(programdeclarations!=null) programdeclarations.accept(visitor);
        if(enumdeclaration!=null) enumdeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(programdeclarations!=null) programdeclarations.traverseTopDown(visitor);
        if(enumdeclaration!=null) enumdeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(programdeclarations!=null) programdeclarations.traverseBottomUp(visitor);
        if(enumdeclaration!=null) enumdeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramdeclarationsDerived4(\n");

        if(programdeclarations!=null)
            buffer.append(programdeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(enumdeclaration!=null)
            buffer.append(enumdeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramdeclarationsDerived4]");
        return buffer.toString();
    }
}
