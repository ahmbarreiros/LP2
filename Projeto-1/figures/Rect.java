package figures;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Rect extends Figure {

    
    private int fillRGBIndex = 0; // Variável que itera sobre a lista de cores para pintar o fundo
    private boolean paintBG = false; // Variável que indica se o fundo deve ser transparente ou não


    public Rect (int x, int y, int w, int h, int contornoRGBIndex, float border, String drag) {
        super(x,y, w,h, contornoRGBIndex, border, drag);
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


    public void paint (Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.colors.get(contornoRGBIndex)); // Insere cor de contorno
        g2d.setStroke(new BasicStroke(this.border)); // Insere largura do contorno
        g2d.drawRect(this.x,this.y, this.w,this.h);
        // Se o fundo não for transparente, preenche o mesmo
        if(this.paintBG) {
            g2d.setColor(this.colors.get(fillRGBIndex));
            g2d.fillRect(this.x+(int)(Math.ceil(this.border/2)), this.y+(int)(Math.ceil(this.border/2)), this.w-(int)this.border, this.h-(int)this.border);
        }
        g2d.setStroke(new BasicStroke(2.0f));
        if(focused) {
            g2d.setColor(new Color(255,0,0));
            g2d.drawRect((this.x-(int)this.border)-3, (this.y-(int)this.border)-3, (this.w+(int)(this.border*2))+6, (this.h+(int)(this.border*2))+6);
        }

        
        g2d.setColor(Color.BLACK);
    }
}
