package optimalroute.model.persistency;

import java.io.File;
import java.util.List;

public class UserPersistency extends Persistency {
    protected String fileName;

    @Override
    public List<Object> getAll() {
        return null;
    }

    @Override
    public boolean add(Object obj) {
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        return false;
    }
}
