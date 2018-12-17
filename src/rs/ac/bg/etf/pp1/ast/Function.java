// generated with ast extension for cup
// version 0.8
// 17/11/2018 21:40:55


package rs.ac.bg.etf.pp1.ast;

public class Function implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.ac.bg.etf.pp1.CompilerAnnotation compilerannotation = null;

    private Functionheader functionheader;
    private Functiondeclarationblock functiondeclarationblock;
    private Statementblock statementblock;

    public Function (Functionheader functionheader, Functiondeclarationblock functiondeclarationblock, Statementblock statementblock) {
        this.functionheader=functionheader;
        if(functionheader!=null) functionheader.setParent(this);
        this.functiondeclarationblock=functiondeclarationblock;
        if(functiondeclarationblock!=null) functiondeclarationblock.setParent(this);
        this.statementblock=statementblock;
        if(statementblock!=null) statementblock.setParent(this);
    }

    public Functionheader getFunctionheader() {
        return functionheader;
    }

    public void setFunctionheader(Functionheader functionheader) {
        this.functionheader=functionheader;
    }

    public Functiondeclarationblock getFunctiondeclarationblock() {
        return functiondeclarationblock;
    }

    public void setFunctiondeclarationblock(Functiondeclarationblock functiondeclarationblock) {
        this.functiondeclarationblock=functiondeclarationblock;
    }

    public Statementblock getStatementblock() {
        return statementblock;
    }

    public void setStatementblock(Statementblock statementblock) {
        this.statementblock=statementblock;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(functionheader!=null) functionheader.accept(visitor);
        if(functiondeclarationblock!=null) functiondeclarationblock.accept(visitor);
        if(statementblock!=null) statementblock.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(functionheader!=null) functionheader.traverseTopDown(visitor);
        if(functiondeclarationblock!=null) functiondeclarationblock.traverseTopDown(visitor);
        if(statementblock!=null) statementblock.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(functionheader!=null) functionheader.traverseBottomUp(visitor);
        if(functiondeclarationblock!=null) functiondeclarationblock.traverseBottomUp(visitor);
        if(statementblock!=null) statementblock.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Function(\n");

        if(functionheader!=null)
            buffer.append(functionheader.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(functiondeclarationblock!=null)
            buffer.append(functiondeclarationblock.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(statementblock!=null)
            buffer.append(statementblock.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Function]");
        return buffer.toString();
    }
}
