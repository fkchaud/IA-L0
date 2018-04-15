package rubik.busqueda;

import java.util.LinkedList;
import java.util.Vector;

/* Clase abstracta que guarda las implementaciones genericas para una Busqueda en Arbol
 * Hereda de la clase RendimientoBusqueda, 
 * para calcular medidas de evaluacion de las estrategias de busqueda
 * Usa lista ABIERTOS (LinkedList)
 * implementa el metodo expandirNodo()
 * implementa el metodo encontrarCamino()
 */

public abstract class BusquedaArbol extends RendimientoBusqueda{
	LinkedList<NodoBusqueda> abierta;
	TrazaGenerica traza;

    /**
     * Expande los nodos hijos del nodo padre.
     * Obtiene todos los nodos hijos del nodo padre. Como es Búsqueda de Árbol,
     * no realiza control de estados repetidos en lista cerrada.
     * @param nodoPadre El nodo del cuál se desea obtener hijos.
     * @return Lista de nodos hijos. Vacía si no existen nodos.
     */
    protected LinkedList<NodoBusqueda> expandirNodo(NodoBusqueda nodoPadre) {
        //inicializa la lista de nodos
        LinkedList<NodoBusqueda> expandidos = new LinkedList<NodoBusqueda>();
        //para cada operador del nodo padre...
        for (Operador op : nodoPadre.getEstado().operadoresAplicables()) {
            //instancio el nuevo nodo, cuyo estado se obtiene aplicando la operación al estado del nodo padre
            NodoBusqueda n = new NodoBusqueda(nodoPadre.getEstado().aplicarOperador(op), nodoPadre, op);
            //se settean la profundidad y el costo del nodo
            n.setProfundidad(nodoPadre.getProfundidad()+1);
            n.setCosto(nodoPadre.getCosto()+1);
            //y se guarda en la lista
            expandidos.add(n);
        }
        //devuelve la lista de nodos expandidos
        return expandidos;
    }

    /**
     * Arma el camino recorrido hasta el nodo final.
     * Recorre los nodos a través de sus padres, y devuelve una lista con los
     * operadores utilizados durante el camino. También muestra en consola el
     * reporte de búsqueda.
     * @param nodoFinal Nodo solución al que se llegó con este camino.
     * @return Camino, lista de operadores, efectuado para llegar al nodo final.
     */
    protected Vector<Operador> encontrarCamino(NodoBusqueda nodoFinal) {
        //crea la lista de operadores vacía
        Vector<Operador> camino = new Vector<Operador>();
        //se recorrerán todos los nodos, a través de sus padres, hasta llegar al inicial
        NodoBusqueda nodoPaso = nodoFinal;
        //mientras que el nodoIntermedio tenga un padre, significa que no es el inicial
        while (nodoPaso.getPadre() != null){
            //al principio de la lista, inserto el operador
            camino.insertElementAt(nodoPaso.getOperador(), 0);
            //y el nuevo nodoIntermedio va a ser el padre del actual
            nodoPaso = (NodoBusqueda) nodoPaso.getPadre();
        }
        
        System.out.println("\n REPORTE DE BUSQUEDA");
        System.out.println("---------------------------------------");
        //Imprimo Reporte de busqueda antes de retornar el camino para llegar a la solucion
        System.out.println(getReporteCompleto());
        return camino;
    }

}
