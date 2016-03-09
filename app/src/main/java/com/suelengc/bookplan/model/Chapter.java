package com.suelengc.bookplan.model;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Chapter extends SugarRecord implements Serializable {

    private Integer firstPageNumber;
    private String label;
    private String title;
    private Book book;
    private boolean finishedChapter;

    public Chapter() {}
    private long dateToFinish;

    public Chapter(String title, String label, Integer firstPageNumber) {
        this.title = title;
        this.label = label;
        this.firstPageNumber = firstPageNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public String toString() {
        if (!this.label.isEmpty()) {
            return this.label + ". " + this.title + " (first page: " + this.firstPageNumber + ")";
        }

        return this.title + " (first page: " + this.firstPageNumber + ")";
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setDateToFinish(long dateToFinish) {
        this.dateToFinish = dateToFinish;
    }

    public String getFormattedDateToFinish() {
        Calendar dateToFinish = Calendar.getInstance();
        dateToFinish.setTimeInMillis(this.dateToFinish);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(dateToFinish.getTime());
    }

    public boolean hasDateToFinish() {
        if (this.dateToFinish > 0) {
            return true;
        }

        return false;
    }

    public void setFinishedChapter(boolean finishedChapter) {
        this.finishedChapter = finishedChapter;
    }

    public boolean isFinished() {
        return finishedChapter;
    }

    public String getLabel() {
        return label;
    }

    public Integer getFirstPageNumber() {
        return firstPageNumber;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setFirstPageNumber(Integer firstPageNumber) {
        this.firstPageNumber = firstPageNumber;
    }
}
