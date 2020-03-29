package optimalroute.controller;
import optimalroute.model.persistency.Persistency;
import optimalroute.view.EmployeeView;
import optimalroute.view.MapArea;
import optimalroute.view.TravelerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    class SaveListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            persistency.add(view.getMapArea().getData());
            travelerView.getBusLinesArea().setStationNodes(view.getMapArea().getData());
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
