package rubik.busqueda;

import java.util.LinkedList;
import java.util.Vector;

/** Estrategia de Busqueda en arbol en Anchura.
 * Implementacion de la interfaz Busqueda.
 * Hereda de la clase abstracta BusquedaArbol (generica independiente de la estrategia).
 * Implementa el metodo buscarSolucion() y devuelve un vector de operadores (solución)
 */


public class BusquedaAnchura extends BusquedaArbol implements Busqueda {

    /**
     * Busca un camino solución al problema.
     * Utiliza la estrategia de búsqueda en anchura para buscar la solución al
     * problema.
     * @param inicial El estado inicial del problema.
     * @return Camino solución al problema. Devuelve una lista vacía si no
     *      encontró solución.
     */
    @Override
    public Vector<Operador> buscarSolucion(Estado inicial){
        //inicializa la búsqueda
        reporteInicioBusqueda();
        //inicializa la lista abierta vacía.
        abierta = new LinkedList<NodoBusqueda>(); 
        //inicializa un booleano para el resultado de la búsqueda
        boolean solucionEncontrada = false;
        //inicializa el estado inicial como nodo incial
        NodoBusqueda nodoActual = new NodoBusqueda(inicial,null,null);
        nodoActual.setCosto(0);
        nodoActual.setProfundidad(0);
        //traza
        traza = new TrazaGenerica(nodoActual);
        //agrego el nodo inicial a la lista abierta
        abierta.add(nodoActual);
        //inicializo un nodo solución vacío.
        NodoBusqueda nodoSolucion = null;       
        while (!solucionEncontrada) {
            if (abierta.isEmpty()) {
                break;
            } else {
                //traza
                traza.imprimirInicioIteracion(abierta);
                //saco el primer elemento de la lista abierta
                nodoActual = abierta.pollFirst();
                //aumento el contador de nodos explorados
                reporteNodosExplorados();
                //evalúo si el nodo es el objetivo
                if(nodoActual.getEstado().esFinal()){
                    //si es el objetivo, cambio el booleano a true
                    solucionEncontrada = true;
                    nodoSolucion = nodoActual;
                } else {
                    //si no es el objetivo, expando los nodos y continúo
                    abierta.addAll(expandirNodo(nodoActual));
                }
            }
        }
        // Se establece el contador de nodos sobrantes según la longitud de la lista abierta
        reporteNodosSobrantes(abierta.size());
        // Se para el temporizador
        reporteFinBusqueda();

        if(nodoSolucion == null) {
            // si no hay solución, devuelve una lista vacía
            return new Vector<Operador>();
        }
        else {
            // si hay solución, busca y devuelve el camino
            return encontrarCamino(nodoSolucion); 
        }
    }	

}
