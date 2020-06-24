package com.example.library;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Context mCtx;
    private List<Book> bookList;

    public BookAdapter(Context mCtx, List<Book> bookList) {
        this.mCtx = mCtx;
        this.bookList = bookList;
    }


    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_books, null);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder bookViewHolder, int i) {
        final Book book = bookList.get(i);
        bookViewHolder.textViewbookname.setText(book.getBookname());
        bookViewHolder.textViewAuthor.setText(book.getAuthor());

        Glide.with(mCtx)
                .load(book.getImage())
                .into(bookViewHolder.imageView);

        bookViewHolder.parentlsyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx.getApplicationContext(),BookViewActivity.class);
                intent.putExtra("image_URL", book.getImage());
                intent.putExtra("bookname",book.getBookname());
                intent.putExtra("Author",book.getAuthor());
                intent.putExtra("bookid",book.getBookid());
                mCtx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewbookname, textViewAuthor;
        RelativeLayout parentlsyout;
        public BookViewHolder(View itemView) {
            super(itemView);
            textViewbookname = itemView.findViewById(R.id.textViewbookname);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            imageView = itemView.findViewById(R.id.imageView);
            parentlsyout = itemView.findViewById(R.id.parentlayout);
        }
    }
}
