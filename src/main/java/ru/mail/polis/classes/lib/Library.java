package ru.mail.polis.classes.lib;

public class Library {

    private final Book[] storage = new Book[10000];
    private final Admin admin;
    private final Librarian librarian;
    private ICatalog catalog;

    private boolean closed;

    public Library(Admin admin, Librarian librarian) {
        this.admin = admin;
        this.librarian = librarian;
    }

    public Book getBook(Customer customer, String title) {
        if (closed) {
            return null;
        }
        int bookCounts = checkBookCount(customer);
        if (bookCounts == 0) {
            return null;
        }
        ICatalog.Card card = catalog.searchByTitle(librarian.getPermission(), title);
        return search(card);
    }

    private int checkBookCount(Customer customer) {
        return librarian.checkBookCount(catalog.searchByPeopleId(librarian.getPermission(), customer.getPeopleId()));
    }

    private Book search(ICatalog.Card card) {
        if (card.getPosition() == null) {
            return null;
        }
        return null;
    }

    public Book[] getBooks(People people) {
        if (closed) {
            return null;
        }
        return null;
    }

    public void returnBook(Book book, People people) {
        if (closed) {
            return;
        }
    }

    public void returnBooks(Book[] books, People people) {
        if (closed) {
            return;
        }
    }

    public String[] getBooksNames() {
        if (closed) {
            return null;
        }
        return null;
    }

    public void addBook(Book book) {
        if (closed) {
            return;
        }
    }

    public void close(People people) {
        if (people.equals(admin)) {
            closed = true;
        } else {
            System.out.println("У вас нет ключа");
        }
    }


    public void open(People people) {
        if (people.equals(admin)) {
            closed = false;
        } else {
            System.out.println("У вас нет ключа");
        }
    }



}
