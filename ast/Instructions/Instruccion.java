package ast.Instructions;

import ast.ASTNode;
import ast.NodeKind;

public abstract class Instruccion extends ASTNode {
    public abstract KindInstruction kind();
    public NodeKind nodeKind() {return NodeKind.INSTRUCTION;}
    public String toString() {return "";}

}