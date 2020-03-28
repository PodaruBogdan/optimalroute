package optimalroute.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class StationNode implements Serializable {
    private String id;
    private Station station;
    private Coordinate apparentCoordinate;
    private List<String> busLines;
    private List<StationNode> neighbors;

    public List<String> getBusLines() {
        return busLines;
    }

    public void setBusLines(List<String> busLines) {
        this.busLines = busLines;
    }

    public void addBusLine(String name){
        busLines.add(name);
    }

    public void setNeighbors(List<StationNode> neighbors) {
        this.neighbors = neighbors;
    }

    public StationNode(String id, Station station, Coordinate apparentCoordinate) {
        this.id = id;
        this.station = station;
        this.apparentCoordinate = apparentCoordinate;
        neighbors=new ArrayList<>();
        busLines=new ArrayList<>();
    }
    public StationNode(String id, Station station, Coordinate apparentCoordinate,List<StationNode> neighbors) {
        this.id = id;
        this.station = station;
        this.apparentCoordinate = apparentCoordinate;
        this.neighbors = neighbors;
        busLines=new ArrayList<>();
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
    public void removeNeighbors(){
        List<StationNode> neighbors = getNeighbors();

        for(StationNode neighbor: neighbors){
            List<StationNode> recNeighbors = new ArrayList<>(neighbor.getNeighbors());
            recNeighbors.remove(this);
            neighbor.setNeighbors(recNeighbors);
        }
        this.neighbors = new ArrayList<>();

    }


    public String toString(){
        String neighbors = "";
        for(StationNode s:this.getNeighbors()){
            neighbors+= s.getStation().getName()+" ";
        }
        return id+" "+station.getName()+" "+apparentCoordinate+" "+neighbors;
    }


    private static StationNode getMin(HashSet<StationNode> visited,HashMap<StationNode,Integer> dist){
        int min = Integer.MAX_VALUE;
        StationNode u = null;
        for(StationNode n:visited){
            if(dist.get(n)<min){
                min=dist.get(n);
                u = n;
            }
        }
        return u;
    }

    public static List<StationNode> Dijkstra(List<StationNode> nodes,String source,String destination){

        HashMap<StationNode,Integer> dist = new HashMap<>();
        HashMap<StationNode,StationNode> prev =new HashMap<>();
        HashSet<StationNode> visited = new HashSet<>();
        StationNode start=null;
        for(StationNode v : nodes){
            dist.put(v,Integer.MAX_VALUE);
            prev.put(v,null);
            visited.add(v);
            if(v.getStation().getName().equals(source))
                start=v;
        }
        dist.replace(start,0);
        while(!visited.isEmpty()){
            StationNode u = getMin(visited,dist);
        }

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
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
