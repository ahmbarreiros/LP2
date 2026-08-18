package figures;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Arc2D.Double;


public class Arc extends Figure {
	private int start = 0;
	private int extent = 180;
	private int type = 0;
	public Arc(int x, int y, int w, int h, int contornoR, int contornoG, int contornoB) {
		super(x, y, w, h, contornoR, contornoG, contornoB);
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
 	    g2d.setColor(new Color(this.contornoR, this.contornoG, this.contornoB));
		g2d.draw(new Arc2D.Double(this.x, this.y, this.w, this.h, this.start, this.extent, this.type));
	}
}
