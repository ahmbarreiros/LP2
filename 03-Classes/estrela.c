#include <stdio.h>

typedef struct {
  int x, y;
  int w, h;
  int pontas;
  int raio;
} Estrela;

void print (Estrela* s) {
  printf("Uma estrela de %d pontas, com raio %d, de tamanho (%d,%d) na posicao (%d,%d).\n",
    s->pontas, s->raio, s->w, s->h, s->x, s->y);
}

void main (void) {
    Estrela s1 = { 5, 3, 1, 1, 10, 10 };
    print(&s1);
}
