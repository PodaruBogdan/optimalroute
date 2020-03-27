package optimalroute.view;

import optimalroute.model.Coordinate;
import optimalroute.model.persistency.Persistency;
import optimalroute.model.persistency.StationNodePersistency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MapArea extends JPanel implements MouseListener, MouseMotionListener {

    private int moveX  = 0;
    private int moveY  = 0;
    private int clickX = 0;
    private int clickY = 0;

    private List<Coordinate> clicks;
    private static boolean canEdit = false;
    private static boolean canAdd = false;
    private static boolean canRmv = false;
    private static boolean canAddLink = false;
    private Persistency persistency;
    private int counter=0;
    private LinkedList<Coordinate> linkedList;

    public static void toggleEdit() {
        MapArea.canEdit = true;
        MapArea.canAdd=false;
        MapArea.canRmv=false;
        MapArea.canAddLink=false;
    }

    public static void toggleAdd() {
        MapArea.canEdit = false;
        MapArea.canAdd=true;
        MapArea.canRmv=false;
        MapArea.canAddLink=false;
    }

    public static void toggleRmv() {
        MapArea.canEdit = false;
        MapArea.canAdd=false;
        MapArea.canRmv=true;
        MapArea.canAddLink=false;
    }

    public static void toggleLink() {
        MapArea.canEdit = false;
        MapArea.canAdd=false;
        MapArea.canRmv=false;
        MapArea.canAddLink=true;
    }

    public MapArea(StationNodePersistency persistency) {
            this.setBackground(Color.white);
            this.setPreferredSize(new Dimension(800, 600));
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            this.clicks=new ArrayList<>();
            linkedList = new LinkedList<>();
            this.persistency = persistency;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawString("x=" + moveX
                    + ", y=" + moveY , 10, 30);

            for(Coordinate c: clicks) {
                g.setColor(Color.BLACK);
                g.drawOval(c.getX()-20, c.getY()-20, 40, 40);
                g.fillOval(c.getX()-20, c.getY()-20, 40, 40);
                g.setColor(Color.GREEN);
                g.drawOval(c.getX()-16, c.getY()-16, 32, 32);
                g.fillOval(c.getX()-16, c.getY()-16, 32, 32);
            }

            for(int i=0;i<linkedList.size()-1;i++){
                g.setColor(Color.RED);
                g.drawLine(linkedList.get(i).getX(),linkedList.get(i).getY(),linkedList.get(i+1).getX(),linkedList.get(i+1).getY());
            }


        }
        public static boolean checkInside(int x,int y,List<Coordinate> list){
            for(Coordinate c : list){
                if(x>= c.getX()-21 && x<=c.getX()+21 && y>= c.getY()-21 && y<=c.getY()+21)
                    return false;
            }
            return true;
        }
        public void mouseClicked(MouseEvent e) {
            clickX = e.getX();
            clickY = e.getY();

            if(checkInside(clickX,clickY,clicks) && canAdd) {
                clicks.add(new Coordinate(clickX, clickY));
                NodePopUp nodePopUp = new NodePopUp(clickX,clickY);

            }
            if(canAddLink){
                if(checkInside(clickX,clickY,clicks) == false) {
                    Coordinate c = getOval(clickX, clickY);
                    linkedList.addFirst(c);
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
