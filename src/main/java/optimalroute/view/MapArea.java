package optimalroute.view;

import optimalroute.model.Coordinate;
import optimalroute.model.StationNode;
import optimalroute.model.persistency.Persistency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.util.List;

public class MapArea extends JPanel implements MouseListener, MouseMotionListener {

    private int moveX  = 0;
    private int moveY  = 0;
    private int clickX = 0;
    private int clickY = 0;

    private static boolean canEdit = false;
    private static boolean canAdd = false;
    private static boolean canRmv = false;
    private static boolean canAddLink = false;
    private static boolean canSave = false;
    private Persistency stationNodePersistency;
    private List<StationNode> data;
    private static Coordinate lastSelected;
    List<Coordinate> clicks;

    public List<StationNode> getData(){
        return data;
    }

    public static void toggleEdit() {
        MapArea.canEdit = true;
        MapArea.canAdd=false;
        MapArea.canRmv=false;
        MapArea.canAddLink=false;
        MapArea.canSave=false;
    }

    public static void toggleAdd() {
        MapArea.canEdit = false;
        MapArea.canAdd=true;
        MapArea.canRmv=false;
        MapArea.canAddLink=false;
        MapArea.canSave=false;
    }

    public static void toggleRmv() {
        MapArea.canEdit = false;
        MapArea.canAdd=false;
        MapArea.canRmv=true;
        MapArea.canAddLink=false;
        MapArea.canSave=false;
    }

    public static void toggleLink() {
        MapArea.canEdit = false;
        MapArea.canAdd=false;
        MapArea.canRmv=false;
        MapArea.canAddLink=true;
        MapArea.canSave=false;
    }
    public static void toggleSave() {
        MapArea.canEdit = false;
        MapArea.canAdd=false;
        MapArea.canRmv=false;
        MapArea.canAddLink=false;
        lastSelected = null;
        MapArea.canSave=true;
    }
    public MapArea(Persistency stationNodePersistency) {
            this.setBackground(Color.white);
            this.setPreferredSize(new Dimension(800, 600));
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            this.stationNodePersistency = stationNodePersistency;
            data = stationNodePersistency.getAll();
            clicks = getListOfClicks(data);
            lastSelected = null;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawString("x=" + moveX
                    + ", y=" + moveY , 10, 30);

            for(StationNode n: data) {
                Coordinate c = n.getApparentCoordinate();
                g.setColor(Color.BLACK);
                g.drawOval(c.getX()-20, c.getY()-20, 40, 40);
                g.fillOval(c.getX()-20, c.getY()-20, 40, 40);
                g.setColor(Color.GREEN);
                g.drawOval(c.getX()-16, c.getY()-16, 32, 32);
                g.fillOval(c.getX()-16, c.getY()-16, 32, 32);
                g.setColor(Color.BLACK);
                g.drawString(n.getStation().getName(),c.getX()+25,c.getY()+25);
            }

            for(StationNode stationNode:data) {
                Coordinate c1 = stationNode.getApparentCoordinate();
                for(StationNode neighbor:stationNode.getNeighbors()){
                    Coordinate c2 = neighbor.getApparentCoordinate();
                    g.setColor(Color.RED);
                    g.drawLine(c1.getX(),c1.getY(),c2.getX(),c2.getY());
                }
            }
        }
        public static boolean checkInside(int x,int y,List<Coordinate> list){
            for(Coordinate c : list){
                if(x>= c.getX()-21 && x<=c.getX()+21 && y>= c.getY()-21 && y<=c.getY()+21)
                    return false;
            }
            return true;
        }
        private static List<Coordinate> getListOfClicks(List<StationNode> data){
            List<Coordinate> coordinates = new ArrayList<>();
            for(StationNode n:data)
                coordinates.add(n.getApparentCoordinate());
            return coordinates;
        }
        public void mouseClicked(MouseEvent e) {
            clickX = e.getX();
            clickY = e.getY();
            clicks = getListOfClicks(data);
            if(checkInside(clickX,clickY,clicks) && canAdd) {
                new NodePopUp(data,clickX,clickY);
            }
            if(canAddLink){
                if(checkInside(clickX,clickY,clicks) == false) {
                    Coordinate c = getOval(clickX, clickY);
                    if(lastSelected != null && c!=null && !lastSelected.equals(c)){
                        StationNode s1=null,s2=null;
                        for(StationNode stationNode : data){
                            if(stationNode.getApparentCoordinate().equals(c)){
                                s1 = stationNode;
                            }
                            if(stationNode.getApparentCoordinate().equals(lastSelected)){
                                s2 = stationNode;
                            }
                        }
                        if(s1!=null && s2!=null) {
                            s1.addNeighbor(s2);
                            Coordinate middle = Coordinate.getMiddleFrom(c,lastSelected);
                            new LinePopUp(s1,s2,middle.getX(),middle.getY());
                        }
                    }
                    lastSelected = c;
                }
            }
            if(canRmv){
                Coordinate c = getOval(clickX, clickY);
                if(c!=null) {
                    for (StationNode stationNode : data) {
                        if (stationNode.getApparentCoordinate().equals(c)){
                            stationNode.removeNeighbors();
                            data.remove(stationNode);
                            break;
                        }
                    }
                }
            }
        }

        public void mouseMoved(MouseEvent e) {
            moveX = e.getX();
            moveY = e.getY();
            this.repaint();
        }
        private double euclidianDistance(int x1,int x2,int y1,int y2){
            return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
        }
        private Coordinate getOval(int x,int y){
            double min=Double.MAX_VALUE;
            int minX=0, minY=0;
            clicks = getListOfClicks(data);
            for(Coordinate c: clicks){
                if(min > euclidianDistance(x,c.getX(),y,c.getY())){
                    min = euclidianDistance(x,c.getX(),y,c.getY());
                    minX = c.getX();
                    minY = c.getY();
                }
            }
            return new Coordinate(minX, minY);
        }


    public void mouseDragged (MouseEvent e) {}
        public void mouseEntered (MouseEvent e) {}
        public void mouseExited  (MouseEvent e) {}
        public void mousePressed (MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}

}
