import java.util.Scanner;
import java.util.ArrayList;

public class Vista2 extends Vista1{
	
	public static void MensajeBienvenida(){
		
		System.out.println("\n\nBIENVENIDO AL BUSCADOR DE HOTELES Y SITIOS TURISTICOS");
		
	}
	
	public static void MensajeDespedida(){
		
		System.out.println("\n-----------------------------------------------------------------------------------\nTENGA UN EXCELENTE DIA.");
		
	}
	
	public static void MostrarMensaje(String mensaje){
		
		System.out.println(mensaje);
		
	}
	
	public static int MostrarMenu(){
		
		int numero = 0;
		boolean validacion = false;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\n-----------------------------------------------------------------------------------\nIngrese la opcion que desea ejecutar\n1.\tBuscar sitios turisticos en funcion de sus necesidades\n2.\tBuscar hoteles en funcion de sus necesidades\n3.\tVer los sitios turisticos mas economicos de un departamento particular\n4.\tVer los hoteles mejor calificados de un departamento en particular\n5.\tBuscar opciones de hospedaje en Ciudad de Guatemala\n6.\tBuscar sitios turisitos en Ciudad de Guatemala\n7.\tSalir\n");
		
		String dato = scan.nextLine();
		
		validacion = comprobarNumero(dato);
		
		if (validacion){
			numero = Integer.parseInt(dato);
		}
		
		while (validacion = false || numero<1 || numero>7){
			System.out.println("\nERROR!! Ingrese un numero entre 1 y 7\n");
			dato = scan.nextLine();
			validacion = comprobarNumero(dato);
			if (validacion){
				numero = Integer.parseInt(dato);
			}
		}
		
		return (numero);
		
	}
	
	public static ArrayList<String> PedirMontoYRegion(){
		//Pedir monto y region de sitio turistico
		
		//Pedir Tipo Turista
		String TipoTurista = "";
		
		int numero = 0;
		boolean validacion = false;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\n-----------------------------------------------------------------------------------\nIngrese el tipo de turista al que pertenece\n1.\tNacional\n2.\tExtranjero\n");
		
		String dato = scan.nextLine();
		
		validacion = comprobarNumero(dato);
		
		if (validacion){
			numero = Integer.parseInt(dato);
		}
		
		while (validacion = false || numero<1 || numero>2){
			System.out.println("\nERROR!! Ingrese un numero entre 1 y 2\n");
			dato = scan.nextLine();
			validacion = comprobarNumero(dato);
			if (validacion){
				numero = Integer.parseInt(dato);
			}
		}
		
		if (numero==1){
			TipoTurista = "Nacional";
		}else if (numero==2){
			TipoTurista = "Extranjero";
		}
		
		//Pedir region a visitar
		
		String region = "";
		
		numero = 0;
		validacion = false;
		
		System.out.println("\nIngrese la region del pais por la que viajara\n1.\tMetropolitana\n2.\tCentral\n3\tSuroccidente\n4.\tNoroccidente\n5.\tSuroriente\n6.\tNororiente\n7.\tNorte\n8.\tPeten\n");
		
		dato = scan.nextLine();
		
		validacion = comprobarNumero(dato);
		
		if (validacion){
			numero = Integer.parseInt(dato);
		}
		
		while (validacion = false || numero<1 || numero>8){
			System.out.println("\nERROR!! Ingrese un numero entre 1 y 8\n");
			dato = scan.nextLine();
			validacion = comprobarNumero(dato);
			if (validacion){
				numero = Integer.parseInt(dato);
			}
		}
		
		if (numero==1){
			region = "Metropolitana";
		}else if (numero==2){
			region = "Central";
		}else if (numero==3){
			region = "Suroccidente";
		}else if (numero==4){
			region = "Noroccidente";
		}else if (numero==5){
			region = "Suroriente";
		}else if (numero==6){
			region = "Nororiente";
		}else if (numero==7){
			region = "Norte";
		}else if (numero==8){
			region = "Peten";
		}
		
		//Pedir Monto Maximo
		
		double montoMax = -1.0;
		validacion = false;
		
		System.out.println("\nIngrese el monto maximo (en quetzales) que esta dispuesto a pagar por la entrada a un sitio turistico\n");
		
		dato = scan.nextLine();
		
		validacion = comprobarDouble(dato);
		
		if (validacion){
			montoMax = Double.parseDouble(dato);
		}
		
		while (validacion = false || montoMax<0.0){
			System.out.println("\nERROR!! Ingrese un numero mayor o igual que 0\n");
			dato = scan.nextLine();
			validacion = comprobarDouble(dato);
			if (validacion){
				montoMax = Double.parseDouble(dato);
			}
		}
		
		//Armar ArrayList
		
		ArrayList<String> MontRegTipoTurSitio = new ArrayList<String>();
		
		MontRegTipoTurSitio.add(TipoTurista);
		MontRegTipoTurSitio.add(region);
		MontRegTipoTurSitio.add(Double.toString(montoMax));
		
		return MontRegTipoTurSitio;
		
	}
	
	public static void MostrarListaString(ArrayList<String> DatosAMostrar){
		
		for (int i=0; i<DatosAMostrar.size(); i++){
			
			System.out.println(i+1 + ".\t" + DatosAMostrar.get(i));
			
		}
		
	}
	
