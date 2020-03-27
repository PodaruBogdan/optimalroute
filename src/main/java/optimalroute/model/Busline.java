package optimalroute.model;

import java.util.List;

public class Busline {
    private List<Station> stations;
    protected String lineName;
    public Busline(String lineName, List<Station> stations){
        this.stations=stations;
        this.lineName=lineName;
    }
    public void addStation(Station station){
        stations.add(station);
    }
}
