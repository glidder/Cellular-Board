/**
* Class that represents the game board.
*
* @author Luis José Quintana Bolaño (@Glidder_)
* @version d1-14.3.6
**/

package coraline;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;
import javax.imageio.*;

public class Board
{
	public static final int DEAD = 0;
	public static final int GREEN = 1;
	public static final int RED = 2;
	static int SCALE;
	private long lastTimeGen;    
    private long timeBetweenGens;

	private int[][] board;
	private int bSize;
	private static BufferedImage [] ii = new BufferedImage[3];

	public Board (int bsize, long timeBetween)
	{
		Initialize(bsize, timeBetween);
		LoadContent();
	}

	private void Initialize(int bsize, long timeBetween)
	{
		timeBetweenGens= timeBetween;
		SCALE = Framework.frameHeight/bsize;
		bSize=bsize;
		ResetBoard();
	}

	private void LoadContent()
	{
		try{
			URL deadImgUrl = this.getClass().getResource("/coraline/data/celld.png");
			URL greenImgUrl = this.getClass().getResource("/coraline/data/cellg.png");
			URL redImgUrl = this.getClass().getResource("/coraline/data/cellr.png");
			ii[0] = ImageIO.read(deadImgUrl);
            ii[1] = ImageIO.read(greenImgUrl);
            ii[2] = ImageIO.read(redImgUrl);
		} catch (IOException ex) {
			Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void ResetBoard()
	{
		board = new int[bSize + 2][bSize + 2];
	}

	public void Update(Point mousePosition, long gameTime)
	{
		int newX = mousePosition.x/SCALE;
		int newY = mousePosition.y/SCALE;
		if (isInRange(newX) && isInRange(newY)){
			if(Canvas.mouseButtonState(MouseEvent.BUTTON1))
				board[newX+1][newY+1] = GREEN;
        	else if (Canvas.mouseButtonState(MouseEvent.BUTTON2))
        		board[newX+1][newY+1] = DEAD;
        	else if (Canvas.mouseButtonState(MouseEvent.BUTTON3))
        		board[newX+1][newY+1] = RED;
        }
		
		if(Canvas.keyboardKeyState(KeyEvent.VK_SPACE) && (gameTime - lastTimeGen)>=timeBetweenGens){
			nextGen();
			lastTimeGen=gameTime;
		}
	}

	private boolean isInRange(int val){
		return val >=0 && val<(board.length-2);
	}

	public void Draw(Graphics2D g2d)
	{
		for (int x=0; x<bSize; ++x)
			for (int y=0; y<bSize; ++y)
				g2d.drawImage(ii[board[x+1][y+1]], x*SCALE, y*SCALE, SCALE, SCALE, null);

		g2d.setColor(Color.white);
		g2d.drawString("Press space to advance a generation", 5, 15);

	}

	private void nextGen()
	{//THIS IS A STUB, but it shall remain for testing purposes.
		int[][] boardaux=new int[board.length][board.length];
		for(int i=0;i<board.length-1;++i)
			for(int j=0;j<board.length-1;++j)
				boardaux[i][j]=board[i][j];
		int auxg, auxr;
		for(int i=1;i<board.length-1;++i){
			for(int j=1;j<board.length-1;++j){
				auxg=auxr=0;
				for(int ii=-1;ii<2;++ii)
					for(int jj=-1;jj<2;++jj)
						if(ii!=0||jj!=0){
							auxg+=((boardaux[i+ii][j+jj]==GREEN)?1:0);
							auxr+=((boardaux[i+ii][j+jj]==RED)?1:0);
						}
				int aux=auxg+auxr;
				if(aux<2||(aux>3 && boardaux[i][j]!=DEAD))
					board[i][j]=DEAD;
				else if (boardaux[i][j]==DEAD && auxg==3 && auxr<3)
					board[i][j]=GREEN;
				else if (boardaux[i][j]==DEAD && auxr==3 && auxg<3)
					board[i][j]=RED;
				else if (boardaux[i][j]==RED && auxg>auxr)
					board[i][j]=DEAD;
				else if (boardaux[i][j]==GREEN && auxr>auxg)
					board[i][j]=DEAD;
				else
					board[i][j]=boardaux[i][j];
				/*==========ORIGINAL CONWAY'S SET OF RULES============
						if(aux<2||(aux>3 && boardaux[i][j]!=DEAD))
					board[i][j]=DEAD;
				else if (boardaux[i][j]==DEAD && auxg==3 && auxr<3)
					board[i][j]=ALIVE;
				else if (boardaux[i][j]==DEAD && auxr==3 && auxg<3)
					board[i][j]=RED;
				else
					board[i][j]=boardaux[i][j];
				if(board[i][j]!=DEAD)
				=====================================================*/
			}
		}

	}
}