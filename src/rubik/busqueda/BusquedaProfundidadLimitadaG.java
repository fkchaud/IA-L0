package rubik.busqueda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

/** Estrategia de Busqueda en grafo en Profundidad Limitada.
 * Implementacion de la interfaz Busqueda
 * Hereda de la clase abstracta BusquedaGrafo (generica independiente de la estrategia)
 * Implementa el metodo buscarSolucion() y devuelve un vector de operadores (solucion)
 */

public class BusquedaProfundidadLimitadaG extends BusquedaGrafo implements Busqueda {

    /**
     * Límite de la profundidad.
     * El límite de la profundidad se settea en 26 porque es el llamado
     * "Número de Dios", el número máximo de movimientos que son necesarios
     * para resolver cualquier cubo rubik, con giros de 90°. (El número es 20
     * para giros de 180°)
     */
    int profundidadLimite = 26;

    public void setProfundidadLimite(int profundidadLimite) {
        this.profundidadLimite = profundidadLimite;
    }
    public int getProfundidadLimite() {
        return profundidadLimite;
    }
    /**
     * Settea automáticamente el mejor límite de la profundidad.
     * El límite de la profundidad se settea en 26 porque es el llamado
     * "Número de Dios", el número máximo de movimientos que son necesarios
     * para resolver cualquier cubo rubik, con giros de 90°. (El número es 20
     * para giros de 180°)
     */
    public void mejorProfundidadLimite(){
        this.profundidadLimite = 26;
    }

    /**
     * Busca un camino solución al problema.
     * Utiliza la estrategia de búsqueda en profundidad limitada para buscar la
     * solución al problema. Comprueba estados repetidos.
     * @param inicial El estado inicial del problema.
     * @return Camino solución al problema. Devuelve una lista vacía si no
     *      encontró solución.
     */
    @Override
    public Vector<Operador> buscarSolucion(Estado inicial){
        //Antes de comenzar la busqueda se contabiliza tiempo llamando metodo de la clase RendimientoBusqueda
        reporteInicioBusqueda();
        //inicializo las listas abierta y cerrada vacías
        listaCerrada = new HashMap<Estado, NodoBusqueda>();
        listaAbierta = new LinkedList<NodoBusqueda>();
        //inicializa un booleano para el resultado de la búsqueda
        boolean solucionEncontrada = false; 
        //inicializo un nodo solución vacío (nulo)
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
          } else {
            //muestro estado de lista abierta al coienzo de cada interación
            traza.imprimirInicioIteracion(listaAbierta);
            //saco el primer elemento de la lista abierta
            nodoActual = listaAbierta.pollFirst();
            //Antes de evaluar si el nodo es solución contabilizo nodos explorados con la clase RendimientoBusqueda
            reporteNodosExplorados();
            //Si el estado del nodo actual no está en la lista cerrada...
            if(busquedaGrafoA(nodoActual)) {
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
    
    @Override
    protected LinkedList<NodoBusqueda> expandirNodo(NodoBusqueda nodoPadre) {
        if (nodoPadre.getProfundidad() >= profundidadLimite) {
            return new LinkedList<NodoBusqueda>();
        } else {
            return super.expandirNodo(nodoPadre);
        }
    }
}
