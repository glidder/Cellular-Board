/**
* Class that controls, updates and draws the game on the screen
*
* @author Luis José Quintana Bolaño (@Glidder_)
* @version d1-14.3.6
**/

package coraline;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;
import javax.imageio.*;

public class Framework extends Canvas {

	public static int frameWidth;
	public static int frameHeight;
	public static final long SEC2NANO = 1000000000L;
	public static final long MILI2NANO = 1000000L;
	public final int GAME_FPS = 60;
	private final long GAME_UPDATE_PERIOD = SEC2NANO / GAME_FPS;
	public static enum GameState{STARTING, VISUALIZING, GAME_CONTENT_LOADING, MAIN_MENU, OPTIONS, PLAYING, GAMEOVER, DESTROYED}
	public static GameState gameState;
	private long gameTime;
	private long lastTime;
	private Game game;

	private BufferedImage menuImg;
	public Framework ()
	{
		super();

		gameState = GameState.VISUALIZING;

		Thread gameThread = new Thread() {
			@Override
			public void run(){
				GameLoop();
			}
		};
		gameThread.start();
	}

	private void Initialize()
    {

    }

    private void LoadContent()
    {
    	try {
    		URL menuImgUrl = this.getClass().getResource("data/menu.png");
    		menuImg = ImageIO.read(menuImgUrl);
    	} catch (IOException ex) {
    		Logger.getLogger(Framework.class.getName()).log(Level.SEVERE, null, ex);
    	}
    }

    private void GameLoop()
    {
    	long visualizingTime = 0, lastVisualizingTime = System.nanoTime();
    	long beginTime, timeTaken, timeLeft;

    	while(true){
    		beginTime = System.nanoTime();

    		switch (gameState){
    			case PLAYING:
    				gameTime += System.nanoTime() - lastTime;
    				game.UpdateGame(gameTime, mousePosition());
    				lastTime = System.nanoTime();
    				break;
    			case GAMEOVER:
    				break;
    			case MAIN_MENU:
    				break;
    			case OPTIONS:
    				break;
    			case GAME_CONTENT_LOADING:
    				break;
    			case STARTING:
    				Initialize();
    				LoadContent();
    				gameState = GameState.MAIN_MENU;
    				break;
    			case VISUALIZING:
    				if(this.getWidth() > 1 && visualizingTime > SEC2NANO){
    					frameWidth = this.getWidth();
    					frameHeight = this.getHeight();
    					gameState = GameState.STARTING;
    				}
    				else{
    					visualizingTime += System.nanoTime() - lastVisualizingTime;
    					lastVisualizingTime = System.nanoTime();
    				}
    				break;
    		}

    		repaint();

    		timeTaken = System.nanoTime() - beginTime;
    		timeLeft = (GAME_UPDATE_PERIOD - timeTaken) / MILI2NANO;
    		if(timeLeft < 10)
    			timeLeft = 10;
    		try {
    			Thread.sleep(timeLeft);
    		} catch (InterruptedException ex) { }
    	}
    }

    @Override
    public void Draw(Graphics2D g2d)
    {
    	switch (gameState)
    	{
    		case PLAYING:
    			game.Draw(g2d, mousePosition());
    			break;
    		case GAMEOVER:
    			game.DrawGameOver(g2d, mousePosition(), gameTime);
    			break;
    		case MAIN_MENU:
    			g2d.drawImage(menuImg, 0, 0, frameWidth, frameHeight, null);
    			g2d.setColor(Color.white);
    			g2d.drawString("Use space to advance a generation.", frameWidth/2 - 117, frameHeight/2);
    			g2d.drawString("Press any key to start the game.", frameWidth/2 - 100, frameHeight/2 + 30);
    			g2d.drawString("Twitter: @Glidder_", 7, frameHeight - 5);
    			break;
    		case OPTIONS:
    			break;
    		case GAME_CONTENT_LOADING:
    			g2d.setColor(Color.white);
    			g2d.drawString("Game is loading.", frameWidth/2 -50, frameHeight/2);
    			break;
    	}
    }

    private void newGame()
    {
    	gameTime = 0;
    	lastTime = System.nanoTime();

    	game = new Game(50);
    }

    private void restartGame()
    {
    	gameTime = 0;
    	lastTime = System.nanoTime();

    	game.RestartGame();
    	gameState = GameState.PLAYING;
    }

    private Point mousePosition()
    {
    	try{
    		Point mp = this.getMousePosition();

    		if(mp != null)
    			return this.getMousePosition();
    		else
    			return new Point(0, 0);
    	} catch (Exception e) {
    		return new Point(0, 0);
    	}
    }

    @Override
    public void keyReleasedFramework(KeyEvent e)
    {
    	switch (gameState)
    	{
    		case MAIN_MENU:
    			newGame();
    			if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                    System.exit(0);
    			break;
    		case GAMEOVER:
    			if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER)
    				restartGame();
    			else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                    System.exit(0);
    			break;
    	}
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
    	switch (gameState)
        {
            case MAIN_MENU:
                if(e.getButton() == MouseEvent.BUTTON1)
                    newGame();
            break;
        }
    }
}