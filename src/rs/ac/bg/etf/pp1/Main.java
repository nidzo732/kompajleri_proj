package rs.ac.bg.etf.pp1;

import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.Yylex;
import rs.ac.bg.etf.pp1.ast.Factor;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.ast.Term;
import rs.ac.bg.etf.pp1.ast.parser;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.mj.runtime.disasm;
import rs.etf.pp1.symboltable.Tab;

import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception{
        Yylex lexer = null;
        parser p = null;
        try {
            System.out.println("=======================SINTAKSNA/LEKSICKA OBRADA=======================");
            lexer = new Yylex(new FileReader(new File("testdata/code.txt")));
            p = new parser(lexer);
            Symbol sy = p.parse();
            System.out.println("============================SINTAKSNO STABLO===========================");
            System.out.println(sy.value.toString());
            System.out.println("===========================SEMANTICKA OBRADA===========================");
            /*TableWrapper.init();
            SemanticAnalyzer sa=new SemanticAnalyzer();
            ((Program)sy.value).traverseBottomUp(sa);
            //sa.dumpReferences(System.out);
            Tab.dump();*/
        }
        catch (CompilerError cerr)
        {
            System.err.println(cerr);
        }

    }
}