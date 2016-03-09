package com.suelengc.bookplan.view.mybooks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.suelengc.bookplan.R;
import com.suelengc.bookplan.model.Book;
import com.suelengc.bookplan.view.booksearch.BookSearchActivity;

import java.util.List;

public class MyBooksActivity extends AppCompatActivity {

    private ListView bookList;
    private List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_my_books);
        this.findViewsById();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.loadMyBooks();
    }

    private void loadMyBooks() {
        this.books = Book.listAll(Book.class);

        if (this.hasBooks()) {
            MyBooksListAdapter adapter = new MyBooksListAdapter(this, books);
            bookList.setAdapter(adapter);

            bookList.setOnItemClickListener(new MyBooksListItemClickListener(this, books));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_my_books, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.my_books_menu_search:
                Intent bookSearch = new Intent(this, BookSearchActivity.class);
                startActivity(bookSearch);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean hasBooks() {
        return this.books != null && this.books.size() > 0;
    }

    private void findViewsById() {
        bookList = (ListView) findViewById(R.id.mybooks_list);
    }
}
