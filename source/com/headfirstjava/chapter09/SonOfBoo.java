package com.headfirstjava.chapter09;

class SonOfBoo extends Boo {

    public SonOfBoo() {
        super("boo");
    }

    public SonOfBoo(int i) {
        super("Fred");
    }

    public SonOfBoo(String s) {
        super(42);
    }

    /*
      %javac SonOfBoo.java

      cannot resolve symbol

      symbol:constructor Boo()
    */
//    public SonOfBoo(int i, String s) {
//    }

    /*
      %javac SonOfBoo.java

      cannot resolve symbol

      symbol : constructor Boo
      (java.lang.String,java.la
      ng.String)
    */
//    public SonOfBoo(String a, String b, String c) {
//        super(a,b);
//    }

    public SonOfBoo(int i, int j) {
        super("man", j);
    }

    /*
      %javac SonOfBoo.java

      cannot resolve symbol

      symbol : constructor Boo
      (int,java.lang.String)
    */
//    public SonOfBoo(int i, int x, int y) {
//        super(i, "star");
//    }
}
