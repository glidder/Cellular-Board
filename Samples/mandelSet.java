/**
* MC, Practica 8, ejercicio 4: Visualizador del conjunto de Mandelbrot
* @author Luis José Quintana Bolaño
**/

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.lang.Math;
import java.io.*;
import javax.imageio.*;

public class mandelSet extends JFrame
{
    public mandelSet() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//setLocationRelativeTo(null);
        setTitle("Conjunto de Mandelbrot");
        mPanel panel=new mPanel();
        add(panel);
        setVisible(true);
        pack();
        panel.repaint();
        panel.captura();
    }
    
    public static void main( String[ ] args ) {
        new mandelSet();
    }
}

class mPanel extends JPanel
{
	public mPanel(){setPreferredSize(new Dimension(MAX_TAM,MAX_TAM));setDoubleBuffered(true);}

	public void paint(Graphics g) {
        super.paint(g);
        Color[] colores=new Color[MAX_COL+1];
    	int r=INI_R,v=INI_V,a=INI_A;
    	for (int c=0; c<MAX_COL; ++c ) {
    		colores[c]=new Color(r,v,a);
    		v+=((MAX_RGB-INI_V)/MAX_COL);
    		r+=((MAX_RGB-INI_R)/MAX_COL);
    		a+=((MAX_RGB-INI_A)/MAX_COL);
    	}
    	colores[MAX_COL]=Color.black;
    	
    	setBackground(Color.black);
    	
    	double x0=-2;
    	for (int i = 0; i < MAX_TAM; ++i){
    		x0+= TMP / MAX_TAM;
    		double y0=-1.25;
    		for (int j = 0; j < MAX_TAM; ++j) {
    			y0+= TMP / MAX_TAM;
    			double x=0.0, y=0.0;
    			int iter=0;
    			while (((x*x+y*y)<2*2) && iter < MAX_COL){
    				double aux=x*x-y*y+x0;
    				y=2*x*y+y0;
    				x=aux;
    				++iter;
    			}
    		g.setColor(colores[iter]);
    		g.fillRect(i, j, 1, 1);
    		}
    	}
    }
    
    void captura () {
    	BufferedImage bi = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_ARGB); 
		Graphics g = bi.createGraphics();
		this.paint(g);  
		g.dispose();
		try{ImageIO.write(bi,"png",new File("test.png"));}catch (Exception e) {}
	}
	public static final int MAX_TAM = 7000; //Tamaño imagen
	public static final int MAX_COL = 120; //Iteraciones máximas
	public static final int MAX_RGB = 255;
	public static final int INI_V = 50;	//Nivel inicial verde
	public static final int INI_R = 0;  //Nivel inicial rojo
	public static final int INI_A = 0;	//Nivel inicial azul
	public static final double TMP = 2.5;
}
