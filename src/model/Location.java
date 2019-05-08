package model;
public class Location{
    private int x;
    private int y;
    public Location(){
        x = 0;
        y = 0;
    }
    public Location(int xInitial, int yInitial){
        x = xInitial;
        y = yInitial;
    }
    public void xShift(int units){
        x += units;
    }
    public void yShift(int units){
        y += units;
    }
    public int getX(){
		return x;
    }
    public int getY(){
		return y;
	}
	public void setX(int newX){
		x = newX;
	}
	public void setY(int newY){
		y = newY;
	}
	public void setXY(int newX, int newY){
		x = newX;
		y = newY;
	}
}
