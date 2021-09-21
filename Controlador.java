import java.io.IOException;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Controlador{
	
	public static void MetodoGeneral(){
		
		boolean SePudoLeerCSV = true;
		
		ArrayList<ArrayList<String>> DatosSitiosTuristicos = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> DatosHoteles = new ArrayList<ArrayList<String>>();
		
		try{
			DatosSitiosTuristicos = LeerCSVSitiosTuristicos();
			DatosHoteles = LeerCSVHoteles();
		}catch (IOException e){
			String s = "Ha ocurrido un error: " + e.getMessage();
			SePudoLeerCSV = false;
			Vista.MostrarMensaje(s);
		}
		
		if (SePudoLeerCSV){
			
			Vista.MensajeBienvenida();
			
			Ciclo(DatosSitiosTuristicos, DatosHoteles);
			
		}
		
	}
	
	private static ArrayList<ArrayList<String>> LeerCSVSitiosTuristicos() throws IOException{
		
		String nombreArchivo = "DatosSitiosTuristicos.csv";
		String Directorio = "C:\\ArchivosCSV\\DatosSitiosTuristicos.csv";
		try{
			ArrayList<ArrayList<String>> DatosSitiosTuristicos = new ArrayList<ArrayList<String>>();
			Path filePath = Paths.get(Directorio);
			BufferedReader br = Files.newBufferedReader(filePath);
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] datosLinea = linea.split(";");
				ArrayList<String> datosTemporal = new ArrayList<String>();
				for (String dato : datosLinea){
					datosTemporal.add(dato);
				}
				DatosSitiosTuristicos.add(datosTemporal);
			}
			
			return (DatosSitiosTuristicos);
		
		}catch(IOException e){
			e.printStackTrace();
			String s = "Compruebe que existe un archivo llamado " + nombreArchivo + " en la siguiente ubicacion: " + Directorio;
			throw new IOException(s);
		}
		
	}
	
	private static ArrayList<ArrayList<String>> LeerCSVHoteles() throws IOException{
		
		String nombreArchivo = "DatosHoteles.csv";
		String Directorio = "C:\\ArchivosCSV\\DatosHoteles.csv";
		try{
			ArrayList<ArrayList<String>> DatosHoteles = new ArrayList<ArrayList<String>>();
			Path filePath = Paths.get(Directorio);
			BufferedReader br = Files.newBufferedReader(filePath);
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] datosLinea = linea.split(";");
				ArrayList<String> datosTemporal = new ArrayList<String>();
				for (String dato : datosLinea){
					datosTemporal.add(dato);
				}
				DatosHoteles.add(datosTemporal);
			}
			
			return (DatosHoteles);
		
		}catch(IOException e){
			e.printStackTrace();
			String s = "Error!! Compruebe que existe un archivo llamado " + nombreArchivo + " en la siguiente ubicacion: " + Directorio;
			throw new IOException(s);
		}
		
	}
	
}