import java.util.ArrayList;

public class Hotel{
	
	private String Nombre;
	private String Direccion;
	private float PrecioNoche;
	private float Estrellas;
	private String SitioWeb;
	
	public Hotel(ArrayList<String> DatosNuevoHotel){
		
		Nombre = DatosNuevoHotel.get(0);
		Direccion = DatosNuevoHotel.get(4);
		PrecioNoche = Float.parseFloat(DatosNuevoHotel.get(6));
		Estrellas = Float.parseFloat(DatosNuevoHotel.get(7));
		SitioWeb = DatosNuevoHotel.get(5);
		
	}
	
	public String toString(){
		
		return "\n\nLos datos del " + Nombre + " son:\n\t-Direccion: " + Direccion + "\n\t-Precio por noche: Desde Q" + PrecioNoche + "\n\t-Calificacion: " + Estrellas + " estrellas de 5\n\t-SitioWeb: " + SitioWeb;
	}
	
	public static ArrayList<ArrayList<String>> FiltrarHotelesPorRegion (String Nombreregion, ArrayList<ArrayList<String>> DatosHoteles){
		
		ArrayList<String> Regiones = new ArrayList<String>();
		
		for (int i=0; i<DatosHoteles.size(); i++){
			ArrayList<String> DatosHotel = DatosHoteles.get(i);
			Regiones.add(DatosHotel.get(1));
		}

		ArrayList<Integer> PosicionesRegion = new ArrayList<Integer>();
		
		for (int i=0;i<Regiones.size(); i++){
			if (Nombreregion.equals(Regiones.get(i))){
				PosicionesRegion.add(i);
			}
		}
		
		ArrayList<ArrayList<String>> HotelesDeUnaRegion = new ArrayList<ArrayList<String>>();
		
		for (int i=0; i<PosicionesRegion.size(); i++){
			int posicion = PosicionesRegion.get(i);
			ArrayList<String> DatosHotel = DatosHoteles.get(posicion);
			HotelesDeUnaRegion.add(DatosHotel);
		}
		
		return HotelesDeUnaRegion;
		
	}
	
	public static ArrayList<ArrayList<String>> FiltrarHotelesPorMonto(float MontoMax, ArrayList<ArrayList<String>> HotelesAFiltrar){
		
		ArrayList<Float> Precios = new ArrayList<Float>();
		
		for (int i=0; i<HotelesAFiltrar.size(); i++){
			ArrayList<String> DatosHotel = HotelesAFiltrar.get(i);
			Precios.add(Float.parseFloat(DatosHotel.get(6)));
		}
		
		ArrayList<Integer> PosicionesPreciosQueCumplen = new ArrayList<Integer>();
		
		for (int i=0;i<Precios.size(); i++){
			if (Precios.get(i) <= MontoMax){
				PosicionesPreciosQueCumplen.add(i);
			}
		}
		
		ArrayList<ArrayList<String>> DatosHotelesElegidos = new ArrayList<ArrayList<String>>();
		
		for (int i=0; i<PosicionesPreciosQueCumplen.size(); i++){
			int posicion = PosicionesPreciosQueCumplen.get(i);
			ArrayList<String> DatosHotel = HotelesAFiltrar.get(posicion);
			DatosHotelesElegidos.add(DatosHotel);
		}
		
		while (DatosHotelesElegidos.size()>10){
			DatosHotelesElegidos.remove(0);
		}
		
		return DatosHotelesElegidos;
		
	}
	
	public static ArrayList<ArrayList<String>> FiltrarHotelesPorDepa(String NombreDepa, ArrayList<ArrayList<String>> DatosHoteles){
		
		ArrayList<String> NombresDepartamentos = new ArrayList<String>();
		
		for (int i=0; i<DatosHoteles.size(); i++){
			ArrayList<String> DatosHotel = DatosHoteles.get(i);
			NombresDepartamentos.add(DatosHotel.get(2));
		}
		
		ArrayList<Integer> PosicionesHotelesQueCumplen = new ArrayList<Integer>();
		
		for (int i=0;i<NombresDepartamentos.size(); i++){
			if (NombreDepa.equals(NombresDepartamentos.get(i))){
				PosicionesHotelesQueCumplen.add(i);
			}
		}
		
		ArrayList<ArrayList<String>> DatosHotelesElegidos = new ArrayList<ArrayList<String>>();
		
		for (int i=0; i<PosicionesHotelesQueCumplen.size(); i++){
			int posicion = PosicionesHotelesQueCumplen.get(i);
			ArrayList<String> DatosHotel = DatosHoteles.get(posicion);
			DatosHotelesElegidos.add(DatosHotel);
		}
		
		return DatosHotelesElegidos;
		
	}
	
	public static ArrayList<ArrayList<String>> FiltrarHotelesMejorCalific(ArrayList<ArrayList<String>> HotelesAFiltrar){
		
		while(HotelesAFiltrar.size()>5){
				
				ArrayList<Float> Calificaciones = new ArrayList<Float>();
		
				for (int i=0; i<HotelesAFiltrar.size(); i++){
					ArrayList<String> DatosHotel = HotelesAFiltrar.get(i);
					Calificaciones.add(Float.parseFloat(DatosHotel.get(7)));
				}
				
				int posCalificacionMasBaja = 0;
				float CalificacionMasBaja = Calificaciones.get(0);
			
				for (int i=0; i<Calificaciones.size(); i++){
					float Calificacion = Calificaciones.get(i);
					if(Calificacion<CalificacionMasBaja){
						CalificacionMasBaja = Calificacion;
						posCalificacionMasBaja = i;
					}
				}
				
				HotelesAFiltrar.remove(posCalificacionMasBaja);
				
			}
		
		return HotelesAFiltrar;
	}
	
	public static ArrayList<ArrayList<String>> FiltrarHotelesDeLaCapital(ArrayList<ArrayList<String>> DatosHoteles){
		
		ArrayList<String> NombresMunicipios = new ArrayList<String>();
		
		for (int i=0; i<DatosHoteles.size(); i++){
			ArrayList<String> DatosHotel = DatosHoteles.get(i);
			NombresMunicipios.add(DatosHotel.get(3));
		}
		
		ArrayList<Integer> PosicionesHotelesQueCumplen = new ArrayList<Integer>();
		
		for (int i=0;i<NombresMunicipios.size(); i++){
			if ("Guatemala".equals(NombresMunicipios.get(i))){
				PosicionesHotelesQueCumplen.add(i);
			}
		}
		
		ArrayList<ArrayList<String>> DatosHotelesElegidos = new ArrayList<ArrayList<String>>();
		
		for (int i=0; i<PosicionesHotelesQueCumplen.size(); i++){
			int posicion = PosicionesHotelesQueCumplen.get(i);
			ArrayList<String> DatosHotel = DatosHoteles.get(posicion);
			DatosHotelesElegidos.add(DatosHotel);
		}
		
		return DatosHotelesElegidos;
		
	}
	
}