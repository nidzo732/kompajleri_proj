// generated with ast extension for cup
// version 0.8
// 28/11/2018 17:15:5


package rs.ac.bg.etf.pp1.ast;

public class Block extends Statement {

    private Statementblock statementblock;

    public Block (Statementblock statementblock) {
        this.statementblock=statementblock;
        if(statementblock!=null) statementblock.setParent(this);
    }

    public Statementblock getStatementblock() {
        return statementblock;
    }

    public void setStatementblock(Statementblock statementblock) {
        this.statementblock=statementblock;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(statementblock!=null) statementblock.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(statementblock!=null) statementblock.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(statementblock!=null) statementblock.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Block(\n");

        if(statementblock!=null)
            buffer.append(statementblock.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Block]");
        return buffer.toString();
    }
}
