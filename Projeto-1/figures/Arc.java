package figures;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Arc2D.Double;


public class Arc extends Figure{
    private int start = 0, extent = 180, type = 0, fillRGBIndex = 0;
    private boolean paintBG = false;

	public Arc(int x, int y, int w, int h, int contornoRGBIndex, float border, String drag) {
		super(x, y, w, h, contornoRGBIndex, border, drag);
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
        if(this.paintBG) {
            g2d.setColor(this.colors.get(fillRGBIndex));
            g2d.fillArc(this.x+(int)(Math.ceil(this.border/2)), this.y+(int)(Math.ceil(this.border/2)), this.w-(int)this.border, this.h, this.start, this.extent);
        }
        g2d.setColor(this.colors.get(contornoRGBIndex));
        g2d.setStroke(new BasicStroke(this.border));
		g2d.draw(new Arc2D.Double(this.x, this.y, this.w, this.h, this.start, this.extent, this.type));
        g2d.setStroke(new BasicStroke(2.0f));
        if(focused) {
            g2d.setColor(new Color(255,0,0));
            g2d.drawRect(this.x-(int)this.border-3, this.y-(int)this.border-3, this.w+(int)(this.border*2)+6, this.h+(int)(this.border*2)+6);
        }
        g2d.setColor(Color.BLACK);
	}
}
