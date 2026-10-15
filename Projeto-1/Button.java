import ivisible.IVisible;
import figures.Figure;
import figures.Carro;
import java.awt.*;

public class Button implements IVisible {
    static int SPC = 20;
    static int DIM = 40;
    static int PAD = 4;

    public  int    idx;
    private Figure fig;
    public String type;

    public Button (int idx, Figure fig, String type) {
        this.idx = idx;
        this.fig = fig;
        this.fig.x = PAD+SPC;
        this.fig.y = PAD+SPC + idx*DIM;
        this.fig.w = DIM-PAD*2;
        this.fig.h = DIM-PAD*2;
        this.type = type;
        if(type == "Carro") {
            Carro carro = (Carro) fig;
            carro.chassi.x = PAD+SPC;
            carro.chassi.y = PAD+SPC + idx*DIM;
            carro.chassi.w = DIM-PAD*2;
            carro.chassi.h = (DIM-PAD*2) * 2/3;

            carro.roda1.x = PAD+SPC;
            carro.roda1.y = (PAD+SPC + idx*DIM) + ((DIM-PAD*2) * 2/3);
            carro.roda1.w = (DIM-PAD*2)/2;
            carro.roda1.h = (DIM-PAD*2) * 1/3;
            carro.roda2.x = (PAD+SPC) + (DIM-PAD*2)/2;
            carro.roda2.y = (PAD+SPC + idx*DIM) + (DIM-PAD*2) * 2/3;
            carro.roda2.w = (DIM-PAD*2)/2;
            carro.roda2.h = (DIM-PAD*2) * 1/3;

        }
    }

    public boolean clicked (int x, int y) {
        return SPC<=x && x<=SPC+DIM && SPC+this.idx*DIM<=y && y<=SPC+this.idx*DIM+DIM;
    }

    public void paint (Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(focused ? Color.GRAY : Color.LIGHT_GRAY);
        g2d.fillRect(SPC, SPC+this.idx*DIM, DIM, DIM);

        g2d.setStroke(new BasicStroke(2.0f));
        g2d.setColor(Color.BLACK);
        g2d.drawRect(SPC, SPC+this.idx*DIM, DIM, DIM);

        this.fig.paint(g, false);
    }
}
