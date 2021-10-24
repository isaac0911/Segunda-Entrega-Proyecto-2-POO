import java.util.Scanner;
import java.util.ArrayList;

public class Vista1 {
	
	public static ArrayList<String> PedirMontoYRegion(){
		
		//Pedir Monto y region para un hotel
		
		//Pedir region a visitar
		
		String region = "";
		
		int numero = 0;
		boolean validacion = false;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\nIngrese la region del pais donde se hospedara\n1.\tMetropolitana\n2.\tCentral\n3\tSuroccidente\n4.\tNoroccidente\n5.\tSuroriente\n6.\tNororiente\n7.\tNorte\n8.\tPeten\n");
		
		String dato = scan.nextLine();
		
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
		
		System.out.println("\nIngrese el monto maximo (en quetzales) que esta dispuesto a pagar por una noche en un hotel\n");
		
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
		
		ArrayList<String> MontoyRegionHotel = new ArrayList<String>();
		
		MontoyRegionHotel.add(region);
		MontoyRegionHotel.add(Double.toString(montoMax));
		
		return MontoyRegionHotel;
		
		
	}
	
	public static boolean PreguntarVerInfo(String SitioUHotel){
		//Se pregunta si se desea ver la informacion de otro hotel luego de que ya se mostro la informacion de alguno prevaimente
		
		boolean confirmacion = false;
		
		int numero = 0;
		boolean validacion = false;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\nDesea ver la informacion de algun otro " + SitioUHotel + "?\n1.\tSI\n2.\tNO\n");
		
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
	
	/*public static float PedirMontoEnCap(){
		
		Pedir monto minimo para un Hotel en la capital
		
	}*/
	
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
	
}