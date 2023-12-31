package ast.Instructions;

import java.util.List;

import ast.Programa;
import ast.Expresions.E;

public class BucleFor extends Bloque {

    private Declaracion dec;
    private E exp;
    private Asignacion a;

    public BucleFor(E exp, Asignacion a, List<Instruccion> instList) {
        super(instList);
        this.exp = exp;
        this.a = a;
        this.dec = null;
    }

    public BucleFor(Declaracion dec, E exp, Asignacion a, List<Instruccion> instList) {
        super(instList);
        this.dec = dec;
        this.exp = exp;
        this.a = a;
    }

    public void generaCodigo(){
        if (dec != null)
            dec.generaCodigo();

        Programa.codigo.println("block");
        Programa.codigo.println("loop");

        exp.generaCodigo();
        
        Programa.codigo.println("i32.eqz");
        Programa.codigo.println("br_if 1");
        
        for (Instruccion ins : instList){
            ins.generaCodigo();
        }

        a.generaCodigo();

        Programa.codigo.println("br 0");
        Programa.codigo.println("end");
        Programa.codigo.println("end");
    }

    @Override
    public void vincular() {
        Programa.pila.abreBloque();
        if (dec != null)
            dec.vincular();
        exp.vincular();

        for (Instruccion instruccion : instList) {
            instruccion.vincular();
        }

        a.vincular();
        Programa.pila.cierraBloque();
    }

    @Override
    public void checkType() {
        if (dec != null) {
            dec.checkType();
            if (dec.tipo == null)
                return;
            if (!dec.tipo.toString().equals("INT")) { // La declaracion tiene que ser int
                System.out.println("Error tipo: declaracion for " + dec + "(" + dec.tipo + ")");
                Programa.okTipos = false;
            }
        }
        exp.checkType();
        if (exp.tipo == null)
            return;
        if (!exp.tipo.toString().equals("BOOL")) { // La condicion tiene que ser bool
            System.out.println("Error tipo: condicion for " + exp + "(" + exp.tipo + ")");
            Programa.okTipos = false;
        }
        a.checkType();

        for (Instruccion instruccion : instList) {
            instruccion.checkType();
        }
    }

    @Override
    public int maxMemoria() {
        int max = super.maxMemoria();
        if (dec != null) {
            max += 4;
        }
        return max;
    }

    @Override
    public KindInstruction kind() {
        return KindInstruction.BUCLEFOR;
    }

    public String toString() {
        if (this.dec != null)
            return "FOR: (" + "Variable: " + dec.toString() + " Condicion:  " + exp.toString() +
                    "Asignacion: " + a.toString() + ")" +
                    "Cuerpo: {" + instList.toString() + "}";
        else
            return "FOR: (" + " Variable: " + ";" + " Condicion:  " + exp.toString() +
                    "Asignacion: " + a.toString() + ")" +
                    "Cuerpo: {" + instList.toString() + "}";
    }

    public Declaracion getId() {
        return this.dec;
    }

    public Asignacion getAsignacion() {
        return this.a;
    }

    public E getValor() {
        return this.exp;
    }
}
