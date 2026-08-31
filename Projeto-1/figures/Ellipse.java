package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class Ellipse extends Figure {
    private int fillRGBIndex = -1;
    public Ellipse (int x, int y, int w, int h, int contornoRGBIndex) {
        super(x,y, w,h, contornoRGBIndex);
    }

    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void changeFillD() {
        if(fillRGBIndex == -1) {fillRGBIndex = 0;}
        else { this.fillRGBIndex = (fillRGBIndex+1) % colors.size();
}
           }
    public void changeFillU() {
        if(this.fillRGBIndex>0) {
            this.fillRGBIndex = (this.fillRGBIndex-1) % colors.size();
        }else{
            this.fillRGBIndex = colors.size()-1;
        }
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.colors.get(contornoRGBIndex));
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
        g2d.setColor(this.colors.get(this.fillRGBIndex));
        g2d.fillOval(this.x, this.y, this.w, this.h);
        g2d.setColor(Color.BLACK);

    }
}
