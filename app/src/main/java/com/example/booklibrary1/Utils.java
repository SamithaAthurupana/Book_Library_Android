package com.example.booklibrary1;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;

    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> watToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favoriteBooks;

    private Utils() {
        if (null == allBooks) {
            allBooks = new ArrayList<>();
            initData();
        }
        if (null == alreadyReadBooks) {
            alreadyReadBooks = new ArrayList<>();
        }
        if (null == watToReadBooks) {
            watToReadBooks = new ArrayList<>();
        }
        if (null == currentlyReadingBooks) {
            currentlyReadingBooks = new ArrayList<>();
        }
        if (null == favoriteBooks) {
            favoriteBooks = new ArrayList<>();
        }

    }

    private void initData() {

        allBooks.add(new Book(1, " Monstress, Volume Two: The Blood (Book #2 of The Monstress Series)", "Majorie Liu (author) and Sana Takeda (artist)", 1350, "https://www.barnesandnoble.com/blog/wp-content/uploads/2019/05/monstressbookonecover-690x1024-1.jpg",
                "the book-cover of the graphic-novel Monstress – Volume Two: The Blood.", "Long Desc"));
        allBooks.add(new Book(2, "Locke & Key, Vol. 1", "dhsidhnzs sdsh" +
                "Joe Hill", 452, "https://images-na.ssl-images-amazon.com/images/I/81Ix49xdzSL.jpg",
                " \"modern masterpiece\" by The A.V. Club, Locke & Key tells a sprawling tale of magic and family, legacy and grief, good and evil.", "Long Desc"));


    }

    public static Utils getInstance() {
        if (null != instance) {
            return instance;
        } else {
            instance = new Utils();
            return instance;
        }
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWatToReadBooks() {
        return watToReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public Book getBookId(int id) {
        for (Book b : allBooks) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book) {
        return alreadyReadBooks.add(book);
    }

    public boolean addToWantToRead(Book book) {
        return alreadyReadBooks.add(book);
    }
    public boolean addToCurrentlyReading(Book book) {
        return currentlyReadingBooks.add(book);
    }
    public boolean addToFavorite(Book book) {
        return favoriteBooks.add(book);
    }
}
