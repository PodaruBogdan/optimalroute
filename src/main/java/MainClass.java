import optimalroute.model.Coordinate;
import optimalroute.model.Station;
import optimalroute.model.StationNode;
import optimalroute.model.persistency.StationNodePersistency;

import java.util.List;

public class MainClass {
    public static void main(String[] args){
        StationNodePersistency persistency = new StationNodePersistency("stations.dat");
        StationNode node1 = new StationNode(1,new Station("station1"),new Coordinate(30,30));
        StationNode node2 = new StationNode(2,new Station("station2"),new Coordinate(40,40));
        persistency.add(node1);
        persistency.add(node2);

        List<StationNode> stations = persistency.getAll();
        for(StationNode s:stations){
            System.out.println(s);
        }
    }
}
