// generated with ast extension for cup
// version 0.8
// 9/9/2018 22:58:35


package rs.ac.bg.etf.pp1.ast;

public class DoWhile extends Statement {

    private Statement statement;
    private Condexpr condexpr;

    public DoWhile (Statement statement, Condexpr condexpr) {
        this.statement=statement;
        if(statement!=null) statement.setParent(this);
        this.condexpr=condexpr;
        if(condexpr!=null) condexpr.setParent(this);
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement=statement;
    }

    public Condexpr getCondexpr() {
        return condexpr;
    }

    public void setCondexpr(Condexpr condexpr) {
        this.condexpr=condexpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(statement!=null) statement.accept(visitor);
        if(condexpr!=null) condexpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(statement!=null) statement.traverseTopDown(visitor);
        if(condexpr!=null) condexpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(statement!=null) statement.traverseBottomUp(visitor);
        if(condexpr!=null) condexpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DoWhile(\n");

        if(statement!=null)
            buffer.append(statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(condexpr!=null)
            buffer.append(condexpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DoWhile]");
        return buffer.toString();
    }
}
