package rubik.busqueda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

/* Clase abstracta que guarda las implementaciones genericas para una Busqueda en Grafo
 * Hereda de la clase RendimientoBusqueda, 
 * para calcular medidas de evaluacion de las estrategias de busqueda
 * Usa lista ABIERTOS (LinkedList) y lista CERRADOS (Hastable) usando Estado como clave
 * implementa el metodo expandirNodo()
 * implementa el metodo encontrarCamino()
 */

public abstract class BusquedaGrafo  extends RendimientoBusqueda{
    HashMap<Estado, NodoBusqueda>   listaCerrada; 
    LinkedList<NodoBusqueda>        listaAbierta;
    TrazaGenerica                   traza;

    /**
     * Expande los nodos hijos del nodo padre.
     * Obtiene todos los nodos hijos del nodo padre. Como es Búsqueda de Grafo,
     * sí realiza control de estados repetidos en lista cerrada.
     * @param nodoPadre El nodo del cuál se desea obtener hijos.
     * @return Lista de nodos hijos. Vacía si no existen nodos.
     */
    protected LinkedList<NodoBusqueda> expandirNodo(NodoBusqueda nodoPadre) {
        //inicializa la lista de nodos
        LinkedList<NodoBusqueda> expandidos = new LinkedList<NodoBusqueda>(); 
        //para cada operador del nodo padre...
        for(Operador op : nodoPadre.getEstado().operadoresAplicables()) {
            //instancio el nuevo nodo, cuyo estado se obtiene aplicando la operación al estado del nodo padre
            NodoBusqueda n = new NodoBusqueda(nodoPadre.getEstado().aplicarOperador(op),nodoPadre,op);
            //revisión de estados repetidos en lista cerrada
            if(busquedaGrafoA(n)) {
                //se settean la profundidad y el costo del nodo    
                n.setProfundidad(nodoPadre.getProfundidad() + 1); 
                n.setCosto(nodoPadre.getCosto() + 1);         
                //y se guarda en la lista
                expandidos.add(n); 
            }
        }
        //TRAZA muestro la lista de hijos del nodo padre expandido
        //devuelve la lista de nodos expandidos
        return expandidos;
    }
    
    /**
     * Verifica que el nodo no esté repetido en la lista cerrada.
     * @param nodo Nodo a verificar.
     * @return Verdadero si el nodo no está en la lista,
     *      falso si el nodo está en la lista.
     */
    protected boolean busquedaGrafoA(NodoBusqueda nodo){
        if(listaCerrada.containsKey(nodo.getEstado())){
            return false;
        } else return true;
    }
    
    protected boolean busquedaGrafoB(NodoBusqueda nodo){
        //¿el estado está en un nodo de la lista cerrada?
        boolean repetidoCerrada = listaCerrada.containsKey(nodo.getEstado());
        
        if(repetidoCerrada){
            //obtengo el nodo previo con el mismo estado del nodo actual
            NodoBusqueda nodoViejo = listaCerrada.get(nodo.getEstado());
            //Si el nodo previo tiene un coste menor o igual al nodo actual,
            // no necesito guardarlo porque el anterior es mejor.
            //Si el nodo nuevo es mejor, tengo que borrar los viejos de la lista
            // y proceder como si no estuviera repetido.
            if (nodoViejo.getCosto() > nodo.getCosto()) {
                //TO DO
                //Eliminar el nodo actual y todos sus hijos de la lista cerrada
            }
        }
        //¿el estado está en un nodo de la lista abierta?
        boolean repetidoAbierta = false;
        for (NodoBusqueda n : listaAbierta) {
            if (n.getEstado() == nodo.getEstado()) {
                repetidoAbierta = true;
                //Si el nodo previo tiene un coste menor o igual al nodo actual,
                // no necesito guardarlo porque el anterior es mejor.
                //Si el nodo nuevo es mejor, tengo que borrar los viejos de la
                // lista y proceder como si no estuviera repetido.
                if (n.getCosto() > nodo.getCosto()) {
                    //elimino el nodo viejo de la lista abierta
                    listaAbierta.remove(n);
                    //hago como si no estuviera repetido en la lista abierta
                    repetidoAbierta = false;
                }
            }
        }
        
        //TODO verificar el return xD
        return !(repetidoCerrada || repetidoAbierta);
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
