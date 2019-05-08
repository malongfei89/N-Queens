package model;
public class ChessBoard implements Cloneable{
    private Location [][] availableSpots;
    private int sizeOfBoard;
    public ChessBoard(int size){
		sizeOfBoard = size;
        availableSpots = new Location [size][size];
        for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++)
				availableSpots[i][j] = new Location(i+1, j+1);
		}
    }
    public Location[][] getAvailableSpots(){
    	return availableSpots;
    }
    //mark the spot as "removed" as well as the spots at the same row, column and diagonal
    public void removeSpot(int xCor, int yCor){
    	//remove all spots at the same row
		for(int i = 0; i < sizeOfBoard; i++) {
			//if the spot was removed before, subtract it by 1 to mark it
			if(availableSpots[xCor - 1][i].getX() <= 0){
			    availableSpots[xCor - 1][i].xShift(-1);
			    availableSpots[xCor - 1][i].yShift(-1);
			}
			//if the spot was not removed before, set its value to (0,0) to mark it
			else
			    availableSpots[xCor - 1][i].setXY(0,0);
		}
		//remove all spots from this location up at the same column
		for(int i = xCor; i < sizeOfBoard; i++) {
			if(availableSpots[i][yCor-1].getX() <= 0){
				availableSpots[i][yCor-1].xShift(-1);
				availableSpots[i][yCor-1].yShift(-1);
			}
			else
				availableSpots[i][yCor-1].setXY(0,0);
		}
		//remove all spots at the right diagonal up from this location
		for(int i = xCor, j = yCor; i < sizeOfBoard && j < sizeOfBoard; i++, j++) {
			if(availableSpots[i][j].getX() <= 0){
				availableSpots[i][j].xShift(-1);
				availableSpots[i][j].yShift(-1);
			}
			else
				availableSpots[i][j].setXY(0,0);
		}
		//remove all spots at the left diagonal up from this location
		for(int i = xCor, j = yCor - 2; i < sizeOfBoard && j >= 0; i++, j--) {
			if(availableSpots[i][j].getX() <= 0){
				availableSpots[i][j].xShift(-1);
				availableSpots[i][j].yShift(-1);
			}
			else
				availableSpots[i][j].setXY(0,0);
		}
	}
    //check if the spot is valid
    public boolean isValid(Location test){
		return availableSpots[test.getX()-1][test.getY()-1].getX() > 0;
    }
    //find the next valid spot for the given row
    public Location firstAvailableLocation(int row){
		for	(int i = 0; i < sizeOfBoard; i++){
		    if(availableSpots[row-1][i].getX() > 0)
		        return availableSpots[row-1][i];
		}
		return null;
    }
    //reset the chess board 
    public void resetAll(){
		for(int i = 0; i < sizeOfBoard; i++){
			for(int j = 0; j < sizeOfBoard; j++)
				availableSpots[i][j].setXY(i+1, j+1);
	    }
    }
    //reset a spot and recover all removed spots from it
    public void resetSpot(int xCor, int yCor){
    	//reset all spots at the same row
		for(int i = 0; i < sizeOfBoard; i++) {
		//if the spot was removed more than once before, add 1 to it to reset it
			if(availableSpots[xCor - 1][i].getX() < 0){
			    availableSpots[xCor - 1][i].xShift(1);
			    availableSpots[xCor - 1][i].yShift(1);
			}
			//if the spot was removed only once, reset its value
			else if(availableSpots[xCor - 1][i].getX() == 0)
			    availableSpots[xCor - 1][i].setXY(xCor, i+1);
		}
		//reset all spots from this location up at the same column
		for(int i = xCor; i < sizeOfBoard; i++) {
			if(availableSpots[i][yCor-1].getX() < 0){
				availableSpots[i][yCor-1].xShift(1);
				availableSpots[i][yCor-1].yShift(1);
			}
			else if(availableSpots[i][yCor-1].getX() == 0)
				availableSpots[i][yCor-1].setXY(i+1, yCor);
		}
		//reset all spots at the right diagonal up from this location
		for(int i = xCor, j = yCor; i < sizeOfBoard && j < sizeOfBoard; i++, j++) {
			if(availableSpots[i][j].getX() < 0){
				availableSpots[i][j].xShift(1);
				availableSpots[i][j].yShift(1);
			}
			else if(availableSpots[i][j].getX() == 0)
				availableSpots[i][j].setXY(i+1, j+1);
		}
		//reset all spots at the left diagonal up from this location
		for(int i = xCor, j = yCor - 2; i < sizeOfBoard && j >= 0; i++, j--) {
			if(availableSpots[i][j].getX() < 0){
				availableSpots[i][j].xShift(1);
				availableSpots[i][j].yShift(1);
			}
			else if(availableSpots[i][j].getX() == 0)
				availableSpots[i][j].setXY(i+1, j+1);
		}
	}
} 
