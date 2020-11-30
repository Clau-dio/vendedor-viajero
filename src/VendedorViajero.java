import java.util.Scanner;

/**
 * 
 */

/**
 * @author Claudio__o
 *
 */
public class VendedorViajero {

	/**
	 * Función encargada de llenar la matriz de distancias con valores random
	 * 
	 * @param cantCiudades : cantidad de ciudades a visitar
	 * @return int[][]
	 */
	public static int[][] llenarMatriz(int cantCiudades){
		/* Creamos una matriz NxN para llenarla con las distancias */
		int ciudades[][] = new int[cantCiudades][cantCiudades];
		
		for(int i = 0; i < cantCiudades ; i++) {
			for(int j = i; j < cantCiudades; j++) {
				
				/* si i = j, entonces significa que es la misma ciudad entonces su distancia es 0 */
				if(i == j) {
					ciudades[i][j] = 0;
				}else {
					/* De lo contrario creamos un valor random entre 1 y 100
					 * y lo agragamos en cada posicion
					 * por ejemplo A respecto B y B respecto A*/
					ciudades[i][j] = (int) Math.floor(Math.random()*(100-1+1)+1);
					ciudades[j][i] = ciudades[i][j];
	          		//int valorEntero = Math.floor(Math.random()*(N-M+1)+M);
				}
			}
			
		}
		return ciudades;
	}

	/**
	 * Función para mostrar los valores contenidos en la matriz de distancias
	 * 
	 * @param matrizDistancias : matriz de distancias
	 */
	public static void mostrarMatriz(int[][] matrizDistancias) {
		
		for(int i = 0 ; i < matrizDistancias.length ;  i++) {
		
			for(int j = 0; j < matrizDistancias.length; j++) {
				
				System.out.print(matrizDistancias[i][j]);
				System.out.print(" ");
			}
			System.out.println("");
			
		}
		
	}
	
	/**
	 * Función encargada de llenar una lista de ciudades
	 * 
	 * @param listaCiudades
	 * @param cantidadCiudades
	 * @return ListaCiudades
	 */
	public static ListaCiudades llenarListaCiudades(int cantidadCiudades) {
		
		ListaCiudades listaCiudades = new ListaCiudades();
		
		for(int i = 0; i < cantidadCiudades; i++) {
			
			String nombre = "Ciudad " + (i+1);
			Ciudad ciudad = new Ciudad(nombre,0,i);
			listaCiudades.ingresarCiudad(ciudad);
			
		}
		
		return listaCiudades;
		
	}
	/**
	 * Función encargada de mostrar las ciudades dentro de una lista
	 * 
	 * @param ciudad
	 * @param sucesores
	 * @param matrizDistancias
	 */
	public static void mostrarSucesores(Ciudad ciudad, ListaCiudades sucesores, int [][] matrizDistancias) {
		NodoCiudad actual 	= sucesores.getFirst();
		
		int distancia = 0;
		System.out.print(" (Ciudades siguientes: ");
		while(actual != null) {
			
			System.out.print(" ");
			System.out.print(actual.getCiudad().getNombre());
			
			distancia = ciudad.getDistancia(matrizDistancias, actual.getCiudad());			
			
			System.out.print("[");
			System.out.print(distancia);
			System.out.print("mts]");
			
			actual = actual.getNext();
		}
		System.out.println(")");
		
	}
	
