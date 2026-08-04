import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PaintApp {
    public static void main (String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}

class PaintFrame extends JFrame {
    Rect r1;
    Rect r2;
    Rect r3;

    PaintFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Painting Figures");
        this.setSize(350, 350);
        this.r1 = new Rect(50,50, 100,30, 0, 0, 255, 255, 0, 0);
	this.r2 = new Rect(70,100, 200,120, 255, 255, 0, 180, 180, 180);
	this.r3 = new Rect(110, 250, 100,90, 120, 30, 25, 205, 10, 170);
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r1.paint(g);
	this.r2.paint(g);
	this.r3.paint(g);
    }
}

class Rect {
    int x, y;
    int w, h;
    int contornoR, contornoG, contornoB;
    int fundoR, fundoG, fundoB;


    Rect (int x, int y, int w, int h, int contornoR, int contornoG, int contornoB, int fundoR, int fundoG, int fundoB) {
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
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
	g2d.setColor(new Color(this.contornoR, this.contornoG, this.contornoB));
        g2d.drawRect(this.x,this.y, this.w,this.h);
	g2d.setColor(new Color(this.fundoR, this.fundoG, this.fundoB));
	g2d.fillRect(this.x, this.y, this.w, this.h);
    }
}
