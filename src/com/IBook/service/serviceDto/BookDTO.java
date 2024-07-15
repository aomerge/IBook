package com.IBook.service.serviceDto;

public interface BookDTO {
    public String getBooks();
    public String getBookById(String id);
    public String getSearchedBooks(String search);
    public String getBooksPage(String page);
}
