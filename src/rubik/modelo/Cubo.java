
package rubik.modelo;

import java.util.Vector;
import java.util.Random;

/**
 * IA 2011
 */
public class Cubo {

    // Reparto de las caras
    //   0
    // 1 2 3 4
    //   5
    //
    // Indices de las casillas en cada cara
    //       012
    //       783
    //       654
    // 
    //  012  012  012  012
    //  783  783  783  783
    //  654  654  654  654
    //
    //       012
    //       783    
    //       654
    //
    //
    
    // Constantes para identificar las caras
    public final static byte UP = 0;
    public final static byte LEFT = 1;
    public final static byte RIGHT = 3;
    public final static byte FRONT = 2;
    public final static byte BACK = 4;
    public final static byte DOWN = 5;
    
    /** Array de constantes para identificar los colores
     */
    public final static byte[] ids_colores = {0, 1, 2, 3, 4, 5};
    
    /** Array de etiquetas para identificar los colores
     */
    public final static String[] etq_colores = {"W", "Y", "O", "R", "G", "B"};
    
    /** Indices de la cara vecina Norte de cada una de las caras  */                             
    public static final byte[] vecinoNorte = {4, 0, 0, 0, 0, 2};
    /** Indices de la cara vecina Este de cada una de las caras  */                             
    public static final byte[] vecinoEste  = {3, 2, 3, 4, 1, 3};
    /** Indices de la cara vecina Sur de cada una de las caras  */                             
    public static final byte[] vecinoSur   = {2, 5, 5, 5, 5, 4};
    /** Indices de la cara vecina Oeste de cada una de las caras  */                             
    public static final byte[] vecinoOeste = {1, 4, 1, 2, 3, 1};
    
    /** Indices de las casillas fronterizas en las caras vecinas Norte, Este, Sur, Oeste 
    /*  a mover en el giro normal (sentido del reloj visto de frente) 
     */
    public static final byte[][] idxNorte = {{2, 1, 0},  // casillas fronterizas al Norte para cara 0
                                             {0, 7, 6},  // idem cara 1
                                             {6, 5, 4},  // idem cara 2
                                             {4, 3, 2},  // idem cara 3
                                             {2, 1, 0},  // idem cara 4
                                             {6, 5, 4}}; // idem cara 5

    /** Indices de las casillas fronterizas en la cara  vecina Este
    /*  a mover en el giro normal (sentido del reloj visto de frente) 
     */
    public static final byte[][] idxEste = {{2, 1, 0}, // casillas fronterizas al Este para cara 0
                                            {0, 7, 6}, // idem cara 1
                                            {0, 7, 6}, // idem cara 2
                                            {0, 7, 6}, // idem cara 3
                                            {0, 7, 6}, // idem cara 4
                                            {6, 5, 4}}; // idem cara 5
    /** Indices de las casillas fronterizas en la cara vecina Sur
    /*  a mover en el giro normal (sentido del reloj visto de frente) 
     */
    public static final byte[][] idxSur = {{2, 1, 0}, // casillas fronterizas al Sur para cara 0
                                           {0, 7, 6}, // idem cara 1
                                           {2, 1, 0}, // idem cara 2
                                           {4, 3, 2}, // idem cara 3
                                           {6, 5, 4}, // idem cara 4
                                           {6, 5, 4}}; // idem cara 5
    
    /** Indices de las casillas fronterizas en las cara vecina Oeste 
    /*  a mover en el giro normal (sentido del reloj visto de frente) 
     */
    public static final byte[][] idxOeste = {{2, 1, 0}, // casillas fronterizas al Oeste para cara 0
                                             {4, 3, 2}, // idem cara 1
                                             {4, 3, 2}, // idem cara 2
                                             {4, 3, 2}, // idem cara 3
                                             {4, 3, 2}, // idem cara 4
                                             {6, 5, 4}}; // idem cara 5

    /**  
     * Array con las 6 caras del cubo
     */
    public Cara[] caras;
    public Random rnd = new Random(); //crear un objeto para generar numero aletorio
    
    /**
     * Crea un nuevo cubo
     */
    public Cubo() {
        caras = new Cara[6];
        for (byte i = 0; i < 6; i++) {
            caras[i] = new Cara(i);
        }
    }

    /**
     * Comprueba si las caras del cubo contienen una configuración final
     * @return true si es un subo solución
     */
    public boolean esConfiguracionFinal() {
        for (Cara c: caras) {
            for (byte i=0; i< c.casillas.length; i++) {
                if (c.casillas[i] != c.color) {
                    return(false);
                }
            }
        }
        return(true);
    }

