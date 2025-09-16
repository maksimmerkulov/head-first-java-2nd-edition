package chapter09;

public class Duck {
    int size;

    public Duck(int newSize) {
        if (newSize == 0) {
            size = 27;
        } else {
            size = newSize;
        }
    }
}
