package modelo;

import java.util.Comparator;

public class BookTitleComparator implements Comparator<BookItem> {
    public int compare(BookItem a, BookItem b) {
        return a.getTítulo().compareTo(b.getTítulo());
    }

}
