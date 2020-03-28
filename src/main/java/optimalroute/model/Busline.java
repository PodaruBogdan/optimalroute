package optimalroute.model;

import java.io.Serializable;
import java.util.LinkedList;

public class Busline implements Serializable {
    private LinkedList<StationNode> stations;
    private String name;

    public Busline(String name, LinkedList<StationNode> stations){
        this.name = name;
        this.stations = stations;
    }
    public Busline(String name){
        this.name = name;
        this.stations = new LinkedList<>();
    }
    public void addStation(StationNode station){
        if(!stations.contains(station))
            stations.addFirst(station);
    }

    public LinkedList<StationNode> getStations() {
        return stations;
    }

    public void setStations(LinkedList<StationNode> stations) {
        this.stations = stations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
