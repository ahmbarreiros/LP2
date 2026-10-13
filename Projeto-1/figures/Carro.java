package figures;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class Carro extends Figure {
	
    // Cria as partes do carro
	private Rect chassi;

	private Ellipse roda1, roda2;


    // Construtor da classe carro com posição X e Y, tamanho W e H, cor do contorno inicial, largura do contorno inicial e marcador de "ação" (mover, transformar, etc.)
	public Carro(int x, int y, int w, int h) {
		super(x, y, w, h);
		this.chassi = new Rect(this.x, this.y, this.w, this.h);
		this.roda1 = new Ellipse(this.x, this.y+this.h/2, this.w/2, this.h/2);
		this.roda2 = new Ellipse(this.x, this.y+this.h/2, this.w/2, this.h/2);
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

    public int getCursor(String section) {
        return this.chassi.getCursor(section);
    }

    // Métodos de transformação da figura
    public void transformNW(int dw, int dh) {
        this.chassi.transformNW(dw, dh);
       	this.roda1.x = this.chassi.x;
       	this.roda1.y = this.chassi.y+this.chassi.h;
       	this.roda2.x = this.chassi.x+this.chassi.w-(this.roda2.w);
       	this.roda2.y = this.chassi.y+this.chassi.h;
    }
    public void transformNE(int dw, int dh) {
        this.chassi.transformNE(dw, dh);
       	this.roda1.x = this.chassi.x;
       	this.roda1.y = this.chassi.y+this.chassi.h;
       	this.roda2.x = this.chassi.x+this.chassi.w-(this.roda2.w);
       	this.roda2.y = this.chassi.y+this.chassi.h;
    }
    public void transformSW(int dw, int dh) {
        this.chassi.transformSW(dw, dh);
       	this.roda1.x = this.chassi.x;
       	this.roda1.y = this.chassi.y+this.chassi.h;
       	this.roda2.x = this.chassi.x+this.chassi.w-(this.roda2.w);
       	this.roda2.y = this.chassi.y+this.chassi.h;
    }
    public void transformSE(int dw, int dh) {
        this.chassi.transformSE(dw, dh);
       	this.roda1.x = this.chassi.x;
       	this.roda1.y = this.chassi.y+this.chassi.h;
       	this.roda2.x = this.chassi.x+this.chassi.w-(this.roda2.w);
       	this.roda2.y = this.chassi.y+this.chassi.h;
    }
    public void transformN(int dh) {
        this.chassi.transformN(dh);
       	this.roda1.x = this.chassi.x;
       	this.roda1.y = this.chassi.y+this.chassi.h;
       	this.roda2.x = this.chassi.x+this.chassi.w-(this.roda2.w);
       	this.roda2.y = this.chassi.y+this.chassi.h;
    }
    public void transformS(int dh) {
        this.chassi.transformS(dh);
       	this.roda1.x = this.chassi.x;
       	this.roda1.y = this.chassi.y+this.chassi.h;
       	this.roda2.x = this.chassi.x+this.chassi.w-(this.roda2.w);
       	this.roda2.y = this.chassi.y+this.chassi.h;
    }
    public void transformW(int dw) {
        this.chassi.transformW(dw);
       	this.roda1.x = this.chassi.x;
       	this.roda2.x = this.chassi.x+this.chassi.w-(this.roda2.w);
    }
    public void transformE(int dw) {
        this.chassi.transformE(dw);
       	this.roda1.x = this.chassi.x;
       	this.roda2.x = this.chassi.x+this.chassi.w-(this.roda2.w);
    }

    // Métodos para alterar cor de contorno
    public void changeBorderR() {
        this.chassi.changeBorderR();
       	this.roda1.x = this.chassi.x;
       	this.roda2.x = this.chassi.x+this.chassi.w-(this.roda2.w);
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

    public String mouseSection(int mouseX, int mouseY) {
        return this.chassi.mouseSection(mouseX, mouseY);
    }

    public boolean clicked(int x, int y) {
        return this.chassi.clicked(x, y);
    }

    // Pinta o carro, incluindo chassi e rodas
	public void paint(Graphics g, boolean focused) {
		this.chassi.paint(g, focused);
		this.roda1.paint(g, focused);
		this.roda2.paint(g, focused);
	}
}
