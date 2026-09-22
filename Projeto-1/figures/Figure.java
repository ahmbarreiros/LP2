package figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Arrays;
import ivisible.IVisible;

public abstract class Figure implements IVisible {
    public int x, y;
    public int w, h;

    public float border; // Variável de largura de contorno
    public int contornoRGBIndex; // Variável de cor de contorno
    public String drag; // Variável de ação da figura
    public static ArrayList<Color> colors = new ArrayList<Color>(Arrays.asList(Color.BLACK, Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.ORANGE, Color.PINK, Color.WHITE)); // Lista de cores disponíveis para uma figura
                                      


    //public abstract void oper(int mouseX, int mouseY);

    public Figure (int x, int y, int w, int h, int contornoRGBIndex, float border, String drag) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.contornoRGBIndex = contornoRGBIndex;
        this.border = border;
        this.drag = drag;
    }

    

    public void drag (int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public int getCursor(String section) {
        switch(section) {
          case "NW":
              return Cursor.NW_RESIZE_CURSOR;
          case "NE":
              return Cursor.NE_RESIZE_CURSOR;
          case "SW":
              return Cursor.SW_RESIZE_CURSOR;
          case "SE":
              return Cursor.SE_RESIZE_CURSOR;
          case "N":
              return Cursor.N_RESIZE_CURSOR;
          case "S":
              return Cursor.S_RESIZE_CURSOR;
          case "W":
              return Cursor.W_RESIZE_CURSOR;
          case "E":
              return Cursor.E_RESIZE_CURSOR;
          case "D":
              return Cursor.MOVE_CURSOR;
          case "NA":
              return Cursor.DEFAULT_CURSOR;
          default:
              return Cursor.DEFAULT_CURSOR;
          }   
    }

    public void transform(String section, int dw, int dh) {
        switch(section) {
            case "D":
                break;
            case "NW":
                this.transformNW(dw, dh);
            case "NE":
                this.transformNE(dw, dh);
            case "SW":
                this.transformSW(dw, dh);
            case "SE":
                this.transformSE(dw, dh);
            case "N":
                this.transformN(dh);
            case "S":
                this.transformS(dh);
            case "W":
                this.transformW(dw);
            case "E":
                this.transformE(dw);
            default:
                return;
        }
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

    // Método para alterar para a próxima cor de contorno
    public void changeBorderR() {
        this.contornoRGBIndex = (contornoRGBIndex+1) % colors.size();
    }

    // Método para alterar para a cor de contorno anterior
    public void changeBorderL() {
        if(contornoRGBIndex>0) {
             this.contornoRGBIndex = (contornoRGBIndex-1) % colors.size();
        }else{
                this.contornoRGBIndex = colors.size()-1;
        }
    }

    // Métodos abstratos para preencher cor de fundo da figura
    public abstract void changeFillD();
    public abstract void changeFillU();

    // Métodos abstratos para aumentar/diminuir largura do contorno
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


    public boolean clicked(int x, int y) {
        if((x >= this.x-6-(int)this.border 
            && x <= (this.x+this.w+6)+(int)this.border 
            && y >= this.y-6-(int)this.border 
            && y <= (this.y+this.h+6)+(int)this.border)) {
            return true;
        } else return false;
    }

    public String mouseSection(int mouseX, int mouseY) {
      if(mouseX >= this.x-6-(int)this.border 
         && mouseX <= this.x+6+(int)this.border 
         && mouseY >= this.y-6-(int)this.border 
         && mouseY <= this.y+6+(int)this.border) {
          return "NW";
      } else if(mouseX >= (this.x+this.w)-6-(int)this.border 
                && mouseX <= (this.x+this.w)+6+(int)this.border 
                && mouseX <= (this.x+this.w+(int)(this.border))+6 
                && mouseY >= this.y-6-(int)this.border 
                && mouseY <= this.y+6+(int)this.border){
          return "NE";
      } else if(mouseX >= this.x-6-(int)this.border 
                && mouseX <= this.x+6+(int)this.border 
                && mouseY >= (this.y+this.h)-6-(int)this.border 
                && mouseY <= (this.y+this.h)+6+(int)this.border){
          return "SW";
      } else if(mouseX >= (this.x+this.w)-6-(int)this.border 
                && mouseX <= (this.x+this.w)+6+(int)this.border 
                && mouseY >= (this.y+this.h)-6-(int)this.border 
                && mouseY <= (this.y+this.h)+6+(int)this.border){
          return "SE";
      } else if(mouseX >= this.x-6-(int)this.border 
                && mouseX <= (this.x+this.w+6)+(int)this.border 
                && mouseY >= this.y-6-(int)this.border 
                && mouseY <= this.y+6+(int)this.border){
          return "N";
      } else if(mouseX >= this.x-6-(int)this.border 
                && mouseX <= (this.x+this.w+6)+(int)this.border 
                && mouseY >= (this.y+this.h-6)-(int)this.border 
                && mouseY <= (this.y+this.h+6)+(int)this.border){
          return "S";
      } else if(mouseX >= this.x-6-(int)this.border 
                && mouseX <= this.x+6+(int)this.border 
                && mouseY >= this.y-6-(int)this.border 
                && mouseY <= (this.y+this.h+6)+(int)this.border) {
          return "W";
      } else if(mouseX >= (this.x+this.w-6)-(int)this.border 
                && mouseX <= (this.x+this.w+6)+(int)this.border 
                && mouseY >= this.y-6-(int)this.border 
                && mouseY <= (this.y+this.h+6)+(int)this.border) {
          return "E";
      } else if(mouseX >= this.x+7 
                && mouseX <= (this.x + this.w - 7) 
                && mouseY >= this.y+7 
                && mouseY <= (this.y + this.h - 7)){
          return "D";
      } else return "NA";
    }

    // Método abstrato de paint e para pintar contorno de foco na figura
    public abstract void paint (Graphics g, boolean focused);

}
