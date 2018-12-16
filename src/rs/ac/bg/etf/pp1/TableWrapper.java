package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.Array;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;

import java.util.*;

class TableWrapper
{
    private static HashMap<Obj, List<Obj>> methodParams=new HashMap<>();
    private static Set<Obj> classMethods=new HashSet<>();
    private static Map<Obj, Obj> methodToClassMap=new HashMap<>();
    private static Stack<Obj> scopeStack = new Stack<>();
    public static Map<String,List<String>> VTPData = new HashMap();
    private static Stack<Integer> adrStack=new Stack<>();
    public static Obj main=null;
    private static int globMethAdr=0;
    private static int noOfClasses=0;
    private static boolean inMethodHeader = false;
    private static Struct currentType = null;
    private static Obj currentTypeObject=null;
    public static int staticMemorySize=0;
    static int fpAddr=0;
    static boolean inClass=false;

    static void init()
    {
        Tab.init();
        Tab.insert(Obj.Type, "bool", new Struct(Struct.Bool));
    }
    static boolean inFunction()
    {
        return scopeStack.size()>0 && scopeStack.peek().getKind()==Obj.Meth;
    }
    static Obj getCurrentFunction()
    {
        if(inFunction()) return scopeStack.peek();
        else return null;
    }
    static int getNextAdr()
    {
        int adr=adrStack.pop();
        adrStack.push(adr+1);
        return adr;
    }
    static int nextMethAdr()
    {
        return globMethAdr++;
    }
    static Scope openScope(Obj scopeObject)
    {
        //for(int i=0;i<scopeStack.size();i++) System.out.print("    ");
        //System.out.println(scopeObject.getName());
        scopeStack.push(scopeObject);
        adrStack.push(0);
        Tab.openScope();
        return Tab.currentScope();
    }
    static Obj closeScope()
    {
        Obj scope = scopeStack.pop();
        if(scope.getKind()==Obj.Meth && scope.getName().equals("main") && scope.getLevel()==0 && scope.getType()==Tab.noType)
        {
            main=scope;
        }
        if(scope.getKind()==Obj.Type)
        {
            inClass=false;
            currentType=null;
            currentTypeObject=null;
        }
        //for(int i=0;i<scopeStack.size();i++) System.out.print("    ");
        //System.out.println(scope.getName());
        if(scope.getKind()==Obj.Type)
        {
            scope.getType().setMembers(Tab.currentScope().getLocals());
        }
        else
        {
            scope.setLocals(Tab.currentScope().getLocals());
        }
        adrStack.pop();
        Tab.closeScope();
        return scope;
    }
    static Obj openProgramScope(String name)
    {
        Obj programObject = tabInsert(Obj.Prog, name, Tab.noType);
        openScope(programObject);
        return programObject;
    }

    static Obj openClassScope(String name)
    {
        if(Tab.currentScope().findSymbol(name)!=null) return null;
        Obj classObject = tabInsert(Obj.Type, name, new Struct(Struct.Class));
        openScope(classObject);
        classObject.setAdr(++noOfClasses);
        inClass=true;
        currentType=classObject.getType();
        currentTypeObject=classObject;
        declareVariable("$clsid", Tab.intType);
        return classObject;
    }
    static Obj openInterfaceScope(String name)
    {
        if(Tab.currentScope().findSymbol(name)!=null) return null;
        Obj interfaceObject = tabInsert(Obj.Type, name, new Struct(Struct.Interface));
        openScope(interfaceObject);
        interfaceObject.setAdr(++noOfClasses);
        currentType=interfaceObject.getType();
        currentTypeObject=interfaceObject;
        inClass=true;
        return interfaceObject;
    }
    static Obj openEnumScope(String name)
    {
        if(Tab.currentScope().findSymbol(name)!=null) return null;
        Obj enumObject = tabInsert(Obj.Type, name, new Struct(Struct.Enum));
        openScope(enumObject);
        return enumObject;
    }

