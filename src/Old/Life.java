/**
* @author Luis José Quintana Bolaño
* @version 0.01
**/

import java.util.*;
import java.io.*;

public class Life 
{
	static final int DEAD=0;
	static final int ALIVE=1;
	static final int RED=2;
	private int[][] board;
	private int population;
	private int prevpop;
	private int sgen;
	private int generation;

	public Life (int bsize, int ncell)
	{
		board=new int[bsize+2][bsize+2];
		population=ncell;
		prevpop=0;
		generation=1;
		ArrayList<Integer> x=new ArrayList<Integer>(bsize);
		ArrayList<ArrayList<Integer>> y= new ArrayList<ArrayList<Integer>>(bsize);
		for (int i=1;i<=bsize;++i){
			x.add(i);
			ArrayList<Integer> row= new ArrayList<Integer>(bsize);
			for (int j=1;j<=bsize;++j){
				row.add(j);
			}
			y.add(row);
		}
		Random rand= new Random();
		for (int i=1;i<=ncell;++i){
			int aux=rand.nextInt(x.size());
			board[x.get(aux)][y.get(aux).remove(rand.nextInt(y.get(aux).size()))]=ALIVE;
			if(y.get(aux).isEmpty()){
				y.remove(aux);
				x.remove(aux);
			}
		}		
	}
	
	public String toString()
	{
		String s= "";
		for(int i=1;i<board.length-1;++i){
			for(int j=1;j<board.length-1;++j){
				s+=((board[i][j]==1)?"0":" ")+" ";
			}
			s+="\n";
		}
		s+="Poblacion: "+((population==0)?"MUERTA":population)
			+((prevpop==population)?" (estable durante "+sgen+" generaciones)\n":"\n");
		return s+="Generacion: "+generation+"\n";
	}
	
	public void setAlive(int x, int y){
		board[x][y]=ALIVE;
	}
	public void setRed(int x, int y){
		board[x][y]=RED;
	}
	public void setDead(int x, int y){
		board[x][y]=DEAD;
	}
	public int sigGen()
	{ //THIS IS A STUB, but it shall remain for testing purposes.
		prevpop=population;
		population=0;
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
							auxg+=((boardaux[i+ii][j+jj]==ALIVE)?1:0);
							auxr+=((boardaux[i+ii][j+jj]==RED)?1:0);
						}
				int aux=auxg+auxr;
				if(aux<2||(aux>4 && boardaux[i][j]!=DEAD))
					board[i][j]=DEAD;
				else if (boardaux[i][j]==DEAD && auxg==3 && auxr<3)
					board[i][j]=ALIVE;
				else if (boardaux[i][j]==DEAD && auxr==3 && auxg<3)
					board[i][j]=RED;
				else if (boardaux[i][j]==RED && auxg>auxr)
					board[i][j]=DEAD;
				else if (boardaux[i][j]==ALIVE && auxr>auxg)
					board[i][j]=DEAD;
				else
					board[i][j]=boardaux[i][j];
				if(board[i][j]!=DEAD)
					population++;
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
					population++;
				=====================================================*/
			}
		}
		generation++;
		sgen=(prevpop==population)?sgen+1:0;
		return population;
	}

	public int[][] currentBoard()
	{
		return board;
	}

	public int currentPopulation()
	{
		return population;
	}

	public int previousPopulation()
	{
		return prevpop;
	}

	public int currentGeneration()
	{
		return generation;
	}

	public int stableGeneration()
	{
		return sgen;
	}
	public int size()
	{
		return board.length-2;
	}
    public static void main(String[] args) {

		Scanner scan= new Scanner(System.in);
		Life game= new Life(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
		do{
			System.out.println(game.toString());
			scan.nextLine();
		}while (game.sigGen()!=0);
	}
}
