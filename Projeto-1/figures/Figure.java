package figures;

import java.awt.Graphics;

public abstract class Figure {
    public int x, y;
    public int w, h;

    public Figure (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void drag (int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void transformNW(int dw, int dh) {
	this.w += (-1*dw);
	this.h += (-1*dh);
	this.x += dw;
	this.y += dh;
    }  
    public void transformNE(int dw, int dh) {
	this.w += (dw);
	this.h += (-1*dh);
	//this.x += dw;
	this.y += dh;
    }
    public void transformSW(int dw, int dh) {
	this.w += (-1*dw);
	this.h += (dh);
	this.x += dw;
	//this.y += dh;
    }
    public void transformSE(int dw, int dh) {
	this.w += (dw);
	this.h += (dh);
	//this.x += dw;
	//this.y += dh;
    }

    public abstract void paint (Graphics g);
}
