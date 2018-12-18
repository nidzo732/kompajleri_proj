// generated with ast extension for cup
// version 0.8
// 18/11/2018 13:27:0


package rs.ac.bg.etf.pp1.ast;

public class If extends Statement {

    private Jmpcond jmpcond;
    private Statement statement;

    public If (Jmpcond jmpcond, Statement statement) {
        this.jmpcond=jmpcond;
        if(jmpcond!=null) jmpcond.setParent(this);
        this.statement=statement;
        if(statement!=null) statement.setParent(this);
    }

    public Jmpcond getJmpcond() {
        return jmpcond;
    }

    public void setJmpcond(Jmpcond jmpcond) {
        this.jmpcond=jmpcond;
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
        if(jmpcond!=null) jmpcond.accept(visitor);
        if(statement!=null) statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(jmpcond!=null) jmpcond.traverseTopDown(visitor);
        if(statement!=null) statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(jmpcond!=null) jmpcond.traverseBottomUp(visitor);
        if(statement!=null) statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("If(\n");

        if(jmpcond!=null)
            buffer.append(jmpcond.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(statement!=null)
            buffer.append(statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [If]");
        return buffer.toString();
    }
}
