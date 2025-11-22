package com.headfirstjava.chapter16;

import java.util.*;

class Test {
    public static void main (String[] args) {
        new Test().go();
    }

    public void go() {
        Book b1 = new Book("How Cats Work");
        Book b2 = new Book("Remix your Body");
        Book b3 = new Book("Finding Emo");
        BookCompare bCompare = new BookCompare();
        TreeSet<Book> tree = new TreeSet<Book>(bCompare);
        tree.add(new Book("How Cats Work"));
        tree.add(new Book("Finding Emo"));
        tree.add(new Book("Remix your Body"));
        System.out.println(tree);
    }
}
