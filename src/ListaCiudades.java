
/**
 * @author Claudio__o
 *
 */
public class ListaCiudades {
	
	/* Primer nodo en la lista */
	private NodoCiudad first;
	/* Ultimo nodo en la lista */
	private NodoCiudad last;
	/* Si la lista esta vacia o no */
	private boolean isEmpty;
	/* Cantidad de ciudades ingresadas en la lista */
	private int cantidadCiudades;
	
	/**
	 * 	Constructor, se encaga de crear una lista vacia
	 * 
	 */
	public ListaCiudades () {
		this.first 				= null;
        this.last 				= null;
        this.isEmpty 			= true;
        this.cantidadCiudades 	= 0;
	}
	
	/**
	 * Función que se encarga de cambiar el primer nodo en la lista
	 * 
	 * @param first : Nuevo primer nodo
	 */
	public void setFirst(NodoCiudad first) {
        this.first = first;
    }
	
	/**
	 * Función que se encarga de devolver el primer nodo en la lista
	 * 
	 * @return NodoCiudad
	 */
	public NodoCiudad getFirst() {
        return first;
    }
	
	/**
	 * Función que se encarga de cambiar el último nodo en la lista
	 * 
	 * @param last : Nuevo último nodo
	 */
	public void setLast(NodoCiudad last) {
        this.last = last;
    }
	
	/**
	 * Función que se encarga de devolver el último nodo en la lista
	 * 
	 * @return NodoCiudad
	 */
	public NodoCiudad getLast() {
        return last;
    }
	
	/**
	 * Función que se encarga de devolver si la lista esta vacía o no
	 * 
	 * @return
	 */
	public boolean isEmpty() {
    	return this.isEmpty;
    }
		
	/**
	 * Función que se encarga de devolver la cantidad de ciudades de la lista
	 * 
	 * @return int
	 */
	public int getCantidadCiudades() {
		return this.cantidadCiudades;
	}
	
	/**
	 * Función que se encarga de ingresar una nueva ciudad en la lista
	 * lo agrega al ultimo
	 * 
	 * @param ciudad : Nueva ciudad a ingresar
	 */
	public void ingresarCiudad(Ciudad ciudad) { 
		
		//Creamos un nodo nuevo para almacenar la ciudad
		NodoCiudad nuevo = new NodoCiudad(ciudad);
		
		//Si la lista está vacía, entonces el nodo a ingresar
		//será el primero y el ultimo
		if (this.isEmpty) {
			
			this.first = nuevo;
			this.last = nuevo;
			
		//si la lista no está vacía, entonces el nodo nuevo
		//será el siguiente al último actual y serú el nuevo último
		} else {
			
    	   	this.last.setNext(nuevo);
    	   	nuevo.setPrev(this.last);
    	   	this.last = nuevo;
    	   	
       	}
		//en cualquier caso la lista dejará de estar vacía
		this.isEmpty = false;
		//Aumentará en uno la cantidad de ciudades en la lista
		this.cantidadCiudades++;
		
	}
	
