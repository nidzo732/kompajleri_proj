package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

class TableWrapper
{
    private static Stack<Obj> scopeStack = new Stack<>();
    private static boolean inMethodHeader = false;
    private static Struct currentType = null;

    static void init()
    {
        Tab.init();
        Tab.insert(Obj.Type, "bool", new Struct(Struct.Bool));

        for(String fn: new String[]{"read", "print"})
        {
            for(String typ: new String[]{"int"})
            {
                setActiveType(typ);
                declareFunction(fn, true);
                declareVariable("v", false);
                methodHeaderDone();
                TableWrapper.closeScope();
            }
        }
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
        Obj scope = scopeStack.pop();
        scope.setLocals(Tab.currentScope().getLocals());
        Tab.closeScope();
    }

    private static boolean declareName(int kind, String name, Struct type)
    {
        int cnt1 = Tab.currentScope().values().size();
        Tab.insert(kind, name, type);
        int cnt2 = Tab.currentScope().values().size();
        if (!scopeStack.empty() && scopeStack.peek().getType().getKind() == Struct.Class)
        {
            scopeStack.peek().getType().setMembers(Tab.currentScope().getLocals());
        }
        return true;
    }

    static Struct getType(String name)
    {
        Obj obj = Tab.find(name);
        if (obj.getKind() == Obj.Type)
        {
            return obj.getType();
        }
        return null;
    }

    static boolean setActiveType(String type)
    {
        currentType = getType(type);
        return currentType != null;
    }

    static Struct getActiveType()
    {
        return currentType;
    }

    static void closeClassDeclarationBlock(Collection<Obj> baseClassMembers)
    {
        for(Obj baseMember: baseClassMembers)
        {
            Tab.currentScope().addToLocals(baseMember);
        }
    }

    static boolean declareVariable(String name, boolean isArray)
    {
        int kind = (scopeStack.peek().getKind() == Obj.Type) ? Obj.Fld : Obj.Var;
        if (inMethodHeader)
        {
            scopeStack.peek().setLevel(scopeStack.peek().getLevel() + 1);
        }
        if (isArray)
        {
            Struct arrayStruct = new Struct(Struct.Array, currentType);
            return declareName(kind, name, arrayStruct);
        }
        else
        {
            return declareName(kind, name, currentType);
        }
    }

    static boolean declareConstant(String name, boolean value)
    {
        boolean ok = declareName(Obj.Con, name, currentType);
        if(!ok) return false;
        getSymbol(name).setAdr(value?1:0);
        return true;
    }

    static boolean declareConstant(String name, int value)
    {
        boolean ok = declareName(Obj.Con, name, currentType);
        if(!ok) return false;
        getSymbol(name).setAdr(value);
        return true;
    }

    static boolean declareConstant(String name, char value)
    {
        boolean ok = declareName(Obj.Con, name, currentType);
        if(!ok) return false;
        getSymbol(name).setAdr((int)value);
        return true;
    }

    static Obj getCurrentFunction()
    {
        if(scopeStack.peek().getKind()==Obj.Meth)
        {
            return scopeStack.peek();
        }
        else
        {
            return null;
        }
    }

    static boolean declareFunction(String name, boolean isVoid)
    {
        Struct curentClass=null;
        if(!scopeStack.empty() && scopeStack.peek()!=null && scopeStack.peek().getKind()==Obj.Type)
        {
            curentClass=scopeStack.peek().getType();
        }
        Struct type = (isVoid) ? Tab.noType : currentType;
        boolean result = declareName(Obj.Meth, name, type);
        if(!result)
        {
            //TODO: handle overrides
            return !scopeStack.empty() && scopeStack.peek() != null && scopeStack.peek().getKind() == Obj.Type;
        }
        Obj obj=Tab.find(name);
        obj.setLevel(0);
        scopeStack.push(obj);
        Tab.openScope();
        inMethodHeader = true;
        if(curentClass!=null)
        {
            Tab.insert(Obj.Var, "this", curentClass);
        }
        return true;
    }

    static void methodHeaderDone()
    {
        inMethodHeader = false;
    }

    static Obj getSymbol(String name)
    {
        Obj obj = Tab.find(name);
        return obj == Tab.noObj ? null : obj;
    }

}
