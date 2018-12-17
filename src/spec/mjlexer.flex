package rs.ac.bg.etf.pp1;
import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.sym;

%%
%public
%cup
%xstate COMMENT

%eofval{
return new_symbol(sym.EOF);
%eofval}

%line
%column
%{
    public int getLineNo()
    {
        return yyline+1;
    }
    public int getColumn()
    {
        return yycolumn+1;
    }
    private Symbol new_symbol(int type) {
        return new Symbol(type, yyline+1, yycolumn);
    }
    
    private Symbol new_symbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn, value);
    }
%}
%%


\".\"   { return new_symbol(sym.CHARACTER, yytext().charAt(1)); }
\'.\'   { return new_symbol(sym.CHARACTER, yytext().charAt(1)); }
"program" {return new_symbol(sym.PROGRAM);}
"break" {return new_symbol(sym.BREAK);}
"class" {return new_symbol(sym.CLASS);}
"interface" {return new_symbol(sym.INTERFACE);}
"implements" {return new_symbol(sym.IMPLEMENTS);}
"enum" {return new_symbol(sym.ENUM);}
"else" {return new_symbol(sym.ELSE);}
"if" {return new_symbol(sym.IF);}
"new" {return new_symbol(sym.NEW);}
"return" {return new_symbol(sym.RETURN);}
"void" {return new_symbol(sym.VOID);}
"for" {return new_symbol(sym.FOR);}
"while" {return new_symbol(sym.WHILE);}
"extends" {return new_symbol(sym.EXTENDS);}
"continue" {return new_symbol(sym.CONTINUE);}
"true"  {return new_symbol(sym.BOOL, true);}
"read" {return new_symbol(sym.READ, true);}
"print" {return new_symbol(sym.PRINT, true);}
"false"  {return new_symbol(sym.BOOL, false);}
"const"  {return new_symbol(sym.CONST);}
"//"    {yybegin(COMMENT);}
<COMMENT> . {}
[A-Za-z][A-Za-z_0-9]*   {return new_symbol(sym.IDENT, yytext());}
"%"             {return new_symbol(sym.MOD);}
"=="            {return new_symbol(sym.EQUALS);}
"!="            {return new_symbol(sym.NOTEQUALS);}
">"            {return new_symbol(sym.GREATER);}
">="            {return new_symbol(sym.GREATEREQUAL);}
"<"            {return new_symbol(sym.LESS);}
"<="            {return new_symbol(sym.LESSEQUAL);}
"&&"            {return new_symbol(sym.AND);}
"||"            {return new_symbol(sym.OR);}
"+" { return new_symbol(sym.PLUS); }
"-" { return new_symbol(sym.MINUS); }
"*" { return new_symbol(sym.STAR); }
"/" { return new_symbol(sym.DIVIDE); }
"=" { return new_symbol(sym.ASSIGN); }
"++" { return new_symbol(sym.INCREMENT); }
"--" { return new_symbol(sym.DECREMENT); }
";" {return new_symbol(sym.SEMICOLON); }
"," { return new_symbol(sym.COMA); }
"(" { return new_symbol(sym.LPAREN); }
")" { return new_symbol(sym.RPAREN); }
"{" { return new_symbol(sym.LBRACE); }
"}" { return new_symbol(sym.RBRACE); }
"[" { return new_symbol(sym.LBRACK); }
"]" { return new_symbol(sym.RBRACK); }
"." { return new_symbol(sym.DOT); }

[0-9]+ { return new_symbol(sym.NUMBER, new Integer(yytext())); }
<COMMENT, YYINITIAL> [\r\n] {yybegin(YYINITIAL);}
[ \t\f] { /* ignore white space. */ }
. { CompilerError.raise("Illegal character: '"+yytext()+"'", yyline+1, yycolumn+1); }