    /**
     * Inicializa las caras de un cubo con los colores indicados en el array ids_colores
     */
    public void inicialiar() {
        for (byte i = 0; i < 6; i++) {
            caras[i].inicializar();
        }
    }

    /**
     * Realiza una mezcla aleatoria de las caras del cubo aplicando un número aleatorio de
     * movientos al azar
     * @return Vector con los movimientos que conforman la mezcla
     */
    public Vector<Movimiento> mezclar(){
        
        double aleatorio = (20 * Math.random());
        return(mezclar((int) Math.round(aleatorio)));
    }

    /**
     * Realiza una mezcla aleatoria de las caras del cubo aplicando el num. indicado
     * de movimientos al azar
     * @param pasos num. de pasos a barajar
     * @return Vector con los movimientos que conforman la mezcla
     */
    public Vector<Movimiento> mezclar(int pasos){
        
         Vector<Movimiento> listaMovimientos = new Vector<Movimiento>(pasos);
        int idMovimiento;
        
        for (int i=0; i < pasos; i++) {
            idMovimiento = (int) Math.round( rnd.nextDouble()*(Movimiento.movimientosPosibles.length-1) );
            mover(Movimiento.movimientosPosibles[idMovimiento]);
            listaMovimientos.add(Movimiento.movimientosPosibles[idMovimiento]);
        }
        return(listaMovimientos);
    }

    /**
     * Realiza el moviminnto indicado sobre la correspondiente cara del cubo
     * @param m Movimiento a realizar
     */
    public void mover(Movimiento m) {
        if (m.tipo < 6) {
            girarHorario(m.tipo);
        } else {
            girarAntiHorario((byte) (m.tipo - 6));
        }
    }

    /**
     * Realiza una lista de movimientos sobre las caras del cubo
     * @param v Vector con la lista de Movimientos a realizar
     */
    public void mover(Vector<Movimiento> v) {
        for (Movimiento m : v) {
            this.mover(m);
        }
    }

    /**
     * Devuelve un array con los movimientos posibles sobre las caras del cubo
     * Los 12 movimientos posibles son válidos, se usa directamente el array 
     * estático de la clase Movimiento
     * @return Array con los posibles movimientos
     */
    public Movimiento[] movientosPosibles() {
        return (Movimiento.movimientosPosibles);
    }

    /**
     * Giro horario sobre la cara indicada y las casillas fronterizas de las 
     * caras vecinas que correspondan
     * @param idxCara cara a mover
     */
    private void girarHorario(byte idxCara) {
        byte aux1, aux2, aux3;

        girarCaraHorario(caras[idxCara]);

        for (byte i = 0; i < 3; i++) {
            // Norte -> Este
            aux1 = caras[vecinoEste[idxCara]].casillas[idxEste[idxCara][i]];
            caras[vecinoEste[idxCara]].casillas[idxEste[idxCara][i]] =
                    caras[vecinoNorte[idxCara]].casillas[idxNorte[idxCara][i]];

            // Este -> Sur
            aux2 = caras[vecinoSur[idxCara]].casillas[idxSur[idxCara][i]];
            caras[vecinoSur[idxCara]].casillas[idxSur[idxCara][i]] = aux1;

            // Sur -> Oeste
            aux3 = caras[vecinoOeste[idxCara]].casillas[idxOeste[idxCara][i]];
            caras[vecinoOeste[idxCara]].casillas[idxOeste[idxCara][i]] = aux2;

            // Oeste -> Norte
            caras[vecinoNorte[idxCara]].casillas[idxNorte[idxCara][i]] = aux3;
        }
    }

    /**
     * Giro antihorario sobre la cara indicada y las casillas fronterizas de las 
     * caras vecinas que correspondan
     * @param idxCara cara a mover
     */
    private void girarAntiHorario(byte idxCara) {
        byte aux1, aux2, aux3;

        girarCaraAntiHorario(caras[idxCara]);

        for (byte i = 0; i < 3; i++) {
            // Norte -> Oeste
            aux1 = caras[vecinoOeste[idxCara]].casillas[idxOeste[idxCara][i]];
            caras[vecinoOeste[idxCara]].casillas[idxOeste[idxCara][i]] =
                    caras[vecinoNorte[idxCara]].casillas[idxNorte[idxCara][i]];

            // Oeste -> Sur
            aux2 = caras[vecinoSur[idxCara]].casillas[idxSur[idxCara][i]];
            caras[vecinoSur[idxCara]].casillas[idxSur[idxCara][i]] = aux1;

            // Sur -> Este
            aux3 = caras[vecinoEste[idxCara]].casillas[idxEste[idxCara][i]];
            caras[vecinoEste[idxCara]].casillas[idxEste[idxCara][i]] = aux2;

            // Este -> Norte
            caras[vecinoNorte[idxCara]].casillas[idxNorte[idxCara][i]] = aux3;
        }
    }

