package rubik.modelo;

/**
 * Clase que encapsula cada uno de los movimeintos posibles en un cubo de Rubik
 * IA 2011
 */
public class Movimiento {

    public final static byte U = Cubo.UP;
    public final static byte Ui = Movimiento.U + 6;
    public final static byte L = Cubo.LEFT;
    public final static byte Li = Movimiento.L + 6;
    public final static byte F = Cubo.FRONT;
    public final static byte Fi = Movimiento.F + 6;
    public final static byte R = Cubo.RIGHT;
    public final static byte Ri = Movimiento.R + 6;
    public final static byte B = Cubo.BACK;
    public final static byte Bi = Movimiento.B + 6;
    public final static byte D = Cubo.DOWN;
    public final static byte Di = Movimiento.D + 6;
    
    /** Etiquetas abreviadas que identifican cada uno de los movimeintos (U, Ui, L, Li, ...)*/
    public final static String[] etq_corta = {"U", "L", "F", "R", "B", "D",
                                               "Ui", "Li", "Fi", "Ri", "Bi", "Di"};
    
    /** Etiquetas que identifican cada uno de los movimeintos */
    public final static String[] etq_larga = {"UP", "LEFT", "FRONT", "RIGHT", "BACK", "DOWN",
                                               "UP inverso", "LEFT inverso", "FRONT inverso", "RIGHT inverso", "BACK inverso", "DOWN inverso"};
    
    /** Array estático con 12 objetos Movimiento para los 12 movimeintos posibles */
    public final static Movimiento[] movimientosPosibles = { // Para crear lista de sucesores
        new Movimiento(Movimiento.U), new Movimiento(Movimiento.L),
        new Movimiento(Movimiento.F), new Movimiento(Movimiento.R),
        new Movimiento(Movimiento.B), new Movimiento(Movimiento.D),
        new Movimiento(Movimiento.Ui), new Movimiento(Movimiento.Li),
        new Movimiento(Movimiento.Fi), new Movimiento(Movimiento.Ri),
        new Movimiento(Movimiento.Bi), new Movimiento(Movimiento.Di)
    };
    
    /** Identificador del tipo de movimiento conforme a las constante static */
    public byte tipo;

    /** Crea un nuevo movimeinto del tipo indicado */
    public Movimiento(byte tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return (Movimiento.etq_corta[this.tipo]);
    }
}
