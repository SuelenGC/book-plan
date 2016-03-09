package com.suelengc.bookplan.view.bookdetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.suelengc.bookplan.R;
import com.suelengc.bookplan.model.Chapter;

import java.util.List;

public class BookDetailsChapterResultAdapter extends BaseAdapter {

    private final Context context;
    private List<Chapter> chapters;

    public BookDetailsChapterResultAdapter(Context context, List<Chapter> chapters) {
        this.context = context;
        this.chapters = chapters;
    }

    @Override
    public int getCount() {
        return chapters.size();
    }

    @Override
    public Object getItem(int position) {
        return chapters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Chapter chapter = chapters.get(position);
        ChapterItemViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_book_details_chapter_item, viewGroup, false);
            holder = new ChapterItemViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder = (ChapterItemViewHolder) convertView.getTag();
        holder.chapter.setText(chapter.toString());

        return convertView;
    }

    class ChapterItemViewHolder {
        TextView chapter;

        ChapterItemViewHolder(View view) {
            chapter = (TextView) view.findViewById(R.id.book_details_item_chapter);
        }
    }
}
