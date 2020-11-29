/**
 * 
 */

/**
 * @author Claudio__o
 *
 */
public class Ciudad {

	/***
	 * Atributos de la clase ciudad
	 */
	/* Nombre de la ciudad */
	private String nombre;
	/* i, posici�n i en la matriz de distancias */
	private int i;
	/* j, posici�n j en la matriz de distancias */
	private int j;
	
	/**
	 * Constructor encargado de instanciar a una ciudad
	 * recibe como parametros el nombre, la posicion i, la posicion j 
	 * 
	 * @param nombre	: Nombre de la ciudad
	 * @param i			: Posici�n i en la matriz de distancias
	 * @param j			: Posici�n j en la matriz de distancias
	 */
	public Ciudad(String nombre, int i, int j) {
		
		this.nombre 	= nombre;
		this.i 			= i;
		this.j 			= j;
		
	}
	/**
	 * Funci�n que se encarga de cambiar el nombre de la ciudad
	 * 
	 * @param nombre: nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Funci�n que se encarga de devolver el nombre de una ciudad
	 * 
	 * @return string : Devuelve el nombre de la ciudad
	 */
	public String getNombre() {
		return this.nombre;
	}
	/**
	 * Funci�n que se encarga de cambiar la posici�n i de la ciudad
	 * 
	 * @param i : Nueva posici�n i en la matriz
	 */
	public void setI(int i) {
		this.i = i;
	}
	/**
	 * Funci�n que se encarga de devolver la posicion i de la ciudad
	 * 
	 * @return int : Devuelve la posicion i de la ciudad
	 */
	public int getI() {
		return this.i;
	}
	/**
	 * Funcion que se encarga de cambiar la posici�n j de la ciudad
	 * 
	 * @param j : Nueva posici�n j en la matriz
	 */
	public void setJ(int j) {
		this.j = j;
	}
	/**
	 * Funci�n que se encarga de devolver la posicion j de la ciudad
	 * 
	 * @return int : Devuelve la posicion j de la ciudad
	 */
	public int getJ() {
		return this.j;
	}
	/**
	 * Funci�n que se encarga de devolver la distancia entre la ciudad misma y otra ciudad indicada
	 * 
	 * @param matrizDistancias 	: Matriz donde se guardan las distancias entre cada ciudad
	 * @param ciudad			: Ciudad con la cual se determinar� la distancia
	 * @return	int 			: Devuelve la distancia entre las ciudades
	 */
	public int getDistancia(int [][] matrizDistancias, Ciudad ciudad) {
		
		int distancia = matrizDistancias[ciudad.getJ()][this.j];
		
		return distancia;
	}
}
