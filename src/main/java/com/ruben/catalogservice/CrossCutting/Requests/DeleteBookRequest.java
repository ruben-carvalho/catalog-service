package com.ruben.catalogservice.CrossCutting.Requests;

import java.util.Date;

public class DeleteBookRequest {

    private int bookId;

    public DeleteBookRequest(int bookId) {
        this.bookId = bookId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
