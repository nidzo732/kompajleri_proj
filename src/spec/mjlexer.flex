package rs.ac.bg.etf.pp1;
import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.sym;

%%
%public
%cup

%xstates COMMENT
%line
%char
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
%}
%%


\".\"   { return new Symbol(sym.CHARACTER, yytext().charAt(1)); }
\'.\'   { return new Symbol(sym.CHARACTER, yytext().charAt(1)); }
"program" {return new Symbol(sym.PROGRAM);}
"break" {return new Symbol(sym.BREAK);}
"class" {return new Symbol(sym.CLASS);}
"else" {return new Symbol(sym.ELSE);}
"if" {return new Symbol(sym.IF);}
"new" {return new Symbol(sym.NEW);}
"return" {return new Symbol(sym.RETURN);}
"void" {return new Symbol(sym.VOID);}
"do" {return new Symbol(sym.DO);}
"while" {return new Symbol(sym.WHILE);}
"extends" {return new Symbol(sym.EXTENDS);}
"continue" {return new Symbol(sym.CONTINUE);}
"true"  {return new Symbol(sym.BOOL, true);}
"false"  {return new Symbol(sym.BOOL, false);}
"const"  {return new Symbol(sym.CONST);}
"//"    {yybegin(COMMENT);}
<COMMENT> . {}
[A-Za-z][A-Za-z_0-9]*   {return new Symbol(sym.IDENT, yytext());}
"%"             {return new Symbol(sym.MOD);}
"=="            {return new Symbol(sym.EQUALS);}
"!="            {return new Symbol(sym.NOTEQUALS);}
">"            {return new Symbol(sym.GREATER);}
">="            {return new Symbol(sym.GREATEREQUAL);}
"<"            {return new Symbol(sym.LESS);}
"<="            {return new Symbol(sym.LESSEQUAL);}
"&&"            {return new Symbol(sym.AND);}
"||"            {return new Symbol(sym.OR);}
"+" { return new Symbol(sym.PLUS); }
"-" { return new Symbol(sym.MINUS); }
"*" { return new Symbol(sym.STAR); }
"/" { return new Symbol(sym.DIVIDE); }
"=" { return new Symbol(sym.ASSIGN); }
"++" { return new Symbol(sym.INCREMENT); }
"--" { return new Symbol(sym.DECREMENT); }
";" {return new Symbol(sym.SEMICOLON); }
"," { return new Symbol(sym.COMA); }
"(" { return new Symbol(sym.LPAREN); }
")" { return new Symbol(sym.RPAREN); }
"{" { return new Symbol(sym.LBRACE); }
"}" { return new Symbol(sym.RBRACE); }
"[" { return new Symbol(sym.LBRACK); }
"]" { return new Symbol(sym.RBRACK); }
"." { return new Symbol(sym.DOT); }

[0-9]+ { return new Symbol(sym.NUMBER, new Integer(yytext())); }
<COMMENT, YYINITIAL> [\r\n] {yybegin(YYINITIAL);}
[ \t\f] { /* ignore white space. */ }
. { System.err.println("Illegal character: "+yytext()); }