	/**
	 * Función que muestra el camino recorrido por el viajero
	 * ademas imprime que opciones tenía ademas de la elegida
	 * 
	 * @param listaCiudades
	 * @param matrizDistancias
	 */
	public static void dibujarCamino(ListaCiudades listaCiudades, int [][] matrizDistancias) {
		
		NodoCiudad actual 	= listaCiudades.getFirst();
		
		int distancia = 0;
		
		while(actual != null) {
			
			Ciudad ciudadActual = actual.getCiudad();
			
			if(actual.getPrev() != null) {
				distancia = actual.getCiudad().getDistancia(matrizDistancias, actual.getPrev().getCiudad());			
				System.out.println("   |");
				System.out.println("[" + distancia + "km]");
				System.out.println("   |");
				System.out.println("   V");
				
			}	
			System.out.print(ciudadActual.getNombre());
			mostrarSucesores(actual.getCiudad(), actual.getCiudad().getCiudadesSucesoras(), matrizDistancias);
			
			
			actual = actual.getNext();
		}
	}
	/**
	 * Implementación del algoritmo de busqueda en anchura
	 * 
	 * @param matrizDistancias
	 * @param ciudadesNovisitadas
	 * @param ciudadPartida
	 */
	public static ListaCiudades breadthFirst(int[][] matrizDistancias, Ciudad ciudadPartida) {
		ListaCiudades ciudadesVisitadas = new ListaCiudades();
		ListaCiudades cola = new ListaCiudades();
		cola.ingresarCiudad(ciudadPartida);
		/* Mientras la cola no esté vacía */
		while(cola.isEmpty() == false) {
			/* Sacamos la primera ciudad de la cola */
			Ciudad ciudadViajero = cola.pop();
			/* Agregamos la ciudad visitada a la lista de ciudades visitadas */
			ciudadesVisitadas.ingresarCiudad(ciudadViajero);
			/* Si tiene sucesores buscamos un ciudad cercana */
			if(ciudadViajero.getCiudadesSucesoras().isEmpty() == false) {
				/* Obtenemos una copia de los sucesores de la ciudad en la que está el viajero*/
				ListaCiudades ciudadesSucesoras = ciudadViajero.getCiudadesSucesoras().copiarLista();
				/* Buscamos la ciudad más cercana entre las ciudades sucesoras a la ultima ciudad visitada */
				Ciudad ciudadMasCercana = ciudadesSucesoras.buscarCiudadCerca(matrizDistancias, ciudadViajero);
				/* Agregamos la ciudad más cercana a la cola para visitar a los sucesores */
				cola.ingresarCiudad(ciudadMasCercana);
			}
		}
		return ciudadesVisitadas;
	}
	/**
	 * Inicia el programa con cantidad de ciudades y ciudad inicial indicada
	 * 
	 * @param cantCiudades
	 * @param ciudadInicial
	 */
	public static void iniciar(int cantCiudades, int ciudadInicial) {
		System.out.println("");
        System.out.println("<----------------------- Iniciando programa ----------------------->");
        System.out.println("");
        
        /* Variables para determinar el tiempo de ejecución*/
        double TInicio, TFin, tiempo; 
        /* Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio */
        TInicio = System.currentTimeMillis(); 
		
        /* Creamos la matriz de distancias */
        int [][] matrizDistancias = llenarMatriz(cantCiudades);
		//mostrarMatriz(matrizDistancias);
        /* Llenamos la lista de ciudades */
		ListaCiudades listaCiudades = llenarListaCiudades(cantCiudades);
		/* Definimos la ciudad de partida */
		Ciudad ciudadPartida = listaCiudades.sacarCiudadIesima(ciudadInicial);
		/* Obtenemos el camino encontrado por breadthFirst */
		ListaCiudades caminoViajero = breadthFirst(matrizDistancias, ciudadPartida);
		
		/* Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T */
		TFin = System.currentTimeMillis(); 
		/* Calculamos los milisegundos de diferencia */
		tiempo = TFin - TInicio; 
		
		/* Mostramos el camino que tomo el viajero */
		dibujarCamino(caminoViajero, matrizDistancias);
		/* Mostramos en pantalla el tiempo de ejecución en milisegundos */
		System.out.println("Tiempo de ejecución en milisegundos: " + tiempo); 
		System.out.println("<----------------------- fin programa ----------------------->");
	}
	/**
	 * Inicia el programa con una cantidad de ciudades 4 y luego aumenta en 2
	 * Hasta que se caiga
	 * 
	 */
	public static void iniciar() {
		System.out.println("");
        System.out.println("<----------------------- Iniciando programa ----------------------->");
        System.out.println("");
        
        int cantCiudades = 4;
        while(true) {
        	/* Variables para determinar el tiempo de ejecución*/
	        double TInicio, TFin, tiempo; 
	        /* Tomamos la hora en que inicio el algoritmo y la almacenamos en la variable inicio */
	        TInicio = System.currentTimeMillis(); 
	        /* Creamos la matriz de distancias */
	        int [][] matrizDistancias = llenarMatriz(cantCiudades);
			//mostrarMatriz(matrizDistancias);
	        /* Llenamos la lista de ciudades */
			ListaCiudades listaCiudades = llenarListaCiudades(cantCiudades);
			/* Definimos la ciudad de partida */
			Ciudad ciudadPartida = listaCiudades.sacarCiudadIesima(1);
			/* Obtenemos el camino encontrado por breadthFirst */
			ListaCiudades caminoViajero = breadthFirst(matrizDistancias, ciudadPartida);
	        
	        /* Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T */
			TFin = System.currentTimeMillis(); 
			/* Calculamos los milisegundos de diferencia */
			tiempo = TFin - TInicio; 
			/* Mostramos el camino que tomo el viajero */
			//dibujarCamino(caminoViajero, matrizDistancias);
			/* Mostramos la cantidad de ciudades que estamos visitando */
			System.out.println("Cantidad de ciudades visitadas: " + cantCiudades);
	        /* Mostramos en pantalla el tiempo de ejecución en milisegundos */
			System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
			
			
			/* Aumentamos la cantidad de ciudades */
			cantCiudades = cantCiudades + 2;
        }    
        //System.out.println("<----------------------- fin programa ----------------------->");
	}
	
