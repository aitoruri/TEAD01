package TareaEvaluativa;
import java.io.*;
import java.util.*;

public class Ejercicio4 {
	public static void main(String[] args) {
		File fichero = new File("Ficheros\\Marvel.dat");
		Scanner teclado = new Scanner(System.in);
		try {
			RandomAccessFile acceso = new RandomAccessFile(fichero, "rw");

			//Carga de los datos en el fichero
			int [] ids= {1, 2, 3, 4, 5, 6, 7};
			String[] dnis= {"01010101A", "03030303C", "05050505E", "07070707G", "02020202B", "04040404D", "06060606F"};
			String[] noms= {"Spiderman", "Green Goblin", "Storm", "Wolverine", "Mystique", "IronMan", "Mandarin"};
			String[] identidades = {"Peter Parker", "Norman Osborn", "Ororo Munroe","James Howlett", "Raven Darkholme", "Tony Stark", "Zhang Tong"};
			String[] tipos = {"heroe","villano","heroe","heroe","villano","heroe","villano"};
			int[] pesos = {76,84,66,136,78,102,70};
			int[] alturas = {178,183,156,152,177,182,188};

			StringBuffer dnisBuffer = null;
			StringBuffer nomsBuffer = null;
			StringBuffer identidadesBuffer = null;
			StringBuffer tiposBuffer = null;

			for(int i=0; i<ids.length;i++) {

				//Inicializamos y damos largo a los buffers para cada registro
				dnisBuffer = new StringBuffer(dnis[i]);
				dnisBuffer.setLength(9);
				nomsBuffer = new StringBuffer(noms[i]);
				nomsBuffer.setLength(10);
				identidadesBuffer = new StringBuffer(identidades[i]);
				identidadesBuffer.setLength(20);
				tiposBuffer = new StringBuffer(tipos[i]);
				tiposBuffer.setLength(10);

				//Escribimos los datos de cada registro
				acceso.writeInt(ids[i]);							//---------4-----------
				acceso.writeChars(dnisBuffer.toString());			//---------18----------
				acceso.writeChars(nomsBuffer.toString());			//---------20----------
				acceso.writeChars(identidadesBuffer.toString());	//---------40----------
				acceso.writeChars(tiposBuffer.toString());			//---------20----------
				acceso.writeInt(pesos[i]);							//---------4-----------
				acceso.writeInt(alturas[i]);						//---------4-----------

				//El largo de cada registro es de 110bytes (4id + 18dni + 20nombre + 40identidad + 20tipo + 4peso + 4altura)
			}
			System.out.println("La carga de los personajes se ha realizado correctamente.");

		} catch (Exception e) {
			System.out.println("La carga de los personajes ha fallado.");
		}

		//Manipulación de los datos
		try {
			boolean dniExiste = false;
			RandomAccessFile leer = new RandomAccessFile(fichero, "r");
			System.out.println("Introduzca el DNI (con letra) del personaje para el control de peso:");
			String dniIntroducido = teclado.nextLine();

			char[] dniChar = new char[9];
			char[] nombreChar = new char[10];

			for(int posicion = 0; posicion < leer.length(); posicion+=110) {
				leer.seek(posicion); 

				//Recogemos dni
				leer.skipBytes(4); 
				for(int i=0;i<9;i++) {
					dniChar[i] = leer.readChar();
				}
				String dniString = new String(dniChar);

				if(dniString.trim().equalsIgnoreCase(dniIntroducido)) { //Si encontramos el dni
					dniExiste = true;
					System.out.println("Introduzca su peso actual: ");
					int pesoActual = teclado.nextInt();

					//Recogemos el nombre
					for(int i=0;i<10;i++) {
						nombreChar[i] = leer.readChar();
					}
					String nombreString = new String(nombreChar).trim();

					//Recogemos peso
					leer.skipBytes(60);
					int pesoAnterior = leer.readInt();

					if(pesoAnterior > pesoActual) {
						System.out.printf("%s ha adelgazado %d kilos.",nombreString,pesoAnterior-pesoActual);
					}
					else if(pesoAnterior < pesoActual) {
						System.out.printf("%s ha engordado %d kilos.",nombreString,pesoActual-pesoAnterior);
					}
					else {
						System.out.printf("%s se mantiene en su peso anterior.",nombreString);
					}	
				}
			}
			
			if(!dniExiste) {
				System.out.println("Ese personaje no existe.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
