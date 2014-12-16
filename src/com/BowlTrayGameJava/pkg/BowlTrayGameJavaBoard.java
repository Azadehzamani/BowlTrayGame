package com.BowlTrayGameJava.pkg;

class BowlTrayGameJavaBoard {
	BowlTrayGameJavaBoard()  {
        bowls  =  new  BowlTrayGameJavaBowl[totalBowls];
        for  (int  bowlNum  =  0;  bowlNum  <  totalBowls;  
              bowlNum++)
			bowls[bowlNum]  =  new BowlTrayGameJavaBowl();
    }

    public  void  setUpForPlay()  {
        for  (int  bowlNum  =  0;  bowlNum  <  totalBowls;  
              bowlNum++)
               if  (!isATray(bowlNum))
                   bowls[bowlNum].addSeeds(3);
    }

    public  int  seedsInTray(int  playerNum)  {
		return  bowls[getTray(playerNum)].getSeeds();
    }

    public  int  seedsInBowl(int  playerNum, int bowlNum)  {
        return  bowls[getBowlNum(playerNum, bowlNum)].getSeeds();
    }

    private  int  getBowlNum(int  playerNum, int  bowlNum) {
    	if(playerNum == 0)
    		return bowlNum;
    	else
    		return  bowlNum + playingBowls + 1;
    }

    private  int  getTray(int  playerNum)  {
        return  playerNum  *  (playingBowls + 1) + 6;
    }

    private  boolean  isATray(int  bowlNum)  {
    	if(bowlNum == 6 || bowlNum == 13)
    		return true;
    	else
    		return false;
    }

    public  BowlTrayGameJavaBoard  makeACopy()  {
    	BowlTrayGameJavaBoard  newBoard  =  new  BowlTrayGameJavaBoard();
        for  (int  bowlNum  =  0;  bowlNum  <  totalBowls;  
              bowlNum++)
           newBoard.bowls[bowlNum].addSeeds(this. 
                                 bowls[bowlNum].getSeeds());
        return  newBoard;
    }

    public  boolean  doTheMove(int  currentPlayerNum,  
                               int  chosenBowlNum)  {
		int  bowlNum  =  getBowlNum(currentPlayerNum, chosenBowlNum);
		if(ownerOf(bowlNum) == currentPlayerNum)
		{
			if(!isATray(bowlNum))
			{
				int  seeds  =  bowls[bowlNum].removeSeeds();
				while  (seeds  !=  0)  {
					bowlNum++;
					if  (bowlNum  >  totalBowls - 1)
						bowlNum  =  0;
					if  (bowlNum  !=  
							getTray(otherPlayerNum(currentPlayerNum)))  {
						bowls[bowlNum].addSeeds(1);
						seeds--;
					}
				}
				if  (bowlNum  ==  getTray(currentPlayerNum))
					return  true;
				if  (ownerOf(bowlNum)  ==  currentPlayerNum  &&
						bowls[bowlNum].getSeeds()  ==  1)  {
					seeds = bowls[bowlNum].removeSeeds();
					bowls[getTray(currentPlayerNum)].addSeeds(seeds);
					seeds  =  bowls[oppositeBowlNum(bowlNum)].removeSeeds();
					bowls[getTray(currentPlayerNum)].addSeeds(seeds);
				}
				return false;
			}
			else
			{
				System.out.println("cannot move from tray");
				return true;
			}
		}
		else
		{
			System.out.println("cannot move from other player's bowl");
			return true;	
		}
	}

	private  int  ownerOf(int  bowlNum)  {
        return  bowlNum  /  (playingBowls + 1);
	}

	private  int  oppositeBowlNum(int  bowlNum)  {
		return  totalBowls  -  bowlNum - 2;
	}

	private  int  otherPlayerNum(int  playerNum)  {
		if  (playerNum  ==  0)
			return  1;
		else
			return  0;
	}

	public  boolean  gameOver()  {
		for  (int  player  =  0;  player  <  2;  player++)  {
	        int  seeds  =  0;
		    for  (int  bowlNum  =  0;  bowlNum  <  playingBowls; bowlNum++)
				seeds  +=  bowls[getBowlNum(player, bowlNum)].getSeeds();
			if  (seeds  ==  0)
               return  true;
        }
        return  false;
    }

    public  void  emptySeedsIntoTrays()  {
        for  (int  player  =  0;  player  <  2;  player++)
           for  (int  bowlNum  =  0;  bowlNum  <=  playingBowls;  bowlNum++)  {
               int  seeds  =  bowls[getBowlNum(player,  
                                 bowlNum)].removeSeeds(); 
                                 bowls[getTray(player)]. 
                                 addSeeds(seeds);
           }
    }

    BowlTrayGameJavaBowl  []  bowls;
    final  int  playingBowls=6;
	final int totalBowls  =  2*(playingBowls+1);

}
