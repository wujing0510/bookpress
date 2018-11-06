package com.yikeo.bookpress.modules.library.model;

import com.yikeo.bookpress.common.model.BaseModel;

import javax.persistence.*;
import java.util.Date;

/**
 * @author WuJing
 * @since 2018-08-09
 */

@Entity
@Table(name = "LIB_BOOK")
public class Book extends BaseModel {

    @Column(length = 512)
    private String title;

    @Column(length = 128)
    private String author;

    @Lob
    @Basic(fetch=FetchType.LAZY)
    @Column(columnDefinition="TEXT")
    private String description;

    @Column(length = 1024)
    private String cover;

    @Column(length = 10)
    private String language;

    private Date date;

    @Column
    private String subject;

    @Column
    private String contributor;

    @Column(length = 50)
    private String published;

    @Column
    private String publisher;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", cover='" + cover + '\'' +
                ", language='" + language + '\'' +
                ", date='" + date + '\'' +
                ", subject='" + subject + '\'' +
                ", contributor='" + contributor + '\'' +
                ", published='" + published + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
