package figures;
import java.awt.*;

public abstract class Figure {
	public int x, y;
	public int w, h;
	public int contornoR, contornoG, contornoB;

	public Figure(int x, int y, int w, int h, int contornoR, int contornoG, int contornoB) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.contornoR = contornoR;
		this.contornoG = contornoG;
		this.contornoB = contornoB;
	}
	
	public abstract void paint(Graphics g);
}
