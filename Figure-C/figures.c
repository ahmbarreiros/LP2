#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int r,g,b;
} Color;

struct Figure;
typedef void (* Figure_Print) (struct Figure*);
typedef void (* Figure_Drag) (struct Figure*, int dx, int dy); // typedef de definição de método abstrato drag em Figure

typedef struct Figure {
    int x, y, border; // Variável border para indicar a largura do contorno;
    Color fg, bg;
    void (* print) (struct Figure*);
    void (* drag) (struct Figure*, int dx, int dy);  // definição de método abstrato drag em Figure
} Figure;

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int w, h;
} Rect;

void rect_print (Rect* this) {
    Figure* sup = (Figure*) this;
    printf("Retangulo de tamanho (%d,%d) na posicao (%d,%d) e largura de contorno de tamanho %d.\n",
           this->w, this->h, sup->x, sup->y, sup->border);
}

// Método drag para retângulo
void rect_drag (Rect* this, int dx, int dy) {
    Figure* sup = (Figure*) this;
    sup->x += dx;
    sup->y += dy;
}

Rect* rect_new (int x, int y, int w, int h, int border) {
    Rect*   this  = malloc(sizeof(Rect));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) rect_print;
    sup->drag = (Figure_Drag) rect_drag; // especificação do método drag na criação de retângulo
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
    sup->border = border; // uso da variavél border no construtor
}

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int w, h;
} Ellipse;

void Ellipse_print (Ellipse* this) {
    Figure* sup = (Figure*) this;
    printf("Elipse de tamanho (%d,%d) na posicao (%d,%d) e largura de contorno de tamanho %d.\n",
           this->w, this->h, sup->x, sup->y, sup->border);
}

// Método drag para ellipse
void ellipse_drag (Ellipse* this, int dx, int dy) {
    Figure* sup = (Figure*) this;
    sup->x += dx;
    sup->y += dy;
}

Ellipse* ellipse_new (int x, int y, int w, int h, int border) {
    Ellipse* this = malloc(sizeof(Ellipse));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) Ellipse_print;
    sup->drag = (Figure_Drag) ellipse_drag; // especificação do método drag na criação de ellipse
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
    sup->border = border; // uso da variavél border no construtor
}

///////////////////////////////////////////////////////////////////////////////

void main (void) {
    Figure* figs[4] = {
        (Figure*) rect_new(10,10,100,100, 2),
        (Figure*) ellipse_new(40,10,140,300, 4),
        (Figure*) rect_new(10,10,100,100, 10),
        (Figure*) ellipse_new(210,110,305,130, 6)
    };

    ///

    for (int i=0; i<4; i++) {
        figs[i]->print(figs[i]);
    }
    
    // Uso do método drag para a elipse na posição 1 da lista
    figs[1]->drag(figs[1], 30, 50);
    figs[1]->print(figs[1]);

    ///

    for (int i=0; i<4; i++) {
        free(figs[i]);
    }
}
