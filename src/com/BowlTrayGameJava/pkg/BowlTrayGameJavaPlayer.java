package com.BowlTrayGameJava.pkg;
import  java.io.*;

class BowlTrayGameJavaPlayer {
	BowlTrayGameJavaPlayer(String  name,  int  playerNum)  {
        this.name  =  name;
        this.playerNum  =  playerNum;
    }
	
	public  String  getName()  {
        if  (name  !=  null)
           return  name;
        else
           return  "Computer";
    }
	
	public  int  getPlayerNum()  {
        return  this.playerNum;
    }
	
	public  int  selectAMove(BowlTrayGameJavaBoard board)   throws  
    IOException  {
			if  (name  !=  null)  {    // Real  player  -  not  the  computer
				BufferedReader  br  =
				new  BufferedReader(
				new InputStreamReader(System.in));
				System.out.print("Enter  a  bowl  to  move from:  ");          // Prompt,
				System.out.flush();
				int  bowlNum  =  Integer.parseInt(br.readLine()); //  get the move,
				return  bowlNum - 1;									//     and return it.
			}

			//Computer player - need to determine best move
			int  bestMove  =  -1;        // No best move initially
			int  repeatMove  =  -1;      // Or go again.
			int  maxNewSeeds  =  -1;    // No move has added seeds to the tray.
			// Trying the possible moves
			for  (int  bowlNum  =  0;  bowlNum  < board.playingBowls;  bowlNum++)  {
				if  (board.seedsInBowl(playerNum, bowlNum)  !=  0)  {	// Only nonempty bowls may be moved from
					BowlTrayGameJavaBoard  testBoard  =  board.makeACopy();		// Make a copy of the board
					boolean  goAgain  = 
							testBoard.doTheMove(playerNum, bowlNum);			// Try the move on the board copy.
					if  (goAgain)										// If move allows us to go again,
						repeatMove  =  bowlNum;							//      remember the move.
					int  newSeeds  =  
							testBoard.seedsInTray(playerNum)  -  
							board.seedsInTray(playerNum);				// See how many seeds this move added
										//      to our tray.
					if  (newSeeds  >  maxNewSeeds) {		// More seeds 
							//     than so far?
						maxNewSeeds = newSeeds;		// Remember 
						bestMove  =  bowlNum;				//     how many 
							//     and the move.
					}
				}
			}

			// Tried all possibilities, return the best one
			if  (maxNewSeeds  >  1)  // maxNewSeeds > 1 means a 
				//      multiseed capture occurred.
				return  bestMove;
			else  if  (repeatMove  !=  -1) // Barring that, use a "go 
				//      	again".
				return  repeatMove;
			else
				return  bestMove;    // 1 or possibly 0 seeds added; oh well!
		}
	
	String  name;
    int  playerNum;
	
}