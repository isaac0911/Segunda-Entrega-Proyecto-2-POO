import java.io.IOException;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

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
			Vista2.MostrarMensaje(s);
		}
		
		if (SePudoLeerCSV){
			
			Vista2.MensajeBienvenida();
			
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
	
	private static void Ciclo(ArrayList<ArrayList<String>> DatosSitiosTuristicos, ArrayList<ArrayList<String>> DatosHoteles){
		
		int opcion = -1;
		
		ArrayList<String> NombresDepartamentos = new ArrayList<>(Arrays.asList("Guatemala","Sacatepequez", "Escuintla", "Chimaltenango", "Solola", "Suchitepequez", "Retalhuheu", "San Marcos", "Quetzaltenango", "Totonicapan", "Quiche", "Huehuetenango", "Baja Verapaz", "Alta Verapaz", "Izabal", "Zacapa", "Chiquimula", "Jutiapa", "Jalapa", "Santa Rosa", "Peten", "El Progreso"));
		
		while (opcion != 9){
			
			opcion = Vista2.MostrarMenu();
			
			if (opcion==1){
				
				//SI DESEA BUSCAR SITIOS TURISTICOS EN FUNCION DE SUS NECESIDADES 
				
				//Pedir datos al usuario
				ArrayList<String> MontRegTipoTurSitio = Vista2.PedirMontoYRegion();
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
					boolean DeseaVerInfo = Vista2.PreguntarVerInfo(SitioUHot);
					
					if (DeseaVerInfo){
						MandarAMostrarInfoSitio(NombresSitiosElegidos, DatosSitiosElegidos);
					}
				}else{
					//Mostrar Mensaje de fallo.
					String mensaje = "\nNo se han encontrado sitios turisticos que cumplan con los requerimientos ingresados.";
					Vista2.MostrarMensaje(mensaje);
				}
				
			}else if (opcion == 2){
				
				//SI DESEA BUSCAR HOTELES EN FUNCION DE SUS NECESIDADES 
				
				//Pedir datos al usuario
				ArrayList<String> MontoyRegionHotel = Vista1.PedirMontoYRegion();
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
					boolean DeseaVerInfo = Vista2.PreguntaVerInfo(SitioUHot);
					
					if (DeseaVerInfo){
						MandarAMostrarInfoHotel(NombresHotelesElegidos, DatosHotelesElegidos);
					}
					
				}else{
					
					String mensaje = "\nNo se han encontrado hoteles que cumplan con los requerimientos ingresados.";
					Vista2.MostrarMensaje(mensaje);
					
				}
				
			}else if (opcion == 3){
				
				//SI DESEA VER LOS SITIOS TURISITICOS MAS ECONOMICOS DE DETERMINADO DEPARTAMENTO
				
				//Pedir datos al usuario
				String TipoDeTurista = Vista2.PedirTipoTurista();
				String Departamento = Vista2.PedirDepartamento(NombresDepartamentos);
				
				//Filtrar sitios turisticos en funcion del departamento
				ArrayList<ArrayList<String>> SitiosDeUnDepartamento = SitioTuristico.FiltrarSitiosPorDepa(Departamento, DatosSitiosTuristicos);
				
				//Filtrar sitios turisiticos en funcion de su precio
				ArrayList<ArrayList<String>> DatosSitiosElegidos = SitioTuristico.FiltrarSitiosMasEconomicos(SitiosDeUnDepartamento, TipoDeTurista);
				
				if(DatosSitiosElegidos.size()>0){
					//Mandar a mostrar los nombres de los sitios que pasaron el filtro
					ArrayList<String> NombresSitiosElegidos = MandarAMostrarSitiosEconomicos(DatosSitiosElegidos, Departamento);
					
					//Mandar a mostrar datos de un sitio particular
					String SitioUHot = "sitios turisticos";
					boolean DeseaVerInfo = Vista2.PreguntarVerInfo(SitioUHot);
					
					if (DeseaVerInfo){
						MandarAMostrarInfoSitio(NombresSitiosElegidos, DatosSitiosElegidos);
					}
				}else{
					//Mostrar Mensaje de fallo.
					String mensaje = "\nNo se han encontrado sitios turisticos en " + Departamento + ". Lamentamos el inconveniente, nuestra base de datos aun esta en crecimiento.";
					Vista2.MostrarMensaje(mensaje);
				}
				
				
			}else if (opcion == 4){
				
				//SI VER LOS SITIOS TURISTICOS DE DETERMINADO TIPO QUE HAY EN UN DEPARTAMENTO EN PARTICULAR
				
				//Pedir datos al usuario
				String region = Vista2.PedirRegion();
				String tipoSitios = Vista2.PedirTipoSitio();
				
				//Filtrar sitios turisticos en funcion de la region
				ArrayList<ArrayList<String>> SitiosDeUnaRegion = SitioTuristico.FiltrarSitiosPorRegion(region, DatosSitiosTuristicos);
				
				//Filtrar los sitios turisticos por el tipo de sitios que el usuario desea
				ArrayList<ArrayList<String>> DatosSitiosElegidos = SitioTuristico.FiltrarSitiosPorTipo(tipoSitios, SitiosDeUnaRegion);
				
				if(DatosSitiosElegidos.size()>0){
					//Mandar a mostrar los nombres de los sitios que pasaron el filtro
					ArrayList<String> NombresSitiosElegidos = MandarAMostrarSitiosDeUnTipo(DatosSitiosElegidos, tipoSitios, region);
					
					//Mandar a mostrar datos de un sitio particular
					String SitioUHot = "sitios turisticos";
					boolean DeseaVerInfo = Vista2.PreguntarVerInfo(SitioUHot);
					
					if (DeseaVerInfo){
						MandarAMostrarInfoSitio(NombresSitiosElegidos, DatosSitiosElegidos);
					}
				}else{
					//Mostrar Mensaje de fallo.
					String mensaje = "\nNo se han encontrado sitios turisticos de tipo " + tipoSitios + " en la region " + region + ". Lamentamos el inconveniente, nuestra base de datos aun esta en crecimiento.";
					Vista2.MostrarMensaje(mensaje);
				}
				
				
			}else if (opcion == 5){
				
				//SI VER LOS HOTELES MEJOR CALIFICADOS DE UN DEPARTAMENTO PARTICULAR
				
				//Pedir datos al usuario
				String Departamento = Vista2.PedirDepartamento(NombresDepartamentos);
				
				//Filtrar hoteles en funcion del departamento
				ArrayList<ArrayList<String>> HotelesDeUnDepartamento = Hotel.FiltrarHotelesPorDepa(Departamento, DatosHoteles);
				
				//Filtrar sitios turisiticos en funcion de su calificacion
				ArrayList<ArrayList<String>> DatosHotelesElegidos = Hotel.FiltrarHotelesMejorCalific(HotelesDeUnDepartamento);
				
				if(DatosHotelesElegidos.size()>0){
					//Mandar a mostrar los nombres de los sitios que pasaron el filtro
					ArrayList<String> NombresHotelesElegidos = MandarAMostrarHotelesMejorCalific(DatosHotelesElegidos, Departamento);
					
					//Mandar a mostrar datos de un sitio particular
					String SitioUHot = "hoteles";
					boolean DeseaVerInfo = Vista2.PreguntarVerInfo(SitioUHot);
					
					if (DeseaVerInfo){
						MandarAMostrarInfoSitio(NombresHotelesElegidos, DatosHotelesElegidos);
					}
				}else{
					//Mostrar Mensaje de fallo.
					String mensaje = "\nNo se han encontrado hoteles en " + Departamento + ". Lamentamos el inconveniente, nuestra base de datos aun esta en crecimiento.";
					Vista2.MostrarMensaje(mensaje);
				}
				
			}else if (opcion == 6){
				
				//SI DESEA VER LOS HOTELES MAS BARATOS DE UN DEPARTAMENTO PARTICULAR
				
				//Pedir datos al usuario
				String Departamento = Vista2.PedirDepartamento(NombresDepartamentos);
				
				//Filtrar hoteles en funcion del departamento
				ArrayList<ArrayList<String>> HotelesDeUnDepartamento = Hotel.FiltrarHotelesPorDepa(Departamento, DatosHoteles);
				
				//Filtrar sitios turisiticos en funcion de su precio
				ArrayList<ArrayList<String>> DatosHotelesElegidos = Hotel.FiltrarHotelesMasEconomicos(HotelesDeUnDepartamento);
				
				if(DatosHotelesElegidos.size()>0){
					//Mandar a mostrar los nombres de los sitios que pasaron el filtro
					ArrayList<String> NombresHotelesElegidos = MandarAMostrarHotelesEconomicos(DatosHotelesElegidos, Departamento);
					
					//Mandar a mostrar datos de un sitio particular
					String SitioUHot = "hoteles";
					boolean DeseaVerInfo = Vista2.PreguntarVerInfo(SitioUHot);
					
					if (DeseaVerInfo){
						MandarAMostrarInfoHotel(NombresHotelesElegidos, DatosHotelesElegidos);
					}
				}else{
					//Mostrar Mensaje de fallo.
					String mensaje = "\nNo se han encontrado hoteles en el departamento de " + Departamento + ". Lamentamos el inconveniente, nuestra base de datos aun esta en crecimiento.";
					Vista2.MostrarMensaje(mensaje);
				}
				
			}else if (opcion == 7){
				
				//SI DESEA BUSCAR OPCIONES DE HOPEDAJE EN CIUDAD DE GUATEMALA
				
				//Pedir datos al usuario
				float MontoMax = Vista2.PedirMontoEnCap();
				
				//Filtrar hoteles en la capital
				ArrayList<ArrayList<String>> HotelesDeLaCapital = Hotel.FiltrarHotelesDeLaCapital(DatosHoteles);
				
				//Filtrar hoteles en funcion de su precio
				ArrayList<ArrayList<String>> DatosHotelesElegidos = Hotel.FiltrarHotelesPorMonto(MontoMax, HotelesDeLaCapital);
				
				if(DatosHotelesElegidos.size()>0){
					//Mandar a mostrar los nombres de los sitios que pasaron el filtro
					ArrayList<String> NombresHotelesElegidos = MandarAMostrarHotelesDeLaCapital(DatosHotelesElegidos);
					
					//Mandar a mostrar datos de un sitio particular
					String SitioUHot = "hoteles";
					boolean DeseaVerInfo = Vista2.PreguntarVerInfo(SitioUHot);
					
					if (DeseaVerInfo){
						MandarAMostrarInfoSitio(NombresHotelesElegidos, DatosHotelesElegidos);
					}
				}else{
					//Mostrar Mensaje de fallo.
					String mensaje = "\nNo se han encontrado hoteles en Ciudad de Guatemala que cumplan con los requisitos ingresados. Lamentamos el inconveniente, nuestra base de datos aun esta en crecimiento.";
					Vista2.MostrarMensaje(mensaje);
				}
					
			}else if (opcion == 8){
				
				//SI DESEA BUSCAR SITIOS TURISTICOS EN CIUDAD DE GUATEMALA
				
				//Pedir datos al usuario
				String TipoDeTurista = Vista2.PedirTipoTurista();
				float MontoMax = Vista2.PedirMontoSitioTuristicoEnCap();
				
				//Filtrar sitios turisticos en la capital
				ArrayList<ArrayList<String>> SitiosDeLaCapital = SitioTuristico.FiltrarSitiosDeLaCap(DatosSitiosTuristicos);
				
				//Filtrar en funcion del monto maximo que el usuario esta dispueso a pagar por la entrada a un sitio turisitico
				
				ArrayList<ArrayList<String>> DatosSitiosElegidos = new ArrayList<ArrayList<String>>();
				
				if(TipoDeTurista=="Nacional"){
					DatosSitiosElegidos = SitioTuristico.FiltrarSitiosPorMontoNac(MontoMax, SitiosDeLaCapital);
				}else if(TipoDeTurista=="Extranjero"){
					DatosSitiosElegidos = SitioTuristico.FiltrarSitiosPorMontoExt(MontoMax, SitiosDeLaCapital);
				}
				
				if(DatosSitiosElegidos.size()>0){
					
					//Mandar a mostrar los nombres de los hoteles que pasaron el filtro
					ArrayList<String> NombresSitiosElegidos = MandarAMostrarSitiosDeLaCapital(DatosSitiosElegidos);
				
					//Mandar a mostrar datos de un hotel particular
					String SitioUHot = "sitios turisticos";
					boolean DeseaVerInfo = Vista2.PreguntaVerInfo(SitioUHot);
					
					if (DeseaVerInfo){
						MandarAMostrarInfoHotel(NombresSitiosElegidos, DatosSitiosElegidos);
					}
					
				}else{
					
					String mensaje = "\nNo se han encontrado hoteles que cumplan con los requerimientos ingresados.";
					Vista2.MostrarMensaje(mensaje);
					
				}
				
			}else if (opcion == 9){
				Vista2.MensajeDespedida();
				
			}
			
		}
		
	}
	
	private static ArrayList<String> MandarAMostrarSitios(ArrayList<ArrayList<String>> DatosSitiosElegidos){
		
		String mensaje = "\nLos sitios turisticos que se adaptan mejor a sus condiciones son los siguientes:";
		Vista2.MostrarMensaje(mensaje);
				
		ArrayList<String> NombresSitiosElegidos = new ArrayList<String>();
				
		for (int i=0; i<DatosSitiosElegidos.size(); i++){
			ArrayList<String> DatosSitio = DatosSitiosElegidos.get(i);
			NombresSitiosElegidos.add(DatosSitio.get(0));
		}
				
		Vista2.MostrarListaString(NombresSitiosElegidos);
		
		return NombresSitiosElegidos;
	}
	
	private static void MandarAMostrarInfoSitio (ArrayList<String> NombresSitiosElegidos, ArrayList<ArrayList<String>> DatosSitiosElegidos){
		
		boolean DeseaVerInfo=true; 
		
		while (DeseaVerInfo){
			
			int posicion = -1;
			String NombreSitio = Vista2.PreguntarSitioUHotel(NombresSitiosElegidos);
			
			for(int i=0; i<DatosSitiosElegidos.size(); i++){
				
				ArrayList<String> DatosSitio = DatosSitiosElegidos.get(i);
				
				if(NombreSitio.equals(DatosSitio.get(0))){
					
					posicion = i;
					
				}
				
			}
			
			ArrayList<String> DatosSitioElegido = DatosSitiosElegidos.get(posicion);
			SitioTuristico Sitio = new SitioTuristico(DatosSitioElegido);
			String mensaje = Sitio.toString();
			Vista2.MostrarMensaje(mensaje);
			
			String SitioUHot = "sitios turisticos";
			DeseaVerInfo = Vista1.PreguntarVerInfo(SitioUHot);
			
		}
		
	}
	
	private static ArrayList<String> MandarAMostrarHoteles(ArrayList<ArrayList<String>> DatosHotelesElegidos){
		
		String mensaje = "\nLos hoteles que se adaptan mejor a sus condiciones son los siguientes:";
		Vista2.MostrarMensaje(mensaje);
				
		ArrayList<String> NombresHotelesElegidos = new ArrayList<String>();
				
		for (int i=0; i<DatosHotelesElegidos.size(); i++){
			ArrayList<String> DatosHotel = DatosHotelesElegidos.get(i);
			NombresHotelesElegidos.add(DatosHotel.get(0));
		}
				
		Vista2.MostrarListaString(NombresHotelesElegidos);
		
		return NombresHotelesElegidos;
		
	}
	
	private static void MandarAMostrarInfoHotel (ArrayList<String> NombresHotelesElegidos, ArrayList<ArrayList<String>> DatosHotelesElegidos){
		
		boolean DeseaVerInfo=true; 
		
		while (DeseaVerInfo){
			
			int posicion = -1;
			String NombreHotel = Vista2.PreguntarSitioUHotel(NombresHotelesElegidos);
			
			for(int i=0; i<DatosHotelesElegidos.size(); i++){
				
				ArrayList<String> DatosHotel = DatosHotelesElegidos.get(i);
				
				if(NombreHotel.equals(DatosHotel.get(0))){
					
					posicion = i;
					
				}
				
			}
			
			ArrayList<String> DatosHotelElegido = DatosHotelesElegidos.get(posicion);
			Hotel Hot = new Hotel(DatosHotelElegido);
			String mensaje = Hot.toString();
			Vista2.MostrarMensaje(mensaje);
			
			String SitioUHot = "hoteles";
			DeseaVerInfo = Vista1.PreguntarVerInfo(SitioUHot);
			
		}
		
	}
	
	private static ArrayList<String> MandarAMostrarSitiosEconomicos (ArrayList<ArrayList<String>> DatosSitiosElegidos, String Departamento){
		
		String mensaje = "\nLos sitios turisticos mas economicos de " + Departamento + " son:";
		Vista2.MostrarMensaje(mensaje);
				
		ArrayList<String> NombresSitiosElegidos = new ArrayList<String>();
				
		for (int i=0; i<DatosSitiosElegidos.size(); i++){
			ArrayList<String> DatosSitio = DatosSitiosElegidos.get(i);
			NombresSitiosElegidos.add(DatosSitio.get(0));
		}
				
		Vista2.MostrarListaString(NombresSitiosElegidos);
		
		return NombresSitiosElegidos;
		
	}
	
	private static ArrayList<String> MandarAMostrarHotelesMejorCalific (ArrayList<ArrayList<String>> DatosHotelesElegidos, String Departamento){
		
		String mensaje = "\nLos hoteles mejor calificados de " + Departamento + " son:";
		Vista2.MostrarMensaje(mensaje);
				
		ArrayList<String> NombresHotelesElegidos = new ArrayList<String>();
				
		for (int i=0; i<DatosHotelesElegidos.size(); i++){
			ArrayList<String> DatosHotel = DatosHotelesElegidos.get(i);
			NombresHotelesElegidos.add(DatosHotel.get(0));
		}
				
		Vista2.MostrarListaString(NombresHotelesElegidos);
		
		return NombresHotelesElegidos;
		
	}
	
	private static ArrayList<String> MandarAMostrarHotelesDeLaCapital(ArrayList<ArrayList<String>> DatosHotelesElegidos){
		
		String mensaje = "\nLos hoteles de Ciudad de Guatemala que se acoplan a su presupuesto son los siguientes:";
		Vista2.MostrarMensaje(mensaje);
				
		ArrayList<String> NombresHotelesElegidos = new ArrayList<String>();
				
		for (int i=0; i<DatosHotelesElegidos.size(); i++){
			ArrayList<String> DatosHotel = DatosHotelesElegidos.get(i);
			NombresHotelesElegidos.add(DatosHotel.get(0));
		}
				
		Vista2.MostrarListaString(NombresHotelesElegidos);
		
		return NombresHotelesElegidos;
		
	}
	
	private static ArrayList<String> MandarAMostrarSitiosDeLaCapital(ArrayList<ArrayList<String>> DatosSitiosElegidos){
		
		String mensaje = "\nLos sitios turisticos de Ciudad de Guatemala que se acoplan a su presupuesto son los siguientes:";
		Vista2.MostrarMensaje(mensaje);
				
		ArrayList<String> NombresHotelesElegidos = new ArrayList<String>();
				
		for (int i=0; i<DatosSitiosElegidos.size(); i++){
			ArrayList<String> DatosHotel = DatosSitiosElegidos.get(i);
			NombresHotelesElegidos.add(DatosHotel.get(0));
		}
				
		Vista2.MostrarListaString(NombresHotelesElegidos);
		
		return NombresHotelesElegidos;
		
	}
	
	private static ArrayList<String> MandarAMostrarSitiosDeUnTipo(ArrayList<ArrayList<String>> DatosSitiosElegidos, String tipoSitios, String region){
		
		String mensaje = "\nLos sitios turisticos de tipo " + tipoSitios + " que se encuentran en la region " + region + ",son los siguientes:";
		Vista2.MostrarMensaje(mensaje);
				
		ArrayList<String> NombresHotelesElegidos = new ArrayList<String>();
				
		for (int i=0; i<DatosSitiosElegidos.size(); i++){
			ArrayList<String> DatosHotel = DatosSitiosElegidos.get(i);
			NombresHotelesElegidos.add(DatosHotel.get(0));
		}
				
		Vista2.MostrarListaString(NombresHotelesElegidos);
		
		return NombresHotelesElegidos;
		
	}
	
	private static ArrayList<String> MandarAMostrarHotelesEconomicos (ArrayList<ArrayList<String>> DatosHotelesElegidos, String Departamento){
		
		String mensaje = "\nLos hoteles mas economicos del departamento de " + Departamento + " son:";
		Vista2.MostrarMensaje(mensaje);
				
		ArrayList<String> NombresHotelesElegidos = new ArrayList<String>();
				
		for (int i=0; i<DatosHotelesElegidos.size(); i++){
			ArrayList<String> DatosHotel = DatosHotelesElegidos.get(i);
			NombresHotelesElegidos.add(DatosHotel.get(0));
		}
				
		Vista2.MostrarListaString(NombresHotelesElegidos);
		
		return NombresHotelesElegidos;
		
	}
	
}