package rubik.busqueda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

/* Estrategia de Busqueda en grafo Costo Uniforme, 
 * Implementacion de la interfaz Busqueda
 * Hereda de la clase abstracta BusquedaGrafo (generica independiente de la estrategia)
 * Implementa el metodo buscarSolucion() y devuelve un vector de operadores (solucion)
 */

public class BusquedaCosteUniformeG extends BusquedaGrafo implements Busqueda {
    @Override
  public Vector<Operador> buscarSolucion(Estado inicial){
    listaCerrada = new HashMap<Estado, NodoBusqueda>();
    listaAbierta = new LinkedList<NodoBusqueda>();
    NodoBusqueda nodoSolucion = null;

     /*
     * ......
     */

    if(nodoSolucion == null) {
      return new Vector<Operador>();
    }
    else {
      return encontrarCamino(nodoSolucion);
    }
	}

  private NodoBusqueda getNodoMenorCostoListaAbierta() {
     /*
     * ......
     */
     return null;
  }

}
