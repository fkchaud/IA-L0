package rubik.busqueda;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Vector;

/** Estrategia de Busqueda en Arbol Costo Uniforme.
 * Implementacion de la interfaz Busqueda
 * Hereda de la clase abstracta BusquedaArbol (generica independiente de la estrategia)
 * Implementa el metodo buscarSolucion() y devuelve un vector de operadores (solucion)
 */

public class BusquedaCosteUniforme extends BusquedaArbol implements Busqueda {

    /**
     * Busca un camino soluci�n al problema.
     * Utiliza la estrategia de b�squeda de costo uniforme para buscar la
     * soluci�n al problema.
     * @param inicial El estado inicial del problema.
     * @return Camino soluci�n al problema. Devuelve una lista vac�a si no
     *      encontr� soluci�n.
     */
    @Override
    public Vector<Operador> buscarSolucion(Estado inicial){
        //Antes de comenzar se inicializa el tiempo para la medida de rendimiento
        reporteInicioBusqueda();
        //inicializo la listas abierta vac�as
        abierta = new LinkedList<NodoBusqueda>();
        //inicializa un booleano para el resultado de la b�squeda
        boolean solucionEncontrada = false;
        //inicializo un nodo soluci�n vac�o (nulo)
        NodoBusqueda nodoSolucion = null;
        //inicializa el estado inicial como nodo incial
        NodoBusqueda nodoActual = new NodoBusqueda(inicial, null, null);
        nodoActual.setCosto(0);
        nodoActual.setProfundidad(0);
        //creo una Traza con el nodo raiz(actual)
        traza = new TrazaGenerica(nodoActual);
        //agrego el nodo inicial a la lista abierta
        abierta.add(nodoActual);
        while (!solucionEncontrada) {
            if (abierta.isEmpty()) {
                break;
            } else {
                //muestro estado de lista abierta al coienzo de cada interaci�n
                traza.imprimirInicioIteracion(abierta);
                //saco el primer elemento de la lista abierta
                nodoActual = abierta.pollFirst();
                //Antes de evaluar si el nodo es soluci�n contabilizo nodos explorados con la clase RendimientoBusqueda
                reporteNodosExplorados();
                //Si el nodo actual es el objetivo
                if (nodoActual.getEstado().esFinal()) {
                    solucionEncontrada = true;
                    nodoSolucion = nodoActual;
                }
                // si el estado actual no es objetivo lo expando (genero y pongo hijos)
                else {
                    abierta.addAll(expandirNodo(nodoActual));
                    Collections.sort(abierta);
                }
            }
        }
        // al terminar contabilizo nodos sobrantes con la clase RendimientoBusqueda
        reporteNodosSobrantes(abierta.size());
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