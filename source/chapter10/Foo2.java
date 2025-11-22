package chapter10;

// This does NOT compile
public class Foo2 {
    int x;

    public static void go() {
        // System.out.println(x); // ERROR: non-static variable x cannot be referenced from a static context
    }
}
