package optimalroute.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StationNode implements Serializable {
    private int id;
    private Station station;
    private Coordinate apparentCoordinate;
    private List<StationNode> neighbors;


    public StationNode(int id, Station station, Coordinate apparentCoordinate) {
        this.id = id;
        this.station = station;
        this.apparentCoordinate = apparentCoordinate;
        neighbors=new ArrayList<>();
    }
    public StationNode(int id, Station station, Coordinate apparentCoordinate,List<StationNode> neighbors) {
        this.id = id;
        this.station = station;
        this.apparentCoordinate = apparentCoordinate;
        this.neighbors = neighbors;
    }

    public void addNeighbor(StationNode neighbor){
        if(!neighbors.contains(neighbor))
            neighbors.add(neighbor);
        if(!neighbor.getNeighbors().contains(this)){
            neighbor.addNeighbor(this);
        }
    }

    public List<StationNode> getNeighbors(){
        return neighbors;
    }

    public String toString(){
        return id+" "+station.getName()+" "+apparentCoordinate;
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
