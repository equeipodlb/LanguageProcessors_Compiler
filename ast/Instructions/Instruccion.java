package ast.Instructions;

import java.util.List;

import ast.ASTNode;
import ast.NodeKind;

public abstract class Instruccion extends ASTNode {
    public abstract KindInstruction kind();
    public void checkType(){}
    public NodeKind nodeKind() {return NodeKind.INSTRUCTION;}
    public String toString() {return "";}
    public void generaCodigo() {};
    public int maxMemoria() {return 0;}
    public void simplifyAlias(List<Alias> lista_alias) {}
}
