package optimalroute.view;

import javax.swing.*;
import java.awt.*;


public class TravelerView extends JFrame {
    private BusLinesArea busLinesArea;
    private BusLinesListing busLinesListing;
    private LoginArea loginArea;
    public TravelerView(){
        busLinesArea = new BusLinesArea();
        busLinesListing = new BusLinesListing();
        loginArea = new LoginArea();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(busLinesListing);
        panel.add(Box.createRigidArea(new Dimension(20,80)));
        panel.add(loginArea);
        this.setContentPane(new DualView(busLinesArea, panel));
        this.pack();
        this.setVisible(true);
    }

    public BusLinesArea getBusLinesArea() {
        return busLinesArea;
    }

    public BusLinesListing getBusLinesListing() {
        return busLinesListing;
    }

    public LoginArea getLoginArea() {
        return loginArea;
    }
}
