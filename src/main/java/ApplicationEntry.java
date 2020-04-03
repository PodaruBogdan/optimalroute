import optimalroute.controller.TravelerController;
import optimalroute.model.persistency.Persistency;
import optimalroute.model.persistency.PersistencyFactory;
import optimalroute.view.TravelerView;

public class ApplicationEntry {
    public static void main(String[] args){
        PersistencyFactory factory = new PersistencyFactory();
        Persistency accountsPersistency = factory.createPersistency("Account","accounts.dat");
        Persistency stationsPersistency = factory.createPersistency("StationNode","stations.dat");
        TravelerView travelerView = new TravelerView();
        new TravelerController(travelerView,accountsPersistency,stationsPersistency);
    }
}
