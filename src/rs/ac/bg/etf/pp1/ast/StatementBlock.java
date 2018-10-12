// generated with ast extension for cup
// version 0.8
// 12/9/2018 12:24:49


package rs.ac.bg.etf.pp1.ast;

public class StatementBlock extends Statementblock {

    private Statementblock statementblock;
    private Statement statement;

    public StatementBlock (Statementblock statementblock, Statement statement) {
        this.statementblock=statementblock;
        if(statementblock!=null) statementblock.setParent(this);
        this.statement=statement;
        if(statement!=null) statement.setParent(this);
    }

    public Statementblock getStatementblock() {
        return statementblock;
    }

    public void setStatementblock(Statementblock statementblock) {
        this.statementblock=statementblock;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement=statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(statementblock!=null) statementblock.accept(visitor);
        if(statement!=null) statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(statementblock!=null) statementblock.traverseTopDown(visitor);
        if(statement!=null) statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(statementblock!=null) statementblock.traverseBottomUp(visitor);
        if(statement!=null) statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementBlock(\n");

        if(statementblock!=null)
            buffer.append(statementblock.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(statement!=null)
            buffer.append(statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementBlock]");
        return buffer.toString();
    }
}
