package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

import java.util.*;

public class TableWrapper {
    private static Stack<Obj> scopeStack = new Stack<>();
    private static boolean inMethodHeader=false;
    private static Struct currentType=null;

    static void init()
    {
        Tab.init();
        Tab.insert(Obj.Type, "bool", new Struct(Struct.Bool));
    }
    static void openScope(int kind, String name, Struct type)
    {
        scopeStack.push(Tab.insert(kind, name, type));
        Tab.openScope();
    }

    static void openClassScope(String name)
    {
        scopeStack.push(Tab.insert(Obj.Type, name, new Struct(Struct.Class)));
        Tab.openScope();
    }

    static void closeScope()
    {
        Obj scope=scopeStack.pop();
        scope.setLocals(Tab.currentScope().getLocals());
        Tab.closeScope();
    }

    private static Obj declareName(int kind, String name, Struct type)
    {
        Obj obj = Tab.insert(kind, name, type);
        if(scopeStack.peek().getType().getKind()==Struct.Class)
        {
            scopeStack.peek().getType().setMembers(Tab.currentScope().getLocals());
        }
        return obj;
    }

    public static Struct getType(String name)
    {
        Obj obj = Tab.find(name);
        if(obj.getKind()==Obj.Type)
        {
            return obj.getType();
        }
        System.err.println("Unknown type: "+name);
        return null;
    }

    static void setActiveType(String type)
    {
        currentType = getType(type);
    }
    static Obj declareVariable(String name, boolean isArray)
    {
        int kind = (scopeStack.peek().getKind()==Obj.Type)?Obj.Fld:Obj.Var;
        if(inMethodHeader)
        {
            scopeStack.peek().setLevel(scopeStack.peek().getLevel()+1);
        }
        if(isArray)
        {
            Struct arrayStruct = new Struct(Struct.Array, currentType);
            declareName(kind, name, arrayStruct);
            return Tab.insert(kind, name, arrayStruct);
        }
        else
        {
            return declareName(kind, name, currentType);
        }
    }
    static Obj declareFunction(String name, boolean isVoid)
    {
        Struct type=(isVoid)?Tab.noType:currentType;
        Obj obj = declareName(Obj.Meth, name, type);
        obj.setLevel(0);
        scopeStack.push(obj);
        Tab.openScope();
        inMethodHeader = true;
        return obj;
    }

    static void methodHeaderDone()
    {
        inMethodHeader = false;
    }

}
