// generated with ast extension for cup
// version 0.8
// 17/11/2018 21:40:56


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Functiondeclarationblock functiondeclarationblock);
    public void visit(Program program);
    public void visit(Classname classname);
    public void visit(Call call);
    public void visit(Formparswrapper formparswrapper);
    public void visit(Enumname enumname);
    public void visit(Interfacename interfacename);
    public void visit(Nonemptyimplementsdeclaration nonemptyimplementsdeclaration);
    public void visit(Constnamedeclaration constnamedeclaration);
    public void visit(Designator designator);
    public void visit(Implementsdeclaration implementsdeclaration);
    public void visit(Implementedname implementedname);
    public void visit(Variabledeclaration variabledeclaration);
    public void visit(Constantdeclaration constantdeclaration);
    public void visit(Condexpr condexpr);
    public void visit(Extendsdeclaration extendsdeclaration);
    public void visit(Errsep errsep);
    public void visit(For3 for3);
    public void visit(Classcodebody classcodebody);
    public void visit(For2 for2);
    public void visit(For1 for1);
    public void visit(Classdeclaration classdeclaration);
    public void visit(Codebody codebody);
    public void visit(Statement statement);
    public void visit(Term term);
    public void visit(Interfacedeclaration interfacedeclaration);
    public void visit(Condfactor condfactor);
    public void visit(Formpars formpars);
    public void visit(Enumdeclaration enumdeclaration);
    public void visit(Whilewrapper whilewrapper);
    public void visit(Jmpcond jmpcond);
    public void visit(Enuminner enuminner);
    public void visit(Namedeclaration namedeclaration);
    public void visit(Programname programname);
    public void visit(Designatorstmt designatorstmt);
    public void visit(Enumconstant enumconstant);
    public void visit(Statementblock statementblock);
    public void visit(Functionheader functionheader);
    public void visit(Programdeclarations programdeclarations);
    public void visit(Expr expr);
    public void visit(Forwrapper forwrapper);
    public void visit(Elsewrapper elsewrapper);
    public void visit(Functionname functionname);
    public void visit(Factor factor);
    public void visit(Declarationblock declarationblock);
    public void visit(Readcall readcall);
    public void visit(Interfaceinner interfaceinner);
    public void visit(Declarationblockwrapper declarationblockwrapper);
    public void visit(Printcallw printcallw);
    public void visit(Actpars actpars);
    public void visit(Printcall printcall);
    public void visit(Emptyimplementsdeclaration emptyimplementsdeclaration);
    public void visit(Designatorline designatorline);
    public void visit(Condterm condterm);
    public void visit(PrintCallWidth PrintCallWidth);
    public void visit(PrintCall PrintCall);
    public void visit(ReadCall ReadCall);
    public void visit(EmptyParameters EmptyParameters);
    public void visit(Parameters Parameters);
    public void visit(Parameter Parameter);
    public void visit(Type Type);
    public void visit(IndexDesignator IndexDesignator);
    public void visit(PathDesignator PathDesignator);
    public void visit(BaseDesignator BaseDesignator);
    public void visit(Negated Negated);
    public void visit(CallResult CallResult);
    public void visit(Parenthesized Parenthesized);
    public void visit(NewArray NewArray);
    public void visit(NewObject NewObject);
    public void visit(SingleFactor SingleFactor);
    public void visit(BooleanConstant BooleanConstant);
    public void visit(CharacterConstant CharacterConstant);
    public void visit(NumericConstant NumericConstant);
    public void visit(FuncCall FuncCall);
    public void visit(Modulus Modulus);
    public void visit(Division Division);
    public void visit(Multiplication Multiplication);
    public void visit(SingleTerm SingleTerm);
    public void visit(Substraction Substraction);
    public void visit(Addition Addition);
    public void visit(SingleExpr SingleExpr);
    public void visit(ErrsepDerived12 ErrsepDerived12);
    public void visit(ErrsepDerived11 ErrsepDerived11);
    public void visit(ErrsepDerived10 ErrsepDerived10);
    public void visit(ErrsepDerived9 ErrsepDerived9);
    public void visit(ErrsepDerived8 ErrsepDerived8);
    public void visit(ErrsepDerived7 ErrsepDerived7);
    public void visit(ErrsepDerived6 ErrsepDerived6);
    public void visit(ErrsepDerived5 ErrsepDerived5);
    public void visit(ErrsepDerived4 ErrsepDerived4);
    public void visit(ErrsepDerived3 ErrsepDerived3);
    public void visit(ErrsepDerived2 ErrsepDerived2);
    public void visit(ErrsepDerived1 ErrsepDerived1);
    public void visit(LessEqual LessEqual);
    public void visit(Less Less);
    public void visit(GreaterEqual GreaterEqual);
    public void visit(Greater Greater);
    public void visit(NotEquals NotEquals);
    public void visit(Equals Equals);
    public void visit(SingleCondFactor SingleCondFactor);
    public void visit(And And);
    public void visit(SingleCondTerm SingleCondTerm);
    public void visit(Or Or);
    public void visit(SingleCondExpr SingleCondExpr);
    public void visit(DesignatorstmtDerived2 DesignatorstmtDerived2);
    public void visit(DesignatorstmtDerived1 DesignatorstmtDerived1);
    public void visit(Decrement Decrement);
    public void visit(Increment Increment);
    public void visit(Assign Assign);
    public void visit(DesignatorlineDerived2 DesignatorlineDerived2);
    public void visit(DesignatorlineDerived1 DesignatorlineDerived1);
    public void visit(StatementBlock StatementBlock);
    public void visit(EmptyBlock EmptyBlock);
    public void visit(JmpcondDerived1 JmpcondDerived1);
    public void visit(JmpCond JmpCond);
    public void visit(ForIncrementEmpty ForIncrementEmpty);
    public void visit(ForIncrement ForIncrement);
    public void visit(ForCondEmpty ForCondEmpty);
    public void visit(ForCond ForCond);
    public void visit(ForInitEmpty ForInitEmpty);
    public void visit(ForInit ForInit);
    public void visit(ForWrapper ForWrapper);
    public void visit(WhileWrapper WhileWrapper);
    public void visit(ElseWrapper ElseWrapper);
    public void visit(PrintStatementW PrintStatementW);
    public void visit(PrintStatement PrintStatement);
    public void visit(ReadStatement ReadStatement);
    public void visit(CallStatement CallStatement);
    public void visit(EmptyReturn EmptyReturn);
    public void visit(Return Return);
    public void visit(Continue Continue);
    public void visit(Break Break);
    public void visit(ForLoop ForLoop);
    public void visit(While While);
    public void visit(IfElse IfElse);
    public void visit(If If);
    public void visit(Block Block);
    public void visit(DesignatorLineStmt DesignatorLineStmt);
    public void visit(NamedeclarationDerived1 NamedeclarationDerived1);
    public void visit(Array Array);
    public void visit(Variable Variable);
    public void visit(NumberConstantDeclaration NumberConstantDeclaration);
    public void visit(CharConstantDeclaration CharConstantDeclaration);
    public void visit(BoolConstantDeclaration BoolConstantDeclaration);
    public void visit(VariabledeclarationDerived2 VariabledeclarationDerived2);
    public void visit(VariabledeclarationDerived1 VariabledeclarationDerived1);
    public void visit(ConstantDeclarationTyped ConstantDeclarationTyped);
    public void visit(ConstantDeclarations ConstantDeclarations);
    public void visit(FormparsDerived4 FormparsDerived4);
    public void visit(FormparsDerived3 FormparsDerived3);
    public void visit(FormparsDerived2 FormparsDerived2);
    public void visit(FormparsDerived1 FormparsDerived1);
    public void visit(FormparswrapperDerived1 FormparswrapperDerived1);
    public void visit(FormParsWrapper FormParsWrapper);
    public void visit(DeclarationblockDerived1 DeclarationblockDerived1);
    public void visit(EmptyDeclarationBlock EmptyDeclarationBlock);
    public void visit(DeclarationBlock DeclarationBlock);
    public void visit(DeclarationblockwrapperDerived1 DeclarationblockwrapperDerived1);
    public void visit(DeclarationBlockWrapper1 DeclarationBlockWrapper1);
    public void visit(FunctionDeclarationBlock FunctionDeclarationBlock);
    public void visit(Function Function);
    public void visit(FuncHeader FuncHeader);
    public void visit(CodebodyDerived2 CodebodyDerived2);
    public void visit(CodebodyDerived1 CodebodyDerived1);
    public void visit(ClasscodebodyDerived2 ClasscodebodyDerived2);
    public void visit(ClasscodebodyDerived1 ClasscodebodyDerived1);
    public void visit(Class Class);
    public void visit(ImplementedName ImplementedName);
    public void visit(NonemptyimplementsdeclarationDerived2 NonemptyimplementsdeclarationDerived2);
    public void visit(NonemptyimplementsdeclarationDerived1 NonemptyimplementsdeclarationDerived1);
    public void visit(EmptyimplementsdeclarationDerived1 EmptyimplementsdeclarationDerived1);
    public void visit(ImplementsdeclarationDerived2 ImplementsdeclarationDerived2);
    public void visit(ImplementsdeclarationDerived1 ImplementsdeclarationDerived1);
    public void visit(ExtendsdeclarationDerived2 ExtendsdeclarationDerived2);
    public void visit(ExtendsdeclarationDerived1 ExtendsdeclarationDerived1);
    public void visit(EmptyExtendsDeclaration EmptyExtendsDeclaration);
    public void visit(ExtendsDeclaration ExtendsDeclaration);
    public void visit(ProgramdeclarationsDerived7 ProgramdeclarationsDerived7);
    public void visit(ProgramdeclarationsDerived6 ProgramdeclarationsDerived6);
    public void visit(ProgramdeclarationsDerived5 ProgramdeclarationsDerived5);
    public void visit(ProgramdeclarationsDerived4 ProgramdeclarationsDerived4);
    public void visit(ProgramdeclarationsDerived3 ProgramdeclarationsDerived3);
    public void visit(ProgramdeclarationsDerived2 ProgramdeclarationsDerived2);
    public void visit(ProgramdeclarationsDerived1 ProgramdeclarationsDerived1);
    public void visit(ProcedureName ProcedureName);
    public void visit(FunctionName FunctionName);
    public void visit(InterfaceMethodDeclaration InterfaceMethodDeclaration);
    public void visit(EmptyInterfaceInner EmptyInterfaceInner);
    public void visit(InterfaceName InterfaceName);
    public void visit(InterfaceDeclaration InterfaceDeclaration);
    public void visit(EnuminnerDerived2 EnuminnerDerived2);
    public void visit(EnuminnerDerived1 EnuminnerDerived1);
    public void visit(EnumDeclaration EnumDeclaration);
    public void visit(NumberedEnumConstant NumberedEnumConstant);
    public void visit(EnumConstant EnumConstant);
    public void visit(EnumName EnumName);
    public void visit(ClassName ClassName);
    public void visit(ProgramName ProgramName);
    public void visit(ProgramDerived1 ProgramDerived1);

}
