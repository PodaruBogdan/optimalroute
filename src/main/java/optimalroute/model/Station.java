package optimalroute.model;

public class Station {
    private Coordinate location;
    protected String name;
    public Station(String name, Coordinate location){
        this.name = name;
        this.location = location;
    }
}
