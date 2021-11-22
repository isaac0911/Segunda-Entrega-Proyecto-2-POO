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
	
	public static ArrayList<ArrayList<String>> FiltrarSitiosPorDepa(String NombreDepa, ArrayList<ArrayList<String>> DatosSitiosTuristicos){
		
		ArrayList<String> NombresDepartamentos = new ArrayList<String>();
		
		for (int i=0; i<DatosSitiosTuristicos.size(); i++){
			ArrayList<String> DatosSitio = DatosSitiosTuristicos.get(i);
			NombresDepartamentos.add(DatosSitio.get(2));
		}
		
		ArrayList<Integer> PosicionesDepartamentosQueCumplen = new ArrayList<Integer>();
		
		for (int i=0;i<NombresDepartamentos.size(); i++){
			if (NombreDepa.equals(NombresDepartamentos.get(i))){
				PosicionesDepartamentosQueCumplen.add(i);
			}
		}
		
		ArrayList<ArrayList<String>> DatosSitiosElegidos = new ArrayList<ArrayList<String>>();
		
		for (int i=0; i<PosicionesDepartamentosQueCumplen.size(); i++){
			int posicion = PosicionesDepartamentosQueCumplen.get(i);
			ArrayList<String> DatosSitio = DatosSitiosTuristicos.get(posicion);
			DatosSitiosElegidos.add(DatosSitio);
		}
		
		return DatosSitiosElegidos;
		
	}
	
	public static ArrayList<ArrayList<String>> FiltrarSitiosMasEconomicos(ArrayList<ArrayList<String>> SitiosAFiltrar, String TipoTurista){
		
		if(TipoTurista=="Nacional"){
			
			while(SitiosAFiltrar.size()>5){
				
				ArrayList<Float> PreiosNac = new ArrayList<Float>();
		
				for (int i=0; i<SitiosAFiltrar.size(); i++){
					ArrayList<String> DatosSitio = SitiosAFiltrar.get(i);
					PreiosNac.add(Float.parseFloat(DatosSitio.get(6)));
				}
				
				int posPrecioMasAlto = 0;
				float PrecioMasAlt = PreiosNac.get(0);
			
				for (int i=0; i<PreiosNac.size(); i++){
					float Precio = PreiosNac.get(i);
					if(Precio>PrecioMasAlt){
						PrecioMasAlt = Precio;
						posPrecioMasAlto = i;
					}
				}
				
				SitiosAFiltrar.remove(posPrecioMasAlto);
				
			}
			
		}else if (TipoTurista=="Extranjero"){
			
			while(SitiosAFiltrar.size()>5){
				
				ArrayList<Float> PreiosExt = new ArrayList<Float>();
				
				for (int i=0; i<SitiosAFiltrar.size(); i++){
					ArrayList<String> DatosSitio = SitiosAFiltrar.get(i);
					PreiosExt.add(Float.parseFloat(DatosSitio.get(7)));
				}
				
				int posPrecioMasAlto = 0;
				float PrecioMasAlt = PreiosExt.get(0);
				
				for (int i=0; i<PreiosExt.size(); i++){
					float Precio = PreiosExt.get(i);
					if(Precio>PrecioMasAlt){
						PrecioMasAlt = Precio;
						posPrecioMasAlto = i;
					}
				}
				
				SitiosAFiltrar.remove(posPrecioMasAlto);
				
			}
			
		}
		
		return SitiosAFiltrar;
		
	}
	
	public static ArrayList<ArrayList<String>> FiltrarSitiosDeLaCap (ArrayList<ArrayList<String>> DatosSitiosTuristicos){
		
		ArrayList<String> NombresMunicipios = new ArrayList<String>();
		
		for (int i=0; i<DatosSitiosTuristicos.size(); i++){
			ArrayList<String> DatosSitio = DatosSitiosTuristicos.get(i);
			NombresMunicipios.add(DatosSitio.get(3));
		}
		
		ArrayList<Integer> PosicionesSitiosQueCumplen = new ArrayList<Integer>();
		
		for (int i=0;i<NombresMunicipios.size(); i++){
			if ("Guatemala".equals(NombresMunicipios.get(i))){
				PosicionesSitiosQueCumplen.add(i);
			}
		}
		
		ArrayList<ArrayList<String>> DatosSitiosElegidos = new ArrayList<ArrayList<String>>();
		
		for (int i=0; i<PosicionesSitiosQueCumplen.size(); i++){
			int posicion = PosicionesSitiosQueCumplen.get(i);
			ArrayList<String> DatosSitio = DatosSitiosTuristicos.get(posicion);
			DatosSitiosElegidos.add(DatosSitio);
		}
		
		return DatosSitiosElegidos;
		
	}
	
	public static ArrayList<ArrayList<String>> FiltrarSitiosPorTipo(String TipoDeSitios, ArrayList<ArrayList<String>> DatosSitiosTuristicos){
		
		ArrayList<String> Tipos = new ArrayList<String>();
		
		for (int i=0; i<DatosSitiosTuristicos.size(); i++){
			ArrayList<String> DatosSitio = DatosSitiosTuristicos.get(i);
			Tipos.add(DatosSitio.get(4));
		}
		
		ArrayList<Integer> PosicionesSitiosQueCumplen = new ArrayList<Integer>();
		
		for (int i=0;i<Tipos.size(); i++){
			if (TipoDeSitios.equals(Tipos.get(i))){
				PosicionesSitiosQueCumplen.add(i);
			}
		}
		
		ArrayList<ArrayList<String>> DatosSitiosElegidos = new ArrayList<ArrayList<String>>();
		
		for (int i=0; i<PosicionesSitiosQueCumplen.size(); i++){
			int posicion = PosicionesSitiosQueCumplen.get(i);
			ArrayList<String> DatosSitio = DatosSitiosTuristicos.get(posicion);
			DatosSitiosElegidos.add(DatosSitio);
		}
		
		return DatosSitiosElegidos;
		
	}
	
}