package com.suelengc.bookplan.view.mybooks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.suelengc.bookplan.R;
import com.suelengc.bookplan.model.Book;

import java.util.List;

public class MyBooksListAdapter extends BaseAdapter {

    private final Context context;
    private final List<Book> books;
    private Book book;
    private BookItemViewHolder holder;

    public MyBooksListAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return books.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        book = books.get(position);

        convertView = loadLayout(convertView, viewGroup);

        this.holder = (BookItemViewHolder) convertView.getTag();

        this.holder.completedInfos.setText(this.getCompletedInfosText());
        this.holder.title.setText(book.toString());
        this.holder.pageCount.setText(book.getPageCount().toString() + " pages");

        if (!book.getThumbnail().isEmpty()) {
            Picasso.with(context).load(book.getThumbnail()).into(holder.thumbnail);
        }

        return convertView;
    }

    @NonNull
    private View loadLayout(View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_my_books_list_item, viewGroup, false);
            this.holder = new BookItemViewHolder(convertView);
            convertView.setTag(holder);
        }
        return convertView;
    }

    private String getCompletedInfosText() {
        if (book.hasChapters()) {
            long completedPercentage = (this.book.getFinishedChaptersCount() * 100) / this.book.getChaptersCount();
            return "completed " +
                    this.book.getFinishedChaptersCount() +
                    " of " + this.book.getChaptersCount() +
                    " (" + completedPercentage + "%)";
        }
        return "";
    }

    class BookItemViewHolder {
        TextView title;
        TextView pageCount;
        TextView completedInfos;
        ImageView thumbnail;

        BookItemViewHolder(View view) {
            this.title = (TextView) view.findViewById(R.id.my_books_item_title);
            this.pageCount = (TextView) view.findViewById(R.id.my_books_item_page_count);
            this.thumbnail = (ImageView) view.findViewById(R.id.my_books_item_thumbnail);
            this.completedInfos = (TextView) view.findViewById(R.id.my_books_item_completed_infos);
        }
    }
}
