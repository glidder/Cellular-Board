/**
* This class creates the main frame and its poperties.
*
* @author Luis José Quintana Bolaño (@Glidder_)
* @version d1-14.3.6
**/

package coraline;

import javax.swing.*;

public class Window extends JFrame
{
	private Window()
	{

		this.setTitle("Coral Supremacy");
		if(false){ 	// Full screen mode
            this.setUndecorated(true);
            this.setExtendedState(this.MAXIMIZED_BOTH);
        }else{ 		// Window mode
            this.setSize(800, 600);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new Framework());
        this.setVisible(true);
	}

	public static void main(String[] args)
    {// Use the event dispatch thread to build the UI for thread-safety.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });
    }
}