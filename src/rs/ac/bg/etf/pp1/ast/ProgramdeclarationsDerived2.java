// generated with ast extension for cup
// version 0.8
// 17/9/2018 23:25:43


package rs.ac.bg.etf.pp1.ast;

public class ProgramdeclarationsDerived2 extends Programdeclarations {

    private Programdeclarations programdeclarations;
    private Classdeclaration classdeclaration;

    public ProgramdeclarationsDerived2 (Programdeclarations programdeclarations, Classdeclaration classdeclaration) {
        this.programdeclarations=programdeclarations;
        if(programdeclarations!=null) programdeclarations.setParent(this);
        this.classdeclaration=classdeclaration;
        if(classdeclaration!=null) classdeclaration.setParent(this);
    }

    public Programdeclarations getProgramdeclarations() {
        return programdeclarations;
    }

    public void setProgramdeclarations(Programdeclarations programdeclarations) {
        this.programdeclarations=programdeclarations;
    }

    public Classdeclaration getClassdeclaration() {
        return classdeclaration;
    }

    public void setClassdeclaration(Classdeclaration classdeclaration) {
        this.classdeclaration=classdeclaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(programdeclarations!=null) programdeclarations.accept(visitor);
        if(classdeclaration!=null) classdeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(programdeclarations!=null) programdeclarations.traverseTopDown(visitor);
        if(classdeclaration!=null) classdeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(programdeclarations!=null) programdeclarations.traverseBottomUp(visitor);
        if(classdeclaration!=null) classdeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramdeclarationsDerived2(\n");

        if(programdeclarations!=null)
            buffer.append(programdeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(classdeclaration!=null)
            buffer.append(classdeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramdeclarationsDerived2]");
        return buffer.toString();
    }
}
