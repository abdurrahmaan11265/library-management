package com.library.library_management.dto;

public class BookSummary {

    private final Long bookId;
    private final String title;
    private final String genre;
    private final int publishedYear;
    private final Long authorId;
    private final String authorName;
    private final String authorNationality;

    public BookSummary(Long bookId, String title, String genre, int publishedYear,
                       Long authorId, String authorName, String authorNationality) {
        this.bookId = bookId;
        this.title = title;
        this.genre = genre;
        this.publishedYear = publishedYear;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorNationality = authorNationality;
    }

    public Long getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getPublishedYear() { return publishedYear; }
    public Long getAuthorId() { return authorId; }
    public String getAuthorName() { return authorName; }
    public String getAuthorNationality() { return authorNationality; }
}