    static Obj declareEnumConstant(String name, int value)
    {
        if(Tab.currentScope().findSymbol(name)!=null) return null;
        Obj enumConstant = tabInsert(Obj.Con, name, scopeStack.peek().getType());
        enumConstant.setAdr(value);
        return enumConstant;
    }
    private static Obj oldBaseFunction;
    static Obj openFunctionScope(String name, Struct type)
    {
        Obj oldSymbol=Tab.currentScope().findSymbol(name);
        if(oldSymbol!=null)
        {
            if(oldSymbol.getKind()!=Obj.Meth)
            {
                return null;
            }
            Tab.currentScope().getLocals().deleteKey(name);
        }
        oldBaseFunction=oldSymbol;
        Obj function = tabInsert(Obj.Meth, name, type);
        openScope(function);
        function.setAdr(nextMethAdr());
        inMethodHeader=true;
        fpAddr=0;
        if(inClass)
        {
            declareVariable("this", currentType);
            classMethods.add(function);
            methodToClassMap.put(function, currentTypeObject);
        }
        methodParams.put(function, new ArrayList<>());
        return function;
    }
    static Obj declareVariable(String name, Struct type)
    {
        int kind;
        if(scopeStack.peek().getKind()==Obj.Type)
        {
            if(Tab.currentScope().findSymbol(name)!=null)
            {
                Tab.currentScope().getLocals().deleteKey(name);
                return declareVariable(name, type);
            }
            kind=Obj.Fld;
        }
        else
        {
            if(Tab.currentScope().findSymbol(name)!=null) return null;
            kind=Obj.Var;
        }
        Obj variable=tabInsert(kind, name, type);
        if(scopeStack.peek().getKind()==Obj.Type)
        {
            variable.setAdr(getNextAdr());
        }
        else if(scopeStack.peek().getKind()==Obj.Prog)
        {
            variable.setAdr(staticMemorySize++);
        }
        else if(scopeStack.peek().getKind()==Obj.Meth)
        {
            if(inMethodHeader)
            {
                variable.setFpPos(fpAddr++);
                if(!(fpAddr==1 && name.equals("this") && inClass))
                {
                    methodParams.get(scopeStack.peek()).add(variable);
                }
            }
            variable.setAdr(getNextAdr());
        }
        return variable;
    }

    static Obj declareConstant(String name, Struct type, int value)
    {
        if(Tab.find(name)!=Tab.noObj) return null;
        Obj con = tabInsert(Obj.Con, name,type);
        con.setAdr(value);
        return con;
    }
    static Obj tabInsert(int kind, String name, Struct type)
    {
        Obj obj=Tab.insert(kind, name, type);
        if(scopeStack.size()>0)
        {
            if(scopeStack.peek().getKind()==Obj.Type)
            {
                scopeStack.peek().getType().getMembersTable().insertKey(obj);
            }
        }
        return obj;
    }

    static void functionHeaderDone()
    {
        inMethodHeader=false;
        if(Tab.currentScope().getLocals()!=null)
        {
            scopeStack.peek().setLevel(Tab.currentScope().getLocals().numSymbols());
            if(inClass) scopeStack.peek().setLevel(Tab.currentScope().getLocals().numSymbols()-1);
        }
    }

