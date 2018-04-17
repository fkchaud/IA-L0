package rubik.busqueda;

/**
 * Nodos usados por la BusquedaAnchura. 
 * Añade el Operador usado para generar el estado almacenado en este Nodo. 
 * Usado para simplificar la reconstrucción del camino solucion.
 * Inteligencia Artificial 2016
 */
public class NodoBusqueda extends Nodo implements Comparable<NodoBusqueda>{
    /** Operador empleado para generar el Estado almacenado en este Nodo*/
    private Operador operador;
    private int costo;
    private int profundidad;
    
    public NodoBusqueda(Estado estado, Nodo padre, Operador operador) {
        super(estado, padre);
        this.operador = operador;
    }

    public Operador getOperador() {
        return operador;
    }
    
    public void setOperador(Operador operador) {
        this.operador = operador;
    }
    
    public int getCosto(){
        return costo;
    }
    
    public void setCosto(int costo){
        this.costo = costo;
    }
    
    @Override
    public String toString(){
        return (super.toString()+"\nOPERADOR:"+operador.getEtiqueta());
    }

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    /**
     * Compara según el costo de los nodos.
     * Implementación del método compareTo para hacer a los nodos comparables.
     * Esto permite que luego se llame
     * {@link java.util.Collections#sort(java.util.List)}, y que la lista de
     * nodos se ordene de menor a mayor costo.
     * En caso de empate, se mantiene primero el más viejo (como si fuera en
     * anchura).
     * @param o
     * @return 
     */
    @Override
    public int compareTo(NodoBusqueda o) {
        return costo < o.getCosto() ? -1
             : costo > o.getCosto() ? 1
             : 0;
    }
}
