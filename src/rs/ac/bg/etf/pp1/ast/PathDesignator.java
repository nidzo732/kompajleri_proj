// generated with ast extension for cup
// version 0.8
// 10/9/2018 20:35:43


package rs.ac.bg.etf.pp1.ast;

public class PathDesignator extends Designator {

    private Designator designator;
    private String val;

    public PathDesignator (Designator designator, String val) {
        this.designator=designator;
        if(designator!=null) designator.setParent(this);
        this.val=val;
    }

    public Designator getDesignator() {
        return designator;
    }

    public void setDesignator(Designator designator) {
        this.designator=designator;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val=val;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(designator!=null) designator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(designator!=null) designator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(designator!=null) designator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PathDesignator(\n");

        if(designator!=null)
            buffer.append(designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PathDesignator]");
        return buffer.toString();
    }
}
