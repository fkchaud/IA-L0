package rubik.modelo;

import rubik.busqueda.Estado;
import rubik.busqueda.Operador;
import java.util.Vector;

/**
 * Objeto que implementa la interfaz Estado para un cubo Rubik concreto.
 * La mayor parte de los métodos definidos en el interfaz Estado se 
 * delegan en el objeto Cubo
 * IA 2011
 */
public class EstadoRubik implements Estado {
    /** Vector estático con la lista de los 12 OperadoresRubik posibles */
    private static final Vector<Operador> listaOperadoresAplicables =
            EstadoRubik.inicializarListaOperadoresAplicables();

    /** Metodo estático para inicializar el vector estÃ¡tico de operadores aplicables*/
    private static Vector<Operador> inicializarListaOperadoresAplicables() {
        Vector<Operador> lista = new Vector<Operador>(Movimiento.movimientosPosibles.length);
        for (Movimiento m : Movimiento.movimientosPosibles) {
            lista.add(new OperadorRubik(m));
        }
        return (lista);
    }
    
    /** Objeto Cubo correspondiente a este estado */
    private Cubo cubo;
    private int id=0;
    public EstadoRubik(Cubo cubo) {
        this.cubo = cubo;
        id = id+1;
     }
     public int getId(){
         return id;
     }
    public Cubo getCubo() {
        return cubo;
    }

    public void setCubo(Cubo cubo) {
        this.cubo = cubo;
    }

    public Vector<Operador> operadoresAplicables() {
        return (EstadoRubik.listaOperadoresAplicables);
    }

    public boolean esFinal() {
        return (cubo.esConfiguracionFinal());
    }

    public Estado aplicarOperador(OperadorRubik o) {
        Cubo nuevo = this.cubo.clone();
        nuevo.mover(o.getMovimiento());
        return (new EstadoRubik(nuevo));
    }

    @Override
    public int hashCode() {
        return (cubo.hashCode());
    }

    @Override
    public boolean equals(Object o) {
        // CORRECCION: comprobar que el objeto recibido es de clase EstadoRubik
        if (this == o) {
            return true;
        }
        if ((o == null) || (this.getClass() != o.getClass())) {
            return false;
        }
        // CORRECCION: usar cubo.equals() aplicado sobre el cubo contenido en el estado recibido
        EstadoRubik e = (EstadoRubik) o;        
        return (cubo.equals(e.getCubo()));
    }

    @Override
    public String toString() {
        return (cubo.toString());
    }
    
    public Estado aplicarOperador(Operador o) {
        return(aplicarOperador((OperadorRubik) o));
    }

    
}
