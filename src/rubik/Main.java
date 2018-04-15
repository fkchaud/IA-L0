package rubik;
import java.util.Vector;
import rubik.busqueda.FactoriaEstrategias;
import rubik.modelo.EstadoRubik;
import rubik.modelo.OperadorRubik;
import rubik.busqueda.Operador;
import rubik.busqueda.Problema;
import rubik.modelo.Cubo;
import rubik.modelo.Movimiento;

/**
 * Interfaz a travez de la consola
 * Inteligencia Artificial 2016
 */

/**
 * Formulacion conceptual de la solucion del Cubo Rubik
 * como un problema de busqueda
 * 
 *      Estado inicial: cualquier estado del cubo, inicializado con Cubo.mezclar(n);
 *                  
 *      Estado meta:    estado en el que las 6 caras del cubo estén compuestas por 9 cuadrados del mismo color
 *                  y ninguna de las caras tenga un color repetido respecto a otra. Verificado por la función
 *                  Cubo.esConfiguracionFinal();
 *      Operadores:     R: Mover la cara derecha hacia arriba
 *                      Ri: Mover la cara derecha hacia abajo
 *                      L: Mover la cara izquierda hacia arriba
 *                      Li: Mover la cara izquierda hacia abajo
 *                      B: Mover la cara trasera hacia la izquierda
 *                      Bi: Mover la cara trasera hacia la derecha
 *                      D: Mover la cara inferior hacia la derecha
 *                      Di: Mover la cara inferior hacia la izquierda
 *                      F: Mover la cara frontal hacia la derecha
 *                      Fi: Mover la cara frontal hacia la izquierda
 *                      U: Mover la cara superior hacia la izquierda
 *                      Ui: Mover la cara superior hacia la derecha
 */

/**
 * Formulacion formal de la solucion del Cubo Rubik
 * como un problema de busqueda
 * 
 *      Estado inicial: 
 *                      cara i: {xj}, j:0-8, i:0-5  xj:0-5
 *                      cubo: {cara i}, i:0-5
 *                      cara i.x8 != cara i+1.x8,   i:0-5
 *                  
 *      Estado meta:    
 *                      cara i: {x0=x1=x2=x3=x4=x5=x6=x7=x8}, i:0-5, xj:0-5
 *                      cubo: {cara i}, i:0-5
 *                      cara i.x8 != cara i+1.x8,   i:0-5
 */

public class Main {
    /**
     * Corre el programa en consola.
     * Mezcla el cubo en una cantidad de pasos de 2, y lo resuelve con la
     * estrategia de Busqueda en Anchura en Grafo.<br>Muestra la solución
     * si lo puede resolver, o informa de su fallo si no lo logró.
     * @param args Argumentos que recibe la función.
     */
     public static void main(String[] args) {
        Cubo cubo = new Cubo();
        // Describir para que son las lineas de codigo sigientes
        long semilla = 621444;                                                  // Indica una semilla
        cubo.rnd.setSeed(semilla);                                              // Settea una semilla para el
                                                                                // generador de números aleatorios
        System.out.println("\nSEMILLA: "+semilla);                              // Muestra cuál es la semilla
        Vector<Movimiento> movsMezcla = cubo.mezclar(2);                        // Mezcla el cubo y devuelve los movimientos realizados
        System.out.println("\nMOVIMIENTOS:");
        for (Movimiento m : movsMezcla) {
            System.out.print(m.toString() + " ");                               // Muestra la lista de movimientos
        }
        System.out.println();                                                   // Muestra línea vacía
        System.out.println("CUBO INICIAL:\n" + cubo.toString());                // Muestra todas las caras del cubo, cómo quedan
                                                                                // en el estado inicial
        
        // Describir para que son las lineas de codigo sigientes
        Problema problema = new Problema(                                       // Crea una instancia de Problema, con el cubo mezclado 
                new EstadoRubik(cubo), FactoriaEstrategias.getEstrategia(3));   // como estado inicial, e indicando la estrategia
                                                                                // (3 = Busqueda en Anchura en Grafo)
        Vector<Operador> opsSolucion = problema.obtenerSolucion();              // Resuelve el problema y devuelve la lista de pasos
                                                                                // para alcanzar la solución
        System.out.println("\nSOLUCION:");
        if (opsSolucion != null) {                                              // Si la lista de pasos no está vacía, hay solución
            for (Operador o : opsSolucion) {
                System.out.println("Accion: " + o.getEtiqueta() + " ");         // Muestra cada movimiento
                OperadorRubik or = (OperadorRubik) o;
                cubo.mover(or.getMovimiento());                                 // Efectua cada movimiento
            }
            System.out.println();
            System.out.println("CUBO FINAL:\n" + cubo.toString());              // Muestra el cubo final
        } else {                                                                // Si la lista de pasos está vacía, no hay solución
            System.out.println("no se ha encontrado solucion");
        }
    }
}
