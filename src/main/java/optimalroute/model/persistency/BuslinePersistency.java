package optimalroute.model.persistency;

import optimalroute.model.Busline;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BuslinePersistency extends Persistency<Busline> {
    protected String fileName;

    public BuslinePersistency(String fileName){
        this.fileName = fileName;
        File file = new File(fileName);
        if(!file.exists()) {
            try {
                FileOutputStream f = new FileOutputStream(fileName);
                f.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean add(List<Busline> map){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(map);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public List<Busline> getAll(){
        List<Busline> result=null;
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            result = (List<Busline>)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        }catch (EOFException e) {
            result = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
