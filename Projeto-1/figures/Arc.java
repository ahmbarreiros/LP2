package figures;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Arc2D.Double;


public class Arc extends Figure{
    private int start = 0, extent = 180, type = 0, fillRGBIndex = 0;
    private boolean paintBG = false;

	public Arc(int x, int y, int w, int h, int contornoRGBIndex) {
		super(x, y, w, h, contornoRGBIndex);
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

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.colors.get(contornoRGBIndex));
		g2d.draw(new Arc2D.Double(this.x, this.y, this.w, this.h, this.start, this.extent, this.type));
        if(this.paintBG) {
            g2d.setColor(this.colors.get(fillRGBIndex));
            g2d.fillArc(this.x, this.y, this.w, this.h, this.start, this.extent);
        }
                g2d.setColor(Color.BLACK);
	}
}
