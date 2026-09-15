import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Arc2D.Double;
import java.awt.geom.AffineTransform;
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
                                      
                                      
                                      // Ao pressionar o mouse, itera sobre a lista de figuras e avalia as operações disponíveis sobre ela caso o mouse esteja sobre a figura
                                      focus = null;
                                      for (Figure fig: figs) {
                                          fig.oper(evt.getX(), evt.getY());
                                          if (fig.drag != "") {
                                              focus = fig;
                                          }
                                      }
                                      repaint();
                                  }
                                  public void mouseReleased(MouseEvent evt) {
                                      // Reavalia o marcador de ação de uma figura em foco ao parar de pressionar o botão do mouse
                                      if (focus != null) {
                                          focus.oper(evt.getX(), evt.getY());
                                          switch(focus.drag) {
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
                                          case "D":
                                              setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
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
                    // Ao arrastar o mouse, avalia o marcador de ação de uma figura em foco e realiza uma operação caso haja uma ação em curso.
                    mousePosX = evt.getX();
                    mousePosY = evt.getY();
                    if (focus != null) {
                        switch(focus.drag) {
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
                    // Avalia o marcador de ação de uma figura em foco e altera o cursor caso a ação coincida com a posição do mouse
                    if (focus != null) {
                                          focus.oper(evt.getX(), evt.getY());
                                          switch(focus.drag) {
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
                                          case "D":
                                              setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                                              break;
                                          default:
                                              setCursor(Cursor.getDefaultCursor());
                                              break;
                                          }
                                      } else setCursor(Cursor.getDefaultCursor());
                mousePosX = evt.getX();
                mousePosY = evt.getY();
                repaint();

                } 
                
            }
        );


        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {

                    // Cria uma figura "Retângulo", na posição central do mouse, com borda padrão 2 e marcador de ação vazio
                    if (evt.getKeyChar() == 'r' || evt.getKeyChar() == 'R') {
                        Rect rect = new Rect(mousePosX-25, mousePosY-25, 50, 50, 0, 2.0f, "");
                        figs.add(rect);
                        focus = rect;
                        repaint();  // outer.repaint()
                    }

                    // Cria uma figura "Ellipse", na posição central do mouse, com borda padrão 2 e marcador de ação vazio
                    if (evt.getKeyChar() == 'e' || evt.getKeyChar() == 'E') {
                        Ellipse ellipse = new Ellipse(mousePosX-40, mousePosY-25, 80, 50, 0, 2.0f, "");
                        figs.add(ellipse);
                        focus = ellipse;
                        repaint();  // outer.repaint()
                    }

                    // Cria uma figura "Arco", na posição central do mouse, com borda padrão 2 e marcador de ação vazio
                    if (evt.getKeyChar() == 'a' || evt.getKeyChar() == 'A') {
                        Arc arc = new Arc(mousePosX-25, mousePosY-25, 50, 50, 0, 2.0f, "");
                        figs.add(arc);
                        focus = arc;
                        repaint();  // outer.repaint()
                    }

                    // Cria uma figura "Reta", na posição central do mouse, com borda padrão 2 e marcador de ação vazio
                    if (evt.getKeyChar() == 'l' || evt.getKeyChar() == 'L') {
                        Line line = new Line(mousePosX-25, mousePosY-25, 50, 50, 0, 2.0f, "");
                        figs.add(line);
                        focus = line;
                        repaint();  // outer.repaint()
                    }

                    // Cria uma figura "Carro", na posição central do mouse, com borda padrão 2 e marcador de ação vazio
		            if (evt.getKeyChar() == '1') {
                        Carro carro = new Carro(mousePosX-25, mousePosY-25, 50, 50, 0, 2.0f, "");
                        // Adiciona o carro da lista de figuras e deixa o carro em foco
                        figs.add(carro);
                        focus = carro;
                        repaint();  // outer.repaint()
                    }

                    // Deleta a figura em foco
                    if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                        if (focus != null) {
                            figs.remove(focus);
                            focus = null;
                        	repaint();
                        }
                    }

                    // Altera para a próxima cor de contorno
                    if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                        if (focus != null) {
                            focus.changeBorderR();
                            repaint();
                        }
                    }

                    // Altera para a cor de contorno anterior
                    if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                        if (focus != null) {
                            focus.changeBorderL();
                            repaint();
                        }
                    }

                    // Altera para a próxima cor de fundo
                    if (evt.getKeyCode() == KeyEvent.VK_UP) {
                        if (focus != null) {
                            focus.changeFillU();
                            repaint();
                        }
                    }

                    // Altera para a cor de fundo anterior
                    if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                        if (focus != null) {
                            focus.changeFillD();
                            repaint();
                        }
                    }

                    // Aumenta a largura do contorno
                    if (evt.getKeyChar() == '=' || evt.getKeyChar() == '+') {
                        if (focus != null) {
                            focus.changeBorderU();
                            repaint();
                        }
                    }

                    // Diminui a largura do contorno
                    if (evt.getKeyChar() == '-') {
                        if (focus != null) {
                            focus.changeBorderD();
                            repaint();
                        }
                    }
                    
                    // Atalho para alterar foco da figura
                    if (evt.getKeyChar() == '2') {
                        // Só altera o foco caso haja alguma figura já em foco
                        if (focus != null) {
                            // Guarda o indice da figura em foco na lista de figuras
                            int i = figs.indexOf(focus);
                            // Passa o foco para a próxima figura da lista, e caso esteja no fim, volta para o início da lista
                            focus = figs.get((i+1) % figs.size());
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
        Graphics2D g2d = (Graphics2D) g;

        // Para cara figura da lista, pinta ela com o seu próprio método
        for (Figure fig: this.figs) {
            fig.paint(g);
        }

        // Caso haja uma figura em foco, pinta o retângulo que a destaca, de acordo com o tipo de figura
        if (focus != null) {
            focus.paintFocus(g, 3, 3);
        }
        


    }
}
