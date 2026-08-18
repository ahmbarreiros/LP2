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
    private boolean isBeingErased = false;


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
                    focus = null;
                    for (Figure fig: figs) {
                        if ((fig.x <= evt.getX() && (fig.x + fig.w) >= evt.getX()) && (fig.y <= evt.getY() && (fig.y + fig.h) >= evt.getY())) {
                            focus = fig;
                            mousePosXPressed = evt.getX();
                            mousePosYPressed = evt.getY();
                            repaint();
                        }
                    }
                }
            }
        );

        this.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent evt) {
                    mousePosX = evt.getX();
                    mousePosY = evt.getY();

                    if (focus != null) {
			if ((evt.getX() >= focus.x-3 && evt.getX() <= focus.x+6 && evt.getY() >= focus.y-3 && evt.getY() <= focus.y+6) ||
			    (evt.getX() == focus.x-3 && evt.getY() == focus.y+6) ||
			    (evt.getX() == focus.x+6 && evt.getY() == focus.y-3) ||
			    (evt.getX() == focus.x+6 && evt.getY() == focus.y+6)) {
			    	focus.transform(mousePosX - mousePosXPressed, mousePosY - mousePosYPressed);
			    }
			else {
 				focus.drag(mousePosX - mousePosXPressed, mousePosY - mousePosYPressed);
			}
                        mousePosXPressed = mousePosX;
                        mousePosYPressed = mousePosY;
                        repaint();
                    }

                }

                public void mouseMoved(MouseEvent evt) {
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
                        figs.add(new Rect(mousePosX, mousePosY, 50, 50));
                        repaint();  // outer.repaint()
                    }
                    if (evt.getKeyChar() == 'e') {
                        figs.add(new Ellipse(mousePosX, mousePosY, 80, 50));
                        repaint();  // outer.repaint()
                    }
                    if (evt.getKeyChar() == 'a') {
                        figs.add(new Arc(mousePosX, mousePosY, 50, 50));
                        repaint();  // outer.repaint()
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                        if (focus != null) {
				isBeingErased = true;
                        	repaint();  // outer.repaint()
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

	    if (isBeingErased) {
	    	g2d.clearRect(focus.x, focus.y, focus.w, focus.h);
		isBeingErased = false;
		figs.remove(focus);
		focus = null;
		repaint();
	    }
        }

        for (Figure fig: this.figs) {
            fig.paint(g);
        }
    }
}
