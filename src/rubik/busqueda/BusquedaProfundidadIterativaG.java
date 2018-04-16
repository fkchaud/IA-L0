package rubik.busqueda;

import java.util.Vector;

/* Estrategia de Busqueda en grafo en Profundidad Interactiva, 
 * Implementacion de la interfaz Busqueda
 * Hereda de la clase abstracta BusquedaGrafo (generica independiente de la estrategia)
 * Implementa el metodo buscarSolucion() y devuelve un vector de operadores (solucion)
 */

public class BusquedaProfundidadIterativaG extends BusquedaGrafo implements Busqueda {

    BusquedaProfundidadLimitadaG bpl = new BusquedaProfundidadLimitadaG();
    int profundidadActual = 0;

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