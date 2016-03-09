package com.suelengc.bookplan.view.booksearch;

import android.content.Context;
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

public class BookSeachResultAdapter extends BaseAdapter {

    private final Context context;
    private final List<Book> books;

    public BookSeachResultAdapter(Context context, List<Book> books) {
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Book book = books.get(position);
        BookItemViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_book_search_result_item, viewGroup, false);
            holder = new BookItemViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder = (BookItemViewHolder) convertView.getTag();

        holder.title.setText(book.toString());
        holder.pageCount.setText("Páginas: " + book.getPageCount().toString());

        if (!book.getThumbnail().isEmpty()) {
            Picasso.with(context).load(book.getThumbnail()).into(holder.thumbnail);
        }

        return convertView;
    }

    class BookItemViewHolder {
        TextView title;
        TextView pageCount;
        ImageView thumbnail;

        BookItemViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.search_result_item_title);
            pageCount = (TextView) view.findViewById(R.id.search_result_item_page_count);
            thumbnail = (ImageView) view.findViewById(R.id.search_result_item_thumbnail);
        }
    }
}
