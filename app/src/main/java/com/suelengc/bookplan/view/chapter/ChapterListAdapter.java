package com.suelengc.bookplan.view.chapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.suelengc.bookplan.R;
import com.suelengc.bookplan.model.Chapter;

import java.util.List;

public class ChapterListAdapter extends BaseAdapter {

    private final Context context;
    private final List<Chapter> chapters;

    public ChapterListAdapter(Context context, List<Chapter> chapters) {
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
        return chapters.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Chapter chapter = chapters.get(position);

        convertView = loadItemLayout(viewGroup);
        ChapterItemViewHolder holder = new ChapterItemViewHolder(convertView);

        holder.title.setText(chapter.toString());

        if (chapter.isFinished()) {
            holder.finished.setVisibility(View.VISIBLE);
        }

        if (chapter.hasDateToFinish()) {
            holder.dateToFinish.setVisibility(View.VISIBLE);
            holder.dateToFinish.setText("Expected to finish at " + chapter.getFormattedDateToFinish());
        }

        return convertView;
    }

    private View loadItemLayout(ViewGroup viewGroup) {
        View convertView;LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.activity_list_chapter_result_item, viewGroup, false);
        return convertView;
    }

    class ChapterItemViewHolder {
        TextView title;
        TextView dateToFinish;
        ImageView finished;

        ChapterItemViewHolder(View view) {
            this.title = (TextView) view.findViewById(R.id.chapter_item_title);
            this.dateToFinish = (TextView) view.findViewById(R.id.chapter_item_date_to_finish);
            this.finished = (ImageView) view.findViewById(R.id.chapter_item_finished);
        }
    }
}
