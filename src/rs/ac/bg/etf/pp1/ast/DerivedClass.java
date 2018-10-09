// generated with ast extension for cup
// version 0.8
// 9/9/2018 22:58:35


package rs.ac.bg.etf.pp1.ast;

public class DerivedClass extends Classdeclaration {

    private Classname classname;
    private Extendsdeclaration extendsdeclaration;
    private Declarationblock declarationblock;
    private Codebody codebody;

    public DerivedClass (Classname classname, Extendsdeclaration extendsdeclaration, Declarationblock declarationblock, Codebody codebody) {
        this.classname=classname;
        if(classname!=null) classname.setParent(this);
        this.extendsdeclaration=extendsdeclaration;
        if(extendsdeclaration!=null) extendsdeclaration.setParent(this);
        this.declarationblock=declarationblock;
        if(declarationblock!=null) declarationblock.setParent(this);
        this.codebody=codebody;
        if(codebody!=null) codebody.setParent(this);
    }

    public Classname getClassname() {
        return classname;
    }

    public void setClassname(Classname classname) {
        this.classname=classname;
    }

    public Extendsdeclaration getExtendsdeclaration() {
        return extendsdeclaration;
    }

    public void setExtendsdeclaration(Extendsdeclaration extendsdeclaration) {
        this.extendsdeclaration=extendsdeclaration;
    }

    public Declarationblock getDeclarationblock() {
        return declarationblock;
    }

    public void setDeclarationblock(Declarationblock declarationblock) {
        this.declarationblock=declarationblock;
    }

    public Codebody getCodebody() {
        return codebody;
    }

    public void setCodebody(Codebody codebody) {
        this.codebody=codebody;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(classname!=null) classname.accept(visitor);
        if(extendsdeclaration!=null) extendsdeclaration.accept(visitor);
        if(declarationblock!=null) declarationblock.accept(visitor);
        if(codebody!=null) codebody.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(classname!=null) classname.traverseTopDown(visitor);
        if(extendsdeclaration!=null) extendsdeclaration.traverseTopDown(visitor);
        if(declarationblock!=null) declarationblock.traverseTopDown(visitor);
        if(codebody!=null) codebody.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(classname!=null) classname.traverseBottomUp(visitor);
        if(extendsdeclaration!=null) extendsdeclaration.traverseBottomUp(visitor);
        if(declarationblock!=null) declarationblock.traverseBottomUp(visitor);
        if(codebody!=null) codebody.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DerivedClass(\n");

        if(classname!=null)
            buffer.append(classname.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(extendsdeclaration!=null)
            buffer.append(extendsdeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(declarationblock!=null)
            buffer.append(declarationblock.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(codebody!=null)
            buffer.append(codebody.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DerivedClass]");
        return buffer.toString();
    }
}