	public static boolean PreguntaVerInfo(String SitioUHot){
		
		//Pregunta si se desea ver la informacion de un hotel
		
		boolean confirmacion = false;
		
		int numero = 0;
		boolean validacion = false;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\nDesea ver la informacion de alguno de estos " + SitioUHot + "?\n1.\tSI\n2.\tNO\n");
		
		String dato = scan.nextLine();
		
		validacion = comprobarNumero(dato);
		
		if (validacion){
			numero = Integer.parseInt(dato);
		}
		
		while (validacion = false || numero<1 || numero>2){
			System.out.println("\nERROR!! Ingrese un numero entre 1 y 2\n");
			dato = scan.nextLine();
			validacion = comprobarNumero(dato);
			if (validacion){
				numero = Integer.parseInt(dato);
			}
		}
		
		if (numero==1){
			confirmacion = true;
		}
		
		return confirmacion;
		
	}
	
	public static String PreguntarSitioUHotel (ArrayList<String> DatosAMostrar){
		
		int numero = 0;
		boolean validacion = false;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\nDe cual?");
		
		Vista2.MostrarListaString(DatosAMostrar);
		
		String dato = scan.nextLine();
		
		validacion = comprobarNumero(dato);
		
		if (validacion){
			numero = Integer.parseInt(dato);
		}
		
		while (validacion = false || numero<1 || numero>DatosAMostrar.size()){
			System.out.println("\nERROR!! Ingrese un numero entre 1 y "+ DatosAMostrar.size() +"\n");
			dato = scan.nextLine();
			validacion = comprobarNumero(dato);
			if (validacion){
				numero = Integer.parseInt(dato);
			}
		}
		
		String nombreElegido = DatosAMostrar.get(numero-1);
		
		return nombreElegido;
	}
	
	private static boolean comprobarDouble(String conjuntoCaracteres){
		
		try{
			Double.parseDouble(conjuntoCaracteres);
			return true;
		}catch (NumberFormatException nfe){
			return false;
		}
		
	}
	
	private static boolean comprobarNumero(String conjuntoCaracteres){
		try{
			Integer.parseInt(conjuntoCaracteres);
			return true;
		}
		catch (NumberFormatException nfe){
			return false;
		}
	}
	
	public static String PedirDepartamento(ArrayList<String> NombresDepartamentos){
		
		int numero = 0;
		boolean validacion = false;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\nIngrese el departamento que desea analizar");
		
		for (int i=0; i<NombresDepartamentos.size(); i++){
			
			System.out.println((i+1) + ".\t" + NombresDepartamentos.get(i));
			
		}
		
		String dato = scan.nextLine();
		
		validacion = comprobarNumero(dato);
		
		if (validacion){
			numero = Integer.parseInt(dato);
		}
		
		while (validacion = false || numero<1 || numero>NombresDepartamentos.size()){
			System.out.println("\nERROR!! Ingrese un numero entre 1 y " + NombresDepartamentos.size() + "\n");
			dato = scan.nextLine();
			validacion = comprobarNumero(dato);
			if (validacion){
				numero = Integer.parseInt(dato);
			}
		}
		
		String departamento = NombresDepartamentos.get(numero-1);
		
		return (departamento);
		
	}
	
	public static String PedirTipoTurista(){
		
		String TipoTurista = "";
		
		int numero = 0;
		boolean validacion = false;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\n-----------------------------------------------------------------------------------\nIngrese el tipo de turista al que pertenece\n1.\tNacional\n2.\tExtranjero\n");
		
		String dato = scan.nextLine();
		
		validacion = comprobarNumero(dato);
		
		if (validacion){
			numero = Integer.parseInt(dato);
		}
		
		while (validacion = false || numero<1 || numero>2){
			System.out.println("\nERROR!! Ingrese un numero entre 1 y 2\n");
			dato = scan.nextLine();
			validacion = comprobarNumero(dato);
			if (validacion){
				numero = Integer.parseInt(dato);
			}
		}
		
		if (numero==1){
			TipoTurista = "Nacional";
		}else if (numero==2){
			TipoTurista = "Extranjero";
		}
		
		return TipoTurista;
	}
	
	public static float PedirMontoEnCap(){
		
		double montoMax = -1.0;
		boolean validacion = false;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\nIngrese el monto maximo (en quetzales) que esta dispuesto a pagar por una noche en un hotel de la capital\n");
		
		String dato = scan.nextLine();
		
		validacion = comprobarDouble(dato);
		
		if (validacion){
			montoMax = Double.parseDouble(dato);
		}
		
		while (validacion = false || montoMax<0.0){
			System.out.println("\nERROR!! Ingrese un numero mayor o igual que 0\n");
			dato = scan.nextLine();
			validacion = comprobarDouble(dato);
			if (validacion){
				montoMax = Double.parseDouble(dato);
			}
		}
		
		String cadena = String.valueOf(montoMax);
		
		float numero = Float.parseFloat(cadena);
		
		return numero;
	}
	
	public static float PedirMontoSitioTuristicoEnCap(){
		
		double montoMax = -1.0;
		boolean validacion = false;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\nIngrese el monto maximo (en quetzales) que esta dispuesto a pagar por la entrada a un sitio turistico en Ciudad de Guatemala\n");
		
		String dato = scan.nextLine();
		
		validacion = comprobarDouble(dato);
		
		if (validacion){
			montoMax = Double.parseDouble(dato);
		}
		
		while (validacion = false || montoMax<0.0){
			System.out.println("\nERROR!! Ingrese un numero mayor o igual que 0\n");
			dato = scan.nextLine();
			validacion = comprobarDouble(dato);
			if (validacion){
				montoMax = Double.parseDouble(dato);
			}
		}
		
		String cadena = String.valueOf(montoMax);
		
		float numero = Float.parseFloat(cadena);
		
		return numero;
		
	}
	
}