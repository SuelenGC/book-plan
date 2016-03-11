package com.suelengc.bookplan.view.chapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.suelengc.bookplan.R;
import com.suelengc.bookplan.model.Book;
import com.suelengc.bookplan.model.Chapter;

import java.util.List;

public class ChapterListActivity extends AppCompatActivity {

    private ListView chapterList;
    private Book book;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_list_chapter);
        this.findViewsById();

        intent = getIntent();

        if (hasBook()) {
            setBookFromIntent();
            fillScreen();
        }
    }

    public void loadChaptersIntoList() {
        List<Chapter> chapters = Chapter.find(Chapter.class, "book = ?", new String[]{book.getId().toString()});

        ChapterListAdapter adapter = new ChapterListAdapter(this, chapters);
        this.chapterList.setAdapter(adapter);
        this.chapterList.setOnItemLongClickListener(new ChapterItemLongClickListener(this, chapters));
        this.chapterList.setOnItemClickListener(new ChapterItemClickListener(this, chapters));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadChaptersIntoList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chapter_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.chapter_list_menu_add:
                openNewChapterActivity();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openNewChapterActivity() {
        Intent newChapter = new Intent(this, ChapterActivity.class);
        newChapter.putExtra("book_id", this.book.getId());
        startActivity(newChapter);
    }

    private void findViewsById() {
        this.chapterList = (ListView) findViewById(R.id.chapter_list);
    }

    private void setBookFromIntent() {
        Long bookId = intent.getLongExtra("book_id", 0);
        book = (Book) intent.getSerializableExtra("book");
        book.setId(bookId);
    }

    private void fillScreen() {
        this.setTitle("Chapters of " + book.getTitle());
        loadChaptersIntoList();
    }

    private boolean hasBook() {
        return intent.getSerializableExtra("book") != null;
    }
}
