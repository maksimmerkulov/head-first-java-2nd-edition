package chapter16;

class Mountain {
    String name;
    int height;

    Mountain(String n, int h) {
        name = n;
        height = h;
    }
    public String toString() {
        return name + " " + height;
    }
}
