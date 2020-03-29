package optimalroute.view;
import optimalroute.model.persistency.Persistency;
import javax.swing.*;

public class EmployeeView extends JFrame {
    private MapArea mapArea;
    private NodeTool nodeTool;
    public EmployeeView(Persistency persistency){
        mapArea=new MapArea(persistency);
        nodeTool=new NodeTool();
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setContentPane(new DualView(mapArea,nodeTool));
        this.pack();
        this.setVisible(true);

    }
    public MapArea getMapArea() {
        return mapArea;
    }
    public NodeTool getNodeTool() {
        return nodeTool;
    }
}
