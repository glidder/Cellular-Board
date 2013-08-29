/**Class Game for a simple Java game.
  *@author Luis José Quintana Bolaño
  *@version 1.0
*/
import javax.swing.*;

public class Game extends JFrame{

    public Game(){
        add(new Board());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,280);
        setLocationRelativeTo(null);
        setTitle("Game");
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new Game();
    }
}
        
