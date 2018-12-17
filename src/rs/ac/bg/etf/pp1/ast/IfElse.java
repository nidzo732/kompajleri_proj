// generated with ast extension for cup
// version 0.8
// 17/11/2018 21:40:55


package rs.ac.bg.etf.pp1.ast;

public class IfElse extends Statement {

    private Jmpcond jmpcond;
    private Statement statement;
    private Elsewrapper elsewrapper;
    private Statement statement1;

    public IfElse (Jmpcond jmpcond, Statement statement, Elsewrapper elsewrapper, Statement statement1) {
        this.jmpcond=jmpcond;
        if(jmpcond!=null) jmpcond.setParent(this);
        this.statement=statement;
        if(statement!=null) statement.setParent(this);
        this.elsewrapper=elsewrapper;
        if(elsewrapper!=null) elsewrapper.setParent(this);
        this.statement1=statement1;
        if(statement1!=null) statement1.setParent(this);
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

    public Elsewrapper getElsewrapper() {
        return elsewrapper;
    }

    public void setElsewrapper(Elsewrapper elsewrapper) {
        this.elsewrapper=elsewrapper;
    }

    public Statement getStatement1() {
        return statement1;
    }

    public void setStatement1(Statement statement1) {
        this.statement1=statement1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(jmpcond!=null) jmpcond.accept(visitor);
        if(statement!=null) statement.accept(visitor);
        if(elsewrapper!=null) elsewrapper.accept(visitor);
        if(statement1!=null) statement1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(jmpcond!=null) jmpcond.traverseTopDown(visitor);
        if(statement!=null) statement.traverseTopDown(visitor);
        if(elsewrapper!=null) elsewrapper.traverseTopDown(visitor);
        if(statement1!=null) statement1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(jmpcond!=null) jmpcond.traverseBottomUp(visitor);
        if(statement!=null) statement.traverseBottomUp(visitor);
        if(elsewrapper!=null) elsewrapper.traverseBottomUp(visitor);
        if(statement1!=null) statement1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfElse(\n");

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

        if(elsewrapper!=null)
            buffer.append(elsewrapper.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(statement1!=null)
            buffer.append(statement1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfElse]");
        return buffer.toString();
    }
}
