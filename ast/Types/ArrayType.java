package ast.Types;

import ast.Expresions.Const;
import ast.Expresions.E;

public class ArrayType extends Types{

    protected Const tam;

    public ArrayType(Types tipo, Const tam) {
        this.tipo = tipo;
        this.tam = tam;
    }


    public KindTypes kind() {
        return KindTypes.ARRAY;
    }

    public String toString(){
        if(tam != null)
            return "Array " + "<" + tipo.toString() + ">" ;
        else 
            return "Array "+ "<" + tipo.toString() + ">" ;
    }


    @Override
    public int getTam() {
        return tipo.getTam() * Integer.parseInt(tam.getValor());
    }

    
}
