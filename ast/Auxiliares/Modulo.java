package ast.Auxiliares;

public class Modulo {
    private String nombre;
    private String extension;

    public Modulo(String name, String ext){
        this.nombre = name;
        this.extension = ext;
    }

    public String toString() {
        return "Incluir: " + this.nombre + "." + this.extension;
    }
}