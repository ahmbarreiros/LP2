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
        if(this.w + (-1*dw) <= 30) {
            this.w = 30;
        } else {
            this.w += (-1*dw);
            this.x += dw;
        }
        if(this.h + (-1*dh) <= 30) {
            this.h = 30;
        } else {
            this.h += (-1*dh);
            this.y += dh;
        }
    }
    public void transformNE(int dw, int dh) {
        if(this.w + dw <= 30) {
            this.w = 30;
        } else {
            this.w += (dw);
        }
        if(this.h + (-1*dh) <= 30) {
            this.h = 30;
        } else {
            this.h += (-1*dh);
            this.y += dh;
        }
    }
    public void transformSW(int dw, int dh) {
        if(this.w + (-1*dw) <= 30) {
            this.w = 30;
        } else {
            this.w += (-1*dw);
            this.x += dw;
        }
        if(this.h + dh <= 30) {
            this.h = 30;
        } else {
            this.h += (dh);
        }
    }
    public void transformSE(int dw, int dh) {
        if(this.w + dw <= 30) {
            this.w = 30;
        } else {
            this.w += (dw);
        }
        if(this.h + dh <= 30) {
            this.h = 30;
        } else {
            this.h += (dh);
        }
    }
    public void transformN(int dh) {
        if(this.h + (-1*dh) <= 30) {
            this.h = 30;
        } else {
            this.h += (-1*dh);
            this.y += dh;
        }
    }
    public void transformS(int dh) {
        if(this.h + dh <= 30) {
            this.h = 30;
        } else {
            this.h += (dh);
        }
    }
    public void transformW(int dw) {
        if(this.w + (-1*dw) <= 30) {
            this.w = 30;
        } else {
            this.w += (-1*dw);
            this.x += dw;
        }
    }
    public void transformE(int dw) {
        if(this.w + dw <= 30) {
            this.w = 30;
        } else {
            this.w += (dw);
        }
    }

    public abstract void paint (Graphics g);
}
