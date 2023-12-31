package ast.Instructions;

import ast.Expresions.E;
import ast.Programa;
import ast.Accesos.*;

public class Asignacion extends Instruccion{
    private KindInstruction tipoIns = KindInstruction.ASIGNACION; // esto sirve de algo?
    private Acceso iden;
    private E exp;

    public Asignacion(Acceso iden, E exp){
        this.iden = iden;
        this.exp = exp;
    }

    public KindInstruction kind() {return tipoIns;}
    public String toString() {
        if(tipo != null)    
            return tipoIns.toString() + ": " + iden  + "(" + this.tipo + ") = " + exp.toString();

        else 
            return tipoIns.toString() + ": " + iden + " = " + exp.toString();
    }

    public Acceso getId(){
        return this.iden;
    }

    public E getValor(){
        return this.exp;
    }

    public void vincular() {
        iden.vincular();
        exp.vincular();
    }

    public void checkType(){
        iden.checkType();
        exp.checkType();

      
        if( iden.tipo != null && !iden.tipo.toString().equals(exp.tipo.toString())){
            System.out.println("Error tipo: Asignacion " + iden + "=" + exp + "(" + iden.tipo + "," + exp.tipo + ")");
            Programa.okTipos = false;
        }
       // else  System.out.println("tipo OK");

    }

    public void generaCodigo(){
        Programa.codigo.println(";; Inicio asignacion " + iden);
        if(exp.getTipo().kind().toString().equals("ENUM")){
            Programa.codigo.println(";; Inicio asignacion " + " " + exp + exp.getTipo().kind());

            iden.calcularDirRelativa();

            Programa.codigo.println("i32.const " + Programa.buscarPosEnum(iden.getTipo(), exp));
            Programa.codigo.println("i32.store");


        }
        else if (! exp.isInMemory()){ // para a = 3 + 2;
            iden.calcularDirRelativa();
            exp.generaCodigo();
            Programa.codigo.println("i32.store");
        } else {
            // Para a = t; (t es un struct)
            Programa.codigo.println(";;exp tipo " + exp);
            exp.calcularDirRelativa();
            Programa.codigo.println(";;iden");
            iden.calcularDirRelativa();
            Programa.codigo.println("i32.const " + (exp.getTipo().getTam() / 4)); // getTam está en bytes no en bloques de 32 bits
            Programa.codigo.println("call $copyn"); // src dest tam
        }
        Programa.codigo.println(";; Fin asignacion " + iden);
    }
}