	/**
	 * Función que se encarga de eliminar un nodo especifico en la lista
	 * 
	 * @param nodoCiudad : recibe el nodo a eliminar
	 */
	private void eliminarNodo(NodoCiudad nodoCiudad) {
		
		/* Obtenemos el nodo siguiente al que vamos a eliminar */
		NodoCiudad anterior 	= nodoCiudad.getPrev();
		/* Obtenemos el nodo anterior al que vamos a eliminar */
		NodoCiudad siguiente 	= nodoCiudad.getNext();

		/* Si el nodo que queremos eliminar es el unico en la lista entonces
		 * la lista quedará vacia */
		if( anterior == null && siguiente == null ) {
			
			this.first 		= null;
			this.last 		= null;
			this.isEmpty 	= true;
			
		}else {
			
			/* Si es el primero el que queremos eliminar */
			if(this.getFirst() == nodoCiudad) {
				
				this.setFirst(siguiente);
				siguiente.setPrev(null);
				
			/* Si es el ultimo nodo */
			}else if(this.getLast() == nodoCiudad) {
				
				this.setLast(anterior);
				anterior.setNext(null);
				
			/* si es alguno de entre medio en la lista*/	
			}else {
				
				anterior.setNext(siguiente);
				siguiente.setPrev(anterior);
			}
		}
		
		/* En cualquier caso disminuimos en uno la cantidad de elementos en la lista */
		this.cantidadCiudades--;
	}
	/**
	 * Este método se encarga de agregar como sucesoras de una ciudad a todas
	 * las ciudades que faltan por recorrer en la lista
	 *  
	 * @param ciudad
	 */
	private void enviarSucesores(Ciudad ciudad) {
		
		NodoCiudad nodoActual = this.first;
		
		while(nodoActual != null) {
			
			ciudad.getCiudadesSucesoras().ingresarCiudad(nodoActual.getCiudad());
			nodoActual = nodoActual.getNext();
		}
		
	}
	/**
	 * Función que busca la ciudad más proxima en una lista de ciudades
	 * respecto una ciudad especifica
	 * 
	 * @param matrizDistancias  : Matriz donde se guardan las distancias entre las ciudades
	 * @param ciudadViajero		: Ciudad donde está actualmente el viajero
	 * @return Ciudad			: Devuelve la ciudad más próxima
	 */
	public Ciudad buscarCiudadCerca(int [][] matrizDistancias, Ciudad ciudadViajero) {
		
		/* Inicialmente el nodo anterior será el primer nodo de la lista de ciudades */
		NodoCiudad nodoAnterior = this.first;
		/* La primerar ciudad a comparar sera la ciudad del nodo anterior de la lista */
		Ciudad ciudadAnterior = nodoAnterior.getCiudad();
		/* Obtenemos la distancia entre la ciudad de la lista y la ciudad donde esta el viajero */
		int distanciaCiudadAnterior = ciudadAnterior.getDistancia(matrizDistancias, ciudadViajero);
		/* Definimos el nodo de la ciudad a devolver el cual inicialmente es el nodo anterior */
		NodoCiudad nodoMasProximo = nodoAnterior;
		
		/* Mientras haya una ciudad en la lista con la cual comparar */
		while(nodoAnterior.getNext() != null) {
			/* Obtenemos el siguiente nodo en la lista para comparar */
			NodoCiudad nodoActual = nodoAnterior.getNext();
			/* Obtenemos la ciudad del nodo actual donde estamos para 
			 * compararla con la ciudad del viajero */
			Ciudad ciudadActual = nodoActual.getCiudad();
			/* Obtenemos la distancia entre la ciudad actual dondes estamos
			 * y la ciudad donde esta el viajero */
			int distanciaCiudadActual = ciudadActual.getDistancia(matrizDistancias, ciudadViajero);
			/* Si la distancia de la ciudad actual en la lista es menor
			 * a la distancia de la ciudad anterior de la lista */
			if(distanciaCiudadActual < distanciaCiudadAnterior) {
				/* entonces la ciudad mas proxima a la ciudad donde esta el viajero
				 * será la ciudad actual */
				nodoMasProximo = nodoActual;
				/* Ahora la distancia anterior corresponde a la del nodo actual */
				distanciaCiudadAnterior = distanciaCiudadActual;
			}
			/* Ahora el nodo anterior corresponde al nodo actual */
			nodoAnterior = nodoActual;
		}
		/* Eliminamos de la lista de ciudades al nodo que vamos a devoler */
		this.eliminarNodo(nodoMasProximo);
		/* La ciudades que quedan son la sucesoras del nodoMasProximo */
		this.enviarSucesores(nodoMasProximo.getCiudad());
		/* Devolvemos la ciudad más proxima a la ciudad donde está el viajero*/
		return nodoMasProximo.getCiudad();
		
	}
	
	/**
	 * Función que obtiene la ciudad en la posición indicada
	 * 
	 * @param i : posición buscada
	 * @return Ciudad
	 */
	public Ciudad sacarCiudadIesima(int i) {
		
		/* Si la posicion indicada es mayor a la cantidad de elementos en la lista
		 * o es negativa entonces retornamos nulo */
		if(this.cantidadCiudades < i || i < 0) {
			
			return null;
		/* De lo contrario buscamos la ciudad en la posicion i  */
		} else {
			
			/* Inicialmente nos posicionaremos en el primer nodo */
			NodoCiudad nodoActual = this.first;
			/* Iniciamos el contador en 1 ya que inicialmente estamos en el primer nodo */
			int contador = 1;
			/* Ciudad Iesima, inicialmente nula */
			Ciudad ciudadIesima = null;
			
			/* Mientras hayan nodos en la lista */
			while(nodoActual != null) {
				/* Si el contador es igual a la posicion necesitada*/
				if(contador == i) {
					/* Encontramos la ciudad y rompemos el ciclo*/
					ciudadIesima = nodoActual.getCiudad();
					break;
				}
				/* De lo contrario nos posicionamos en el siguiente nodo */
				nodoActual = nodoActual.getNext();
				/* Aumentamos el contador */
				contador++;
			}
			/* Si la ciudad iesima fue encontrada entonces
			 * la retornamos y la eliminamos de la lista */
			if(ciudadIesima != null) {
				this.eliminarNodo(nodoActual);
				/* La ciudades que quedan son la sucesoras del nodoActual */
				this.enviarSucesores(nodoActual.getCiudad());
				return ciudadIesima;
			}else {
				return null;
			}
		}
	}
	
	/**
	 * Método para sacar la ciudad de encima de la lista
	 * ademas la elimina de la lista
	 * @return Ciudad
	 */
	public Ciudad pop() {
		
		Ciudad ciudad = this.first.getCiudad();
		
		this.eliminarNodo(this.first);
		
		return ciudad;
		
	}
}
