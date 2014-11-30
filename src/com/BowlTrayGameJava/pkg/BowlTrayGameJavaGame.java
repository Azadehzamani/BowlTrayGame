package com.BowlTrayGameJava.pkg;
import java.io.*;

class BowlTrayGameJavaGame {
	BowlTrayGameJavaGame(String  name0,  String  name1)  {
	        board  =  new BowlTrayGameJavaBoard();
	        board.setUpForPlay();
	        players  =  new BowlTrayGameJavaPlayer[2];
	        players[0]  =  new BowlTrayGameJavaPlayer(name0,  0);
	        players[1]  =  new BowlTrayGameJavaPlayer(name1,  1);
	        currentPlayer  =  0;
	    }

	    public  void play()  throws  IOException  {
	        displayBoard();
	        while  (!board.gameOver())  {
	           int  bowlNum  =  players[currentPlayer].      
	                       selectAMove(board);  // The player chooses 
	                                            //    the move.
			    boolean  goAgain  =  
	                    board.doTheMove(currentPlayer, bowlNum);
	                                         //    which is then carried 
	                                         //     out by the board.
			    bowlNum++;
				System.out.println("Player  "  +  currentPlayer  +
	                           " moved  from  "  +  bowlNum);
				displayBoard();
				if  (!goAgain)         // If the current player does not go again,
				if  (currentPlayer  ==  0)     // switch to the other player.
					currentPlayer  =  1;
				else
					currentPlayer  =  0;
				else
				System.out.println("Player  "  +  
	                         currentPlayer  + "  goes  again");
			}
			board.emptySeedsIntoTrays();    // Game is over                                        //      board empty stones,
			displayBoard();                     //      display final board,
			if  (board.seedsInTray(0)  > 
					board.seedsInTray(1))	//      and announce winner.
				System.out.println(players[0].getName()+"  wins");
			else if (board.seedsInTray(0)  <  
	                                 board.seedsInTray(1))
				System.out.println(players[1].getName()+"  wins");
			else
				System.out.println("Tie");
		}

	    private  void  displayBoard()  {
	        String  gameLineFiller  =  "";    // Used to properly 
	                                             //      space the 
	                                             //      game line
	        System.out.println("-----------------------");
	                                             // Top border
	        // Player 1's pit line
	        System.out.print("      ");   // space  past  tray  entry
	        for  (int  i  =  board.playingBowls-1;  i  >= 0 ; i--)  {
	           System.out.print(board.seedsInBowl(1,  i)  +  
	                      "    ");    // Print bowl's contents and bowl spacing.
	           gameLineFiller  +=  "     ";   // Build tray 
	                                             //      spacing string.
	        }
	        displayPlayer(1);    // Player 1 info
	        // tray line
	        System.out.print(board.seedsInTray(1)  +  
	                   "    ");    // Print player 1's tray and spacing.
	        System.out.print(gameLineFiller);   // Space past bowl 
	                                               //      entries.
	        System.out.println(board.seedsInTray(0));
	                                       // Print player 0's tray.
	        // Player 0's bowl line
	        System.out.print("      ");
	        for  (int  i  =  0;  i  <  
	                board.playingBowls;  i++)
	           System.out.print(board.seedsInBowl(0,  i)  +  
	                       "    ");    // Print bowl's contents and spacing.
	        displayPlayer(0);    // Player  0  info
	        System.out.println("-----------------------"); 
	                                               // Bottom border
	    }

	    private  void  displayPlayer(int  playerNum)  {
	        // Turn indicator
	        if  (currentPlayer  ==  playerNum)			// If it this player's turn,
			    System.out.print("            -->");	//      display turn 
														//      indicator,
	        else
				System.out.print("                  "); //      or display equal number of spaces otherwise.

			// player info
	        System.out.println("Player  "  +  playerNum  +  
					"(  "  +
					players[playerNum].getName()  +  ")");
	    }

	    public  static  void  main(String  []  args)  throws  
	                               IOException  {
			BowlTrayGameJavaGame  game  =  new  BowlTrayGameJavaGame("Azadeh",  null);
			game.play();
	    }

	    int  currentPlayer  =  0;
	    BowlTrayGameJavaBoard  board;
	    BowlTrayGameJavaPlayer  []  players;

}
