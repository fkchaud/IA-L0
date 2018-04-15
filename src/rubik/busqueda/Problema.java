package rubik.busqueda;

import java.util.Vector;

/**
 * Clase generica (indepeniente de estados y algoritmos concretos) que representa
 * un problema de busqueda en espacio de estados.
 * Esta caracterizado por un Estado inicial y un metodo de Busqueda
 * Inteligencia Artificial 2016
 */
public class Problema {
    /** Estado inicial del problema*/
    private Estado inicial;
    /** Método de busqueda e utilizar en la resolución del problema */
    private Busqueda buscador;

    public Problema(Estado inicial, Busqueda buscador) {
        this.inicial = inicial;
        this.buscador = buscador;
    }

    public void setBuscador(Busqueda buscador) {
        this.buscador = buscador;
    }

    public void setInicial(Estado inicial) {
        this.inicial = inicial;
    }
    
    public Busqueda getBuscador (){
        return buscador;
    }
    
    /**
     * Aplica el metodo de Busqueda de este Problema concreto para resolverlo.
     * Devuelve la lista de Operadores que permiten alcanzar un Estado final desde
     * el Estado inicial del Problema
     * @return null o Vector de Operadores
     */
    public Vector<Operador> obtenerSolucion() {
        return(buscador.buscarSolucion(inicial));
    }
    
    
    
}
