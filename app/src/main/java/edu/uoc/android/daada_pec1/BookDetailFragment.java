package edu.uoc.android.daada_pec1;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.io.Serializable;
import java.util.Date;

import modelo.BookItem;

public class BookDetailFragment extends Fragment {
    private static final String ARG_book = "param_book";

    private BookItem mBook;

    public BookDetailFragment() {
        // Required empty public constructor
    }

    public static BookDetailFragment newInstance(BookItem bookparam) {
        BookDetailFragment fragment = new BookDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_book, (Serializable) bookparam);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBook = (BookItem) getArguments().getSerializable(ARG_book);
        }

        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(mBook.getTítulo());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_book_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mBook != null) {

            /*String details_text = "Details from book " + mParam1 + " - Title:" + mParam2;
            Log.d("BookDetailFragment", details_text);*/
            //((TextView) rootView.findViewById(R.id.id_detail_book_name)).setText(mBook.getTítulo());
            ((TextView) rootView.findViewById(R.id.id_detail_author_name)).setText(mBook.getAutor());
            ((TextView) rootView.findViewById(R.id.id_detail_book_date)).setText(mBook.getFecha().toString());
            ((TextView) rootView.findViewById(R.id.id_detail_book_description)).setText(mBook.getDescripcion());

            String name = mBook.getportadaURL();
            int id = getResources().getIdentifier(name, "drawable", this.getActivity().getPackageName());
            ((ImageView) rootView.findViewById(R.id.bookImageView)).setImageResource(id);
        }

        return rootView;

    }
}
