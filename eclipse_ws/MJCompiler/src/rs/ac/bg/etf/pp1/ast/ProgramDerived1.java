// generated with ast extension for cup
// version 0.8
// 28/11/2018 17:15:5


package rs.ac.bg.etf.pp1.ast;

public class ProgramDerived1 extends Program {

    private Programname programname;
    private Programdeclarations programdeclarations;
    private Codebody codebody;

    public ProgramDerived1 (Programname programname, Programdeclarations programdeclarations, Codebody codebody) {
        this.programname=programname;
        if(programname!=null) programname.setParent(this);
        this.programdeclarations=programdeclarations;
        if(programdeclarations!=null) programdeclarations.setParent(this);
        this.codebody=codebody;
        if(codebody!=null) codebody.setParent(this);
    }

    public Programname getProgramname() {
        return programname;
    }

    public void setProgramname(Programname programname) {
        this.programname=programname;
    }

    public Programdeclarations getProgramdeclarations() {
        return programdeclarations;
    }

    public void setProgramdeclarations(Programdeclarations programdeclarations) {
        this.programdeclarations=programdeclarations;
    }

    public Codebody getCodebody() {
        return codebody;
    }

    public void setCodebody(Codebody codebody) {
        this.codebody=codebody;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(programname!=null) programname.accept(visitor);
        if(programdeclarations!=null) programdeclarations.accept(visitor);
        if(codebody!=null) codebody.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(programname!=null) programname.traverseTopDown(visitor);
        if(programdeclarations!=null) programdeclarations.traverseTopDown(visitor);
        if(codebody!=null) codebody.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(programname!=null) programname.traverseBottomUp(visitor);
        if(programdeclarations!=null) programdeclarations.traverseBottomUp(visitor);
        if(codebody!=null) codebody.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramDerived1(\n");

        if(programname!=null)
            buffer.append(programname.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(programdeclarations!=null)
            buffer.append(programdeclarations.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(codebody!=null)
            buffer.append(codebody.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramDerived1]");
        return buffer.toString();
    }
}
