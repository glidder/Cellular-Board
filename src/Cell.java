/**Class Cell for a simple Java game.
*@author Luis Quintana
*@version 1.0
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Cell {

	private String cellg = "cellg.png";
	private String cellr = "cellr.png";
	private Image image;
	private int dx;
    private int dy;
    private int x;
    private int y;

     public Cell(String type) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(((type.equals("g"))?cellg:cellr)));
        image = ii.getImage();
        x = 40;
        y = 60;
    }

     public void move() {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
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