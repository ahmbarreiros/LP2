package figures;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Arc2D.Double;


public class Arc {
	private int x, y;
	private int w, h;
	private int start;
	private int extent;
	private int type;
    private int contornoR, contornoG, contornoB;

	public Arc(int x, int y, int w, int h, int start, int extent, int type, int contornoR, int contornoG, int contornoB) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.start = start;
		this.extent = extent;
		this.type = type;
	    this.contornoR = contornoR;
	    this.contornoG = contornoG;
	    this.contornoB = contornoB;
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
 	    g2d.setColor(new Color(this.contornoR, this.contornoG, this.contornoB));
		g2d.draw(new Arc2D.Double(this.x, this.y, this.w, this.h, this.start, this.extent, this.type));
	}
}
