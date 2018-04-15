package rubik.busqueda;

import java.util.LinkedList;
import java.util.Vector;

/* Estrategia de Busqueda en arbol en Profundidad, 
 * Implementacion de la interfaz Busqueda
 * Hereda de la clase abstracta BusquedaArbol (generica independiente de la estrategia)
 * Implementa el metodo buscarSolucion() y devuelve un vector de operadores (solucion)
 */

public class BusquedaProfundidad extends BusquedaArbol implements Busqueda {

    @Override
  public Vector<Operador> buscarSolucion(Estado inicial){
    
    abierta = new LinkedList<NodoBusqueda>();
  
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

}
