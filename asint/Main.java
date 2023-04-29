package asint;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoTiny;
import ast.Programa;

public class Main {
	public static void main(String[] args) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(args[0]));
		AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
		AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(alex);
		Programa p = (Programa) asint.parse().value;

		System.out.println("------------------------ VINCULACIÓN ------------------------");
		p.vincular(); // vinculacion
		System.out.println("------------------------ TIPADO ------------------------");
		p.checkType();//tipado
		System.out.println("------------------------ GENERACION DE CODIGO ------------------------");
		p.generaCodigo();
		// System.out.println(p);
	}
}
