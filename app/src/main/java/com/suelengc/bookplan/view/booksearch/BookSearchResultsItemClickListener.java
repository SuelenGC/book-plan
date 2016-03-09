package com.suelengc.bookplan.view.booksearch;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.suelengc.bookplan.model.Book;
import com.suelengc.bookplan.view.bookdetails.BookDetailsActivity;

import java.util.List;

public class BookSearchResultsItemClickListener implements android.widget.AdapterView.OnItemClickListener {

    private BookSearchActivity bookSearchActivity;
    private List<Book> books;

    public BookSearchResultsItemClickListener(BookSearchActivity bookSearchActivity, List<Book> books) {
        this.bookSearchActivity = bookSearchActivity;
        this.books = books;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Book book = books.get(position);

        Intent bookDetails = new Intent(bookSearchActivity, BookDetailsActivity.class);
        bookDetails.putExtra("book", book);

        bookSearchActivity.startActivity(bookDetails);
    }
}
