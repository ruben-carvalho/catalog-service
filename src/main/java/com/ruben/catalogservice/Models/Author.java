package com.ruben.catalogservice.Models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "author", schema = "dev")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "full_original_name")
    private String fullOriginalName;

    @Column(name = "born_date")
    private Date bornDate;

    @Column(name = "born_place")
    private String bornPlace;

    @Column(name = "died_date")
    private Date diedDate;

    @Column(name = "died_place")
    private String diedPlace;

    @Column(name = "about_text")
    private String aboutText;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullOriginalName() {
        return fullOriginalName;
    }

    public void setFullOriginalName(String fullOriginalName) {
        this.fullOriginalName = fullOriginalName;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public String getBornPlace() {
        return bornPlace;
    }

    public void setBornPlace(String bornPlace) {
        this.bornPlace = bornPlace;
    }

    public Date getDiedDate() {
        return diedDate;
    }

    public void setDiedDate(Date diedDate) {
        this.diedDate = diedDate;
    }

    public String getDiedPlace() {
        return diedPlace;
    }

    public void setDiedPlace(String diedPlace) {
        this.diedPlace = diedPlace;
    }

    public String getAboutText() {
        return aboutText;
    }

    public void setAboutText(String aboutText) {
        this.aboutText = aboutText;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}