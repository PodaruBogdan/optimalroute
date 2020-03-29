package optimalroute.view;


import optimalroute.model.Coordinate;
import optimalroute.model.StationNode;
import optimalroute.model.persistency.Persistency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class BusLinesArea extends JPanel implements MouseMotionListener {
    private Persistency persistency;
    private List<StationNode> stationNodes;
    private String currentBus;
    int x=0,y=0;
    public void setSelectedBus(String value){
        currentBus = value;
    }

    public BusLinesArea(Persistency persistency) {
        this.persistency = persistency;
        stationNodes = persistency.getAll();
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(800, 600));
        this.addMouseMotionListener(this);

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(currentBus!=null) {
            for(StationNode station:stationNodes){
                if(station.getBusLines().contains(currentBus.substring(7))) {
                    Coordinate c = station.getApparentCoordinate();
                    g.setColor(Color.BLACK);
                    g.drawOval(c.getX() - 20, c.getY() - 20, 40, 40);
                    g.fillOval(c.getX() - 20, c.getY() - 20, 40, 40);
                    g.setColor(Color.GREEN);
                    g.drawOval(c.getX() - 16, c.getY() - 16, 32, 32);
                    g.fillOval(c.getX() - 16, c.getY() - 16, 32, 32);
                    g.setColor(Color.BLACK);
                    g.drawString(station.getStation().getName(), c.getX() + 25, c.getY() + 25);
                }
            }
            g.setColor(Color.RED);
            for(StationNode stationNode:stationNodes) {
                Coordinate c1 = stationNode.getApparentCoordinate();
                for(StationNode neighbor:stationNode.getNeighbors()){
                    Coordinate c2 = neighbor.getApparentCoordinate();
                    if(stationNode.getBusLines().contains(currentBus.substring(7)) && neighbor.getBusLines().contains(currentBus.substring(7))) {
                        g.setColor(Color.RED);
                        g.drawLine(c1.getX(),c1.getY(),c2.getX(),c2.getY());
                    }
                }
            }
        }
        this.repaint();
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x=e.getX();
        y=e.getY();
        this.repaint();
    }
}

