import istackable.IStackable;
import java.util.LinkedList;

class LinkedStack implements IStackable {
    private LinkedList listaenc = new LinkedList();
    public int size() {
       return this.listaenc.size();
    }

    public void push(int v) {
        this.listaenc.addFirst(v);
    }

    public int pop() {
        return (int)this.listaenc.removeFirst();
    }
}
