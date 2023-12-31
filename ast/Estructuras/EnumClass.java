package ast.Estructuras;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import ast.ASTNode;
import ast.Programa;
import ast.Util;
import ast.Expresions.Const;
import ast.Types.EnumTypes;

public class EnumClass extends ASTNode{
    private String name;
    private List<Const> campos;
    

    public EnumClass(String name, List<String> valores){
        this.name = name;
        this.campos = new ArrayList<Const>();
        for (String s : valores) {
            this.campos.add(new Const(s, new EnumTypes(name)));
        }
        Programa.enumList.add(this);
        Programa.lista_tipos.add(this.name);
        Collections.reverse(this.campos);
    }

    public String getName(){
        return this.name;
    }
   

    public List<Const> getCampos(){
        return this.campos;
    }
 
    public void vincular() {
        ASTNode nodo = Programa.pila.buscaId(name);
        if (nodo == null) { // devuelve null cuando no esta
            Programa.pila.insertaId(name, this);
            for (Const c : campos) {
                Programa.pila.insertaId(c.getValor(), c);
            }
        } else {
            System.out.println("Error vinculacion: Este identificador ya esta usado: " + name);
            Programa.okVinculacion = false;
        }
    }

    

    public String toString(){ // enum{a,b,c}
        return name + '{' + Util.prettify(campos) + '}';
    }
    
}
