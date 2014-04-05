/**Class Cell for a simple Java game.
*@author Luis Quintana
*@version 1.0
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

enum States { dead, green, red }

public class Cell {

    private final static String celld = "data/celld.png";
	private final static String cellg = "data/cellg.png";
	private final static String cellr = "data/cellr.png";
    private final ImageIcon [] ii = {
    		new ImageIcon(this.getClass().getResource(celld)),
            new ImageIcon(this.getClass().getResource(cellg)),
            new ImageIcon(this.getClass().getResource(cellr))};
    private int dx;
    private int dy;
    private int x;
    private int y;
    private States _state;

    public Cell(String type) {
        _state = States.valueOf(type);
	}
	
    public void move() {
        x += dx;
        y += dy;
    }

	public States getState() {
        return _state;
    }

	public void setState(int state) {
        _state=States.values()[state];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return ii[_state.ordinal()].getImage();
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
        }
        
        if (key == KeyEvent.VK_SPACE) {
            _state=States.values()[(_state.ordinal()+1)%3];
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}
