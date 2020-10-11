package modelo;

import java.util.Comparator;

public class BookAuthorComparator implements Comparator<BookItem> {
    public int compare(BookItem a, BookItem b) {
        return a.getAutor().compareTo(b.getAutor());
    }
}
