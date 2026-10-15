import istackable.IStackable;
import java.util.ArrayList;

class ArrayStack implements IStackable {
    private ArrayList arrayl = new ArrayList();
    public int size() {
        return this.arrayl.size();
    }

    public void push(int v) {
        this.arrayl.add(v);
    }

    public int pop() {
        return (int)this.arrayl.remove(this.arrayl.size()-1);
    }
}
