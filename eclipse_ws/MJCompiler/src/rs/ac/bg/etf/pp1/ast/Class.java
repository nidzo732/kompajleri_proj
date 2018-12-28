// generated with ast extension for cup
// version 0.8
// 28/11/2018 16:9:49


package rs.ac.bg.etf.pp1.ast;

public class Class extends Classdeclaration {

    private Classname classname;
    private Extendsdeclaration extendsdeclaration;
    private Implementsdeclaration implementsdeclaration;
    private Declarationblockwrapper declarationblockwrapper;
    private Classcodebody classcodebody;

    public Class (Classname classname, Extendsdeclaration extendsdeclaration, Implementsdeclaration implementsdeclaration, Declarationblockwrapper declarationblockwrapper, Classcodebody classcodebody) {
        this.classname=classname;
        if(classname!=null) classname.setParent(this);
        this.extendsdeclaration=extendsdeclaration;
        if(extendsdeclaration!=null) extendsdeclaration.setParent(this);
        this.implementsdeclaration=implementsdeclaration;
        if(implementsdeclaration!=null) implementsdeclaration.setParent(this);
        this.declarationblockwrapper=declarationblockwrapper;
        if(declarationblockwrapper!=null) declarationblockwrapper.setParent(this);
        this.classcodebody=classcodebody;
        if(classcodebody!=null) classcodebody.setParent(this);
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

    public Implementsdeclaration getImplementsdeclaration() {
        return implementsdeclaration;
    }

    public void setImplementsdeclaration(Implementsdeclaration implementsdeclaration) {
        this.implementsdeclaration=implementsdeclaration;
    }

    public Declarationblockwrapper getDeclarationblockwrapper() {
        return declarationblockwrapper;
    }

    public void setDeclarationblockwrapper(Declarationblockwrapper declarationblockwrapper) {
        this.declarationblockwrapper=declarationblockwrapper;
    }

    public Classcodebody getClasscodebody() {
        return classcodebody;
    }

    public void setClasscodebody(Classcodebody classcodebody) {
        this.classcodebody=classcodebody;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(classname!=null) classname.accept(visitor);
        if(extendsdeclaration!=null) extendsdeclaration.accept(visitor);
        if(implementsdeclaration!=null) implementsdeclaration.accept(visitor);
        if(declarationblockwrapper!=null) declarationblockwrapper.accept(visitor);
        if(classcodebody!=null) classcodebody.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(classname!=null) classname.traverseTopDown(visitor);
        if(extendsdeclaration!=null) extendsdeclaration.traverseTopDown(visitor);
        if(implementsdeclaration!=null) implementsdeclaration.traverseTopDown(visitor);
        if(declarationblockwrapper!=null) declarationblockwrapper.traverseTopDown(visitor);
        if(classcodebody!=null) classcodebody.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(classname!=null) classname.traverseBottomUp(visitor);
        if(extendsdeclaration!=null) extendsdeclaration.traverseBottomUp(visitor);
        if(implementsdeclaration!=null) implementsdeclaration.traverseBottomUp(visitor);
        if(declarationblockwrapper!=null) declarationblockwrapper.traverseBottomUp(visitor);
        if(classcodebody!=null) classcodebody.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Class(\n");

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

        if(implementsdeclaration!=null)
            buffer.append(implementsdeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(declarationblockwrapper!=null)
            buffer.append(declarationblockwrapper.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(classcodebody!=null)
            buffer.append(classcodebody.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Class]");
        return buffer.toString();
    }
}
