// generated with ast extension for cup
// version 0.8
// 10/9/2018 20:35:43


package rs.ac.bg.etf.pp1.ast;

public class Else extends Statement {

    private Statement statement;

    public Else (Statement statement) {
        this.statement=statement;
        if(statement!=null) statement.setParent(this);
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
        if(statement!=null) statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(statement!=null) statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(statement!=null) statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Else(\n");

        if(statement!=null)
            buffer.append(statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Else]");
        return buffer.toString();
    }
}
