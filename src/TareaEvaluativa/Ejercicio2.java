package TareaEvaluativa;
import java.io.*;
import java.util.*;

public class Ejercicio2 {
	public static void main(String[] args) throws Exception {
		File fichero = new File("Ficheros\\palabralarga.txt");
		BufferedReader lector = new BufferedReader(new FileReader(fichero));
		String linea = lector.readLine();
		String[] palabras = linea.split(" ");
		String larga = "";
		int max = 0;
		
		for(int i=0;i<palabras.length;i++) {
			if(palabras[i].length() > max) {
				max = palabras[i].length(); 
				if(palabras[i].length() == max) {
					larga = palabras[i];
				}
			}
		}
		System.out.println(larga);
	}
}
