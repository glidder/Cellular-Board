/**
* Class that represents the game itself.
*
* @author Luis José Quintana Bolaño (@Glidder_)
* @version d1-14.3.6
**/

package coraline;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;
import javax.imageio.*;

public class Game {

	private Board board;
	private int bSize;
	private Font font;
	private BufferedImage sightImg;
	private int sightImgMiddleWidth, sightImgMiddleHeight;
	private long timeBetween;

	public Game(int bsize)
	{
		Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;
		bSize=bsize;//Eliminate this when ready to start with empty board.
		timeBetween = Framework.SEC2NANO / 6;
		Thread threadForInitGame = new Thread() {
			@Override
			public void run(){
				Initialize();
				LoadContent();
				Framework.gameState = Framework.GameState.PLAYING;
			}
		};
		threadForInitGame.start();
	}

	private void Initialize()
	{
		font = new Font("monospaced", Font.BOLD, 18);
		board = new Board(bSize, timeBetween);//Start an empty board, rather.
	}

	private void LoadContent()
	{
		//BackGround, cursor...
		try{
			URL sightImgUrl = this.getClass().getResource("data/sight.png");
            sightImg = ImageIO.read(sightImgUrl);
            sightImgMiddleWidth = sightImg.getWidth() / 2;
            sightImgMiddleHeight = sightImg.getHeight() / 2;
        } catch (IOException ex) {
        	Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	public void RestartGame()
	{
		board.ResetBoard();
	}

	public void UpdateGame(long gameTime, Point mousePosition)
	{
		board.Update(mousePosition, gameTime);

		if(false) //Game ends
			Framework.gameState = Framework.GameState.GAMEOVER;
	}

	public void Draw(Graphics2D g2d, Point mousePosition)
	{
		//background
		board.Draw(g2d);
		g2d.drawImage(sightImg, mousePosition.x - sightImgMiddleWidth, mousePosition.y - sightImgMiddleHeight, null);
	}

	public void DrawGameOver(Graphics2D g2d, Point mousePosition, long gameTime)
	{
		Draw(g2d, mousePosition);
        
        g2d.drawString("Press space or enter to restart.", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 70);
        
        if(true){//Player wins
            g2d.drawString("You have won!", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3);
            g2d.drawString("You have won in " + gameTime / Framework.SEC2NANO + " seconds.", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 20);
        }
        else{//Player loses
            g2d.setColor(Color.red);
            g2d.drawString("You lose!", Framework.frameWidth / 2 - 95, Framework.frameHeight / 3);
         
        }
	}
}