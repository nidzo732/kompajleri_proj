// generated with ast extension for cup
// version 0.8
// 20/11/2018 13:38:8


package rs.ac.bg.etf.pp1.ast;

public class ProgramdeclarationsDerived3 extends Programdeclarations {

    private Programdeclarations programdeclarations;
    private Interfacedeclaration interfacedeclaration;

    public ProgramdeclarationsDerived3 (Programdeclarations programdeclarations, Interfacedeclaration interfacedeclaration) {
        this.programdeclarations=programdeclarations;
        if(programdeclarations!=null) programdeclarations.setParent(this);
        this.interfacedeclaration=interfacedeclaration;
        if(interfacedeclaration!=null) interfacedeclaration.setParent(this);
    }

    public Programdeclarations getProgramdeclarations() {
        return programdeclarations;
    }

    public void setProgramdeclarations(Programdeclarations programdeclarations) {
        this.programdeclarations=programdeclarations;
    }

    public Interfacedeclaration getInterfacedeclaration() {
        return interfacedeclaration;
    }

    public void setInterfacedeclaration(Interfacedeclaration interfacedeclaration) {
        this.interfacedeclaration=interfacedeclaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(programdeclarations!=null) programdeclarations.accept(visitor);
        if(interfacedeclaration!=null) interfacedeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(programdeclarations!=null) programdeclarations.traverseTopDown(visitor);
        if(interfacedeclaration!=null) interfacedeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(programdeclarations!=null) programdeclarations.traverseBottomUp(visitor);
        if(interfacedeclaration!=null) interfacedeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramdeclarationsDerived3(\n");

        if(programdeclarations!=null)
            buffer.append(programdeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(interfacedeclaration!=null)
            buffer.append(interfacedeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramdeclarationsDerived3]");
        return buffer.toString();
    }
}
