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

    public void paint(Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.colors.get(contornoRGBIndex));
        g2d.setStroke(new BasicStroke(this.border));
        g2d.drawLine(x, y, x+w, y+h);
        g2d.setStroke(new BasicStroke(2.0f));
        if(focused) {
            g2d.setColor(new Color(255,0,0));
            g2d.drawRect(this.x-(int)this.border-3, this.y-(int)this.border-3, this.w+(int)(this.border*2)+6, this.h+(int)(this.border*2)+6);
        }
        g2d.setColor(Color.BLACK);
    }
}
