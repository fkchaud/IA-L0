package rubik.modelo;

/**
 * IA 2011
 */
public class Cara {

    /** Id del color de esta cara */
    public byte color;
    
    /** Array donde se almacenan los colores de las casillas de la cara */
    public byte[] casillas;

    /**
     * Crea una cara del colo indicado
     * @param color
     */
    public Cara(byte color) {
        this.color = color;
        this.casillas = new byte[9];
        this.inicializar();
    }

    /**
     * Inicializa el array de casillas al color de la cara
     */
    public void inicializar() {
        for (byte i = 0; i < 9; i++) {
            this.casillas[i] = this.color;
        }
    }

    /**
     * Crea una copia de la cara
     * @return
     */
    @Override
    public Cara clone() {
        Cara c = new Cara(this.color);
        System.arraycopy(this.casillas, 0,
                         c.casillas, 0, this.casillas.length);
        return (c);
    }
    
    /**
     * Comprueba los colores de las casillas de 2 caras son iguales
     * @param o Cara a comparar
     * @return true si los respectivos arrays casillas coinciden
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (this.getClass() != o.getClass())) {
            return false;
        }
        Cara c = (Cara) o;
        for (byte i = 0; i < this.casillas.length; i++) {
            if (this.casillas[i] != c.casillas[i]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Valor hash del contenido de la cara. Concatena los 3 bits del color de cada casilla
     */
    @Override
    public int hashCode(){
        int hash=this.casillas[0];      
        for (byte i=1; i< this.casillas.length-1; i++) {
            hash = hash << 3;
            hash = hash + this.casillas[i];
        }
        return(hash);
    }
}
