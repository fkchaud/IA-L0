package rubik.busqueda;

import java.util.Vector;

/* Estrategia de Busqueda en grafo en Profundidad Interactiva, 
 * Implementacion de la interfaz Busqueda
 * Hereda de la clase abstracta BusquedaGrafo (generica independiente de la estrategia)
 * Implementa el metodo buscarSolucion() y devuelve un vector de operadores (solucion)
 */

public class BusquedaProfundidadIterativaG extends BusquedaGrafo implements Busqueda {

    /**
     * Instancia de BPL.
     * Esta instancia ejecutará la búsqueda, con niveles de profundidad
     * diferentes.
     */
    BusquedaProfundidadLimitadaG bpl = new BusquedaProfundidadLimitadaG();
    /**
     * Profundidad actual del límite de la BPL.
     * La profundidad inicia en 0 y aumentará en 1 cada vez que se cumpla una
     * iteración.
     */
    int profundidadActual = 0;
    
    /**
     * Settea automáticamente el mejor límite para iniciar la iteración.
     * Consideramos que el mejor límite es 0, porque el estado inicial podría
     * ser la solución, y porque el libro indica que se inicia desde 0.
     */
    public void mejorProfundidadInicial () {
        this.profundidadActual = 0;
    }

    /**
     * Busca un camino solución al problema.
     * Utiliza la estrategia de búsqueda en profundidad iterativa para buscar la
     * solución al problema. Comprueba estados repetidos.
     * @param inicial El estado inicial del problema.
     * @return Camino solución al problema. Devuelve una lista vacía si no
     *      encontró solución.
     */
    @Override
    public Vector<Operador> buscarSolucion(Estado inicial){
        //Antes de comenzar la busqueda se contabiliza tiempo llamando metodo de la clase RendimientoBusqueda
        reporteInicioBusqueda();
        Vector<Operador> caminoSolucion = null;
        boolean solucionEncontrada = false;
        while (!solucionEncontrada) {
            bpl.setProfundidadLimite(profundidadActual);
            caminoSolucion = bpl.buscarSolucion(inicial);
            reporteNodosExplorados(bpl.getNroNodosExplorados());
            reporteNodosSobrantes(bpl.getNroNodosSobrantes());
            if (!caminoSolucion.isEmpty()) {
                solucionEncontrada = true;
            } else {
                profundidadActual++;
            }
        }
        reporteFinBusqueda();
        return caminoSolucion;
    }
}