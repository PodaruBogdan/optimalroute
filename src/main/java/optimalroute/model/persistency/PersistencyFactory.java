package optimalroute.model.persistency;

public class PersistencyFactory {
    public Persistency createPersistency(String type,String fileName){
        if(type.equals("Account"))
            return new AccountPersistency(fileName);
        else if(type.equals("Report"))
            return new ReportPersistency();
        else if(type.equals("StationNode"))
            return new StationNodePersistency(fileName);
        else
            return new UserPersistency();
    }
}
