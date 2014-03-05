/**
* @author Luis José Quintana Bolaño
* @version 0.01
**/

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class LifeGUI extends JFrame
{
	public LifeGUI(Game game){
        JPanel menu = new JPanel();
        JButton button1 = new JButton("Sig. Gen.");
        JTextArea pop = new JTextArea(5,20);
        pop.setEditable(false);
        add(game);
        menu.setLayout(new GridLayout());
        menu.add(button1);
        //menu.add(pop);
        add(menu, BorderLayout.EAST);
        
        addComponentListener(game);
        button1.addActionListener(game);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setSize(640,520);
        setLocationRelativeTo(null);
        setTitle("Conway's Game of Life");
        setResizable(true);
        setVisible(true);
        pack();
    }

    
	public static void main(String[] args) {

		Game game= new Game(new Life(Integer.parseInt(args[0]),Integer.parseInt(args[1])));
		new LifeGUI(game);
	}

}

class Game extends JPanel implements ActionListener, ComponentListener
{
	private Life life;
	private int painting = 0;
	private final static String celld = "data/celld.png";
	private final static String cellg = "data/cellg.png";
	private final static String cellr = "data/cellr.png";
	private final ImageIcon [] ii = {
    		new ImageIcon(this.getClass().getResource(celld)),
            new ImageIcon(this.getClass().getResource(cellg)),
            new ImageIcon(this.getClass().getResource(cellr))};

	public Game(Life l) {
		life=l;
		int aux=480/l.size();
		aux*=l.size();
		setPreferredSize(new Dimension(aux,aux));
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e))
					painting = 1;
				else if(SwingUtilities.isRightMouseButton(e))
					painting = 2;
				else
					painting = 3;
				tryAdjustValue(e.getPoint());
			}

			public void mouseReleased(MouseEvent e) {
				painting = 0;
			}
		});
		addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				tryAdjustValue(e.getPoint());
			}

			public void mouseMoved(MouseEvent e) {
				tryAdjustValue(e.getPoint());
			}
		});
	}
	private void tryAdjustValue(Point pt) {
		int[][] matrix=life.currentBoard();
		int SCALE=getSize().height/(matrix.length-2);
		int newX=pt.x/SCALE;
		int newY=pt.y/SCALE;
		if(painting==1 && isInRange(newX)&&isInRange(newY)){
			life.setAlive(newX+1,newY+1);
			repaint();
		}else if(painting==2&& isInRange(newX)&&isInRange(newY)){
			life.setRed(newX+1,newY+1);
			repaint();
		}else if(painting==3&& isInRange(newX)&&isInRange(newY)){
			life.setDead(newX+1,newY+1);
			repaint();
		}
	}
	public boolean isInRange(int val){
		return val >=0 && val<(life.currentBoard().length-2);
	}
	public int currentPopulation(){
		return life.currentPopulation();
	}
	public void actionPerformed(ActionEvent e) {
        life.sigGen();
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d= (Graphics2D)g;
        //g.setColor(Color.GREEN);
        int[][] matrix=life.currentBoard();
        int SCALE=getSize().height/(matrix.length-2);
        setPreferredSize(new Dimension(SCALE*(matrix.length-2),SCALE*(matrix.length-2)));
        for (int x=0; x<matrix.length-2; ++x) {
            for (int y=0; y<matrix.length-2; ++y) {
                //if (matrix[x+1][y+1]==1) {
                g2d.drawImage(ii[matrix[x+1][y+1]].getImage(), x * SCALE, y * SCALE, this);
                    //g.fillRect(x * SCALE, y * SCALE, SCALE, SCALE);
                //}
            }
        }
        Toolkit.getDefaultToolkit().sync();
        g.setColor(Color.RED);
        g.setFont(new Font(null, Font.PLAIN, 15));//(int)(getSize().height*0.1)));
        g.drawString("Population: "+life.currentPopulation(),5,20);//(int)(getSize().height*0.1+5));
    	g.dispose();
    }

    public void componentResized(ComponentEvent evt) {
    		JFrame c=(JFrame)evt.getSource();
    		int w=c.getContentPane().getSize().width;
    		int h=c.getContentPane().getSize().height;
    		int s=((h<w)?h:w);
    		s=s/life.size();
			s*=life.size();
            setSize(s,s);
            //Component c = (Component)evt.getSource();
            //........
    }
    public void componentHidden(ComponentEvent evt) {}
    public void componentShown(ComponentEvent evt) {}
    public void componentMoved(ComponentEvent evt) {}
}
