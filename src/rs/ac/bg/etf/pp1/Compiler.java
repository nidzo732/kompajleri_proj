package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.parser;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.mj.runtime.disasm;
import rs.etf.pp1.symboltable.Tab;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Compiler
{
    private Yylex lexer;
    private parser parser;
    private SemanticAnalyzer semanticAnalyzer;
    private CodeGenerator codeGenerator;
    private Symbol rootSymbol = null;

    private Compiler(String inputFile) throws IOException
    {
        lexer = new Yylex(new FileReader(new File(inputFile)));
        parser = new parser(lexer);
        semanticAnalyzer = new SemanticAnalyzer();
        codeGenerator = new CodeGenerator();
    }

    public static void main(String[] args) throws Exception
    {
        Compiler c = new Compiler("testdata/code.txt");
        c.parse();
        c.semanticProcess();
        c.tsdump();
        c.refDump();
        System.out.println("==========================END OF INPUT PARSING=========================");
        if (TableWrapper.main == null)
        {
            System.err.println("Failed to find void main()");
        }
        if (!c.compileable())
        {
            System.err.println("Input contains error, skipping compilation");
        }
        else
        {
            c.compile();
            c.dump("testdata/output.obj");
        }
        //Run.main(new String[]{"testdata/output.obj"});

        /*//sa.dumpReferences(System.out);
        Tab.dump();*/
    }

    private void parse() throws Exception
    {
        System.out.println("=======================SYNTAX/LEXICAL PROCESSING=======================");
        rootSymbol = parser.parse();
        System.out.println("===============================SYNTAX TREE=============================");
        if(rootSymbol!=null && rootSymbol.value!=null)
        {
            System.out.println(rootSymbol.value.toString());
        }
    }

    private void semanticProcess()
    {
        System.out.println("==========================SEMANTIC PROCESSING==========================");
        TableWrapper.init();
        CompilerError.errorsMade = false;
        if(rootSymbol!=null && rootSymbol.value!=null)
        {
            ((SyntaxNode) rootSymbol.value).traverseBottomUp(semanticAnalyzer);
        }
    }

    public void tsdump()
    {
        try
        {
            Tab.dump();
        }
        catch (StackOverflowError ignored)
        {

        }
    }

    private void refDump()
    {
        System.out.println("===========================REFERENCED SYMBOLS==========================");
        semanticAnalyzer.dumpReferences(System.out);
    }

    private boolean compileable()
    {
        return !(lexer.lexError || parser.syntaxError || CompilerError.errorsMade || TableWrapper.main == null);
    }

    private void compile()
    {
        ((SyntaxNode) rootSymbol.value).traverseBottomUp(codeGenerator);
        disasm.decode(Code.buf, Code.pc);
    }

    private void dump(String filename) throws IOException
    {
        File fi = new File(filename);
        if (!fi.exists()) fi.createNewFile();
        Code.write(new FileOutputStream(fi));
    }

}