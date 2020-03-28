package optimalroute.model.persistency;

import java.util.List;

public abstract class Persistency<T> {
    public abstract List<T> getAll();
    public abstract boolean add(List<T> obj);

}
