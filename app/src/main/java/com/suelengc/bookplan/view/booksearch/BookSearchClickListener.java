package com.suelengc.bookplan.view.booksearch;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.suelengc.bookplan.service.book.BookSearchTask;
import com.suelengc.bookplan.service.infraestructure.NetworkConnection;

public class BookSearchClickListener implements View.OnClickListener {

    private BookSearchActivity bookSearchActivity;
    private EditText bookName;

    public BookSearchClickListener(BookSearchActivity bookSearchActivity, EditText bookName) {
        this.bookSearchActivity = bookSearchActivity;
        this.bookName = bookName;
    }

    @Override
    public void onClick(View view) {
        NetworkConnection networkConnection = new NetworkConnection(bookSearchActivity);

        if (networkConnection.isAvailable()) {
            new BookSearchTask(bookSearchActivity, bookSearchActivity).execute(bookName.getText().toString());
            return;
        }

        Toast.makeText(bookSearchActivity, "Tente mais tarde. Sem conexão.", Toast.LENGTH_LONG).show();
    }
}
