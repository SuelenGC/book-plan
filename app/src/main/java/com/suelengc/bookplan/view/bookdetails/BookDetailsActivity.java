package com.suelengc.bookplan.view.bookdetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.suelengc.bookplan.R;
import com.suelengc.bookplan.model.Book;
import com.suelengc.bookplan.model.Chapter;
import com.suelengc.bookplan.service.chapter.ChapterTask;

import java.util.List;

public class BookDetailsActivity extends AppCompatActivity implements ChapterTask.CompleteBookDetailsCallback {

    private TextView title, isbn, pageCount;
    private ListView chapterList;
    private TextView chapterListEmpty;
    private Book book;
    private List<Chapter> chapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        findViewsById();

        book = (Book) getIntent().getSerializableExtra("book");

        if (book != null) {
            loadBookToView();
            new ChapterTask(this, this).execute(book.getISBN10());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_book_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.book_details_menu_add_to_plan:
                book.save();

                for (Chapter c : this.chapters) {
                    c.setBook(book);
                    c.save();
                }

                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadBookToView() {
        title.setText(book.getTitle());
        isbn.setText(book.getISBN10());
        pageCount.setText(book.getPageCount().toString());
    }

    private void findViewsById() {
        title = (TextView) findViewById(R.id.book_details_title);
        isbn = (TextView) findViewById(R.id.book_details_isbn);
        pageCount = (TextView) findViewById(R.id.book_details_page_count);
        chapterList = (ListView) findViewById(R.id.book_details_chapter_list);
        chapterListEmpty = (TextView) findViewById(R.id.book_details_chapter_list_empty);
    }

    @Override
    public void chapterComplete(List<Chapter> chapters) {
        this.chapters = chapters;

        BookDetailsChapterResultAdapter adapter = new BookDetailsChapterResultAdapter(this, this.chapters);
        chapterList.setAdapter(adapter);
        chapterList.setEmptyView(chapterListEmpty);
    }
}
