package optimalroute.controller;
import com.opencsv.exceptions.CsvValidationException;
import optimalroute.model.*;
import optimalroute.model.persistency.Persistency;
import optimalroute.view.EmployeeView;
import optimalroute.view.MapArea;
import optimalroute.view.TravelerView;
import org.apache.commons.lang3.tuple.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

public class EmployeeController {
    Persistency persistency;
    EmployeeView view;
    TravelerView travelerView;
    public EmployeeController(TravelerView travelerView, EmployeeView view, Persistency persistency){
        this.view=view;
        this.travelerView=travelerView;
        this.persistency=persistency;
        view.getNodeTool().AddSaveListener(new SaveListener());
        view.getNodeTool().AddAddLinkListener(new AddLinkListener());
        view.getNodeTool().AddAddListener(new AddListener());
        view.getNodeTool().AddRemoveListener(new RemoveListener());
        view.getNodeTool().AddOptimalListener(new SearchOptimalListener());
        //view.getNodeTool().AddSaveMapListener(new SaveMapListener());
    }

    class AddLinkListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MapArea.toggleLink();
        }
    }
    class AddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            MapArea.toggleAdd();
        }
    }
    private List<String> getStationNames(List<StationNode> stations){
        List<String> result=new ArrayList<>();
        for(StationNode node: stations){
            List<String> l = node.getBusLines();
            for(String s:l) {
                if (!result.contains(s)) {
                    result.add(s);
                }
            }
        }
        return result;
    }
    class SaveListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            List<StationNode> list= view.getMapArea().getData();
            persistency.add(list);
            travelerView.getBusLinesArea().setStationNodes(list);
            DefaultListModel<String> model = new DefaultListModel<>();
            for(String s:getStationNames(list)){
                model.addElement("Line : "+s);
            }
            travelerView.getBusLinesListing().getList().setModel(model);
            MapArea.toggleSave();
        }
    }





    class SearchOptimalListener implements ActionListener{

        private Child getMyRoot(Stack<StationNode> stat,HashMap<StationNode,Integer> distances){
            Child root=new Child("optimal_route");
            root.setType("route_info");
            StationNode end=null;
            for(int i=0;i<stat.size();i++) {
                if (stat.get(i).getStation().getName().equals(view.getNodeTool().getToField().getText())) {
                    end = stat.get(i);
                    break;
                }
            }
            Child cost=new Child(""+distances.get(end));
            cost.setType("cost");
            root.addChild(cost);
            while(!stat.isEmpty()){
                StationNode node=stat.pop();
                Child c=new Child(node.getStation().getName());
                c.setType("station");
                Child buses=new Child("buslines");
                buses.setType("buslines");
                HashSet<String> s=new HashSet(node.getBusLines());
                for(String b:s){
                    Child t=new Child(b);
                    t.setType("busline");
                    buses.addChild(t);
                }
                Child neighbors=new Child("neighbors");
                neighbors.setType("neighbors");
                for(StationNode n:node.getNeighbors()){
                    Child t=new Child(n.getStation().getName());
                    t.setType("neighbor");
                    neighbors.addChild(t);
                }
                Child appC = new Child(node.getApparentCoordinate().toString());
                Child ID = new Child(node.getId());
                ID.setType("id");
                appC.setType("apparent_coordinate");
                c.addChild(ID);
                c.addChild(appC);
                c.addChild(neighbors);
                c.addChild(buses);
                root.addChild(c);
            }
            return root;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Pair<Stack<StationNode>, HashMap<StationNode,Integer>> list= StationNode.Dijkstra(persistency.getAll(),view.getNodeTool().getFromField().getText(),view.getNodeTool().getToField().getText());
            if(list!=null) {
                Stack<StationNode> stat = list.getKey();
                HashMap<StationNode, Integer> distances = list.getValue();
                Object[] options = {".csv",
                        ",json",
                        ".xml"};

                int result = JOptionPane.showOptionDialog(null,
                        "Save found route as",
                        "Save file",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        null);
                switch (result) {
                    case 0:
                        try {
                            StationNode end = null;
                            for (int i = 0; i < stat.size(); i++) {
                                if (stat.get(i).getStation().getName().equals(view.getNodeTool().getToField().getText())) {
                                    end = stat.get(i);
                                    break;
                                }
                            }
                            String data = distances.get(end) + ",";
                            while (!stat.isEmpty()) {
                                StationNode s = stat.pop();
                                data += s.getStation().getName() + " " + s.getApparentCoordinate() + ",";
                            }
                            String[] line = CSVReport.convertToCSV(data, ',');
                            CSVReport.writeLine("optimal.csv", line, ',');
                        } catch (CsvValidationException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 1:
                        Child root = getMyRoot(stat, distances);
                        JSONReport.writeLine("optimal.json", root);
                        break;
                    case 2:
                        Child root2 = getMyRoot(stat, distances);
                        XMLReport.writeLine(root2, "optimal.xml");
                        break;
                    default:
                        break;
                }
            }
        }

    }

    class SaveMapListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    class RemoveListener implements ActionListener {
        @Override public void actionPerformed (ActionEvent e){
            MapArea.toggleRmv();
        }
    }

    public EmployeeView getView() {
        return view;
    }

}
