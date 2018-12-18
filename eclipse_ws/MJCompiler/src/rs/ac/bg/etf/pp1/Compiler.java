package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.MJParser;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.mj.runtime.Run;
import rs.etf.pp1.mj.runtime.disasm;
import rs.etf.pp1.symboltable.Tab;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Compiler
{
    private MJParser parser;
    private SemanticAnalyzer semanticAnalyzer;
    private CodeGenerator codeGenerator;
    private Symbol rootSymbol = null;
    private boolean verbose = false;

    private Compiler(String inputFile) throws IOException
    {
        CompilerError.errorsMade = false;
        Yylex lexer = new Yylex(new FileReader(new File(inputFile)));
        parser = new MJParser(lexer);
        semanticAnalyzer = new SemanticAnalyzer();
        codeGenerator = new CodeGenerator();
    }

    public static void main(String[] args) throws Exception
    {
        try
        {
            if(args.length<2)
            {
                System.out.println("Format: program.jar INPUT_FILE OUTPUT_FILE [-v] [-x]");
                return;
            }
            boolean verbose = false;
            boolean execute = false;
            for (String arg : args)
            {
                if ("-x".equals(arg)) execute = true;
                if ("-v".equals(arg)) verbose = true;
            }
            Compiler c = new Compiler(args[0]);
            c.verbose = verbose;
            c.parse();
            if (!c.parser.unrecoveredSyntaxError) c.semanticProcess();
            if (verbose) c.tsdump();
            if (verbose) c.refDump();
            if (verbose) System.out.println("==========================END OF INPUT PARSING=========================");
            if (!c.compileable() || TableWrapper.main == null)
            {
                System.out.flush();
                System.err.flush();
                CompilerError.dumpAll();
                if(TableWrapper.main==null)
                {
                    System.err.println("void main() not found");
                }
                System.err.println("Input contains errors, skipping compilation");
                System.err.flush();
            }
            else
            {
                c.compile();
                c.dump(args[1]);
                if(execute)
                {
                    Run.main(new String[]{args[1]});
                }
            }
        }
        catch (IOException ex)
        {
            System.out.println("Failed to open files " + ex.getLocalizedMessage());
        }
    }

    private void parse() throws Exception
    {
        if (verbose) System.out.println("=======================SYNTAX/LEXICAL PROCESSING=======================");
        rootSymbol = parser.parse();
        if (verbose) System.out.println("===============================SYNTAX TREE=============================");
        if (verbose && rootSymbol != null && rootSymbol.value != null)
        {
            System.out.println(rootSymbol.value.toString());
        }
    }

    private void semanticProcess()
    {
        if (verbose) System.out.println("==========================SEMANTIC PROCESSING==========================");
        TableWrapper.init();
        if (rootSymbol != null && rootSymbol.value != null)
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
        return !(CompilerError.errorsMade || TableWrapper.main == null);
    }

    private void compile()
    {
        ((SyntaxNode) rootSymbol.value).traverseBottomUp(codeGenerator);
        if (verbose) disasm.decode(Code.buf, Code.pc);
    }

    private void dump(String filename) throws IOException
    {
        File fi = new File(filename);
        if (!fi.exists()) fi.createNewFile();
        Code.write(new FileOutputStream(fi));
    }

}