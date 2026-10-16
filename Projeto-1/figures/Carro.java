package figures;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class Carro extends Figure {
	
    // Cria as partes do carro
	public Rect chassi;

	public Ellipse roda1, roda2;


    // Construtor da classe carro com posição X e Y, tamanho W e H, cor do contorno inicial, largura do contorno inicial e marcador de "ação" (mover, transformar, etc.)
	public Carro(int x, int y, int w, int h) {
		super(x, y, w, h);
		this.chassi = new Rect(this.x, this.y, this.w, this.h * 2/3);
		this.roda1 = new Ellipse(this.x, this.y+this.h*2/3, this.w/2, this.h*1/3);
		this.roda2 = new Ellipse(this.x + this.w - this.w/2, this.y+this.h*2/3, this.w/2, this.h*1/3);
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

    public String mouseSection(int mouseX, int mouseY) {
    /**
         * Método para definir a posição do mouse ao ser clicado em relação à figura.
         *
         * Verifica se o mouse, na posição mouseX, mouseY,  foi pressionado dentro de cada seção da figura, incluindo seu tamanho de borda e uma "folga"
         * para impresições do usuário ao clicar.
         * Esse método difere do método clicked(int x, int y) ao retornar a seção específica em que o mouse foi pressionado, para que seja possível
         * aplicar cálculos de transformação sobre a figura.
         *
         * int mouseX: posição x do mouse.
         * int mouseY: posição y do mouse.
         * return String: Retorna a seção específica, ou "NA", em que o mouse foi pressionado sobre a figura.
         */
      if(mouseX >= this.chassi.x-6-(int)this.chassi.border
         && mouseX <= this.chassi.x+6+(int)this.chassi.border
         && mouseY >= this.chassi.y-6-(int)this.chassi.border
         && mouseY <= this.chassi.y+6+(int)this.chassi.border) {
          return "NW";
      } else if(mouseX >= (this.chassi.x+this.chassi.w)-6-(int)this.chassi.border
                && mouseX <= (this.chassi.x+this.chassi.w)+6+(int)this.chassi.border
                && mouseX <= (this.chassi.x+this.chassi.w+(int)(this.chassi.border))+6
                && mouseY >= this.chassi.y-6-(int)this.chassi.border
                && mouseY <= this.chassi.y+6+(int)this.chassi.border){
          return "NE";
      } else if(mouseX >= this.chassi.x-6-(int)this.chassi.border
                && mouseX <= this.chassi.x+6+(int)this.chassi.border
                && mouseY >= (this.chassi.y+this.chassi.h+this.roda1.h)-6-(int)this.chassi.border
                && mouseY <= (this.chassi.y+this.chassi.h+this.roda1.h)+6+(int)this.chassi.border){
          return "SW";
      } else if(mouseX >= (this.chassi.x+this.chassi.w)-6-(int)this.chassi.border
                && mouseX <= (this.chassi.x+this.chassi.w)+6+(int)this.chassi.border
                && mouseY >= (this.chassi.y+this.chassi.h+this.roda1.h)-6-(int)this.chassi.border
                && mouseY <= (this.chassi.y+this.chassi.h+this.roda1.h)+6+(int)this.chassi.border){
          return "SE";
      } else if(mouseX >= this.chassi.x-6-(int)this.chassi.border
                && mouseX <= (this.chassi.x+this.chassi.w+6)+(int)this.chassi.border
                && mouseY >= this.chassi.y-6-(int)this.chassi.border
                && mouseY <= this.chassi.y+6+(int)this.chassi.border){
          return "N";
      } else if(mouseX >= this.chassi.x-6-(int)this.chassi.border
                && mouseX <= (this.chassi.x+this.chassi.w+6)+(int)this.chassi.border
                && mouseY >= (this.chassi.y+this.chassi.h+this.roda1.h-6)-(int)this.chassi.border
                && mouseY <= (this.chassi.y+this.chassi.h+this.roda1.h+6)+(int)this.chassi.border){
          return "S";
      } else if(mouseX >= this.chassi.x-6-(int)this.chassi.border
                && mouseX <= this.chassi.x+6+(int)this.chassi.border
                && mouseY >= this.chassi.y-6-(int)this.chassi.border
                && mouseY <= (this.chassi.y+this.chassi.h+this.roda1.h+6)+(int)this.chassi.border) {
          return "W";
      } else if(mouseX >= (this.chassi.x+this.chassi.w-6)-(int)this.chassi.border
                && mouseX <= (this.chassi.x+this.chassi.w+6)+(int)this.chassi.border
                && mouseY >= this.chassi.y-6-(int)this.chassi.border
                && mouseY <= (this.chassi.y+this.chassi.h+this.roda1.h+6)+(int)this.chassi.border) {
          return "E";
      } else if(mouseX >= this.chassi.x+7+(int)this.chassi.border
                && mouseX <= (this.chassi.x + this.chassi.w - 7)+(int)this.chassi.border
                && mouseY >= this.chassi.y+7+(int)this.chassi.border
                && mouseY <= (this.chassi.y + this.chassi.h+this.roda1.h - 7)+(int)this.chassi.border){
          return "D";
      } else return "NA";
    }

    public boolean clicked(int x, int y) {
    /**
         * Método para determinar se a figura foi clicada.
         *
         * Verifica se o mouse, na posição x, y,  foi pressionado dentro dos limites x, x+w, y, y+h da figura, incluindo seu tamanho de borda e uma "folga"
         * para impresições do usuário ao clicar.
         *
         * int x: posição x do mouse.
         * int y: posição y do mouse.
         * return Boolean: Retorna "true" caso a posição do mouse esteja dentro dos limites da figura, e "false" caso contrário.
         */
         return (this.chassi.clicked(x, y) || this.roda1.clicked(x, y) || this.roda2.clicked(x, y));
    }

    // Pinta o carro, incluindo chassi e rodas
	public void paint(Graphics g, boolean focused) {
		this.chassi.paint(g, false);
		this.roda1.paint(g, false);
		this.roda2.paint(g, false);

        Graphics2D g2d = (Graphics2D) g;
        if(focused) {
            g2d.setStroke(new BasicStroke(2.0f));
            g2d.setColor(new Color(255,0,0));
            g2d.drawRect((this.chassi.x-(int)this.chassi.border)-3, (this.chassi.y-(int)this.chassi.border)-3, (this.chassi.w+(int)(this.chassi.border*2))+6, (this.chassi.h+this.roda1.h+(int)(this.chassi.border*2))+6);
        }
        g2d.setColor(Color.BLACK);
	}

    public String toSVG() {
        return this.chassi.toSVG() + this.roda1.toSVG() + this.roda2.toSVG();
    }
}
