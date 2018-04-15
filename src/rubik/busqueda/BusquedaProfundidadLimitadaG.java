package rubik.busqueda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

/* Estrategia de Busqueda en grafo en Profundidad Limitada, 
 * Implementacion de la interfaz Busqueda
 * Hereda de la clase abstracta BusquedaGrafo (generica independiente de la estrategia)
 * Implementa el metodo buscarSolucion() y devuelve un vector de operadores (solucion)
 */

public class BusquedaProfundidadLimitadaG extends BusquedaGrafo implements Busqueda {

  int profundidadLimite = 2;

  public void setProfundidadLimite(int profundidadLimite) {
    this.profundidadLimite = profundidadLimite;
  }
  public int getProfundidadLimite() {
    return profundidadLimite;
  }

  public Vector<Operador> buscarSolucion(Estado inicial){

    int profundidadActual = 0;
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

}
