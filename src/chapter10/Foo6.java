package chapter10;

// This compiles
public class Foo6 {
    int x = 12;

    public static void go(final int x) {
        System.out.println(x); // OK: uses method parameter, not the field
    }
}
