package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.SyntaxNode;

public class CompilerError extends Exception{
    protected String message;
    protected int line;
    protected int column;
    protected CompilerError(String message, int line, int column)
    {
        this.message = message;
        this.line=line;
        this.column=column;
    }

    @Override
    public String toString()
    {
        String msg="Compilation eror: "+message+"\nOn Line:"+line;
        if(column!=-1)
        {
            msg+=", Column:"+column;
        }
        return msg;
    }

    public static void raise(String message, int line, int column) throws CompilerError
    {
        throw new CompilerError(message, line, column);
    }

    public static void raise(String message, SyntaxNode node) throws CompilerError
    {
        raise(message, node.getLine(), -1);
    }
}
