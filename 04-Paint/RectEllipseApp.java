import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

class RectEllipseApp {
    public static void main (String[] args) {
        RectEllipseFrame frame = new RectEllipseFrame();
        frame.setVisible(true);
    }
}

class RectEllipseFrame extends JFrame {
    Rect r1;
    Ellipse e1, e2, e3;

    RectEllipseFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Rect + Ellipse");
        this.setSize(350, 350);
        this.r1 = new Rect(50,50, 100,30);
        this.e1 = new Ellipse(50,100, 100,30, 0, 0, 255, 0, 255, 0);
        this.e2 = new Ellipse(180,140, 120,80, 255, 0, 255, 255, 255, 0);
	this.e3 = new Ellipse(310,250, 22, 80, 0, 255, 255, 0, 0, 255);
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r1.paint(g);
        this.e1.paint(g);
	this.e2.paint(g);
	this.e3.paint(g);
    }
}

class Rect {
    int x, y;
    int w, h;

    Rect (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g) {
        g.drawRect(this.x,this.y, this.w,this.h);
    }
}

class Ellipse {
    int x, y;
    int w, h;
    int contornoR, contornoG, contornoB;
    int fundoR, fundoG, fundoB;

    Ellipse (int x, int y, int w, int h, int contornoR, int contornoG, int contornoB, int fundoR, int fundoG, int fundoB) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
	this.contornoR = contornoR;
	this.contornoG = contornoG;
	this.contornoB = contornoB;
	this.fundoR = fundoR;
	this.fundoG = fundoG;
	this.fundoB = fundoB;
    }

    void print () {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
	g2d.setColor(new Color(this.contornoR, this.contornoG, this.contornoB));
	g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
	g2d.setColor(new Color(this.fundoR, this.fundoG, this.fundoB));
	g2d.fill(new Ellipse2D.Double(this.x, this.y, this.w, this.h));
    }
}
