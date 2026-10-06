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
                                      focus = null;

                                      // Itera pela lista de figuras ao pressionar o mouse, verificando se alguma delas deve ser focada.
                                      for (Figure fig: figs) {
                                          if(fig.clicked(evt.getX(), evt.getY())) {
                                            fig.mousePressedSection = "";
                                            focus = fig;

                                            // Verifica a posição do mouse ao clicar na figura, para realizar alguma ação de transformar ou arrastar caso se aplique.
                                            focus.mousePressedSection = focus.mouseSection(evt.getX(), evt.getY());
                                          }
                                      }
                                      repaint();
                                  }
                                  public void mouseReleased(MouseEvent evt) {
                                      // Reavalia o marcador de ação de uma figura em foco ao parar de pressionar o botão do mouse.
                                      if (focus != null) {
                                          focus.mousePressedSection = "";
                                          setCursor(Cursor.getPredefinedCursor(focus.getCursor(focus.mouseSection(evt.getX(), evt.getY()))));
                                      }
                                      repaint();
                                  }
                              }
                             );

        this.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent evt) {
                    // Ao arrastar o mouse, avalia o marcador de ação de uma figura em foco e realiza uma operação caso haja uma ação em curso.
                    mousePosX = evt.getX();
                    mousePosY = evt.getY();
                    if(focus != null && focus.mousePressedSection != "") {
                        if(focus.mousePressedSection == "D") {
                            focus.drag(mousePosX - mousePosXPressed, mousePosY - mousePosYPressed);
                        } else if (focus.mousePressedSection == "NW"){
                            focus.transformNW(mousePosX - mousePosXPressed, mousePosY - mousePosYPressed);
                        } else if (focus.mousePressedSection == "NE"){
                            focus.transformNE(mousePosX - mousePosXPressed, mousePosY - mousePosYPressed);
                        } else if (focus.mousePressedSection == "SW"){
                            focus.transformSW(mousePosX - mousePosXPressed, mousePosY - mousePosYPressed);
                        } else if (focus.mousePressedSection == "SE"){
                            focus.transformSE(mousePosX - mousePosXPressed, mousePosY - mousePosYPressed);
                        } else if (focus.mousePressedSection == "N"){
                            focus.transformN(mousePosY - mousePosYPressed);
                        } else if (focus.mousePressedSection == "W"){
                            focus.transformW(mousePosX - mousePosXPressed);
                        } else if (focus.mousePressedSection == "E"){
                            focus.transformE(mousePosX - mousePosXPressed);
                        } else if (focus.mousePressedSection == "S"){
                            focus.transformS(mousePosY - mousePosYPressed);
                        }
                    }
                    mousePosXPressed = mousePosX;
                    mousePosYPressed = mousePosY;
                    repaint();
                }

                

                public void mouseMoved(MouseEvent evt) {
                    // Avalia o marcador de ação de uma figura em foco e altera o cursor caso a ação coincida com a posição do mouse.
                    if (focus != null) {
                        setCursor(Cursor.getPredefinedCursor(focus.getCursor(focus.mouseSection(evt.getX(), evt.getY()))));
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

                    switch(evt.getKeyCode()) {

                        // Cria uma figura "Retângulo", na posição central do mouse, com borda padrão 2 e marcador de ação vazio.
                        case (KeyEvent.VK_R):
                            Rect rect = new Rect(mousePosX-25, mousePosY-25, 50, 50);
                            figs.add(rect);
                            focus = rect;
                            repaint();  // outer.repaint()
                            break;

                        // Cria uma figura "Ellipse", na posição central do mouse, com borda padrão 2 e marcador de ação vazio.
                        case (KeyEvent.VK_E):
                            Ellipse ellipse = new Ellipse(mousePosX-40, mousePosY-25, 80, 50);
                            figs.add(ellipse);
                            focus = ellipse;
                            repaint();  // outer.repaint()
                            break;

                        // Cria uma figura "Arco", na posição central do mouse, com borda padrão 2 e marcador de ação vazio.
                        case (KeyEvent.VK_A):
                            Arc arc = new Arc(mousePosX-25, mousePosY-25, 50, 50);
                            figs.add(arc);
                            focus = arc;
                            repaint();  // outer.repaint()
                            break;

                        // Cria uma figura "Reta", na posição central do mouse, com borda padrão 2 e marcador de ação vazio.
                        case (KeyEvent.VK_L):
                            Line line = new Line(mousePosX-25, mousePosY-25, 50, 50);
                            figs.add(line);
                            focus = line;
                            repaint();  // outer.repaint()
                            break;

                        // Cria uma figura "Carro", na posição central do mouse, com borda padrão 2 e marcador de ação vazio.
                        case (KeyEvent.VK_1):
                            Carro carro = new Carro(mousePosX-25, mousePosY-25, 50, 50);
                            // Adiciona o carro da lista de figuras e deixa o carro em foco
                            figs.add(carro);
                            focus = carro;
                            repaint();  // outer.repaint()
                            break;

                        // Deleta a figura em foco.
                        case (KeyEvent.VK_DELETE):
                            if (focus != null && figs.size() > 1) {
                                int i = figs.indexOf(focus);
                                figs.remove(focus);
                                focus = focus = figs.get((i+1) % figs.size());
                            	repaint();
                            } else if (focus != null) {
                                figs.remove(focus);
                                focus = null;
                                repaint();
                            }
                            break;

                        // Altera para a próxima cor de contorno.
                        case (KeyEvent.VK_RIGHT):
                            if (focus != null) {
                                focus.changeBorderR();
                                repaint();
                            }
                            break;

                        // Altera para a cor de contorno anterior.
                        case (KeyEvent.VK_LEFT):
                            if (focus != null) {
                                focus.changeBorderL();
                                repaint();
                            }
                            break;

                        // Altera para a próxima cor de fundo.
                        case (KeyEvent.VK_UP):
                            if (focus != null) {
                                focus.changeFillU();
                                repaint();
                            }
                            break;

                        // Altera para a cor de fundo anterior.
                        case (KeyEvent.VK_DOWN):
                            if (focus != null) {
                                focus.changeFillD();
                                repaint();
                            }
                            break;

                        // Aumenta a largura do contorno.
                        case (KeyEvent.VK_EQUALS):
                            if (focus != null) {
                                focus.changeBorderU();
                                repaint();
                            }
                            break;

                        // Diminui a largura do contorno.
                        case (KeyEvent.VK_MINUS):
                            if (focus != null) {
                                focus.changeBorderD();
                                repaint();
                            }
                            break;

                        // Atalho para alterar foco da figura.
                        case (KeyEvent.VK_2):
                            // Só altera o foco caso haja alguma figura já em foco.
                            if (focus != null) {
                                // Guarda o indice da figura em foco na lista de figuras.
                                int i = figs.indexOf(focus);
                                // Passa o foco para a próxima figura da lista, e caso esteja no fim, volta para o início da lista.
                                focus = figs.get((i+1) % figs.size());
                                repaint();
                            }
                            break;
                        default:
                            break;
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

        // Para cara figura da lista, pinta ela com o seu próprio método.
        for (Figure fig: this.figs) {
            fig.paint(g, focus==fig);
        }
    }
}
