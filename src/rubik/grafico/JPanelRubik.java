/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rubik.grafico;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import rubik.modelo.Cubo;
import rubik.modelo.Movimiento;

/**
 *
 * @author ribadas
 */
public class JPanelRubik extends JPanel {

    private AnimCube animCube;
    private Cubo cubo;

    
    public JPanelRubik() {
        this(new Cubo());
    }

    
    public JPanelRubik(Cubo c) {
        super();
        cubo = c;
        animCube = new AnimCube();
        animCube.setPreferredSize(new Dimension(250, 250));
        animCube.init();
        animCube.setCubo(c);
                
        this.setLayout(new BorderLayout());
        

        this.add(animCube, BorderLayout.CENTER);
        JPanel aux = new JPanel();
        
        JTextArea ta = new JTextArea("Basado en el Applet AnimCube\t"+
                "Autor: Josef Jelinek \n"+
                "http://software.rubikscube.info/AnimCube/index.html\n");
        ta.setEditable(false);
        this.add(ta, BorderLayout.SOUTH);
    }

    public Cubo getCubo() {
        return (animCube.getCubo());
    }

    public void setCubo(Cubo cubo) {
        this.animCube.setCubo(cubo);
    }
    
    public void animarCubo(Vector<Movimiento> movimientos)  {
        Cubo cuboActual = animCube.getCubo();
        
        for (Movimiento m : movimientos){
            cuboActual.mover(m);
            animCube.setCubo(cuboActual);
            
        }
        
    }

//    public final static void main(String[] args) {
//        JFrame f = new JFrame();
//
//        //JPanelRubik p = new JPanelRubik(new Cubo());
//
//        //AnimCube animCube = new  AnimCube();
//        //animCube.setSize(this.getSize());
//        //animCube.init();
//
//        //animCube.setPreferredSize(new Dimension(200, 200));
//        f.setLocation(300, 200);
//        f.setSize(250, 250);
//        //f.getContentPane().add(animCube);
//
//        AnimCube a = new AnimCube();
//        a.setPreferredSize(new Dimension(250, 250));
//        a.init();
//
//        f.getContentPane().add(a);
//        f.setSize(350, 350);
//        f.pack();
//        f.setVisible(true);
//
//        Cubo c = new Cubo();
//        c.mover(Movimiento.movimientosPosibles[Movimiento.U]);
//        c.mover(Movimiento.movimientosPosibles[Movimiento.F]);
//        a.setCubo(c);
//
//        System.out.println(c.toString());
//
//        Cubo b = a.getCubo();
//
//        System.out.println(b.toString());
//
//    }
}
