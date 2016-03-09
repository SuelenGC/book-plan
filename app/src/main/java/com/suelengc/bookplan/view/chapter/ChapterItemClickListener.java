package com.suelengc.bookplan.view.chapter;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.suelengc.bookplan.model.Chapter;

import java.util.List;

public class ChapterItemClickListener implements AdapterView.OnItemClickListener {

    private final ChapterListActivity chapterListActivity;
    private final List<Chapter> chapters;

    public ChapterItemClickListener(ChapterListActivity chapterListActivity, List<Chapter> chapters) {
        this.chapterListActivity = chapterListActivity;
        this.chapters = chapters;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Chapter chapter = this.chapters.get(position);

        Intent editChapter = new Intent(this.chapterListActivity, ChapterActivity.class);
        editChapter.putExtra("chapter_id", chapter.getId());
        this.chapterListActivity.startActivity(editChapter);
    }
}
