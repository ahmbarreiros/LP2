package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class Ellipse extends Figure {
    private int x, y;
    private int w, h;
    private int contornoR, contornoG, contornoB;

    public Ellipse (int x, int y, int w, int h, int contornoR, int contornoG, int contornoB) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
	    this.contornoR = contornoR;
	    this.contornoG = contornoG;
	    this.contornoB = contornoB;
    }

    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
	    g2d.setColor(new Color(this.contornoR, this.contornoG, this.contornoB));
	    g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
    }
}
