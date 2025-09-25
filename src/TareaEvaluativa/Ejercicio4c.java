package TareaEvaluativa;
import java.io.*;
import java.util.*;

public class Ejercicio4c {
	public static void main(String[] args) {

		File fichero = new File("Ficheros\\Marvel.dat");
		Scanner teclado = new Scanner(System.in);
		
		//El largo de cada registro es de 110bytes (4id + 18dni + 20nombre + 40identidad + 20tipo + 4peso + 4altura)
		try {
			RandomAccessFile leer = new RandomAccessFile(fichero, "r");
			System.out.print("Introduce un tipo de personaje: ");
			String tipoSeleccionado = teclado.nextLine();
			
			boolean tipoExiste = false;
			int encontrado = 0;
			
			//Por cada personaje recogemos todos sus datos y los añadimos a una lista
			//[dni=xxxxxx, nombre=Spiderman, ..........................]
			
			for(int posicion=0; posicion<leer.length(); posicion+=110) {
				ArrayList<String> lista = new ArrayList<String>();
				leer.seek(posicion);

				//Recogemos el tipo
				leer.skipBytes(82);
				char[] tipoChar = new char[10];
				for(int i=0; i<10; i++) {
					tipoChar[i] = leer.readChar();
				}
				String tipoString = new String(tipoChar).trim();

				if(tipoSeleccionado.equalsIgnoreCase(tipoString)) {
					tipoExiste = true;
					encontrado++;
					
					leer.seek(posicion);
					
					//Recogemos dni
					leer.skipBytes(4);
					char[] dniChar = new char[9];
					for(int i=0;i<9;i++) {
						dniChar[i] = leer.readChar();
					}
					String dniString = new String(dniChar).trim();
					lista.add("dni="+dniString+" ");
					
					//Recogemos nombre
					char[] nombreChar = new char[10];
					for(int i=0;i<10;i++) {
						nombreChar[i] = leer.readChar();
					}
					String nombreString = new String(nombreChar).trim();
					lista.add("nombre="+nombreString +" ");
					
					//Recogemos identidad
					char[] identidadChar = new char[20];
					for(int i=0;i<20;i++) {
						identidadChar[i] = leer.readChar();
					}
					String identidadString = new String(identidadChar).trim();
					lista.add("identidad="+identidadString+" ");
					
					//Tipo
					lista.add("tipo="+tipoString+" ");
					
					//Peso
					leer.skipBytes(20);
					lista.add("peso="+leer.readInt()+" ");
					
					//Altura
					lista.add("altura="+leer.readInt());

					System.out.println("Personaje "+lista.toString());
				}
			}
			
			if(!tipoExiste) {
				System.out.printf("No existen %ss en el fichero.",tipoSeleccionado);
			} else {
				System.out.printf("Se han encontrado %d %ss%n",encontrado,tipoSeleccionado);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