	/**
	 * Funcion principal del programa
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String opcion = "";
		while(opcion.equals("")) {
			
			System.out.println("Desea que el programa sea automatico? (s/n): ");
			opcion = sc.nextLine();
			
			while(!opcion.equals("s") || !opcion.equals("n")){
                switch(opcion){
                    case "s": 
                    	iniciar();
                    	break;
                    case "n":
                    	Scanner dc = new Scanner(System.in);
				        int cantCiudades = -1;
				        while(cantCiudades == -1){
				            try{
				                System.out.println("Ingrese la cantidad de ciudades: ");
				                cantCiudades = Integer.parseInt(dc.nextLine());
				                
				                if (cantCiudades < 2){
				                    throw new         NumberFormatException("Deben ser como minino 2 ciudades!");
				                }
				             
				                Scanner scan = new Scanner(System.in);
				                int ciudadInicial = -1;
				                while(ciudadInicial == -1){
				                    try{
				                    	System.out.println("Ingrese la ciudad inicial (1-"+cantCiudades + "): ");
				                    	ciudadInicial = Integer.parseInt(scan.nextLine());
				                    	if (ciudadInicial < 1 || ciudadInicial > cantCiudades){
				                            throw new         NumberFormatException("Ciudad Inicial fuera de rango!");
				                        }
				                    	//sc.close();
				                    }catch(NumberFormatException nfe){
				                    	ciudadInicial = -1;
				                        System.out.println("Error:" + nfe.getMessage());
				                    }
				                }
				                
				                // Iniciamos el programa manual
				                iniciar(cantCiudades,ciudadInicial);
				                
				            }catch(NumberFormatException nfe){
				            	cantCiudades = -1;
				            	System.out.println("Error:" + nfe.getMessage());
				            }
				        }
                        break;
                    default:
                    	opcion = "";
                        System.out.println("Opcion invalida");
                }
                System.out.println("Desea que el programa sea automatico? (S/N): ");
    			opcion = sc.nextLine();
			}
			
		}
		
	}

}
