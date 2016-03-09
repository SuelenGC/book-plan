package com.suelengc.bookplan.service.chapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.suelengc.bookplan.model.Chapter;
import com.suelengc.bookplan.service.infraestructure.WebClient;

import java.io.IOException;
import java.util.List;

public class ChapterTask extends AsyncTask<String, Void, List<Chapter>>{

    private ProgressDialog progressDialog;
    private CompleteBookDetailsCallback delegate;
    private Context context;

    private String BOOK_DETAILS_URI = "https://openlibrary.org/api/books?jscmd=details&format=json&bibkeys=ISBN:";

    public ChapterTask(CompleteBookDetailsCallback delegate, Context context) {
        this.delegate = delegate;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context, "Aguarde", "Buscando capítulos...", true, false);
    }

    @Override
    protected List<Chapter> doInBackground(String... isbn) {
        try {
            String queryUri = BOOK_DETAILS_URI + isbn[0];

            WebClient webClient = new WebClient(queryUri);
            String jsonBook = webClient.get();

            ChapterConverter chapterConverter = new ChapterConverter();
            return chapterConverter.fromJsonToList(jsonBook, isbn[0]);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Chapter> chapters) {
        delegate.chapterComplete(chapters);
        progressDialog.dismiss();
    }

    public interface CompleteBookDetailsCallback {
        void chapterComplete(List<Chapter> chapters);
    }
}
