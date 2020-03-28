package optimalroute.model;

import java.io.Serializable;

public class Coordinate implements Serializable {
    private int x;
    private int y;
    public Coordinate(int x,int y){
        this.x = x;
        this.y = y;
    }

    public static Coordinate getMiddleFrom(Coordinate c1, Coordinate c2){
       return new Coordinate((c1.getX()+c2.getX())/2, (c1.getY()+c2.getY())/2);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString(){
        return "("+x+","+y+")";
    }
    @Override
    public boolean equals(Object obj){
        Coordinate c = (Coordinate)obj;
        if(x == c.getX() && y == c.getY()){
            return true;
        }
        return false;
    }


}
