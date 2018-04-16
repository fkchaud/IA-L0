package rubik.busqueda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

/* Estrategia de Busqueda en grafo en Profundidad, 
 * Implementacion de la interfaz Busqueda
 * Hereda de la clase abstracta BusquedaGrafo (generica independiente de la estrategia)
 * Implementa el metodo buscarSolucion() y devuelve un vector de operadores (solucion)
 */

public class BusquedaProfundidadG extends BusquedaGrafo implements Busqueda {

    /**
     * Busca un camino soluci�n al problema.
     * Utiliza la estrategia de b�squeda en profundidad para buscar la soluci�n
     * al problema. Comprueba estados repetidos.
     * @param inicial El estado inicial del problema.
     * @return Camino soluci�n al problema. Devuelve una lista vac�a si no
     *      encontr� soluci�n.
     */
    @Override
    public Vector<Operador> buscarSolucion(Estado inicial){
        //Antes de comenzar la busqueda se contabiliza tiempo llamando metodo de la clase RendimientoBusqueda
        reporteInicioBusqueda();
        //inicializo las listas abierta y cerrada vac�as
        listaCerrada = new HashMap<Estado, NodoBusqueda>();
        listaAbierta = new LinkedList<NodoBusqueda>();
        //inicializa un booleano para el resultado de la b�squeda
        boolean solucionEncontrada = false; 
        //inicializo un nodo soluci�n vac�o (nulo)
        NodoBusqueda nodoSolucion = null;
        //inicializa el estado inicial como nodo incial
        NodoBusqueda nodoActual = new NodoBusqueda(inicial,null,null);
        nodoActual.setProfundidad(0);   
        nodoActual.setCosto(0); 
        //creo una Traza con el nodo raiz(actual)
        traza = new TrazaGenerica(nodoActual);
        //agrego el nodo inicial a la lista abierta
        listaAbierta.add(nodoActual);
        while(!solucionEncontrada) {
          if(listaAbierta.isEmpty()) {
            break;  
          }
          else {
            //muestro estado de lista abierta al coienzo de cada interaci�n
            traza.imprimirInicioIteracion(listaAbierta);
            //saco el primer elemento de la lista abierta
            nodoActual = listaAbierta.pollFirst();
            //Antes de evaluar si el nodo es soluci�n contabilizo nodos explorados con la clase RendimientoBusqueda
            reporteNodosExplorados();
            //Si el estado del nodo actual no est� en la lista cerrada...
            if(!listaCerrada.containsKey(nodoActual.getEstado())) {
                //Si el nodo actual es el objetivo
                if (nodoActual.getEstado().esFinal()) {
                    solucionEncontrada = true;
                    nodoSolucion = nodoActual;
                }
                // si el estado actual no es objetivo lo expando (genero y pongo hijos)
                else {
                    listaCerrada.put(nodoActual.getEstado(), nodoActual);
                    listaAbierta.addAll(0, expandirNodo(nodoActual));
                }
            }
          }
        }
        // al terminar contabilizo nodos sobrantes con la clase RendimientoBusqueda
        reporteNodosSobrantes(listaAbierta.size());
        // Contabilizo tiempo al finalizar busqueda con la clase RendimientoBusqueda
        reporteFinBusqueda();
        
        if(nodoSolucion == null) {
          return new Vector<Operador>();
        }
        else {
          return encontrarCamino(nodoSolucion);
        }
    }

}
