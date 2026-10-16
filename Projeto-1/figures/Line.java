package figures;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.geom.Line2D;

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
         * Verifica se o mouse, na posição x, y,  foi pressionado dentro dos limites x, x+w, y, y+h da figura.
         * para impresições do usuário ao clicar.
         *
         * int x: posição x do mouse.
         * int y: posição y do mouse.
         * return Boolean: Retorna "true" caso a posição do mouse esteja dentro dos limites da figura, e "false" caso contrário.
         */
         return (new Rectangle2D.Double(this.x, this.y, this.w, this.h)).contains(x, y);
    }

    public void paint(Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.getArrayList().get(contornoRGBIndex));
        g2d.setStroke(new BasicStroke(this.border));
        g2d.draw(new Line2D.Double(this.x, this.y, this.x+this.w, this.y+this.h));
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
