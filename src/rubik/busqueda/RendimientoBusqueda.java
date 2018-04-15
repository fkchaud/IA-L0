package rubik.busqueda;

/** Clase abstracta que guarda los metodos requeridos para
 * calular las medidas de rendimiento para evaluar los algoritmos de busqueda.
 * <p>
 * Inteligencia Artificial 2016
 */

public abstract class RendimientoBusqueda {

  /**
   * Tiempo de búsqueda en segundos.
   * Inicialmente almacena el horario de inicio de la búsqueda, y cuando esta
   * termina guarda la duración total de la búsqueda, en segundos.
   */
  long tiempoBusquedaSeg;  
  /**
   * Tiempo de búsqueda en milisegundos.
   * Inicialmente almacena el horario de inicio de la búsqueda, y cuando esta
   * termina guarda la duración total de la búsqueda, en milisegundos.
   */
  long tiempoBusquedaMili; 
  /**
   * Cantidad de nodos explorados.
   * Cantidad de nodos que son explorados en la búsqueda.
   */
  long cantidadNodosExplorados; 
  /**
   * Cantidad de nodos sobrantes.
   * Cantidad de nodos que quedan en la lista abierta al final de la búsqueda.
   */
  long cantidadNodosSobrantes;  

   // debe llamarse al finalzar la busqueda antes de retornar el camino solucion
  /**
   * Obtiene el reporte de rendimiento.
   * Devuelve un string indicando el tiempo de búsqueda en milisegundos, el
   * tiempo de búsqueda en segundos, la cantidad de nodos generados, y la
   * cantidad de nodos explorados.
   * @return Reporte del rendimiento.
   */
  public String getReporteCompleto(){
         return ("Tiempo de busqueda: " + tiempoBusquedaMili + " milisegundos (" + tiempoBusquedaSeg + " segundos)" +
        "\n"+"Nodos Generados: " + (cantidadNodosExplorados + cantidadNodosSobrantes) +
        "\n"+"Nodos Explorados: " + cantidadNodosExplorados) ;
   }
  
  /** 
   * Inicializa los atributos de búsqueda.
   * {@link #cantidadNodosExplorados} = 0,<br>
   * {@link #cantidadNodosSobrantes} = 0,<br>
   * {@link #tiempoBusquedaMili} como tiempo actual en milisegundos, y<br>
   * {@link #tiempoBusquedaSeg} como tiempo actual en segundos.<br>
   * El tiempo de búsqueda se inicializa como el tiempo de inicio de la búsqueda,
   * y no como 0.
   */
  protected void reporteInicioBusqueda() {
    cantidadNodosExplorados = 0;
    cantidadNodosSobrantes = 0;
    tiempoBusquedaMili = System.currentTimeMillis();
    tiempoBusquedaSeg = (System.currentTimeMillis()/ 1000);
  }

  /**
   * Aumenta en 1 la cantidad de nodos explorados.
   * Aumenta en 1 el atributo {@link #cantidadNodosExplorados}. Se llama cuando
   * se evalúa un nodo extraído de la lista abierta.
   */
  protected void reporteNodosExplorados() {
    cantidadNodosExplorados++;
  }
  /**
   * Aumenta la cantidad de nodos explorados.
   * Aumenta en <code>cantidad</code> el atributo
   * {@link #cantidadNodosExplorados}. Se llama cuando se evalúa un nodo
   * extraído de la lista abierta.
   * @param cantidad Cantidad de nodos explorados a agregar.
   */
  protected void reporteNodosExplorados(long cantidad) {
    cantidadNodosExplorados += cantidad;
  }
  /**
   * Aumenta la cantidad de nodos sobrantes.
   * Aumenta en <code>cantidad</code> el atributo
   * {@link #cantidadNodosSobrantes}. Se llama cuando se evalúa un nodo extraído
   * de la lista abierta.
   * @param cantidad Cantidad de nodos sobrantes a agregar.
   */
  protected void reporteNodosSobrantes(long cantidad) {
    cantidadNodosSobrantes += cantidad;
  }
  /**
   * Establece el tiempo final de la búsqueda.
   * Calcula la diferencia entre el tiempo final de la búsqueda y el tiempo
   * inicial de la búsqueda, dando como resultado la duración de la búsqueda.
   */
  protected void reporteFinBusqueda() {
    tiempoBusquedaMili = System.currentTimeMillis() - tiempoBusquedaMili;
    tiempoBusquedaSeg = (System.currentTimeMillis()/ 1000) - tiempoBusquedaSeg;
  }
  
  /**
   * Devuelve la cantidad de nodos generados.
   * @return cantidad de nodos explorados + sobrantes.
   */
  public long getNroNodosGenerados() {
    return cantidadNodosExplorados + cantidadNodosSobrantes;
  }
  /**
   * Devuelve la cantidad de nodos explorados.
   * @return cantidad de nodos explorados.
   */
  public long getNroNodosExplorados() {
    return cantidadNodosExplorados;
  }
  /**
   * Devuelve la cantidad de nodos sobrantes.
   * @return cantidad de nodos sobrantes.
   */
  public long getNroNodosSobrantes() {
    return cantidadNodosSobrantes;
  }
  /**
   * Devuelve el tiempo de búsqueda en milisegundos.
   * Devuelve la hora de inicio o el tiempo de la búsqueda, según cuándo sea
   * llamado el método, en milisegundos.
   * @return Tiempo de búsqueda en milisegundos.
   */
  public long getTiempoBusquedaMili() {
    return tiempoBusquedaMili;
  }
  /**
   * Devuelve el tiempo de búsqueda en segundos.
   * Devuelve la hora de inicio o el tiempo de la búsqueda, según cuándo sea
   * llamado el método, en segundos.
   * @return Tiempo de búsqueda en segundos.
   */
  public long getTiempoBusquedaMinu() {
    return tiempoBusquedaSeg;
  }
  

}
