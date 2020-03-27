package optimalroute.model;

import java.util.List;

public class BuslineBuilder {
    private String lineName;
    private List<Station> stations;
    public BuslineBuilder(String name, List<Station> stations){
        this.lineName = name;
        this.stations = stations;
    }
    public BuslineBuilder setName(String lineName){
        this.lineName = lineName;
        return this;
    }
    public BuslineBuilder setName(List<Station> stations){
        this.stations=stations;
        return this;
    }
    public Busline build(){
        return new Busline(lineName, stations);
    }
}
