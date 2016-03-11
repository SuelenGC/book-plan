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
    private boolean isComplete;

    public Chapter() {}
    private long deadline;

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
            return this.label + ". " + this.title;
        }

        return this.title;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public String getFormattedDeadline() {
        if (this.hasDeadline()) {
            Calendar dateToFinish = Calendar.getInstance();
            dateToFinish.setTimeInMillis(this.deadline);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.format(dateToFinish.getTime());
        }
        return "";
    }

    public boolean hasDeadline() {
        if (this.deadline > 0) {
            return true;
        }

        return false;
    }

    public void setComplete(boolean complete) {
        this.isComplete = complete;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public String getLabel() {
        return label;
    }

    public Integer getFirstPageNumber() {
        return this.firstPageNumber;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setFirstPageNumber(Integer firstPageNumber) {
        this.firstPageNumber = firstPageNumber;
    }
}
