package figures;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class Carro extends Figure {
	
    // Cria as partes do carro
	Rect chassi;

	Ellipse roda1, roda2;


    // Construtor da classe carro com posição X e Y, tamanho W e H, cor do contorno inicial, largura do contorno inicial e marcador de "ação" (mover, transformar, etc.)
	public Carro(int x, int y, int w, int h, int contornoRGBIndex, float border, String drag) {
		super(x, y, w, h, contornoRGBIndex, border, drag);
		this.chassi = new Rect(this.x, this.y, this.w, this.h, this.contornoRGBIndex, this.border, "");
		this.roda1 = new Ellipse(this.x, (this.y+this.h-15), this.w/4, 30, this.contornoRGBIndex, this.border, "");
		this.roda2 = new Ellipse((this.x+(this.w)-(this.w/4)), (this.y+this.h-15), this.w/4, 30, this.contornoRGBIndex, this.border, "");
	}

    // Métodos para alterar cor de fundo
	public void changeFillU() {
		this.chassi.changeFillU();
		this.roda1.changeFillU();
		this.roda2.changeFillU();
	}

	public void changeFillD() {
		this.chassi.changeFillD();
		this.roda1.changeFillD();
		this.roda2.changeFillD();
	}

    // Método para mover a figura
    public void drag (int dx, int dy) {
        this.chassi.drag(dx, dy);
        this.roda1.drag(dx, dy);
        this.roda2.drag(dx, dy);
    }

    // Métodos de transformação da figura
    public void transformNW(int dw, int dh) {
        this.chassi.transformNW(dw, dh);
        this.roda1.transformNW(dw, dh);
        this.roda2.transformNW(dw, dh);
    }
    public void transformNE(int dw, int dh) {
        this.chassi.transformNE(dw, dh);
        this.roda1.transformNE(dw, dh);
        this.roda2.transformNE(dw, dh);
    }
    public void transformSW(int dw, int dh) {
        this.chassi.transformSW(dw, dh);
        this.roda1.transformSW(dw, dh);
        this.roda2.transformSW(dw, dh);
    }
    public void transformSE(int dw, int dh) {
        this.chassi.transformSE(dw, dh);
        this.roda1.transformSE(dw, dh);
        this.roda2.transformSE(dw, dh);
    }
    public void transformN(int dh) {
        this.chassi.transformN(dh);
        this.roda1.transformN(dh);
        this.roda2.transformN(dh);
    }
    public void transformS(int dh) {
        this.chassi.transformS(dh);
        this.roda1.transformS(dh);
        this.roda2.transformS(dh);
    }
    public void transformW(int dw) {
        this.chassi.transformW(dw);
        this.roda1.transformW(dw);
        this.roda2.transformW(dw);
    }
    public void transformE(int dw) {
        this.chassi.transformE(dw);
        this.roda1.transformE(dw);
        this.roda2.transformE(dw);
    }

    // Métodos para alterar cor de contorno
    public void changeBorderR() {
        this.chassi.changeBorderR();
        this.roda1.changeBorderR();
        this.roda2.changeBorderR();
    }
    public void changeBorderL() {
        this.chassi.changeBorderL();
        this.roda1.changeBorderL();
        this.roda2.changeBorderL();    
    }

    // Métodos para alterar largura do contorno
    public void changeBorderU() {
    
        this.chassi.changeBorderU();
        this.roda1.changeBorderU();
        this.roda2.changeBorderU();
    }
    public void changeBorderD() {
        this.chassi.changeBorderD();
        this.roda1.changeBorderD();
        this.roda2.changeBorderD();
    }

    // Guarda a operação que está sendo realizada na figura
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

   
    // Pinta o contorno do foco, comportando o chassi e as rodas
    public void paintFocus(Graphics g, int incW, int incH) {
        this.chassi.paintFocus(g, this.roda1.w/2, this.roda1.h/2);
    }

    // Pinta o carro, incluindo chassi e rodas
	public void paint(Graphics g) {
		this.chassi.paint(g);
		this.roda1.paint(g);
		this.roda2.paint(g);
	}
}
