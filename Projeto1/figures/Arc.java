package figures;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Arc2D.Double;


public class Arc extends Figure{
    private int start = 0, extent = 180, type = 0;
	public Arc(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(new Arc2D.Double(this.x, this.y, this.w, this.h, this.start, this.extent, this.type));
	}
}
