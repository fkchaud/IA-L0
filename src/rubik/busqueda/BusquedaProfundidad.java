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
        //inicializa la b�squeda
        reporteInicioBusqueda();
        //inicializa la lista abierta vac�a.
        abierta = new LinkedList<NodoBusqueda>(); 
        //inicializa un booleano para el resultado de la b�squeda
        boolean solucionEncontrada = false;
        //inicializa el estado inicial como nodo incial
        NodoBusqueda nodoActual = new NodoBusqueda(inicial,null,null);
        nodoActual.setCosto(0);
        nodoActual.setProfundidad(0);
        //traza
        traza = new TrazaGenerica(nodoActual);
        //agrego el nodo inicial a la lista abierta
        abierta.add(nodoActual);
        //inicializo un nodo soluci�n vac�o.
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
                //eval�o si el nodo es el objetivo
                if(nodoActual.getEstado().esFinal()){
                    //si es el objetivo, cambio el booleano a true
                    solucionEncontrada = true;
                    nodoSolucion = nodoActual;
                } else {
                    //si no es el objetivo, expando los nodos y contin�o
                    abierta.addAll(0, expandirNodo(nodoActual));
                }
            }
        }
        // Se establece el contador de nodos sobrantes seg�n la longitud de la lista abierta
        reporteNodosSobrantes(abierta.size());
        // Se para el temporizador
        reporteFinBusqueda();

        if(nodoSolucion == null) {
            // si no hay soluci�n, devuelve una lista vac�a
            return new Vector<Operador>();
        }
        else {
            // si hay soluci�n, busca y devuelve el camino
            return encontrarCamino(nodoSolucion); 
        }    
    }

}
