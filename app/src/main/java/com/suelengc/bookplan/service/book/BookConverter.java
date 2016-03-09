package com.suelengc.bookplan.service.book;

import com.suelengc.bookplan.model.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class BookConverter {

    public List<Book> fromJsonToList(String inputJson) {
        List<Book> books = new ArrayList<Book>();

        try {
            JSONObject jsonObject = new JSONObject(inputJson);
            JSONArray jsonArray = jsonObject.getJSONArray("items");

            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject bookItem = jsonArray.getJSONObject(i);
                JSONObject volumeInfo = bookItem.getJSONObject("volumeInfo");

                Book book = getBookFromVolumeInfo(volumeInfo);
                books.add(book);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return books;
    }

    private Book getBookFromVolumeInfo(JSONObject volumeInfo) throws JSONException {
        String bookTitle = "";
        if (volumeInfo.has("title")) {
           bookTitle = volumeInfo.getString("title");
        }

        String bookSubtitle = "";
        if (volumeInfo.has("subtitle")) {
            bookSubtitle = volumeInfo.getString("subtitle");
        }

        String author = "";
        if (volumeInfo.has("authors")) {
            JSONArray authors = volumeInfo.getJSONArray("authors");
            if (authors.length() > 0) {
                author = authors.get(0).toString();
            }
        }

        long pageCount = 0;
        if (volumeInfo.has("pageCount")) {
            pageCount = volumeInfo.getLong("pageCount");
        }

        String isbn = getISBNFromVolumeInfo(volumeInfo);
        String thumbnail = getThumbNailFromVolumeInfo(volumeInfo);

        Book book = new Book(bookTitle, bookSubtitle, author, isbn);
        book.setPageCount(pageCount);
        book.setThumbnail(thumbnail);
        book.setISBN10(isbn);

        return book;
    }

    private String getISBNFromVolumeInfo(JSONObject volumeInfo) throws JSONException {
        if (volumeInfo.has("industryIdentifiers")) {
            JSONArray industryIdentifiers = volumeInfo.getJSONArray("industryIdentifiers");

            for (int i = 0; i < industryIdentifiers.length(); i++) {
                JSONObject industryIdentifier = industryIdentifiers.getJSONObject(i);

                if (industryIdentifier.has("type") && industryIdentifier.get("type").equals("ISBN_10")) {
                    return industryIdentifier.getString("identifier");
                }
            }
        }

        return "";
    }

    private String getThumbNailFromVolumeInfo(JSONObject volumeInfo) throws JSONException {
        if (volumeInfo.has("imageLinks")) {
            JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");

            if (imageLinks.has("thumbnail")) {
                return imageLinks.getString("thumbnail");
            }

            if (imageLinks.has("smallThumbnail")) {
                return imageLinks.getString("smallThumbnail");
            }
        }
        return "";
    }
}
