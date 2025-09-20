package chapter10;

// This compiles
public class Foo {
    static int x;

    public void go() {
        System.out.println(x); // OK: static field can be accessed in an instance method
    }
}
