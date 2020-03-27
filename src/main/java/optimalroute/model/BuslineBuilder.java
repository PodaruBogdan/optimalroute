package optimalroute.model;

import java.util.LinkedList;
import java.util.List;

public class BuslineBuilder {
    private String lineName;
    private LinkedList<StationNode> nodes;
    // private List<List<StationNode>> links;


    public BuslineBuilder(String name, LinkedList<StationNode> nodes){
        this.lineName = name;
        this.nodes = nodes;
    }
    public BuslineBuilder setName(String lineName){
        this.lineName = lineName;
        return this;
    }
    public BuslineBuilder setName(LinkedList<StationNode> nodes){
        this.nodes=nodes;
        return this;
    }
    public Busline build(){
        return new Busline(lineName, nodes);
    }
}
