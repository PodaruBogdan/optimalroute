package optimalroute.model;

public class RoleFactory{
    public Role createRole(String type){
        if(type.equals("Traveler"))
            return new Traveler();
        else if(type.equals("Admin"))
            return new Admin();
        else
            return new Employee();

    }
}
