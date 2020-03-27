package optimalroute.model;

import java.util.List;

public class Busline {
    private List<StationNode> nodes;
    private List<List<StationNode>> links;
    protected String lineName;

    public Busline(String lineName, List<StationNode> nodes){
        this.nodes = nodes;
        this.lineName=lineName;
    }
    public void addStationNone(StationNode node){
        nodes.add(node);
    }
    public void createLink(StationNode node1, StationNode node2){
        List<StationNode> node1Neighbors = links.get(node1.getId());
        List<StationNode> node2Neighbors = links.get(node2.getId());
        node1Neighbors.set(node2.getId(),node2);
        node2Neighbors.set(node1.getId(),node1);
    }
    public void createLinksBetweenAll(List<StationNode> nodes){

    }

}
