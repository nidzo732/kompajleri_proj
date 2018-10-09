package rs.ac.bg.etf.pp1;

import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.Yylex;
import rs.ac.bg.etf.pp1.ast.Factor;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.ast.Term;
import rs.ac.bg.etf.pp1.ast.parser;
import rs.etf.pp1.symboltable.Tab;

import java.io.*;

public class Main {

    public static void main(String[] args){
        Yylex lexer = null;
        parser p = null;
        try {
            PrintWriter treeOut = new PrintWriter(new FileWriter(new File("testdata/tree.txt")));
            lexer = new Yylex(new FileReader(new File("testdata/code.txt")));
            p = new parser(lexer);
            Symbol sy = p.parse();
            Tab.init();
            SemanticAnalyzer sa=new SemanticAnalyzer();
            treeOut.println(sy.value.toString());
            ((Program)sy.value).traverseBottomUp(sa);
            Tab.dump();

        }
        catch (Exception ex)
        {
            System.out.println(ex);
            ex.printStackTrace();
            if(lexer!=null)
            {
                System.out.println(lexer.getLineNo()+":"+lexer.getColumn());
            }
        }

    }
}