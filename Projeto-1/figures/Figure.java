package figures;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Figure {
    public int x, y;
    public int w, h;
    public int contornoRGBIndex;
    public Color contornoRGB = Color.BLACK;
    public static ArrayList<Color> colors = new ArrayList<Color>(Arrays.asList(Color.BLACK, Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.ORANGE, Color.PINK, Color.WHITE));


    public Figure (int x, int y, int w, int h, int contornoRGBIndex) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.contornoRGBIndex = contornoRGBIndex;
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

    public void changeBorderR() {
        this.contornoRGBIndex = (contornoRGBIndex+1) % colors.size();
    }
    public void changeBorderL() {
        if(contornoRGBIndex>0) {
             this.contornoRGBIndex = (contornoRGBIndex-1) % colors.size();
        }else{
                this.contornoRGBIndex = colors.size()-1;
        }
    }
    public abstract void changeFillD();
    public abstract void changeFillU();

    public abstract void paint (Graphics g);
}
