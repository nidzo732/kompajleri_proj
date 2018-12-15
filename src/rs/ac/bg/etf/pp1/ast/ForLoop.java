// generated with ast extension for cup
// version 0.8
// 15/11/2018 5:57:14


package rs.ac.bg.etf.pp1.ast;

public class ForLoop extends Statement {

    private For1 for1;
    private For2 for2;
    private For3 for3;
    private Statement statement;

    public ForLoop (For1 for1, For2 for2, For3 for3, Statement statement) {
        this.for1=for1;
        if(for1!=null) for1.setParent(this);
        this.for2=for2;
        if(for2!=null) for2.setParent(this);
        this.for3=for3;
        if(for3!=null) for3.setParent(this);
        this.statement=statement;
        if(statement!=null) statement.setParent(this);
    }

    public For1 getFor1() {
        return for1;
    }

    public void setFor1(For1 for1) {
        this.for1=for1;
    }

    public For2 getFor2() {
        return for2;
    }

    public void setFor2(For2 for2) {
        this.for2=for2;
    }

    public For3 getFor3() {
        return for3;
    }

    public void setFor3(For3 for3) {
        this.for3=for3;
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
        if(for1!=null) for1.accept(visitor);
        if(for2!=null) for2.accept(visitor);
        if(for3!=null) for3.accept(visitor);
        if(statement!=null) statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(for1!=null) for1.traverseTopDown(visitor);
        if(for2!=null) for2.traverseTopDown(visitor);
        if(for3!=null) for3.traverseTopDown(visitor);
        if(statement!=null) statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(for1!=null) for1.traverseBottomUp(visitor);
        if(for2!=null) for2.traverseBottomUp(visitor);
        if(for3!=null) for3.traverseBottomUp(visitor);
        if(statement!=null) statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForLoop(\n");

        if(for1!=null)
            buffer.append(for1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(for2!=null)
            buffer.append(for2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(for3!=null)
            buffer.append(for3.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(statement!=null)
            buffer.append(statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForLoop]");
        return buffer.toString();
    }
}
