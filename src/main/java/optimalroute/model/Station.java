package optimalroute.model;

import java.io.Serializable;

public class Station implements Serializable {
    private Coordinate realLocation;
    protected String name;
    public Station(String name, Coordinate realLocation){
        this.name = name;
        this.realLocation = realLocation;
    }
    public Station(String name){
        this.name = name;
    }

    public Coordinate getRealLocation() {
        return realLocation;
    }

    public void setRealLocation(Coordinate realLocation) {
        this.realLocation = realLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
