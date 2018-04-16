package rubik.busqueda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

/** Estrategia de Busqueda en grafo Costo Uniforme.
 * Implementacion de la interfaz Busqueda
 * Hereda de la clase abstracta BusquedaGrafo (generica independiente de la estrategia)
 * Implementa el metodo buscarSolucion() y devuelve un vector de operadores (solucion)
 */

public class BusquedaCosteUniformeG extends BusquedaGrafo implements Busqueda {
    
    /**
     * Busca un camino solución al problema.
     * Utiliza la estrategia de búsqueda de costo uniforme para buscar la
     * solución al problema. Comprueba estados repetidos.
     * @param inicial El estado inicial del problema.
     * @return Camino solución al problema. Devuelve una lista vacía si no
     *      encontró solución.
     */
    @Override
    public Vector<Operador> buscarSolucion(Estado inicial){
        //Antes de comenzar se inicializa el tiempo para la medida de rendimiento
        reporteInicioBusqueda();
        listaCerrada = new HashMap<Estado, NodoBusqueda>();
        listaAbierta = new LinkedList<NodoBusqueda>();
        boolean solucionEncontrada = false;
        NodoBusqueda nodoSolucion = null;
        NodoBusqueda nodoActual = new NodoBusqueda(inicial, null, null);
        nodoActual.setCosto(0);
        nodoActual.setProfundidad(0);
        traza = new TrazaGenerica(nodoActual);
        listaAbierta.add(nodoActual);
        
        while (!solucionEncontrada) {
            if (listaAbierta.isEmpty()) {
                break;
            } else {
                traza.imprimirInicioIteracion(listaAbierta);
                nodoActual = getNodoMenorCostoListaAbierta();
                reporteNodosExplorados();
                if (!listaCerrada.containsKey(nodoActual.getEstado())) {
                    if (nodoActual.getEstado().esFinal()) {
                        solucionEncontrada = true;
                        nodoSolucion = nodoActual;
                    } else {
                        listaCerrada.put(nodoActual.getEstado(),nodoActual);
                        listaAbierta.addAll(expandirNodo(nodoActual));
                    }
                }
            }
        }
        reporteNodosSobrantes(listaAbierta.size());
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
    private NodoBusqueda getNodoMenorCostoListaAbierta() {
        NodoBusqueda nodoMenorCosto = listaAbierta.get(0);
        
        for (NodoBusqueda n : listaAbierta) {
            if (n.getCosto() < nodoMenorCosto.getCosto()) {
                nodoMenorCosto = n;
            } else if (n.getCosto() == nodoMenorCosto.getCosto()) {
                //TO DO gestionar desempates xD
            }
        }
        
        listaAbierta.remove(nodoMenorCosto);
        return nodoMenorCosto;
    }

}
