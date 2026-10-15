package figures;

import java.awt.*;

public class Line extends Figure {

    public Line(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    public void changeFillU() {}
    public void changeFillD() {}

    public void changeBorderU() {
    
        if (this.border <= 20.0f) {
            this.border += 2.0f;
         }    
    }
    public void changeBorderD() {
        if (this.border > 2.0f) {
            this.border -= 2.0f;
        }
    }

    public boolean clicked(int x, int y) {
    /**
         * Método para determinar se a figura foi clicada.
         *
         * Verifica se o mouse, na posição x, y,  foi pressionado dentro dos limites x, x+w, y, y+h da figura, incluindo seu tamanho de borda e uma "folga"
         * para impresições do usuário ao clicar.
         *
         * int x: posição x do mouse.
         * int y: posição y do mouse.
         * return Boolean: Retorna "true" caso a posição do mouse esteja dentro dos limites da figura, e "false" caso contrário.
         */
        return ((x >= this.x-(int)this.border
                 && x <= (this.x+this.w)+(int)this.border
                 && y >= this.y-(int)this.border
                 && y <= (this.y+this.h)+(int)this.border));
    }

    public void paint(Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.getArrayList().get(contornoRGBIndex));
        g2d.setStroke(new BasicStroke(this.border));
        g2d.drawLine(x, y, x+w, y+h);
        g2d.setStroke(new BasicStroke(2.0f));
        if(focused) {
            g2d.setColor(new Color(255,0,0));
            g2d.drawRect(this.x-(int)this.border-3, this.y-(int)this.border-3, this.w+(int)(this.border*2)+6, this.h+(int)(this.border*2)+6);
        }
        g2d.setColor(Color.BLACK);
    }

    public String toSVG() {
        return String.format("<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" stroke=\"%s\" stroke-width=\"%d\" />", this.x, this.y, this.x+this.w, this.y+this.h, this.colorToHex(this.getArrayList().get(this.contornoRGBIndex)), (int)this.border);
    }
}
