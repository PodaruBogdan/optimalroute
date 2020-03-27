package optimalroute.model.persistency;

import optimalroute.model.StationNode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StationNodePersistency extends Persistency<StationNode>{
    protected String fileName;
    public StationNodePersistency(String fileName){
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

    private boolean checkContained(StationNode node, List<StationNode> list){
        for(StationNode n:list){
            if(node.getId() == n.getId())
                return true;
        }
        return false;
    }

    public boolean add(StationNode node){
        List<StationNode> result = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            result = (List<StationNode>) objectInputStream.readObject();
            if(checkContained(node, result)){
                System.out.println("Station node with id "+node.getId()+" already exists!");
                return false;
            }
            result.add(node);
            objectInputStream.close();
            fileInputStream.close();
        }catch (EOFException e) {
            result = new ArrayList<>();
            result.add(node);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(result);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean remove(StationNode node){
        return true;
    }
    public List<StationNode> getAll(){
        List<StationNode> result = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            result = (List<StationNode>)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }


}
