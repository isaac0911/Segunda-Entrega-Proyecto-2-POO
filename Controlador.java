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
	
	public static void Ciclo(ArrayList<ArrayList<String>> DatosSitiosTuristicos, ArrayList<ArrayList<String>> DatosHoteles){
		
		int opcion = -1;
		
		while (opcion != 3){
			
			opcion = Vista.MostrarMenu();
			
			if (opcion==1){
				
				//Pedir datos al usuario
				ArrayList<String> MontRegTipoTurSitio = Vista.PedirMontRegTipTurSitio();
				String Nombreregion = MontRegTipoTurSitio.get(1);
				String TipoTurista = MontRegTipoTurSitio.get(0);
				float MontoMax = Float.parseFloat(MontRegTipoTurSitio.get(2));
				
				//Filtrar sitios turisticos en funcion de la region
				ArrayList<ArrayList<String>> SitiosDeUnaRegion = SitioTuristico.FiltrarSitiosPorRegion(Nombreregion, DatosSitiosTuristicos);
				
				//Filtrar en funcion del monto maximo que el usuario esta dispueso a pagar
				ArrayList<ArrayList<String>> DatosSitiosElegidos = new ArrayList<ArrayList<String>>();
				
				if(TipoTurista == "Nacional"){
					DatosSitiosElegidos = SitioTuristico.FiltrarSitiosPorMontoNac(MontoMax, SitiosDeUnaRegion);
				}else if(TipoTurista == "Extranjero"){
					DatosSitiosElegidos = SitioTuristico.FiltrarSitiosPorMontoExt(MontoMax, SitiosDeUnaRegion);
				}
				
				if(DatosSitiosElegidos.size()>0){
					//Mandar a mostrar los nombres de los sitios que pasaron el filtro
					ArrayList<String> NombresSitiosElegidos = MandarAMostrarSitios(DatosSitiosElegidos);
				
					//Mandar a mostrar datos de un sitio particular
					String SitioUHot = "sitios turisticos";
					boolean DeseaVerInfo = Vista.PreguntaVerInfo(SitioUHot);
					
					if (DeseaVerInfo){
						MandarAMostrarInfoSitio(NombresSitiosElegidos, DatosSitiosElegidos);
					}
				}else{
					//Mostrar Mensaje de fallo.
					String mensaje = "\nNo se han encontrado sitios turisticos que cumplan con los requerimientos ingresados.";
					Vista.MostrarMensaje(mensaje);
				}
				
			}else if (opcion == 2){
				
				//Pedir datos al usuario
				ArrayList<String> MontoyRegionHotel = Vista.PedirMontoyRegionHotel();
				String Nombreregion = MontoyRegionHotel.get(0);
				float MontoMax = Float.parseFloat(MontoyRegionHotel.get(1));
				
				//Filtrar hoteles en funcion de la region
				ArrayList<ArrayList<String>> HotelesDeUnaRegion = Hotel.FiltrarHotelesPorRegion(Nombreregion, DatosHoteles);
				
				//Filtrar en funcion del monto maximo que el usuario esta dispueso a pagar por una noche en un hotel
				ArrayList<ArrayList<String>> DatosHotelesElegidos = Hotel.FiltrarHotelesPorMonto(MontoMax, HotelesDeUnaRegion);
				
				if(DatosHotelesElegidos.size()>0){
					
					//Mandar a mostrar los nombres de los hoteles que pasaron el filtro
					ArrayList<String> NombresHotelesElegidos = MandarAMostrarHoteles(DatosHotelesElegidos);
				
					//Mandar a mostrar datos de un hotel particular
					String SitioUHot = "hoteles";
					boolean DeseaVerInfo = Vista.PreguntaVerInfo(SitioUHot);
					
					if (DeseaVerInfo){
						MandarAMostrarInfoHotel(NombresHotelesElegidos, DatosHotelesElegidos);
					}
					
				}else{
					
					String mensaje = "\nNo se han encontrado hoteles que cumplan con los requerimientos ingresados.";
					Vista.MostrarMensaje(mensaje);
					
				}
				
			}else if (opcion == 3){
				Vista.MensajeDespedida();
				
			}
			
		}
		
	}
	
}