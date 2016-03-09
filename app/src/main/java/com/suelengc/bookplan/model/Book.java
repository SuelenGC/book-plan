package com.suelengc.bookplan.model;

import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.io.Serializable;

public class Book extends SugarRecord implements Serializable {

    private String title;
    private String subtitle;
    private String author;
    private String isbn_10;
    private Long pageCount;
    private String thumbnail;

    public Book() {}

    public Book(String title, String subtitle, String author, String isbn_10) {
        this.title = title;
        this.subtitle = subtitle;
        this.author = author;
        this.isbn_10 = isbn_10;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getISBN10() {
        return isbn_10;
    }

    public void setISBN10(String isbn_10) {
        this.isbn_10 = isbn_10;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getChaptersCount() {
        return Select.from(Chapter.class).where(Condition.prop("book").eq(this.getId())).count();
    }

    public long getFinishedChaptersCount() {
        return Select.from(Chapter.class)
                .where(Condition.prop("book").eq(this.getId()))
                .and(Condition.prop("finished_chapter").eq(1))
                .count();
    }

    @Override
    public String toString() {
        if (!this.subtitle.isEmpty()) {
            return this.title + " - " + this.subtitle;
        }

        return this.title;
    }

    public boolean hasChapters() {
        return this.getChaptersCount() > 0;
    }
}
