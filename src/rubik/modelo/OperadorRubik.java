package rubik.modelo;

import rubik.busqueda.Operador;

/**
 * Implementa el interfaz Operador encapsulando un movimiento rubik
 * IA 2011
 */
public class OperadorRubik implements Operador {
    /** Movimiento asociado al operador */
    private Movimiento movimiento;

    public OperadorRubik(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public String getEtiqueta() {
        return (movimiento.toString());
    }
    
}
