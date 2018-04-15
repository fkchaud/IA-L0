package rubik.busqueda;

/**
 * Nodos a almacenar como parte de los algoritmos de busqueda
 * Inteligencia Artificial 2016
 */
public class Nodo {
    /** Estado almacenado en este Nodo*/
    private Estado estado;
    /** Nodo padre de este*/
    private Nodo padre;

    public Nodo(Estado estado, Nodo padre) {
        this.estado = estado;
        this.padre = padre;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }
    
    @Override
    public String toString() {
        return("ESTADO:"+estado.toString()+"\nPADRE"+padre);
    }
    
}
