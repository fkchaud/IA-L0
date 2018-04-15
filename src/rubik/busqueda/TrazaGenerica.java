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
     public TrazaGenerica (NodoBusqueda nodo){
           System.out.println("\n/**** Accion: <estado,(costo),profundidad>    Por ejemplo Ui: <150,(2),2> ****/");
           System.out.println("\n    Nodo raiz: " + toStringNodo(nodo));
     }
    //Este metodo debe llamarse despues de la expansion del nodo actual evaluado.

    public void imprimirFinalIteracion(NodoBusqueda actual, LinkedList<NodoBusqueda> sucesores){
        System.out.println("Nodo expanido: " + toStringNodo(actual) + " \n" + 
                           "        Hijos: " + toStringListaNodos(sucesores));
    }
    //Este metodo debe llamarse antes de extraer un nodo de la lista abierta

    public void imprimirInicioIteracion(LinkedList<NodoBusqueda> abierta){
        System.out.println("\n" + 
                           "     Abierta: " + toStringListaNodos(abierta));
    }
    
    private String toStringNodo(NodoBusqueda nodo){
            int valor = java.lang.Math.abs(nodo.getEstado().hashCode()/5000000);
            int costo = nodo.getCosto();
            int profundidad = nodo.getProfundidad();
            return (nodo.getOperador()!=null?nodo.getOperador().getEtiqueta():"") + ": <" + valor + ",(" + costo + ")," + profundidad +">";
    }
 
    private String toStringListaNodos(LinkedList<NodoBusqueda> a){
        String out = "";
        for(NodoBusqueda nodo : a){
            out += toStringNodo(nodo) + " ";
        }
        return out;
    }
}
    
    
    
