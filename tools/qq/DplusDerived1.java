// generated with ast extension for cup
// version 0.8
// 4/9/2018 17:42:56


package qq;

public class DplusDerived1 extends Dplus {

    public DplusDerived1 () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DplusDerived1(\n");

        buffer.append(tab);
        buffer.append(") [DplusDerived1]");
        return buffer.toString();
    }
}
