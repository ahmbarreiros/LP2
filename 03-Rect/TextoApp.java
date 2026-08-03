public class TextoApp {
    public static void main (String[] args) {
        Texto t1 = new Texto(1,1, 10,10, 5, "Arial", "Texto de exemplo");
        t1.print();
    }
}
class Texto {
    int x, y;
    int w, h;
    int espacamento;
    String fonte;
    String conteudo;
    Texto (int x, int y, int w, int h, int espacamento, String fonte, String conteudo) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
	this.espacamento = espacamento;
	this.fonte = fonte;
	this.conteudo = conteudo;
    }
    void print () {
        System.out.format("Texto '%s' com espacamento de %d px entre os caracteres e fonte %s, de tamanho (%d, %d) e posição (%d, %d).\n",
           this.conteudo, this.espacamento, this.fonte, this.w, this.h, this.x, this.y);
    }
}
