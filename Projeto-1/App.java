import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Arc2D.Double;
import java.util.ArrayList;
import java.util.Arrays;
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
    private String drag = "";

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
                                      drag = "";
                                      if(focus != null && ((focus.x-6 <= evt.getX() && (focus.x + focus.w + 6) >= evt.getX()) && (focus.y-6 <= evt.getY() && (focus.y + focus.h + 6) >= evt.getY()))) {
                                          if(evt.getX() >= focus.x-6 && evt.getX() <= focus.x+6 && evt.getY() >= focus.y-6 && evt.getY() <= focus.y+6) {
                                              drag = "NW";
                                          } else if(evt.getX() >= (focus.x+focus.w)-6 && evt.getX() <= (focus.x+focus.w)+6 && evt.getY() >= focus.y-6 && evt.getY() <= focus.y+6){
                                              drag = "NE";
                                          } else if(evt.getX() >= focus.x-6 && evt.getX() <= focus.x+6 && evt.getY() >= (focus.y+focus.h)-6 && evt.getY() <= (focus.y+focus.h)+6){
                                              drag = "SW";
                                          } else if(evt.getX() >= (focus.x+focus.w)-6 && evt.getX() <= (focus.x+focus.w)+6 && evt.getY() >= (focus.y+focus.h)-6 && evt.getY() <= (focus.y+focus.h)+6){
                                              drag = "SE";
                                          } else if(evt.getY() >= focus.y-6 && evt.getY() <= focus.y+6){
                                              drag = "N";
                                          } else if(evt.getY() >= (focus.y+focus.h-6) && evt.getY() <= (focus.y+focus.h+6)){
                                              drag = "S";
                                          } else if(evt.getX() >= focus.x-6 && evt.getX() <= focus.x+6){
                                              drag = "W";
                                          } else if(evt.getX() >= (focus.x+focus.w-6) && evt.getX() <= (focus.x+focus.w+6)){
                                              drag = "E";
                                          } else if(evt.getX() >= focus.x+7 && evt.getX() <= (focus.x + focus.w - 7) && evt.getY() >= focus.y+7 && evt.getY() <= (focus.y + focus.h - 7)){
                                              drag = "D";
                                          }
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
                                  public void mouseReleased(MouseEvent evt) {
                                      if (focus != null && drag != "") {
                                          switch(drag) {
                                          case "NW":
                                              setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
                                              break;
                                          case "NE":
                                              setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
                                              break;
                                          case "SW":
                                              setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
                                              break;
                                          case "SE":
                                              setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                                              break;
                                          case "N":
                                              setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                                              break;
                                          case "S":
                                              setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                                              break;
                                          case "W":
                                              setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
                                              break;
                                          case "E":
                                              setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                                              break;
                                          default:
                                              setCursor(Cursor.getDefaultCursor());
                                              break;
                                          }
                                          repaint();
                                      }
                                  }
                              }
                              );

        this.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent evt) {
                    mousePosX = evt.getX();
                    mousePosY = evt.getY();
                    if (focus != null && drag != "") {
                        switch(drag) {
                        case "NW":
                            focus.transformNW(mousePosX - mousePosXPressed, mousePosY - mousePosYPressed);
                            break;
                        case "NE":
                            focus.transformNE(mousePosX - mousePosXPressed, mousePosY - mousePosYPressed);
                            break;
                        case "SW":
                            focus.transformSW(mousePosX - mousePosXPressed, mousePosY - mousePosYPressed);
                            break;
                        case "SE":
                            focus.transformSE(mousePosX - mousePosXPressed, mousePosY - mousePosYPressed);
                            break;
                        case "N":
                            focus.transformN(mousePosY - mousePosYPressed);
                            break;
                        case "S":
                            focus.transformS(mousePosY - mousePosYPressed);
                            break;
                        case "W":
                            focus.transformW(mousePosX - mousePosXPressed);
                            break;
                        case "E":
                            focus.transformE(mousePosX - mousePosXPressed);
                            break;
                        case "D":
                            focus.drag(mousePosX - mousePosXPressed, mousePosY - mousePosYPressed);
                            break;
                        default:
                            break;
                        }
                    }
                    mousePosXPressed = mousePosX;
                    mousePosYPressed = mousePosY;
                    repaint();
                }

                

                public void mouseMoved(MouseEvent evt) {
                    if (focus != null) {
                        if(evt.getX() >= focus.x-6 && evt.getX() <= focus.x+6 && evt.getY() >= focus.y-6 && evt.getY() <= focus.y+6) {
                            setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
                        } else if(evt.getX() >= (focus.x+focus.w)-6 && evt.getX() <= (focus.x+focus.w)+6 && evt.getY() >= focus.y-6 && evt.getY() <= focus.y+6){
                            setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
                        } else if(evt.getX() >= focus.x-6 && evt.getX() <= focus.x+6 && evt.getY() >= (focus.y+focus.h)-6 && evt.getY() <= (focus.y+focus.h)+6){
                            setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
                        } else if(evt.getX() >= (focus.x+focus.w)-6 && evt.getX() <= (focus.x+focus.w)+6 && evt.getY() >= (focus.y+focus.h)-6 && evt.getY() <= (focus.y+focus.h)+6){
                            setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                        } else if(evt.getY() >= focus.y-6 && evt.getY() <= focus.y+6){
                            setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                        } else if(evt.getY() >= (focus.y+focus.h)-6 && evt.getY() <= (focus.y+focus.h)+6){
                            setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                        } else if(evt.getX() >= focus.x-6 && evt.getX() <= focus.x+6){
                            setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
                        } else if(evt.getX() >= (focus.x+focus.w)-6 && evt.getX() <= (focus.x+focus.w)+6){
                            setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
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
                        figs.add(new Rect(mousePosX-25, mousePosY-25, 50, 50, 0));
                        repaint();  // outer.repaint()
                    }
                    if (evt.getKeyChar() == 'e') {
                        figs.add(new Ellipse(mousePosX-40, mousePosY-25, 80, 50, 0));
                        repaint();  // outer.repaint()
                    }
                    if (evt.getKeyChar() == 'a') {
                        figs.add(new Arc(mousePosX-25, mousePosY-25, 50, 50, 0));
                        repaint();  // outer.repaint()
                    }
                    if (evt.getKeyChar() == 'l') {
                        figs.add(new Line(mousePosX-25, mousePosY-25, 50, 50, 0));
                        repaint();  // outer.repaint()
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                        if (focus != null) {
                            figs.remove(focus);
                            focus = null;
                        	repaint();
                        }
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                        if (focus != null) {
                            focus.changeBorderR();
                            repaint();
                        }
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                        if (focus != null) {
                            focus.changeBorderL();
                            repaint();
                        }
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_UP) {
                        if (focus != null) {
                            focus.changeFillU();
                            repaint();
                        }
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                        if (focus != null) {
                            focus.changeFillD();
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

        for (Figure fig: this.figs) {
            fig.paint(g);
        }
        if (focus != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(255,0,0));
            g2d.drawRect(focus.x-3, focus.y-3, focus.w+6, focus.h+6);
            g2d.setColor(new Color(0,0,0));
	    }


    }
}
