package TareaEvaluativa;
import java.io.*;

public class Ejercicio3 {
	public static void main(String[] args) throws Exception {
		File fichero = new File("Ficheros\\DAM_AD01_TaEv01.pdf");
		FileInputStream fis = new FileInputStream(fichero);
		String cabecera = "37806870";
		String comprobacion = "";
		int i = 0;
		int numbytes = 0;
		
		//Leemos los 4 primeros bytes y comprobamos
		while((i = fis.read()) != -1) {
			numbytes++;
			if(numbytes > 4) {
				break;
			}
			comprobacion += i;
		}
		
		if(cabecera.equals(comprobacion)) {
			System.out.println("¡El archivo es un PDF!");
		} else {
			System.out.println("No es un archivo PDF");
		}
	}
}
