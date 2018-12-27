package rs.ac.bg.etf.pp1;

import java.util.HashSet;
import java.util.Set;

import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;

public class SafeTableVisitor extends DumpSymbolTableVisitor {

	private Set<Object> visitedNodes=new HashSet<>();
	@Override
	public void visitObjNode(Obj x) {
		if(visitedNodes.contains(x)) 
		{
			output.append(x.getName());
			return;
		}
		visitedNodes.add(x);
		super.visitObjNode(x);
		
	}

	@Override
	public void visitScopeNode(Scope x) {
		if(visitedNodes.contains(x)) return;
		visitedNodes.add(x);
		super.visitScopeNode(x);
	}

	@Override
	public void visitStructNode(Struct x) {
		if(visitedNodes.contains(x)) return;
		visitedNodes.add(x);
		super.visitStructNode(x);
	}
}
