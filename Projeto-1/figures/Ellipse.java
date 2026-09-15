package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class Ellipse extends Figure {
    private int fillRGBIndex = 0;
    private boolean paintBG = false;

    public Ellipse (int x, int y, int w, int h, int contornoRGBIndex, float border, String drag) {
        super(x,y, w,h, contornoRGBIndex, border, drag);
    }

    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
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

    public void oper(int mouseX, int mouseY) {
        this.drag = "";
        if((mouseX >= this.x-6-(int)this.border 
            && mouseX <= (this.x+this.w+6)+(int)this.border 
            && mouseY >= this.y-6-(int)this.border 
            && mouseY <= (this.y+this.h+6)+(int)this.border)) {
                                          if(mouseX >= this.x-6-(int)this.border 
                                            && mouseX <= this.x+6+(int)this.border 
                                            && mouseY >= this.y-6-(int)this.border 
                                            && mouseY <= this.y+6+(int)this.border) {
                                              this.drag = "NW";
                                          } else if(mouseX >= (this.x+this.w)-6-(int)this.border && mouseX <= (this.x+this.w)+6+(int)this.border && mouseX <= (this.x+this.w+(int)(this.border))+6 && mouseY >= this.y-6-(int)this.border && mouseY <= this.y+6+(int)this.border){
                                              this.drag = "NE";
                                          } else if(mouseX >= this.x-6-(int)this.border && mouseX <= this.x+6+(int)this.border && mouseY >= (this.y+this.h)-6-(int)this.border && mouseY <= (this.y+this.h)+6+(int)this.border){
                                              this.drag = "SW";
                                          } else if(mouseX >= (this.x+this.w)-6-(int)this.border && mouseX <= (this.x+this.w)+6+(int)this.border && mouseY >= (this.y+this.h)-6-(int)this.border && mouseY <= (this.y+this.h)+6+(int)this.border){
                                              this.drag = "SE";
                                          } else if(mouseX >= this.x-6-(int)this.border && mouseX <= (this.x+this.w+6)+(int)this.border && mouseY >= this.y-6-(int)this.border && mouseY <= this.y+6+(int)this.border){
                                              this.drag = "N";
                                          } else if(mouseX >= this.x-6-(int)this.border && mouseX <= (this.x+this.w+6)+(int)this.border && mouseY >= (this.y+this.h-6)-(int)this.border && mouseY <= (this.y+this.h+6)+(int)this.border){
                                              this.drag = "S";
                                          } else if(mouseX >= this.x-6-(int)this.border && mouseX <= this.x+6+(int)this.border && mouseY >= this.y-6-(int)this.border && mouseY <= (this.y+this.h+6)+(int)this.border) {
                                              this.drag = "W";
                                          } else if(mouseX >= (this.x+this.w-6)-(int)this.border && mouseX <= (this.x+this.w+6)+(int)this.border && mouseY >= this.y-6-(int)this.border && mouseY <= (this.y+this.h+6)+(int)this.border) {
                                              this.drag = "E";
                                          } else if(mouseX >= this.x+7 && mouseX <= (this.x + this.w - 7) && mouseY >= this.y+7 && mouseY <= (this.y + this.h - 7)){
                                              this.drag = "D";
                                          }
        } else this.drag = "";
    }

    public void paintFocus(Graphics g, int incW, int incH) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(255,0,0));
        g2d.drawRect(this.x-(int)this.border-incW, this.y-(int)this.border-incH, this.w+(int)(this.border*2)+2*incW, this.h+(int)(this.border*2)+2*incH);
        g2d.setColor(new Color(0,0,0));
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.colors.get(contornoRGBIndex));
        g2d.setStroke(new BasicStroke(this.border));
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
        if(this.paintBG) {
            g2d.setColor(this.colors.get(this.fillRGBIndex));
            g2d.fillOval(this.x+(int)(Math.ceil(this.border/2)), this.y+(int)(Math.ceil(this.border/2)), this.w-(int)this.border, this.h-(int)this.border);
        }
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.setColor(Color.BLACK);

    }
}
