package com.suelengc.bookplan.view.mybooks;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.suelengc.bookplan.model.Book;
import com.suelengc.bookplan.view.chapter.ChapterListActivity;

import java.util.List;

public class MyBooksListItemClickListener implements AdapterView.OnItemClickListener {

    private final Context context;
    private final List<Book> books;

    public MyBooksListItemClickListener(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Book book = this.books.get(position);

        Intent planChapters = new Intent(this.context, ChapterListActivity.class);
        planChapters.putExtra("book", book);
        planChapters.putExtra("book_id", book.getId());

        this.context.startActivity(planChapters);
    }
}
