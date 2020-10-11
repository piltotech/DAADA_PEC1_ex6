package modelo;

import android.util.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class BookModel {
    public static final List <BookItem> ITEMS = new ArrayList <BookItem> ();
    private static final int COUNT = 16;

    static {
    for (int i = 1; i <= COUNT; i++) {
            int randomAuthor = (int) Math.floor(((Math.random() * COUNT + 1)));
            BookItem book = new BookItem(i, "Title" + String.format("%02d", i),
                    "Author" + String.format("%02d", randomAuthor),
                            new Date(),
                            "Description" + i, "bookcover" +(((i-1)%3+1)));
            ITEMS.add (book);
        }

    for (int i = 0; i < ITEMS.size(); i++){
        Log.d("BookModel", "pos" + i + ":" + ITEMS.get(i).getTítulo() + "-" + ITEMS.get(i).getAutor());
    }


    /*    BookItem book1 = new BookItem(0, "Title1", "Author1", new Date(), "Description", null);
        BookItem book2 = new BookItem(1, "Title2", "Author2", new Date (), "Description2", null);
        ITEMS.add (book1);
        ITEMS.add (book2);
    */
    }

}

