// generated with ast extension for cup
// version 0.8
// 28/11/2018 17:15:5


package rs.ac.bg.etf.pp1.ast;

public class ProgramdeclarationsDerived6 extends Programdeclarations {

    private Programdeclarations programdeclarations;
    private Variabledeclaration variabledeclaration;

    public ProgramdeclarationsDerived6 (Programdeclarations programdeclarations, Variabledeclaration variabledeclaration) {
        this.programdeclarations=programdeclarations;
        if(programdeclarations!=null) programdeclarations.setParent(this);
        this.variabledeclaration=variabledeclaration;
        if(variabledeclaration!=null) variabledeclaration.setParent(this);
    }

    public Programdeclarations getProgramdeclarations() {
        return programdeclarations;
    }

    public void setProgramdeclarations(Programdeclarations programdeclarations) {
        this.programdeclarations=programdeclarations;
    }

    public Variabledeclaration getVariabledeclaration() {
        return variabledeclaration;
    }

    public void setVariabledeclaration(Variabledeclaration variabledeclaration) {
        this.variabledeclaration=variabledeclaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(programdeclarations!=null) programdeclarations.accept(visitor);
        if(variabledeclaration!=null) variabledeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(programdeclarations!=null) programdeclarations.traverseTopDown(visitor);
        if(variabledeclaration!=null) variabledeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(programdeclarations!=null) programdeclarations.traverseBottomUp(visitor);
        if(variabledeclaration!=null) variabledeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramdeclarationsDerived6(\n");

        if(programdeclarations!=null)
            buffer.append(programdeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(variabledeclaration!=null)
            buffer.append(variabledeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramdeclarationsDerived6]");
        return buffer.toString();
    }
}
