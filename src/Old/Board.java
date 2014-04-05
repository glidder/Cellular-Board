/**Class Board for a simple Java game.
*@author Luis Quintana
*@version 1.0
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener {

	private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            cell.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            cell.keyPressed(e);
        }
    }

	private Timer timer;
    private Cell cell;

	public Board (){
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		
		cell= new Cell("green");
		timer = new Timer(5, this);
        timer.start();
	}

	public void paint(Graphics g){
		super.paint(g);

		Graphics2D g2d= (Graphics2D)g;
		g2d.drawImage(cell.getImage(), cell.getX(), cell.getY(), this);
		Toolkit.getDefaultToolkit().sync();
        g.dispose();
	}

 	public void actionPerformed(ActionEvent e) {
        cell.move();
        repaint();  
    }
}
