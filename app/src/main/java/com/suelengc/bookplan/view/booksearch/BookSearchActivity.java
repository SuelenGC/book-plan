package com.suelengc.bookplan.view.booksearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.suelengc.bookplan.R;
import com.suelengc.bookplan.model.Book;
import com.suelengc.bookplan.service.book.BookSearchTask;

import java.util.List;

public class BookSearchActivity extends AppCompatActivity implements BookSearchTask.CompleteBookSearchCallback {

    private EditText bookName;
    private ImageView bookSearch;
    private ListView bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);
        findViewsById();

        bookSearch.setOnClickListener(new BookSearchClickListener(this, bookName));
    }

    private void findViewsById() {
        bookName = (EditText) findViewById(R.id.booklist_book_name);
        bookSearch = (ImageView) findViewById(R.id.booklist_search_button);
        bookList = (ListView) findViewById(R.id.booklist_list);
    }

    @Override
    public void bookSearchComplete(List<Book> books) {
        BookSeachResultAdapter adapter = new BookSeachResultAdapter(this, books);
        bookList.setAdapter(adapter);

        bookList.setOnItemClickListener(new BookSearchResultsItemClickListener(this, books));
    }
}
