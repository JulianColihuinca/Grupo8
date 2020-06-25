
package modelo;

import java.util.HashMap;
import java.util.Iterator;
/**
 * @author Grupo8
 *<br>
 *Coleccion generica
 */
public class ColeccionGenerica<T extends Cloneable> implements Cloneable {

	private HashMap<Integer,T> coleccion;
	
	public ColeccionGenerica() {
		this.coleccion= new HashMap<Integer,T>();
	}
	/**
	 * Agrega el elemento que corresponda a la coleccion generica
	 */
	public void agregaElemento(Integer valor,T elemento) {
		this.coleccion.put(valor,elemento);
	}
	/**
	 * Elimina el elemento que corresponda a la coleccion generica
	 */
	public void removerElemento(Integer valor, T elemento) {
		this.coleccion.remove(valor, elemento);
	}
	/**
	 * @return devuelve un elemento de la coleccion generica
	 */
	public T getElemento(Integer valor) {
		return this.coleccion.get(valor);
	}
	/**
	 * @return devuelve el iterator de la clase de la coleccion generica
	 */
	public Iterator<T> getValuesIterator(){
		return this.coleccion.values().iterator();
	}
	/**
	 * @return devuelve el iterator de la llave de la coleccion generica
	 */
	
	public Iterator<Integer> getKeyIterator(){
		return this.coleccion.keySet().iterator();
	}
	/**
	 * Metodo que realiza la clonacion de la coleccion generica
	 */
	public Object clone() throws CloneNotSupportedException {
		ColeccionGenerica clonado= null;
		clonado= (ColeccionGenerica) super.clone();
		Iterator<Integer> keys= clonado.coleccion.keySet().iterator();
		Iterator<T> values= clonado.coleccion.values().iterator();
		clonado.coleccion.clear();
		while (keys.hasNext() && values.hasNext()) {
			clonado.coleccion.put(keys.next(), values.next());
		}
		return clonado;
	}
}
