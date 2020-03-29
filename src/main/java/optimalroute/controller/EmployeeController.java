package optimalroute.controller;
import optimalroute.model.StationNode;
import optimalroute.model.persistency.Persistency;
import optimalroute.view.EmployeeView;
import optimalroute.view.MapArea;
import optimalroute.view.TravelerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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

    class RemoveListener implements ActionListener {
        @Override public void actionPerformed (ActionEvent e){
            MapArea.toggleRmv();
        }
    }

    public EmployeeView getView() {
        return view;
    }

}
