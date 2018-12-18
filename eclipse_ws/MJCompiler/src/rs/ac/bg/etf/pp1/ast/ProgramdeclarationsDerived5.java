// generated with ast extension for cup
// version 0.8
// 18/11/2018 13:27:0


package rs.ac.bg.etf.pp1.ast;

public class ProgramdeclarationsDerived5 extends Programdeclarations {

    private Programdeclarations programdeclarations;
    private Constantdeclaration constantdeclaration;

    public ProgramdeclarationsDerived5 (Programdeclarations programdeclarations, Constantdeclaration constantdeclaration) {
        this.programdeclarations=programdeclarations;
        if(programdeclarations!=null) programdeclarations.setParent(this);
        this.constantdeclaration=constantdeclaration;
        if(constantdeclaration!=null) constantdeclaration.setParent(this);
    }

    public Programdeclarations getProgramdeclarations() {
        return programdeclarations;
    }

    public void setProgramdeclarations(Programdeclarations programdeclarations) {
        this.programdeclarations=programdeclarations;
    }

    public Constantdeclaration getConstantdeclaration() {
        return constantdeclaration;
    }

    public void setConstantdeclaration(Constantdeclaration constantdeclaration) {
        this.constantdeclaration=constantdeclaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(programdeclarations!=null) programdeclarations.accept(visitor);
        if(constantdeclaration!=null) constantdeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(programdeclarations!=null) programdeclarations.traverseTopDown(visitor);
        if(constantdeclaration!=null) constantdeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(programdeclarations!=null) programdeclarations.traverseBottomUp(visitor);
        if(constantdeclaration!=null) constantdeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramdeclarationsDerived5(\n");

        if(programdeclarations!=null)
            buffer.append(programdeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(constantdeclaration!=null)
            buffer.append(constantdeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramdeclarationsDerived5]");
        return buffer.toString();
    }
}
