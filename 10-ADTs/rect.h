#ifndef RECT_H_
#define RECT_H_

typedef struct Rect Rect;
Rect* rect_new(int x, int y, int w, int h);
void rect_drag(Rect* this, int dx, int dy);
void rect_print(Rect* this);



#endif // RECT_H_
