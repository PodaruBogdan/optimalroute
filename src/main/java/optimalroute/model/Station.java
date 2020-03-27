package optimalroute.model;

public class Station {
    private Coordinate realLocation;
    protected String name;
    public Station(String name, Coordinate realLocation){
        this.name = name;
        this.realLocation = realLocation;
    }
}
