// generated with ast extension for cup
// version 0.8
// 17/9/2018 23:25:43


package rs.ac.bg.etf.pp1.ast;

public class DerivedClass extends Classdeclaration {

    private Classname classname;
    private Extendsdeclaration extendsdeclaration;
    private Declarationblockwrapper declarationblockwrapper;
    private Codebody codebody;

    public DerivedClass (Classname classname, Extendsdeclaration extendsdeclaration, Declarationblockwrapper declarationblockwrapper, Codebody codebody) {
        this.classname=classname;
        if(classname!=null) classname.setParent(this);
        this.extendsdeclaration=extendsdeclaration;
        if(extendsdeclaration!=null) extendsdeclaration.setParent(this);
        this.declarationblockwrapper=declarationblockwrapper;
        if(declarationblockwrapper!=null) declarationblockwrapper.setParent(this);
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

    public Declarationblockwrapper getDeclarationblockwrapper() {
        return declarationblockwrapper;
    }

    public void setDeclarationblockwrapper(Declarationblockwrapper declarationblockwrapper) {
        this.declarationblockwrapper=declarationblockwrapper;
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
        if(declarationblockwrapper!=null) declarationblockwrapper.accept(visitor);
        if(codebody!=null) codebody.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(classname!=null) classname.traverseTopDown(visitor);
        if(extendsdeclaration!=null) extendsdeclaration.traverseTopDown(visitor);
        if(declarationblockwrapper!=null) declarationblockwrapper.traverseTopDown(visitor);
        if(codebody!=null) codebody.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(classname!=null) classname.traverseBottomUp(visitor);
        if(extendsdeclaration!=null) extendsdeclaration.traverseBottomUp(visitor);
        if(declarationblockwrapper!=null) declarationblockwrapper.traverseBottomUp(visitor);
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

        if(declarationblockwrapper!=null)
            buffer.append(declarationblockwrapper.toString("  "+tab));
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
