package rubik.busqueda;

import java.util.LinkedList;

/* Permite visualizar cada estado de la forma: 
 * Accion: <estado,(costo),profundidad>
 * Se imprime por pantalla el estado inicial 
 * y en cada interacción, al inicio, lista de estados de la frontera, 
 * y al final de la interacción el estado evaluado y sus hijos
 * Inteligencia Artificial 2016
 */
public class TrazaGenerica {
    /**
     * Constructor.
     * @param nodo Nodo raíz.
     */
    public TrazaGenerica (NodoBusqueda nodo){
          System.out.println("\n/**** Accion: <estado,(costo),profundidad>    Por ejemplo Ui: <150,(2),2> ****/");
          System.out.println("\n    Nodo raiz: " + toStringNodo(nodo));
    }

    /**
     * Muestra el nodo expandido y sus hijos.
     * Debe llamarse despues de la expansion del nodo actual evaluado.
     * @param actual El nodo actual que está siendo expandido.
     * @param sucesores Los hijos del nodo actual que ha sido expandido.
     */
    public void imprimirFinalIteracion(NodoBusqueda actual, LinkedList<NodoBusqueda> sucesores){
        System.out.println("Nodo expandido: " + toStringNodo(actual) + " \n" + 
                            "        Hijos: " + toStringListaNodos(sucesores));
    }

    /**
     * Muestra los nodos de la lista abierta.
     * Debe llamarse antes de extraer un nodo de la lista abierta.
     * @param abierta La lista abierta con los nodos a mostrar.
     */
    public void imprimirInicioIteracion(LinkedList<NodoBusqueda> abierta){
        System.out.println("\n" + 
                           "     Abierta: " + toStringListaNodos(abierta));
    }
    
    /**
     * Convierte un Nodo a un String.
     * Muestra Etiqueta del operador: &lt;valor,(costo), profundidad&gt;
     * @param nodo El nodo a convertir en String.
     * @return La cadena a la que corresponde el nodo.
     */
    private String toStringNodo(NodoBusqueda nodo){
            int valor = java.lang.Math.abs(nodo.getEstado().hashCode()/5000000);
            int costo = nodo.getCosto();
            int profundidad = nodo.getProfundidad();
            return (nodo.getOperador()!=null?nodo.getOperador().getEtiqueta():"") + ": <" + valor + ",(" + costo + ")," + profundidad +">";
    }
 
    /**
     * Convierte una lista de Nodos en un String.
     * Concatena una serie de Strings del método
     * {@link #toStringNodo(rubik.busqueda.NodoBusqueda)}
     * @param a Lista de nodos.
     * @return String de nodos.
     */
    private String toStringListaNodos(LinkedList<NodoBusqueda> a){
        String out = "";
        for(NodoBusqueda nodo : a){
            out += toStringNodo(nodo) + " ";
        }
        return out;
    }
}
    
    
    
