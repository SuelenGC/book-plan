package com.suelengc.bookplan.view.chapter;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;

import com.suelengc.bookplan.model.Chapter;
import com.suelengc.bookplan.view.commondialog.DatePickerFragment;

import java.util.Calendar;
import java.util.List;

public class ChapterItemLongClickListener implements AdapterView.OnItemLongClickListener, DatePickerDialog.OnDateSetListener {

    private final ChapterListActivity chapterListActivity;
    private final List<Chapter> chapters;
    private Chapter currentChapter;

    public ChapterItemLongClickListener(ChapterListActivity chapterListActivity, List<Chapter> chapters) {
        this.chapterListActivity = chapterListActivity;
        this.chapters = chapters;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
        this.currentChapter = this.chapters.get(position);
        showDateDialog();
        return true;
    }

    private void showDateDialog() {
        DatePickerFragment datePickerDialog = DatePickerFragment.newInstance(this);
        datePickerDialog.show(this.chapterListActivity.getSupportFragmentManager(), null);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar dateSelected = Calendar.getInstance();
        dateSelected.set(year, month, day);

        this.currentChapter.setDateToFinish(dateSelected.getTimeInMillis());
        this.currentChapter.save();
        this.chapterListActivity.loadChaptersIntoList();
    }
}
