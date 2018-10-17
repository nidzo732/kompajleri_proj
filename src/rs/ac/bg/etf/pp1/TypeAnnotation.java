package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

import java.util.List;

public class TypeAnnotation
{
    Struct type = null;
    Obj obj = null;
    String path="";
    List<TypeAnnotation> arglist=null;
}
