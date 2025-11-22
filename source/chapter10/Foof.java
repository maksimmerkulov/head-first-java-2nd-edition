package chapter10;

class Foof {
    final int size = 3;
    final int whuffie;

    Foof() {
        whuffie = 42;
    }

    void doStuff(final int x) {
        // you can’t change x
    }

    void doMore() {
        final int z = 7;
        // you can’t change z
    }
}
