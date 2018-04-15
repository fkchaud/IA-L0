package rubik.busqueda;

import java.util.Vector;

/* Estrategia de Busqueda en grafo en Profundidad Interactiva, 
 * Implementacion de la interfaz Busqueda
 * Hereda de la clase abstracta BusquedaGrafo (generica independiente de la estrategia)
 * Implementa el metodo buscarSolucion() y devuelve un vector de operadores (solucion)
 */

public class BusquedaProfundidadIterativaG extends BusquedaGrafo implements Busqueda {

  BusquedaProfundidadLimitadaG busquedapl = new BusquedaProfundidadLimitadaG();
  int profundidadActual = 0;

    @Override
public Vector<Operador> buscarSolucion(Estado inicial){
    
    Vector<Operador> caminoSolucion = null;
                
     /*
     * ......
     */

    return caminoSolucion;
	}

}
