package edu.uoc.android.daada_pec1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import modelo.BookAuthorComparator;
import modelo.BookItem;
import modelo.BookModel;
import modelo.BookTitleComparator;


public class BookListActivity extends AppCompatActivity {

    boolean tabletMode;

    private ListView bookListView ;
    ArrayList<HashMap<String,String>> list=new ArrayList<>();

    private final static int EVEN = 0;
    private final static int ODD = 1;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);

        // Set table/mobile mode
        tabletMode = findViewById(R.id.book_detail) != null;
        Log.d("BookListActivity", "onCreate - Tablet mode:" + tabletMode);

        recyclerView = findViewById(R.id.book_list);
        assert recyclerView != null;

        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, BookModel.ITEMS, tabletMode));

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater ();
        inflater.inflate (R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // gracias a la id, sabemos que item es el que se oprime, en este caso usamos un switch
        switch (item.getItemId())
        {
            case R.id.sortTitle:
                //presiono em item1
                Collections.sort(BookModel.ITEMS, new BookTitleComparator());
                recyclerView.getAdapter().notifyDataSetChanged();
                //adapter.notifyDatasetChanged()
                return true;
            case R.id.sortAuthor:
                Collections.sort(BookModel.ITEMS, new BookAuthorComparator());
                recyclerView.getAdapter().notifyDataSetChanged();
                //presiono em item2
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }


    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final BookListActivity mParentActivity;
        private final List<BookItem> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        };


        SimpleItemRecyclerViewAdapter(BookListActivity parent,
                                      List<BookItem> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public int getItemViewType(int position) {
            int type;
            if (position % 2 != 0)
                type = ODD;
            else
                type = EVEN;
            return type;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            if (viewType == EVEN) {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.book_list_content_even, parent, false);
            }
            else{
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.book_list_content_odd, parent, false);
            }

            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mitema = mValues.get(position);
            Log.d("onBindViewHolder", "pos" + position + ":" + holder.mitema.getTítulo() + "-" + holder.mitema.getAutor());

            holder.mTitleView.setText(mValues.get(position).getTítulo());
            holder.mAuthorView.setText(mValues.get(position).getAutor());

            holder.itemView.setTag(position+1);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        //if table mode, get details in fragment
                        BookDetailFragment fragment = BookDetailFragment.newInstance(holder.mitema);
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.book_detail, fragment);
                        ft.commit();
                    } else {
                        //if mobile mode, get details in new activity
                        Context context = v.getContext();
                        Intent intent = new Intent(context, BookDetailActivity.class);

                        intent.putExtra("data", (Serializable) holder.mitema);
                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            BookItem mitema;
            final TextView mTitleView;
            final TextView mAuthorView;
            View mView;

            ViewHolder(View view) {
                super(view);
                mView = view;
                mTitleView = (TextView) view.findViewById(R.id.id_book_name);
                mAuthorView = (TextView) view.findViewById(R.id.id_author_name);
            }
        }


    }
}
