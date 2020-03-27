package optimalroute.model;

public class StationNode {
    private int id;
    private Station station;
    private Coordinate apparentCoordinate;


    public StationNode(int id, Station station, Coordinate apparentCoordinate) {
        this.id = id;
        this.station = station;
        this.apparentCoordinate = apparentCoordinate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Coordinate getApparentCoordinate() {
        return apparentCoordinate;
    }

    public void setApparentCoordinate(Coordinate apparentCoordinate) {
        this.apparentCoordinate = apparentCoordinate;
    }
}
