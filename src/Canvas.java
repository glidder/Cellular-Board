/**
* This class creates a JPanel where the game is drawn and
* imput events are captured.
*
* @author Luis José Quintana Bolaño (@Glidder_)
* @version d1-14.3.6
**/

package coraline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public abstract class Canvas extends JPanel implements KeyListener, MouseListener
{
	private static boolean[] keyboardState = new boolean[525];
    private static boolean[] mouseState = new boolean[3];

    public Canvas()
    {
    	this.setDoubleBuffered(true);
    	this.setFocusable(true);
    	this.setBackground(Color.black);

    	if(true) //Remove traditional mouse cursor
    	{
    		BufferedImage blankCursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
    		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(blankCursorImg, new Point(0, 0), null);
    		this.setCursor(blankCursor);
    	}

    	this.addKeyListener(this);
    	this.addMouseListener(this);
    }

    // PAINTING ===============================================
    public abstract void Draw(Graphics2D g2d);

    @Override
    public void paintComponent(Graphics g)
    {
    	Graphics2D g2d = (Graphics2D)g;
    	super.paintComponent(g2d);
    	Draw(g2d);
    }

    // KEYBOARD ===============================================
    public static boolean keyboardKeyState(int key)
    {
    	return keyboardState[key];
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
    	keyboardState[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    	keyboardState[e.getKeyCode()] = false;
    	keyReleasedFramework(e);
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    public abstract void keyReleasedFramework(KeyEvent e);

    // MOUSE ==================================================
    public static boolean mouseButtonState(int button)
    {
    	return mouseState[button - 1];
    }

    private void mouseKeyStatus(MouseEvent e, boolean status)
    {
    	if(e.getButton() == MouseEvent.BUTTON1)
    		mouseState[0] = status;
    	else if(e.getButton() == MouseEvent.BUTTON2)
    		mouseState[1] = status;
    	else if(e.getButton() == MouseEvent.BUTTON3)
    		mouseState[2] = status;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
    	mouseKeyStatus(e, true);
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
    	mouseKeyStatus(e, false);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) { }
    
    @Override
    public void mouseEntered(MouseEvent e) { }
    
    @Override
    public void mouseExited(MouseEvent e) { }
}