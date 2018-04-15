package rubik.busqueda;

import java.util.Vector;
  
/**
 * Interfaz generico para algorimtos de busqueda
 * Inteligencia Artificial 2016
 */
public interface Busqueda {
    /**
     * Interfaz generico para algorimtos de busqueda
     * @param   inicial Estado inicial
     * @return  null o Vector con la lista de Operadores
     */
    
    public Vector<Operador> buscarSolucion(Estado inicial);
}