    /**
     * Giro horario sobre las casillas de una cara (no afecta a las caras vecinas)
     */
    private void girarCaraHorario(Cara cara) {
        byte[] copia = new byte[9];
        System.arraycopy(cara.casillas, 0, copia, 0, cara.casillas.length);

        // Casilla num. 8 no se mueve
        for (byte i = 0; i < 8; i++) {
            cara.casillas[(i + 2) % 8] = copia[i];
        }
    }

    /**
     * Giro antihorario sobre las casillas de una cara (no afecta a las caras vecinas)
     */
    private void girarCaraAntiHorario(Cara cara) {
        byte[] copia = new byte[9];
        System.arraycopy(cara.casillas, 0, copia, 0, cara.casillas.length);

        // Casilla num. 8 no se mueve
        for (byte i = 0; i < 8; i++) {
            cara.casillas[i] = copia[(i + 2) % 8];
        }
    }

    /**
     * Crea una copia del cubo actual
     * @return Nuevo cubo
     */
    @Override
    public Cubo clone() {
        Cubo c = new Cubo();

        for (byte i = 0; i < 6; i++) {
            c.caras[i].color = this.caras[i].color;
            System.arraycopy(this.caras[i].casillas, 0,
                             c.caras[i].casillas, 0, this.caras[i].casillas.length);
        }

        return (c);
    }

    /**
     * Compara 2 cubos. Necesario para implementar los métodos contains() de 
     * colecciones (List) y tablas hash (Hashmap, HasSet, ...)
     * @param o Objeto(Cubo) a comparar
     * @return true si los colores de las casillas de cada cara coinciden
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (this.getClass() != o.getClass())) {
            return false;
        }
        Cubo c = (Cubo) o;
        for (byte i = 0; i < 6; i++) {
            if (!this.caras[i].equals(c.caras[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calcula el hash de un cubo combinando el valor de hash de las 6 caras
     * Necesario para usar tablas hash (Hashtable, HashSet,...)
     * @return Valor del hash
     */
    @Override
    public int hashCode() {
        int highorder;
        int hash = this.caras[0].hashCode();
        for (byte i = 1; i < 6; i++) {
            highorder = hash & 0xf8000000;
            hash = hash << 5;
            hash = hash ^ (highorder >> 27);
            hash = hash ^ this.caras[i].hashCode();
        }
        return (hash);
    }

    /**
     * Prettyprint del cubo
     * @return
     */
    @Override
    public String toString() {
        String resultado;

        // Cara 0
        resultado = "    " + stringFila1(caras[0]) + "\n" +
                "    " + stringFila2(caras[0]) + "\n" +
                "    " + stringFila3(caras[0]) + "\n\n";

        // Caras 1, 2, 3, 4
        resultado += stringFila1(caras[1]) + " " + stringFila1(caras[2]) + " " +
                stringFila1(caras[3]) + " " + stringFila1(caras[4]) + "\n" +
                stringFila2(caras[1]) + " " + stringFila2(caras[2]) + " " +
                stringFila2(caras[3]) + " " + stringFila2(caras[4]) + "\n" +
                stringFila3(caras[1]) + " " + stringFila3(caras[2]) + " " +
                stringFila3(caras[3]) + " " + stringFila3(caras[4]) + "\n\n";

        // Cara 5
        resultado += "    " + stringFila1(caras[5]) + "\n" +
                "    " + stringFila2(caras[5]) + "\n" +
                "    " + stringFila3(caras[5]) + "\n\n";

        return (resultado);

    }

    private String stringFila1(Cara cara) {
        return (etq_colores[cara.casillas[0]] +
                etq_colores[cara.casillas[1]] +
                etq_colores[cara.casillas[2]]);
    }

    private String stringFila2(Cara cara) {
        return (etq_colores[cara.casillas[7]] +
                etq_colores[cara.casillas[8]] +
                etq_colores[cara.casillas[3]]);
    }

    private String stringFila3(Cara cara) {
        return (etq_colores[cara.casillas[6]] +
                etq_colores[cara.casillas[5]] +
                etq_colores[cara.casillas[4]]);
    }

}  // Fin clase Cubo