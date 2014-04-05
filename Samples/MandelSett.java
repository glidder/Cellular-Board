import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
* Hoofdklasse voor het tekenen van de Mandelbrot set.
* Drawing the mandelbrot set is a time consuming process.
* The program is meant to illustrate the use of a thread for this proces
* so the GUI buttons are still responsive to user actions.
* Practicumopgave Programmeren 2.
* @author: Martin Kalin
*/
public class MandelSet {
    public MandelSet() {
        win = new JFrame( "Mandelbrot Set" );
        Container c = win.getContentPane();
        c.setBackground( Color.white );
        c.setLayout( new BorderLayout() );
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu( "Menu" );
        JMenuItem draw = new JMenuItem( "Draw", 'D' );
        draw.addActionListener( new AbstractAction() {
                public void actionPerformed( ActionEvent e ) {
                    canvas.draw();
                } } );
        JMenuItem exit = new JMenuItem( "Exit", 'E' );
        exit.addActionListener( new AbstractAction() {
                public void actionPerformed( ActionEvent e ) {
                    System.exit( 0 );
                } } );
        menu.add( draw ); menu.add( exit );
        menuBar.add( menu );
        canvas = new MandelPanel();
        win.setJMenuBar( menuBar );
        c.add( canvas, BorderLayout.CENTER );
        win.setSize( 400, 400 );
        win.setVisible(true);
    }

    private JFrame win;
    private MandelPanel canvas;

    public static void main( String[ ] args ) {
        new MandelSet();
    }
}
