package chapter16;

import java.util.*;

public class BookCompare implements Comparator<Book> {
    public int compare(Book one, Book two) {
        return (one.title.compareTo(two.title));
    }
}
