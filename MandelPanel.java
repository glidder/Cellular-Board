import javax.swing.*;
import java.awt.*;

/**
* Grafische component voor het tekenen van de Mandelbrot set.
* Practicumopdracht Programmeren 2.
* @author Martin Kalin, aangepast door Arend Rensink
* @version 15-01-2002
*/
class MandelPanel extends JPanel implements Runnable {
    public void draw() {
        (new Thread(this)).start();
    }

    // draw the fractal
    void drawMandel( ) {
        Graphics g = getGraphics();
        int width = getWidth();
        int height = getHeight();
        double xstep = DIV / width;
        double ystep = DIV / height;
        double y = Y_START;
        for ( int j = 0; j < height; y += ystep, j++ ) {
            double x = X_START;
            for ( int i = 0; i < width; x += xstep, i++ ) {
                double c1_real = x; double c1_imag = y;
                double c2_real = 0.0; double c2_imag = 0.0;
                int iter;
                double spread = 0.0;
                for ( iter = 0;
                      iter < MAX_COLORS && spread < MAX_SPREAD;
                      iter++ ) {
                    double real = c1_real + c2_real;
                    double imag = c1_imag + c2_imag;
                    c2_real = real * real - imag * imag;
                    c2_imag = 2 * real * imag;
                    spread = c2_real * c2_real + c2_imag * c2_imag;
                }
                g.setColor( COLORS[ iter ] );
                g.fillRect( i, j, 1, 1 );
            }
            try {
                Thread.sleep( 20 );
            } catch( InterruptedException e ) {
                return;
            }
        }
    }

    private static final double MAX_SPREAD = 4.0;
    private static final double LIMIT = 1.0;
    private static final double X_START = -1.5;
    private static final double Y_START = -1.0;
    private static final double DIV = 2.0;
    private static Color[ ] COLORS;
    private static final int MAX_COLORS = 64;

    // Onderstaande code is een zgn. static initializer;
    // hierin kunnen static variabelen van een beginwaarde worden voorzien.
    // Het is dus een soort constructor voor de klasse i.p.v. een instantie.
    // In dit geval gaat het om de static variabele COLORS.
    //
    // In de oorspronkelijke klasse (van Kalin) wordt dit gedaan in een
    // niet-static methode makeColors, die bij de constructie van elk nieuw
    // object opnieuw wordt aangeroepen. Dat is natuurlijk de grootste onzin.
    {
        int maxRGB = 255, r = 0, g = 0, b = 0;
        COLORS = new Color[ MAX_COLORS + 1 ];
        for ( int i = 0; i < MAX_COLORS; i++ ) {
            COLORS[ i ] = new Color( r, g, b );
            r += MAX_COLORS;
            if ( r > maxRGB ) {
                r = 0;
                g += MAX_COLORS;
                if ( g > maxRGB ) {
                    g = 0;
                    b += MAX_COLORS;
                }
                if ( b > maxRGB )
                    b = 0;
            }
        }
        COLORS[ MAX_COLORS ] = Color.white;
    }

        @Override
        public void run() {
        drawMandel();
        }
}
