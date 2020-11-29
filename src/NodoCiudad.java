
/**
 * @author Claudio__o
 *
 */
public class NodoCiudad {

	/* Ciudad que guarda el nodo */
	private Ciudad ciudad;
	/* NodoCiudad siguiente */
	private NodoCiudad next;
	/* NodoCiudad anterior */
	private NodoCiudad prev;
	
	/**
	 * Funci�n que se encarga que crear un nodo
	 * 
	 * @param ciudad : ciudad que contendra el nodo
	 */
	public NodoCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
		this.next = null;
	}
	
	/**
	 *  Funci�n que se encarga de devolver la ciudad que contiene el nodo actual
	 * 
	 * @return Ciudad
	 */
	public Ciudad getCiudad() {
		return this.ciudad;
	}
	
	/**
	 * Funci�n que se encagar de cambiar el nodo siguiente al nodo actual
	 * 
	 * @param next : Nuevo nodo anterior
	 */
	public void setNext(NodoCiudad next) {
		this.next = next;
	}
	
	/**
	 * Funci�n que se encagar de cambiar el nodo siguiente al nodo actual
	 * 
	 * @return NodoCiudad
	 */
	public NodoCiudad getNext() {
		return this.next;
	}
	
	/**
	 * Funci�n que se encagar de cambiar el nodo anterior al nodo actual
	 * 
	 * @param prev : Nuevo nodo anterior
	 */
	public void setPrev(NodoCiudad prev) {
		this.prev = prev;
	}
	
	/**
	 * Funci�n que se encarga de devolver el nodo anterior al nodo actual
	 * 
	 * @return NodoCiudad
	 */
	public NodoCiudad getPrev() {
		return this.prev;
	}

}
