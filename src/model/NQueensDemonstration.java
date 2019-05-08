package model;

import java.util.Scanner;

public class NQueensDemonstration{
    public static void main(String [] args){
        Scanner getInput = new Scanner (System.in);
        System.out.print("Please enter the size of the board: ");
        int size = getInput.nextInt();
        int counter = 0;
        ArrayBag solutions = findSolution(size);
        int length = solutions.size();
        Location [] answer = solutions.getData();
        if(length/size >= 1) {
        	System.out.printf("For a %d * %d chessboard, I find %d different way(s).\n",size,size,length/size);
	        for(int i = 0; i < length; i++){
	            if(counter % size == 0)
	                System.out.print("Way "+(counter/size + 1)+":\n");
	            System.out.println("Row "+ answer[i].getX()+", Column "+ answer[i].getY());
	            counter++;
			}
        }
        else System.out.printf("Sorry, I can't find a way on a %d * %d chessboard!", size,size);
        getInput.close();
    }
    //method to find out all possible solutions
    public static ArrayBag findSolution(int n){
    	//holds all possible solutions
    	ArrayBag solutions = new ArrayBag();
    	if (n<=0)
    		return solutions;
    	//holds current solution
    	ArrayBag oneSolution = new ArrayBag();
    	ChessBoard board = new ChessBoard(n);
    	Location temp;
    	boolean canFind = true;
    	//find the first solution, i stands for the row we're currently working on
    	for(int i = 1; i <= n;) {
    		temp = board.firstAvailableLocation(i);
    		//couldn't find a spot based on previous lay-out
    		while(temp == null) {
    			/*since we couldn't find one at current row, we need to go down one row 
    			and retrieve the spot we chose for the row from current solution*/
    			i--;
    			Location invalidSpot = oneSolution.getLast();
    			//reset all spots that's been removed because of invalidSpot
				board.resetSpot(invalidSpot.getX(), invalidSpot.getY());
    			invalidSpot.yShift(1);
    			//make sure after shifting, the spot is both valid and still within the chess board
    			while(invalidSpot.getY() > n || !board.isValid(invalidSpot)) {
    				/*if the spot is not in the chess board, which means we couldn't find a valid spot at the current row,
    				 we need to remove the last spot in our current solution, go down one row, reset all spots removed because of 
    				 this invalidSpot, shift it one position to the right and check
    				 */
    				if(invalidSpot.getY() > n) {
    					oneSolution.removeLast();
    					i--;
    					/*when i gets to zero, which means we couldn't find a valid spot for current solution,
    					 * we want to set canFind to false and exist
    					 */
    					if(i == 0) {
    						canFind = false;
    						break;
    					}
    					invalidSpot = oneSolution.getLast();
    					board.resetSpot(invalidSpot.getX(), invalidSpot.getY());
    					invalidSpot.yShift(1);
    				} 
    				//it's in the chess board, but not valid. Shift it again
    				else {
    					invalidSpot.yShift(1);
    				}
    			}
    			//we found a valid spot, so we remove its related spots, go up to the next row, find first available spot and go back to check
    			if(canFind) {
	    			board.removeSpot(invalidSpot.getX(), invalidSpot.getY());
	    			i++;
	    			temp = board.firstAvailableLocation(i);
    			} else break;
    		}
    		//temp is valid, we add it to our current solution, remove its related spots and go to next row
    		if(canFind) {
    			oneSolution.add(temp);
    			board.removeSpot(temp.getX(), temp.getY());
				i++;
    		} else break;
    	}
    	//add the current solution
		solutions.addAll(oneSolution.clone());
		//if we found one solution, we try to find another solution based on that with the same idea above
		while(canFind) {
			int i = n;
			Location cursorSpot = oneSolution.getLast();
			board.resetSpot(cursorSpot.getX(), cursorSpot.getY());
    		cursorSpot.yShift(1);
    		while(cursorSpot.getY() > n || !board.isValid(cursorSpot)) {
    			if(cursorSpot.getY() > n) {
    				oneSolution.removeLast();
    				i--;
    				if(i == 0) {
    					canFind = false;
    					break;
    				}
    				cursorSpot = oneSolution.getLast();
    				board.resetSpot(cursorSpot.getX(), cursorSpot.getY());
    				cursorSpot.yShift(1);
    			} else
    				cursorSpot.yShift(1);
    		}
    		if(canFind) {
    			board.removeSpot(cursorSpot.getX(), cursorSpot.getY());
    			i++;
    			while(i<=n) {
    				temp = board.firstAvailableLocation(i);
    				while(temp == null) {
    	    			i--;
    	    			cursorSpot = oneSolution.getLast();
    					board.resetSpot(cursorSpot.getX(), cursorSpot.getY());
    					cursorSpot.yShift(1);
    	    			while(cursorSpot.getY() > n || !board.isValid(cursorSpot)) {
    	    				if(cursorSpot.getY() > n) {
    	    					oneSolution.removeLast();
    	    					i--;
    	    					if(i == 0) {
    	    						canFind = false;
    	    						break;
    	    					}
    	    					cursorSpot = oneSolution.getLast();
    	    					board.resetSpot(cursorSpot.getX(), cursorSpot.getY());
    	    					cursorSpot.yShift(1);
    	    				} else {
    	    					cursorSpot.yShift(1);
    	    				}
    	    			}
    	    			if(canFind) {
    		    			board.removeSpot(cursorSpot.getX(), cursorSpot.getY());
    		    			i++;
    		    			temp = board.firstAvailableLocation(i);
    	    			} else break;
    	    		}
    	    		if(canFind) {
    	    			oneSolution.add(temp);
    	    			board.removeSpot(temp.getX(), temp.getY());
    					i++;
    	    		} else break;
    	    	}
    			if(canFind) 
    				solutions.addAll(oneSolution.clone());
    			else break;
    			}
			}
    		return solutions;
    	}
    }