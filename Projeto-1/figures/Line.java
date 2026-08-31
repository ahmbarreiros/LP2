package figures;

import java.awt.*;

public class Line extends Figure {


    public Line(int x, int y, int w, int h, int contornoRGBIndex) {
        super(x, y, w, h, contornoRGBIndex);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.colors.get(contornoRGBIndex));
        g2d.drawLine(x, y, x+w, y+h);
        g2d.setColor(Color.BLACK);
    }
}
