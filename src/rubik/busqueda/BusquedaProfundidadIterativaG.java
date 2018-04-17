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
     * Esta instancia ejecutar� la b�squeda, con niveles de profundidad
     * diferentes.
     */
    BusquedaProfundidadLimitadaG bpl = new BusquedaProfundidadLimitadaG();
    /**
     * Profundidad actual del l�mite de la BPL.
     * La profundidad inicia en 0 y aumentar� en 1 cada vez que se cumpla una
     * iteraci�n.
     */
    int profundidadActual = 0;
    
    /**
     * Settea autom�ticamente el mejor l�mite para iniciar la iteraci�n.
     * Consideramos que el mejor l�mite es 0, porque el estado inicial podr�a
     * ser la soluci�n, y porque el libro indica que se inicia desde 0.
     */
    public void mejorProfundidadInicial () {
        this.profundidadActual = 0;
    }

    /**
     * Busca un camino soluci�n al problema.
     * Utiliza la estrategia de b�squeda en profundidad iterativa para buscar la
     * soluci�n al problema. Comprueba estados repetidos.
     * @param inicial El estado inicial del problema.
     * @return Camino soluci�n al problema. Devuelve una lista vac�a si no
     *      encontr� soluci�n.
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