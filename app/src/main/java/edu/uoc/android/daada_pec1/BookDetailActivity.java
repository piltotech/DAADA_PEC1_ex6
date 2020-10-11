package edu.uoc.android.daada_pec1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import modelo.BookItem;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        BookItem book = (BookItem) getIntent().getSerializableExtra("data");

        if (savedInstanceState == null) {
             BookDetailFragment fragment = BookDetailFragment.newInstance(book);
             getSupportFragmentManager().beginTransaction()
                     .add(R.id.book_detail, fragment)
                     .commit();
         }

    }
}
