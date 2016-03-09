package com.suelengc.bookplan.service.book;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.suelengc.bookplan.model.Book;
import com.suelengc.bookplan.service.infraestructure.WebClient;

import java.io.IOException;
import java.util.List;

public class BookSearchTask extends AsyncTask<String, Void, List<Book>>{

    private ProgressDialog progressDialog;
    private CompleteBookSearchCallback delegate;
    private Context context;

    private String SEARCH_BOOK_URI = "https://www.googleapis.com/books/v1/volumes?maxResults=40&printType=books&q=";

    public BookSearchTask(CompleteBookSearchCallback delegate, Context context) {
        this.delegate = delegate;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context, "Aguarde", "Buscando livros...", true, false);
    }

    @Override
    protected List<Book> doInBackground(String... searchBookQuery) {
        try {
            String queryUri = SEARCH_BOOK_URI + searchBookQuery[0].replace(" ", "+");

            WebClient webClient = new WebClient(queryUri);
            String jsonBook = webClient.get();
            BookConverter bookConverter = new BookConverter();

            return bookConverter.fromJsonToList(jsonBook);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Book> books) {
        delegate.bookSearchComplete(books);
        progressDialog.dismiss();
    }

    public interface CompleteBookSearchCallback {
        void bookSearchComplete(List<Book> books);
    }
}
