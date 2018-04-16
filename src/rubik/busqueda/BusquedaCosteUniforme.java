package rubik.busqueda;

import java.util.LinkedList;
import java.util.Vector;

/** Estrategia de Busqueda en Arbol Costo Uniforme.
 * Implementacion de la interfaz Busqueda
 * Hereda de la clase abstracta BusquedaArbol (generica independiente de la estrategia)
 * Implementa el metodo buscarSolucion() y devuelve un vector de operadores (solucion)
 */

public class BusquedaCosteUniforme extends BusquedaArbol implements Busqueda {

    /**
     * Busca un camino solución al problema.
     * Utiliza la estrategia de búsqueda de costo uniforme para buscar la
     * solución al problema.
     * @param inicial El estado inicial del problema.
     * @return Camino solución al problema. Devuelve una lista vacía si no
     *      encontró solución.
     */
    @Override
    public Vector<Operador> buscarSolucion(Estado inicial){
        //Antes de comenzar se inicializa el tiempo para la medida de rendimiento
        reporteInicioBusqueda();
        abierta = new LinkedList<NodoBusqueda>();
        boolean solucionEncontrada = false;
        NodoBusqueda nodoSolucion = null;
        NodoBusqueda nodoActual = new NodoBusqueda(inicial, null, null);
        nodoActual.setCosto(0);
        nodoActual.setProfundidad(0);
        traza = new TrazaGenerica(nodoActual);
        abierta.add(nodoActual);
        
        while (!solucionEncontrada) {
            if (abierta.isEmpty()) {
                break;
            } else {
                traza.imprimirInicioIteracion(abierta);
                nodoActual = getNodoMenorCostoFrontera();
                reporteNodosExplorados();
                if (nodoActual.getEstado().esFinal()) {
                    solucionEncontrada = true;
                    nodoSolucion = nodoActual;
                } else {
                    abierta.addAll(expandirNodo(nodoActual));
                }
            }
        }
        reporteNodosSobrantes(abierta.size());
        reporteFinBusqueda();
        if(nodoSolucion == null) {
          return new Vector<Operador>();
        }
        else {
          return encontrarCamino(nodoSolucion);
        }
    }

    /**
     * Elimina y devueve el nodo de menor costo en la frontera.
     * Busca cuál es el nodo de menor costo en la lista abierta o frontera, lo
     * devuelve, y lo elimina de la lista.
     * @return Nodo de menor costo en la frontera.
     */
    private NodoBusqueda getNodoMenorCostoFrontera() {
        NodoBusqueda nodoMenorCosto = abierta.get(0);
        
        for (NodoBusqueda n : abierta) {
            if (n.getCosto() < nodoMenorCosto.getCosto()) {
                nodoMenorCosto = n;
            } else if (n.getCosto() == nodoMenorCosto.getCosto()) {
                //TO DO gestionar desempates xD
            }
        }
        
        abierta.remove(nodoMenorCosto);
        return nodoMenorCosto;
    }
}
