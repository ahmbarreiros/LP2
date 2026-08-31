package figures;

import java.awt.*;

public class Rect extends Figure {
    private int fillRGBIndex = 0;
    private boolean paintBG = false;

    public Rect (int x, int y, int w, int h, int contornoRGBIndex) {
        super(x,y, w,h, contornoRGBIndex);
    }

    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void changeFillD() {
        this.paintBG = true;
        this.fillRGBIndex = (fillRGBIndex+1) % colors.size();
    }
    public void changeFillU() {
        this.paintBG = true;
        if(this.fillRGBIndex>0) {
            this.fillRGBIndex = (this.fillRGBIndex-1) % colors.size();
        }else{
            this.fillRGBIndex = colors.size()-1;
        }
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.colors.get(contornoRGBIndex));
        g2d.drawRect(this.x,this.y, this.w,this.h);
        if(this.paintBG) {
            g2d.setColor(this.colors.get(fillRGBIndex));
            g2d.fillRect(this.x+1, this.y+1, this.w-1, this.h-1);
        }
        g2d.setColor(Color.BLACK);
    }
}