    static void setBaseClass(Struct base)
    {
        scopeStack.peek().getType().setElementType(base);
        int maxFieldAddr=0;
        for(Obj symbol:base.getMembersTable().symbols())
        {
            if(symbol.getName().equals("$clsid")) continue;
            Tab.currentScope().addToLocals(symbol);
            currentType.getMembersTable().insertKey(symbol);
            if(symbol.getKind()==Obj.Fld)
            {
                maxFieldAddr=Math.max(symbol.getAdr(), maxFieldAddr);
            }
        }
        scopeStack.peek().getType().getImplementedInterfaces().addAll(base.getImplementedInterfaces());
        adrStack.pop();
        adrStack.push(maxFieldAddr+1);
    }
    static void addImplementedInterface(Struct interf)
    {
        scopeStack.peek().getType().addImplementedInterface(interf);
    }
    static Struct getType(String name)
    {
        Obj typeObject=getSymbol(name);
        if(typeObject==null) return null;
        else return typeObject.getType();
    }
    static boolean validateAgainstBase()
    {
        try
        {
            if (oldBaseFunction == null) return true;
            return compatibleMethods(oldBaseFunction, scopeStack.peek());
        }
        finally
        {
            oldBaseFunction=null;
        }
    }
    static boolean validateImplementation()
    {
        for (Struct implementedInterface:scopeStack.peek().getType().getImplementedInterfaces())
        {
            for (Obj method:implementedInterface.getMembers())
            {
                Obj ourMethod=Tab.currentScope().findSymbol(method.getName());
                if(ourMethod==null) return false;
                if(!compatibleMethods(method, ourMethod)) return false;
            }
        }
        return true;
    }
    static boolean compatibleMethods(Obj m1, Obj m2)
    {
        if(m1.getKind()!=Obj.Meth || m2.getKind()!=Obj.Meth) return false;
        List<Obj> params1 = methodParams.get(m1);
        List<Obj> params2 = methodParams.get(m2);
        if (params1.size() != params2.size()) return false;
        if (!redefinitionCompatible(m1.getType(), m2.getType())) return false;
        for (int i = 0; i < params1.size(); i++)
        {
            Obj p1 = params1.get(i);
            Obj p2 = params2.get(i);
            if(!redefinitionCompatible(p1.getType(), p2.getType())) return false;
        }
        return true;
    }
    static List<Integer> callCompatible(Obj method, List<Struct> types)
    {
        List<Integer> errors=new ArrayList<>();
        if(method==getSymbol("ord"))
        {
            if(types.size()==1 && types.get(0).getKind()==Struct.Char) return errors;
            errors.add(0);
            return errors;
        }
        if(method==getSymbol("chr"))
        {
            if(types.size()==1 && assignmentCompatible("int", types.get(0))) return errors;
            errors.add(0);
            return errors;
        }
        if(method==getSymbol("len"))
        {
            if(types.size()==1 && types.get(0).getKind()==Struct.Array) return errors;
            errors.add(0);
            return errors;
        }
        List<Obj> parTypes=methodParams.get(method);
        if(parTypes.size()!=types.size()) return new ArrayList<>();
        for(int i=0;i<types.size();i++)
        {
            if(!assignmentCompatible(parTypes.get(i).getType(), types.get(i)))
            {
                errors.add(i);
            }
        }
        return errors;
    }
    static boolean redefinitionCompatible(Struct s1, Struct s2)
    {
        return s1==s2
                || ( s1.getKind()==Struct.Array && s2.getKind()==Struct.Array && redefinitionCompatible(s1.getElemType(), s2.getElemType()));
    }
    static boolean assignmentCompatible(Struct dst, String src)
    {
        Struct srcS=getType(src);
        if(srcS==null) return false;
        return assignmentCompatible(dst, srcS);
    }
    static boolean assignmentCompatible(String dst, Struct src)
    {
        Struct dstS=getType(dst);
        if(dstS==null) return false;
        return assignmentCompatible(dstS, src);
    }
    static boolean assignmentCompatible(Struct dst, Struct src)
    {
        if(src==null || dst==null) return false;
        if(src.assignableTo(dst)) return true;
        if(dst.getKind()==Struct.Int && src.getKind()==Struct.Enum) return true;
        if(dst.getKind()==Struct.Array && src.getKind()==Struct.Array) return assignmentCompatible(dst.getElemType(), src.getElemType());
        if(dst.getKind()==Struct.Interface && src.getKind()==Struct.Class && src.getImplementedInterfaces().contains(dst)) return true;
        if(dst.getKind()==Struct.Class && src.getKind()==Struct.Class)
        {
            while(src.getElemType()!=null)
            {
                if(src.getElemType().assignableTo(dst)) return true;
                src=src.getElemType();
            }
        }
        return false;
    }
    static Obj getSymbol(String name)
    {
        Obj obj=Tab.find(name);
        if(obj==Tab.noObj) return null;
        else return obj;
    }
    static String nodeToString(Obj obj)
    {
        if(obj==Tab.noObj) return "Symbol not found";
        DumpSymbolTableVisitor dstv=new DumpSymbolTableVisitor();
        dstv.visitObjNode(obj);
        return dstv.getOutput();
    }
    static void dumpVtpData(Obj classObject)
    {
        if(classObject.getKind()!=Obj.Type && classObject.getType().getKind()!=Struct.Class) throw new RuntimeException();
        classObject.setAdr(staticMemorySize);
        Struct type=classObject.getType();
        VTPData.put(classObject.getName(), new ArrayList<>());
        for(Obj member:type.getMembers())
        {
            if(member.getKind()==Obj.Meth)
            {
                staticMemorySize+=member.getName().length()+2;
                VTPData.get(classObject.getName()).add(member.getName());
            }
        }
        staticMemorySize++;
    }
    static String nodeToString(Struct obj)
    {
        DumpSymbolTableVisitor dstv=new DumpSymbolTableVisitor();
        dstv.visitStructNode(obj);
        return dstv.getOutput();
    }
    static String nodeToString(Scope obj)
    {
        DumpSymbolTableVisitor dstv=new DumpSymbolTableVisitor();
        dstv.visitScopeNode(obj);
        return dstv.getOutput();
    }
    static int getTypeSize(Struct type)
    {
        int sz=0;
        for (Obj member:type.getMembers())
        {
            if(member.getKind()==Obj.Fld) sz++;
        }
        return sz;
    }
    public static boolean isClassMethod(Obj function)
    {
        return classMethods.contains(function);
    }

    /*static void startCompiling(String programName)
    {
        Obj obj = Tab.find(programName);
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
    }*/

}
