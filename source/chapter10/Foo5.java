package chapter10;

// This compiles
public class Foo5 {
    static final int x = 12;

    public void go(final int x) {
        System.out.println(x); // OK: method parameter shadows the field
    }
}
