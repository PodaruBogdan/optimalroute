package optimalroute.model;

import java.util.LinkedList;

public class Busline {
    private LinkedList<StationNode> stations;
    private String name;

    public Busline(String name, LinkedList<StationNode> stations){
        this.name = name;
        this.stations = stations;
    }
    public void addStation(StationNode station){
        stations.addFirst(station);
    }

}
