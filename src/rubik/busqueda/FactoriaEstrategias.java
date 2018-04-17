/*
 * Esta clase es una factoria de estrategias de busqueda que implementen la interface Busqueda.java.
 * ...
 *
 *
 */

package rubik.busqueda;

public class FactoriaEstrategias {
    /**
     * Entero para la Estrategia de Busqueda en Anchura en Arbol.
     */
    public static final int ANCHURA_ARBOL = 0;
    /**
     * Entero para la Estrategia de Busqueda de Coste Uniforme en Arbol.
     */
    public static final int COSTO_UNIFORME_ARBOL = 1;
    /**
     * Entero para la Estrategia de Busqueda en Profundidad en Arbol.
     */
    public static final int PROFUNDIDAD_ARBOL = 2;
    /**
     * Entero para la Estrategia de Busqueda en Anchura en Grafo.
     */
    public static final int ANCHURA_GRAFO = 3;
    /**
     * Entero para la Estrategia de Busqueda de Coste Uniforme en Grafo.
     */
    public static final int COSTO_UNIFORME_GRAFO = 4; 
    /**
     * Entero para la Estrategia de Busqueda en Profundidad en Grafo.
     */
    public static final int PROFUNDIDAD_GRAFO = 5; 
    /**
     * Entero para la Estrategia de Busqueda en Profundidad Limitada en Grafo.
     */
    public static final int PROFUNDIDAD_LIMIT_GRAFO = 6;
    /**
     * Entero para la Estrategia de Busqueda en Profundidad Iterativa en Grafo.
     */
    public static final int PROFUNDIDAD_INTE_GRAFO = 7;    
    /**
     * ¿Se puede borrar?
     */
    private static int estrategia = 1; 

    /**
     * Devuelve la estrategia solicitada.
     * Devuelve una estrategia de búsqueda, que pueden ser:
     * <br>{@link rubik.busqueda.BusquedaAnchura}
     * <br>{@link rubik.busqueda.BusquedaCosteUniforme}
     * <br>{@link rubik.busqueda.BusquedaProfundidad}
     * <br>{@link rubik.busqueda.BusquedaAnchuraG}
     * <br>{@link rubik.busqueda.BusquedaCosteUniformeG}
     * <br>{@link rubik.busqueda.BusquedaProfundidadG}
     * <br>{@link rubik.busqueda.BusquedaProfundidadIterativaG}
     * @param estrategia Número de la estrategia
     * @return Estrategia solicitada
     */
    public static Busqueda getEstrategia(int estrategia){ 
        FactoriaEstrategias.estrategia = estrategia;  
        switch(estrategia) {
          case 0:
            System.out.println("BUSQUEDA ANCHURA EN ARBOL");
            return new BusquedaAnchura();
          case 1:
            System.out.println("BUSQUEDA COSTO UNIFORME EN ARBOL");
            return new BusquedaCosteUniforme();
          case 2:
            System.out.println("BUSQUEDA PROFUNDIDAD EN ARBOL");
            return new BusquedaProfundidad();
          case 3:
            System.out.println("BUSQUEDA ANCHURA EN GRAFO");
            return new BusquedaAnchuraG();          
          case 4:
            System.out.println("BUSQUEDA COSTO UNIFORME EN GRAFO");
            return new BusquedaCosteUniformeG();
          case 5:
            System.out.println("BUSQUEDA PROFUNDIDAD EN GRAFO");
            return new BusquedaProfundidadG();
          case 6:
            System.out.println("BUSQUEDA PROFUNDIDAD LIMITADA EN GRAFO");
            BusquedaProfundidadLimitadaG b = new BusquedaProfundidadLimitadaG();
            b.mejorProfundidadLimite();
            return b;
          case 7:
            System.out.println("BUSQUEDA PROFUNDIDAD INTERACTIVA EN GRAFO");
            return new BusquedaProfundidadIterativaG();
          default:  return null;
        }
    } 
}
