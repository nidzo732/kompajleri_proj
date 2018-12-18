package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;

import java.util.List;

public class CompilerAnnotation
{
    Struct type = null;
    Obj obj = null;
    String path="";
    List<CompilerAnnotation> arglist;
    Scope scope;
    boolean valueOnStack=false;
    Integer start;

    public void setStart(Integer start)
    {
        if(start==null) throw new RuntimeException("Missing start");
        this.start = start;
    }

    public void setEnd(Integer end)
    {
        if(start==null) throw new RuntimeException("Missing end");
        this.end = end;
    }

    public void setSize(Integer size)
    {
        if(start==null) throw new RuntimeException("Missing size");
        this.size = size;
    }

    Integer end;
    Integer size;
}
