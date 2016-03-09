package com.suelengc.bookplan.view.chapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import com.suelengc.bookplan.R;
import com.suelengc.bookplan.model.Book;
import com.suelengc.bookplan.model.Chapter;

public class ChapterActivity extends AppCompatActivity {

    private EditText label, title, firstPageNumber;
    private CheckBox finishedChapter;
    private Intent intent;
    private Book book;
    private Chapter chapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        this.findViewsById();

        this.intent = getIntent();

        if (this.hasChapterId()) {
            this.setupForEditChapter();
            return;
        }

        this.setupForNewChapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_chapter, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_chapter_save:
                this.saveChapter();
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupForEditChapter() {
        Long chapterId = (Long) intent.getSerializableExtra("chapter_id");
        this.chapter = Chapter.findById(Chapter.class, chapterId);
        this.book = this.chapter.getBook();

        setTitle("Edit Chapter for " + this.book.getTitle());

        this.fillScreenWithChapterInfo(this.chapter);
    }

    private void fillScreenWithChapterInfo(Chapter chapter) {
        this.title.setText(chapter.getTitle());
        this.label.setText(chapter.getLabel());
        this.firstPageNumber.setText(chapter.getFirstPageNumber().toString());
        this.finishedChapter.setChecked(chapter.isFinished());
    }

    private void setupForNewChapter() {
        if (this.hasBookId()) {
            Long bookId = (Long) this.intent.getSerializableExtra("book_id");
            this.chapter = new Chapter();
            this.book = Book.findById(Book.class, bookId);
            setTitle("New Chapter for " + this.book.getTitle());
        }
    }

    private void findViewsById() {
        this.label = (EditText) findViewById(R.id.add_chapter_dialog_label);
        this.title = (EditText) findViewById(R.id.add_chapter_dialog_title);
        this.firstPageNumber = (EditText) findViewById(R.id.add_chapter_dialog_first_page_number);
        this.finishedChapter = (CheckBox) findViewById(R.id.add_chapter_dialog_finished);
    }

    private void saveChapter() {
        this.updateChapterWithInfoScreen();
        this.chapter.save();
    }

    private boolean hasChapterId() {
        return this.intent.getSerializableExtra("chapter_id") != null;
    }

    private boolean hasBookId() {
        return this.intent.getSerializableExtra("book_id") != null;
    }

    private void updateChapterWithInfoScreen() {
        String newTitle = this.title.getText().toString();
        String newLabel = this.label.getText().toString();
        Integer newFirstPageNumber = Integer.parseInt(this.firstPageNumber.getText().toString());

        this.chapter.setBook(this.book);
        this.chapter.setTitle(newTitle);
        this.chapter.setLabel(newLabel);
        this.chapter.setFirstPageNumber(newFirstPageNumber);
        this.chapter.setFinishedChapter(this.finishedChapter.isChecked());
    }
}
