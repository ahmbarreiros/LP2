import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Arc2D.Double;
import java.util.ArrayList;
import java.util.Random;
import figures.*;

class App {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    private Figure focus = null;

    private int mousePosXPressed = 0;
    private int mousePosYPressed = 0;
    private int mousePosX = 0;
    private int mousePosY = 0;

    ListFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        this.addMouseListener(
            new MouseAdapter() {
                public void mousePressed(MouseEvent evt) {
		    mousePosXPressed = evt.getX();
		    mousePosYPressed = evt.getY();
		    if(focus != null && ((focus.x <= evt.getX() && (focus.x + focus.w) >= evt.getX()) && (focus.y <= evt.getY() && (focus.y + focus.h) >= evt.getY()))) {
			   return;
		    }
		    focus = null;
                    for (Figure fig: figs) {

                        if ((fig.x <= evt.getX() && (fig.x + fig.w) >= evt.getX()) && (fig.y <= evt.getY() && (fig.y + fig.h) >= evt.getY())) {
			    focus = fig;
                        }
                    }
		    repaint();
                }
            }
        );

        this.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent evt) {
                    mousePosX = evt.getX();
                    mousePosY = evt.getY();
                    if (focus != null) {
			if(evt.getX() >= focus.x-6 && evt.getX() <= focus.x+12 && evt.getY() >= focus.y-6 && evt.getY() <= focus.y+12) {
				focus.transformNW(mousePosX - mousePosXPressed, mousePosY - mousePosYPressed);
			}
			if(evt.getX() >= (focus.x+focus.w)-6 && evt.getX() <= (focus.x+focus.w)+12 && evt.getY() >= focus.y-6 && evt.getY() <= focus.y+12){
				focus.transformNE(mousePosX - mousePosXPressed, mousePosY - mousePosYPressed);
			}
			if(evt.getX() >= focus.x-6 && evt.getX() <= focus.x+12 && evt.getY() >= (focus.y+focus.h)-6 && evt.getY() <= (focus.y+focus.h)+12){
					focus.transformSW(mousePosX - mousePosXPressed, mousePosY - mousePosYPressed);
			}
			if(evt.getX() >= (focus.x+focus.w)-6 && evt.getX() <= (focus.x+focus.w)+12 && evt.getY() >= (focus.y+focus.h)-6 && evt.getY() <= (focus.y+focus.h)+12){
				focus.transformSE(mousePosX - mousePosXPressed, mousePosY - mousePosYPressed);
			}
			}
			else {
				setCursor(Cursor.getDefaultCursor());
 				//focus.drag(mousePosX - mousePosXPressed, mousePosY - mousePosYPressed);
			}
			mousePosXPressed = mousePosX;
			mousePosYPressed = mousePosY;
			repaint();
                    }

                

                public void mouseMoved(MouseEvent evt) {
			if (focus != null) {
				if(evt.getX() >= focus.x-6 && evt.getX() <= focus.x+12 && evt.getY() >= focus.y-6 && evt.getY() <= focus.y+12) {
					setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
				}
				else if(evt.getX() >= (focus.x+focus.w)-6 && evt.getX() <= (focus.x+focus.w)+12 && evt.getY() >= focus.y-6 && evt.getY() <= focus.y+12){
					setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
				}
				else if(evt.getX() >= focus.x-6 && evt.getX() <= focus.x+12 && evt.getY() >= (focus.y+focus.h)-6 && evt.getY() <= (focus.y+focus.h)+12){
					setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
				}
				else if(evt.getX() >= (focus.x+focus.w)-6 && evt.getX() <= (focus.x+focus.w)+12 && evt.getY() >= (focus.y+focus.h)-6 && evt.getY() <= (focus.y+focus.h)+12){
					setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
			
				} else {
					setCursor(Cursor.getDefaultCursor());
				}
			} else {
				setCursor(Cursor.getDefaultCursor());
			}

                    mousePosX = evt.getX();
                    mousePosY = evt.getY();
                    repaint();
                }
            }
        );


        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    if (evt.getKeyChar() == 'r') {
                        figs.add(new Rect(mousePosX-25, mousePosY-25, 50, 50));
                        repaint();  // outer.repaint()
                    }
                    if (evt.getKeyChar() == 'e') {
                        figs.add(new Ellipse(mousePosX-40, mousePosY-25, 80, 50));
                        repaint();  // outer.repaint()
                    }
                    if (evt.getKeyChar() == 'a') {
                        figs.add(new Arc(mousePosX-25, mousePosY-25, 50, 50));
                        repaint();  // outer.repaint()
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                        if (focus != null) {
				figs.remove(focus);
				focus = null;
                        	repaint();
			}
                    }
                }
            }
        );

        this.setTitle("Editor Gráfico Vetorial");
        this.setSize(1280, 720);
    }

    public void paint (Graphics g) {
        super.paint(g);

        if (focus != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(255,0,0));
            g2d.drawRect(focus.x-3, focus.y-3, focus.w+6, focus.h+6);
            g2d.setColor(new Color(0,0,0));
	    }

        for (Figure fig: this.figs) {
            fig.paint(g);
        }
    }
}
