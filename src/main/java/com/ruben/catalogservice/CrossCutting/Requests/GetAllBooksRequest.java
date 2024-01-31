package com.ruben.catalogservice.CrossCutting.Requests;

import java.util.Date;

public class GetAllBooksRequest {

    private String title;
    private String isbn;
    private String language;
    private String format;
    private Date releaseDate;
    private String genre;
    private boolean isSeries;

    public GetAllBooksRequest(String title, String isbn, String language, String format, Date releaseDate, String genre, boolean isSeries) {
        this.title = title;
        this.isbn = isbn;
        this.language = language;
        this.format = format;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.isSeries = isSeries;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isSeries() {
        return isSeries;
    }

    public void setSeries(boolean series) {
        isSeries = series;
    }
}
