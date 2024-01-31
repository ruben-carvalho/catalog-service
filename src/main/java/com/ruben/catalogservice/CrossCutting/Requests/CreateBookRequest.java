package com.ruben.catalogservice.CrossCutting.Requests;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class CreateBookRequest {
    private String title;
    private String originalTitle;
    private String isbn;
    private String language;
    private String format;
    private Date releaseDate;
    private Date editionDate;
    private String genre;
    private String edition;
    private boolean isSeries;
    private String publisher;
    private String synopsis;
    private double price;
    private double promotionalPrice;
    private String availability;
    private int stockAvailable;
    private Set<String> authors;
    private Set<String> tags;

    public CreateBookRequest(String title, String originalTitle, String isbn, String language, String format, Date releaseDate, Date editionDate, String genre, String edition, boolean isSeries, String publisher, String synopsis, double price, double promotionalPrice, String availability, int stockAvailable, Set<String> authors, Set<String> tags) {
        this.title = title;
        this.originalTitle = originalTitle;
        this.isbn = isbn;
        this.language = language;
        this.format = format;
        this.releaseDate = releaseDate;
        this.editionDate = editionDate;
        this.genre = genre;
        this.edition = edition;
        this.isSeries = isSeries;
        this.publisher = publisher;
        this.synopsis = synopsis;
        this.price = price;
        this.promotionalPrice = promotionalPrice;
        this.availability = availability;
        this.stockAvailable = stockAvailable;
        this.authors = authors;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
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

    public Date getEditionDate() {
        return editionDate;
    }

    public void setEditionDate(Date editionDate) {
        this.editionDate = editionDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public boolean isSeries() {
        return isSeries;
    }

    public void setSeries(boolean series) {
        isSeries = series;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPromotionalPrice() {
        return promotionalPrice;
    }

    public void setPromotionalPrice(double promotionalPrice) {
        this.promotionalPrice = promotionalPrice;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public int getStockAvailable() {
        return stockAvailable;
    }

    public void setStockAvailable(int stockAvailable) {
        this.stockAvailable = stockAvailable;
    }

    public Set<String> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<String> authors) {
        this.authors = authors;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
