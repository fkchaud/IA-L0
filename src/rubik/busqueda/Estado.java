package rubik.busqueda;

import java.util.Vector;

/**
 * Interfaz generico para los estados del espacio de estados
 * Inteligencia Artificial 2016
 */
public interface Estado {
      
    /** 
     * Devuelve el Vector con la lista de Operadores aplicables sobre este Estado
     * @return Vector de operadores aplicables
     */
    public Vector<Operador> operadoresAplicables();
    
    /**
     * Indica si este es un estad final (solucion)
     * @return true si es un estado final
     */
    public boolean esFinal();
    
    /**
     * Genera un nuevo Estado resultante de aplicar el Operador indicado 
     * sobr el este Estado
     * @param o Operador a aplicar
     * @return Estado resultante
     */
    public Estado aplicarOperador(Operador o);
    
    /** Codigo hash del Estado, necesario para usar Tablas Hash */
    @Override
    public int hashCode();
    
    /** Igualdad entre Estados, necesario para usar Listas */
    @Override
    public boolean equals(Object o);
    
    @Override
    public String toString();
}
