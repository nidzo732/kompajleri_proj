package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.SyntaxNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CompilerError
{
    static boolean errorsMade = false;
    protected int line;
    private String message;
    private int column;
    private static List<CompilerError> errors=new LinkedList<>();

    private CompilerError(String message, int line, int column)
    {
        this.message = message;
        this.line = line;
        this.column = column;
    }

    private CompilerError(String message, int line)
    {
        this(message, line, -1);
    }

    public static void raise(String message, int line, int column)
    {
        errorsMade = true;
        errors.add(new CompilerError(message, line, column));
    }

    public static void raise(String message, SyntaxNode node)
    {
        errorsMade = true;
        raise(message, node.getLine(), -1);
    }

    public static void dumpAll()
    {
        for(CompilerError error: errors)
        {
            System.err.println(error);
        }
    }

    @Override
    public String toString()
    {
        String msg = "Compilation eror: " + message + "\nOn Line:" + line;
        if (column != -1)
        {
            msg += ", Column:" + column;
        }
        return msg;
    }
}
