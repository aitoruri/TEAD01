package TareaEvaluativa;
import java.io.*;
import java.util.*;

public class Ejercicio1 {
	public static void main(String[] args) throws Exception{
		File fichEntrada = new File("Ficheros\\noinvertido.txt");
		File fichSalida = new File("Ficheros\\invertido.txt");
		FileReader fr = new FileReader(fichEntrada);
		FileWriter fw = new FileWriter(fichSalida);
		
		int i;
		char caracter;
		StringBuilder palabras = new StringBuilder();
		
		while((i = fr.read()) != -1) {
			caracter = (char)i;
			palabras.append(caracter);
		}
		
		//Separamos el String en palabras independientes
		String p = new String(palabras);
		String[] separadas = p.split(" ");
		
		//Damos la vuelta a cada palabra por separado
		String vuelta = "";
		for(String pal : separadas) {
			for(int j=pal.length()-1;j>=0;j--) {
				vuelta += pal.charAt(j);
			}
			vuelta +=" ";
		}	
		System.out.println(vuelta);
		fw.write(vuelta);
		fw.flush();
	}
}
