package chapter10;

// This does NOT compile
public class Foo3 {
    // final int x; // blank final, not initialized

    public void go() {
        // System.out.println(x); // ERROR: variable x might not have been initialized
    }
}
