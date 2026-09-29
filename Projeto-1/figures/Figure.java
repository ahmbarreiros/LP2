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

    public String mousePressedSection = ""; // Variável que guarda a ação que está sendo realizada na figura, caso se aplique

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
    /**
         * Arrasta a figura sobre o canvas
         *
         * Quando um mouse arrasta uma figura que está em foco, altera o valor x e y dela baseado na distância que o mouse percorreu, 
         * com a posição em que o mouse estava quando pressionou a figura.
         *
         * int dx: Distância em que o mouse percorreu ao pressionar a figura no eixo X.
         * int dy: Distância em que o mouse percorreu ao pressionar a figura no eixo Y.
         */

        this.x += dx;
        this.y += dy;
    }

    
    public int getCursor(String section) {
    /**
         * Retorna string com cada posição de ação possível para o mouse em uma figura.
         *
         * Quando o mouse está sobre uma figura em foco, ele pode receber o ícone de cursor baseado na posição dele sobre a figura.
         * Ex: Ao arrastar o mouse sobre o canto superior esquerdo de uma figura em foco, será retornado o ícone de cursor NW_RESIZE_CURSOR, do java.awt.
         *
         * String section: String que determina a seção em que o mouse está sobre a figura em foco.
         * return int: Retorna um tipo de cursor para cada seção possível do mouse.
         */
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

    public void transformNW(int dw, int dh) {
    /**
         * Transforma a figura em foco a partir do canto superior esquerdo.
         *
         * Ao pressionar no canto superior esquerdo de uma figura em foco (Noroeste da figura), é alterado a posição e tamanho da figura,
         * para "redimensionar" ela para cima (Norte) e para à esquerda (Oeste).
         *
         * int dw: A Distância que o mouse percorre ao pressionar a figura em foco e arrastá-la no eixo X.
         * int dh: A Distância que o mouse percorre ao pressionar a figura em foco e arrastá-la no eixo Y.
         */
        if(this.w + (-1*dw) >= 30) {
            this.w += (-1*dw);
            this.x += dw;
        }
        if(this.h + (-1*dh) >= 30) {
            this.h += (-1*dh);
            this.y += dh;
        }
    }

    public void transformNE(int dw, int dh) {
    /**
         * Transforma a figura em foco a partir do canto superior direito.
         *
         * Ao pressionar no canto superior direito de uma figura em foco (Nordeste da figura), é alterado a posição e tamanho da figura,
         * para "redimensionar" ela para cima (Norte) e para à direita (Leste).
         *
         * int dw: A Distância que o mouse percorre ao pressionar a figura em foco e arrastá-la no eixo X.
         * int dh: A Distância que o mouse percorre ao pressionar a figura em foco e arrastá-la no eixo Y.
         */
        if(this.w + dw >= 30) {
            this.w += (dw);
        }
        if(this.h + (-1*dh) >= 30) {
            this.h += (-1*dh);
            this.y += dh;
        }
    }

    public void transformSW(int dw, int dh) {
    /**
         * Transforma a figura em foco a partir do canto inferior esquerdo.
         *
         * Ao pressionar no canto inferior esquerdo de uma figura em foco (Sudoeste da figura), é alterado a posição e tamanho da figura,
         * para "redimensionar" ela para baixo (Sul) e para à esquerda (Oeste).
         *
         * int dw: A Distância que o mouse percorre ao pressionar a figura em foco e arrastá-la no eixo X.
         * int dh: A Distância que o mouse percorre ao pressionar a figura em foco e arrastá-la no eixo Y.
         */
        if(this.w + (-1*dw) >= 30) {
            this.w += (-1*dw);
            this.x += dw;
        }
        if(this.h + dh >= 30) {
            this.h += (dh);
        }
    }

    public void transformSE(int dw, int dh) {
    /**
         * Transforma a figura em foco a partir do canto inferior direito.
         *
         * Ao pressionar no canto inferior direito de uma figura em foco (Sudeste da figura), é alterado a posição e tamanho da figura,
         * para "redimensionar" ela para baixo (Sul) e para à direita (Leste).
         *
         * int dw: A Distância que o mouse percorre ao pressionar a figura em foco e arrastá-la no eixo X.
         * int dh: A Distância que o mouse percorre ao pressionar a figura em foco e arrastá-la no eixo Y.
         */
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
    /**
         * Transforma a figura em foco no eixo Y a partir da sua seção superior.
         *
         * Ao pressionar na parte superior de uma figura em foco (Norte da figura), é alterado a posição e tamanho da figura,
         * para "redimensionar" ela para cima (Norte).
         *
         * int dh: A Distância que o mouse percorre ao pressionar a figura em foco e arrastá-la no eixo Y.
         */
        if(this.h + (-1*dh) >= 30) {
            this.h += (-1*dh);
            this.y += dh;
        }
    }

    public void transformS(int dh) {
    /**
         * Transforma a figura em foco no eixo Y a partir da sua seção inferior.
         *
         * Ao pressionar na parte inferior de uma figura em foco (Sul da figura), é alterado a posição e tamanho da figura,
         * para "redimensionar" ela para baixo (Sul).
         *
         * int dh: A Distância que o mouse percorre ao pressionar a figura em foco e arrastá-la no eixo Y.
         */
        if(this.h + dh >= 30) {
            this.h += (dh);
        }
    }

    public void transformW(int dw) {
    /**
         * Transforma a figura em foco no eixo X a partir da sua seção oeste.
         *
         * Ao pressionar na parte oeste de uma figura em foco, é alterado a posição e tamanho da figura,
         * para "redimensionar" ela para a esquerda (Oeste).
         *
         * int dw: A Distância que o mouse percorre ao pressionar a figura em foco e arrastá-la no eixo X.
         */
        if(this.w+(-1*dw) >= 30) {
            this.w += (-1*dw);
            this.x += dw;  
        }
    }

    public void transformE(int dw) {
    /**
         * Transforma a figura em foco no eixo X a partir da sua seção leste.
         *
         * Ao pressionar na parte leste de uma figura em foco, é alterado a posição e tamanho da figura,
         * para "redimensionar" ela para a esquerda (Leste).
         *
         * int dw: A Distância que o mouse percorre ao pressionar a figura em foco e arrastá-la no eixo X.
         */
        if(this.w + dw >= 30) {
            this.w += (dw);
        }
    }

    public void changeBorderR() {
    /**
         * Método para alterar para a próxima cor de contorno da lista de cores.
         *
         * Itera sobre a lista de cores da variável "colors", 
         * aumentando o index contornoRGBIndex que guarda a cor do contorno da figura em foco até que chegue
         * na última posição da lista de cores, assim voltando para a posição 0 (inicio) da lista.
         *
         */
        this.contornoRGBIndex = (contornoRGBIndex+1) % colors.size();
    }

    public void changeBorderL() {
    /**
         * Método para alterar para a cor anterior de contorno da lista de cores.
         *
         * Itera sobre a lista de cores da variável "colors", 
         * diminuindo o index contornoRGBIndex que guarda a cor do contorno da figura em foco até que chegue
         * na primeira posição da lista de cores, assim avançando para a última posição da lista.
         *
         */
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
    /**
         * Método para aumentar o tamanho do contorno.
         *
         * Aumenta a largura do contorno da figura em foco, até chegar em um limite de tamanho 20.
         *
         */
        if (this.border <= 20.0f) {
            this.border += 2.0f;
         }    
    }

    public void changeBorderD() {
    /**
         * Método para diminuir o tamanho do contorno.
         *
         * Diminui a largura do contorno da figura, até chegar em um limite de tamanho 2.
         *
         */
        if (this.border > 2.0f) {
            this.border -= 2.0f;
        }
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
        if((x >= this.x-6-(int)this.border 
            && x <= (this.x+this.w+6)+(int)this.border 
            && y >= this.y-6-(int)this.border 
            && y <= (this.y+this.h+6)+(int)this.border)) {
            return true;
        } else return false;
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
      } else if(mouseX >= this.x+7+(int)this.border 
                && mouseX <= (this.x + this.w - 7)+(int)this.border 
                && mouseY >= this.y+7+(int)this.border 
                && mouseY <= (this.y + this.h - 7)+(int)this.border){
          return "D";
      } else return "NA";
    }

    // Método abstrato de paint e para pintar contorno de foco na figura
    public abstract void paint (Graphics g, boolean focused);

}
