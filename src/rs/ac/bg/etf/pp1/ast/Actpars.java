// generated with ast extension for cup
// version 0.8
// 15/11/2018 5:57:14


package rs.ac.bg.etf.pp1.ast;

public abstract class Actpars implements SyntaxNode {

    private SyntaxNode parent;

    private int line;

    public rs.ac.bg.etf.pp1.TypeAnnotation typeannotation = null;

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
