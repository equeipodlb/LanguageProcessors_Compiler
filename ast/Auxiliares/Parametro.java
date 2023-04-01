package ast.Auxiliares;

import ast.Types.KindTypes;

public class Parametro {
    private KindTypes tipo;
    private String nombre;
    private boolean andpersand;

    public Parametro(KindTypes tipo, String nombre, boolean andpersand){
        this.tipo = tipo;
        this.nombre = nombre;
        this.andpersand = andpersand;
    }

    @Override
    public String toString() {
        return tipo.toString() + " " + (andpersand ? "&":"") + nombre;
    }
}