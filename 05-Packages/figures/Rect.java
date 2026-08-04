package figures;

import java.awt.*;

public class Rect {
    private int x, y;
    private int w, h;
    private int contornoR, contornoG, contornoB;
    private int fundoR, fundoG, fundoB;

    public Rect (int x, int y, int w, int h, int contornoR, int contornoG, int contornoB, int fundoR, int fundoG, int fundoB) {
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


    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
	    g2d.setColor(new Color(this.contornoR, this.contornoG, this.contornoB));
        g2d.drawRect(this.x,this.y, this.w,this.h);
	    g2d.setColor(new Color(this.fundoR, this.fundoG, this.fundoB));
	    g2d.fillRect(this.x, this.y, this.w, this.h);
    }
}
