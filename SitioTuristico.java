import java.util.ArrayList;

public class SitioTuristico{
	
	private String Nombre;
	private String Departamento;
	private String Municipio;
	private String Descripcion;
	private float PrecioNacional;
	private float PrecioExtranjero;
	
	
	public SitioTuristico(ArrayList<String> DatosNuevoSitio){
		
		Nombre = DatosNuevoSitio.get(0);
		Departamento = DatosNuevoSitio.get(2);
		Municipio = DatosNuevoSitio.get(3);
		Descripcion = DatosNuevoSitio.get(5);
		PrecioNacional = Float.parseFloat(DatosNuevoSitio.get(6));
		PrecioExtranjero = Float.parseFloat(DatosNuevoSitio.get(7));
	}
	
	public String toString(){
		
		return "\nLos datos del sitio turistico '" + Nombre + "' son:\n\t-Ubicacion: " + Municipio + ", " + Departamento +"\n\t-Descripcion: " + Descripcion + "\n\t-Precio nacionales: Q" + PrecioNacional + "\n\t-Precio extranjeros: Q" + PrecioExtranjero;
		
	}
	
	public static ArrayList<ArrayList<String>> FiltrarSitiosPorRegion(String NombreRegion, ArrayList<ArrayList<String>> DatosSitiosTuristicos){
		
		ArrayList<String> Regiones = new ArrayList<String>();
		
		for (int i=0; i<DatosSitiosTuristicos.size(); i++){
			ArrayList<String> DatosSitio = DatosSitiosTuristicos.get(i);
			Regiones.add(DatosSitio.get(1));
		}

		ArrayList<Integer> PosicionesRegion = new ArrayList<Integer>();
		
		for (int i=0;i<Regiones.size(); i++){
			if (NombreRegion.equals(Regiones.get(i))){
				PosicionesRegion.add(i);
			}
		}
		
		ArrayList<ArrayList<String>> SitiosDeUnaRegion = new ArrayList<ArrayList<String>>();
		
		for (int i=0; i<PosicionesRegion.size(); i++){
			int posicion = PosicionesRegion.get(i);
			ArrayList<String> DatosSitio = DatosSitiosTuristicos.get(posicion);
			SitiosDeUnaRegion.add(DatosSitio);
		}
		
		return SitiosDeUnaRegion;
	}
	
	public static ArrayList<ArrayList<String>> FiltrarSitiosPorMontoNac(float MontoMax, ArrayList<ArrayList<String>> SitiosAFiltrar){
		
		ArrayList<Float> PreciosNac = new ArrayList<Float>();
		
		for (int i=0; i<SitiosAFiltrar.size(); i++){
			ArrayList<String> DatosSitio = SitiosAFiltrar.get(i);
			PreciosNac.add(Float.parseFloat(DatosSitio.get(6)));
		}
		
		ArrayList<Integer> PosicionesPreciosNacQueCumplen = new ArrayList<Integer>();
		
		for (int i=0;i<PreciosNac.size(); i++){
			if (PreciosNac.get(i) <= MontoMax){
				PosicionesPreciosNacQueCumplen.add(i);
			}
		}
		
		ArrayList<ArrayList<String>> DatosSitiosElegidos = new ArrayList<ArrayList<String>>();
		
		for (int i=0; i<PosicionesPreciosNacQueCumplen.size(); i++){
			int posicion = PosicionesPreciosNacQueCumplen.get(i);
			ArrayList<String> DatosSitio = SitiosAFiltrar.get(posicion);
			DatosSitiosElegidos.add(DatosSitio);
		}
		
		while (DatosSitiosElegidos.size()>10){
			DatosSitiosElegidos.remove(0);
		}
		
		return DatosSitiosElegidos;
	}
	
	public static ArrayList<ArrayList<String>> FiltrarSitiosPorMontoExt(float MontoMax, ArrayList<ArrayList<String>> SitiosAFiltrar){
		
		ArrayList<Float> PreciosExt = new ArrayList<Float>();
		
		for (int i=0; i<SitiosAFiltrar.size(); i++){
			ArrayList<String> DatosSitio = SitiosAFiltrar.get(i);
			PreciosExt.add(Float.parseFloat(DatosSitio.get(7)));
		}
		
		ArrayList<Integer> PosicionesPreciosExtQueCumplen = new ArrayList<Integer>();
		
		for (int i=0;i<PreciosExt.size(); i++){
			if (PreciosExt.get(i) <= MontoMax){
				PosicionesPreciosExtQueCumplen.add(i);
			}
		}
		
		ArrayList<ArrayList<String>> DatosSitiosElegidos = new ArrayList<ArrayList<String>>();
		
		for (int i=0; i<PosicionesPreciosExtQueCumplen.size(); i++){
			int posicion = PosicionesPreciosExtQueCumplen.get(i);
			ArrayList<String> DatosSitio = SitiosAFiltrar.get(posicion);
			DatosSitiosElegidos.add(DatosSitio);
		}
		
		while (DatosSitiosElegidos.size()>10){
			DatosSitiosElegidos.remove(0);
		}
		
		return DatosSitiosElegidos;
		
	}
	
}