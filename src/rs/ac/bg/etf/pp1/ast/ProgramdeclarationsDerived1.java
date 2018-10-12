// generated with ast extension for cup
// version 0.8
// 12/9/2018 12:24:49


package rs.ac.bg.etf.pp1.ast;

public class ProgramdeclarationsDerived1 extends Programdeclarations {

    public ProgramdeclarationsDerived1 () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramdeclarationsDerived1(\n");

        buffer.append(tab);
        buffer.append(") [ProgramdeclarationsDerived1]");
        return buffer.toString();
    }
}
