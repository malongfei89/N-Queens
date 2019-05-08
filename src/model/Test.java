package model;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChessBoard a = new ChessBoard(3);
		a.removeSpot(1, 3);
		Location [][] b = a.getAvailableSpots();
		for(int i=0;i<3;i++) {
			for(int j =0;j<3;j++) {
				System.out.println("("+b[i][j].getX()+", "+b[i][j].getY()+")");
			}
		}
		a.resetSpot(1,3);
		for(int i=0;i<3;i++) {
			for(int j =0;j<3;j++) {
				System.out.println("("+b[i][j].getX()+", "+b[i][j].getY()+")");
			}
		}
	}

}
