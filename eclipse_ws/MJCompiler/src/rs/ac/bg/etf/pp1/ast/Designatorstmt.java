// generated with ast extension for cup
// version 0.8
// 28/11/2018 17:15:5


package rs.ac.bg.etf.pp1.ast;

public abstract class Designatorstmt implements SyntaxNode {

    private SyntaxNode parent;

    private int line;

    public rs.ac.bg.etf.pp1.CompilerAnnotation compilerannotation = null;

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

    public abstract void accept(Visitor visitor);
    public abstract void childrenAccept(Visitor visitor);
    public abstract void traverseTopDown(Visitor visitor);
    public abstract void traverseBottomUp(Visitor visitor);

    public String toString() { return toString(""); }
    public abstract String toString(String tab);
}
