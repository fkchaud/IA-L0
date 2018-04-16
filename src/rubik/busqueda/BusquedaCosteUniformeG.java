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
