package rubik.busqueda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

/* Estrategia de Busqueda en Grafo en Anchura, 
 * Implementacion de la interfaz Busqueda
 * Hereda de la clase abstracta BusquedaGrafo (generica independiente de la estrategia)
 * Implementa el metodo buscarSolucion() y devuelve un vector de operadores (solución)
 * Usa lista ABIERTOS (LinkedList) y lista CERRADOS (Hastable usando Estado como clave)
 */

public class BusquedaAnchuraG extends BusquedaGrafo implements Busqueda {

    @Override
  public Vector<Operador> buscarSolucion(Estado inicial){
    //Antes de comenzar la busqueda se contabiliza tiempo llamando metodo de la clase RendimientoBusqueda
    listaCerrada = new HashMap<Estado, NodoBusqueda>();
    listaAbierta = new LinkedList<NodoBusqueda>();
    Boolean solucionEncontrada = false; 
    NodoBusqueda nodoSolucion = null;
    NodoBusqueda nodoActual = new NodoBusqueda(inicial,null,null);
		nodoActual.setProfundidad(0);   
		nodoActual.setCosto(0); 
		//creo una Traza con el nodo raiz(actual)
    listaAbierta.add(nodoActual);
    while(!solucionEncontrada) {
      if(listaAbierta.size() == 0) {
        break;  
      }
      else {
	//muestro estado de lista abierta al coienzo de cada interación
        nodoActual = listaAbierta.pollFirst();
       //Antes de evaluar si el nodo es solución contabilizo nodos explorados con la clase RendimientoBusqueda
        if(!listaCerrada.containsKey(nodoActual.getEstado())) {
          if(nodoActual.getEstado().esFinal()) {
            solucionEncontrada = true;
            nodoSolucion = nodoActual;
          }
          // si el estado actual no es objetivo lo expando (genero y pongo hijos)
          else {
            listaCerrada.put(nodoActual.getEstado(), nodoActual);
            listaAbierta.addAll(expandirNodo(nodoActual));
          }
        }
      }
    }
    // al terminar contabilizo nodos sobrantes con la clase RendimientoBusqueda
    // Contabilizo tiempo al finalizar busqueda con la clase RendimientoBusqueda
    if(nodoSolucion == null) {
      return new Vector<Operador>();
    }
    else {
      return encontrarCamino(nodoSolucion);
    }
  }

}
