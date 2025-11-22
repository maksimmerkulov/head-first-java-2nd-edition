package chapter08;

public class TestObject {
    public static void main(String[] args) {
        Dog a = new Dog();
        Cat c = new Cat();
        if (a.equals(c)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        System.out.println(c.getClass());
        System.out.println(c.hashCode());
        System.out.println(c.toString());
    }
}
