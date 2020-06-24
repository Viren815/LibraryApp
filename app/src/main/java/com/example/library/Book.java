package com.example.library;

public class Book {

    private int bookid;
    private String bookname;
    private String Author;
    private String image;

    public Book(int bookid, String bookname, String author, String image) {
        this.bookid = bookid;
        this.bookname = bookname;
        this.Author = author;
        this.image = image;
    }

    public int getBookid() {
        return bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public String getAuthor() {
        return Author;
    }

    public String getImage() {
        return image;
    }
}
