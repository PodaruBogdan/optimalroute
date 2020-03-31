package optimalroute.model;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class JSONReport {
    private static void flattenTree(Child root){
        root.setProcessed(true);
        for(Child c:root.getChildList()){
            flattenTree(c);
        }
        if(root.hasChilds()) {
            boolean flag=false;
            for(Child c:root.getChildList()){
                if(!c.isProcessed()){
                    flag=true;
                }
            }
            if(flag==false){
                JSONObject object=new JSONObject();
                JSONArray array=new JSONArray();
                for(Child c:root.getChildList()){
                    array.add(c.getObj());
                }
                object.put(root.getInfo(),array);
                root.setObj(object);
            }
        }else{
            JSONObject obj = new JSONObject();
            obj.put("",root.getInfo());
            root.setObj(obj);
        }

    }
    public static void writeLine(String fileName, Child root){
        flattenTree(root);
        JSONArray result=new JSONArray();
        for(Child c:root.getChildList()){
            result.add(c.getObj());
        }
        try (FileWriter file = new FileWriter("optimal.json")) {

            file.append(result.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
