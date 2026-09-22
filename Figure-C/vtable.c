#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int r,g,b;
} Color;

struct Figure;
typedef void (* Figure_Print) (struct Figure*);
typedef int  (* Figure_Area)  (struct Figure*);
typedef void (* Figure_Drag) (struct Figure*, int dx, int dy); // typedef de definição de método abstrato drag em Figure

typedef struct {
    void (* print) (struct Figure*);
    int  (* area)  (struct Figure*);
    void (* drag) (struct Figure*, int dx, int dy);  // definição de método abstrato drag em Figure
} Figure_vtable;

typedef struct Figure {
    int x, y, border;
    Color fg, bg;
    Figure_vtable* vtable;
} Figure;

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int w, h;
} Rect;

void rect_print (Rect* this) {
    Figure* sup = (Figure*) this;
    printf("Retangulo de tamanho (%d,%d) na posicao (%d,%d), area %d e largura de contorno de tamanho %d.\n",
           this->w, this->h, sup->x, sup->y, sup->vtable->area(sup), sup->border);
}

int rect_area (Rect* this) {
    Figure* sup = (Figure*) this;
    return this->w * this->h;
}

// Método drag para retângulo
void rect_drag (Rect* this, int dx, int dy) {
    Figure* sup = (Figure*) this;
    sup->x += dx;
    sup->y += dy;
}

Figure_vtable rect_vtable = {
    (Figure_Print) rect_print,
    (Figure_Area)  rect_area,
    (Figure_Drag)  rect_drag
};



Rect* rect_new (int x, int y, int w, int h, int border) {
    Rect*   this  = malloc(sizeof(Rect));
    Figure* sup = (Figure*) this;
    sup->vtable = &rect_vtable;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
    sup->border = border;
}

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
    int w, h;
} Ellipse;

void ellipse_print (Rect* this) {
    Figure* sup = (Figure*) this;
    printf("Elipse de tamanho (%d,%d) na posicao (%d,%d), area %d e largura de contorno de tamanho %d.\n",
           this->w, this->h, sup->x, sup->y, sup->vtable->area(sup), sup->border);
}

int ellipse_area (Rect* this) {
    Figure* sup = (Figure*) this;
    return this->w * this->h;
}

// Método drag para ellipse
void ellipse_drag (Ellipse* this, int dx, int dy) {
    Figure* sup = (Figure*) this;
    sup->x += dx;
    sup->y += dy;
}

Figure_vtable ellipse_vtable = {
    (Figure_Print) ellipse_print,
    (Figure_Area)  ellipse_area,
    (Figure_Drag)  ellipse_drag
};

Ellipse* ellipse_new (int x, int y, int w, int h, int border) {
    Ellipse* this = malloc(sizeof(Ellipse));
    Figure* sup = (Figure*) this;
    sup->vtable = &ellipse_vtable;
    sup->x = x;
    sup->y = y;
    this->w = w;
    this->h = h;
    sup->border = border;
}

///////////////////////////////////////////////////////////////////////////////

void main (void) {
    Figure* figs[4] = {
        (Figure*) rect_new(10,10,100,100, 10),
        (Figure*) ellipse_new(40,10,140,300, 8),
        (Figure*) rect_new(10,10,100,100, 6),
        (Figure*) ellipse_new(210,110,305,130, 2)
    };

    ///

    for (int i=0; i<4; i++) {
        figs[i]->vtable->print(figs[i]);
    }

    figs[1]->vtable->drag(figs[0], 50, 50); // Exemplo de drag no retângulo de posição 0 na lista de figuras
    figs[1]->vtable->print(figs[0]);

    ///

    for (int i=0; i<4; i++) {
        free(figs[i]);
    }
}
