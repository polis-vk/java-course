package ru.mail.polis.classes.lib;

import java.util.List;

public interface ICatalog {

    class Card {
        private final String bookId;
        private final String position;
        private String peopleId;
        private long date;
        private long period;

        public Card(String bookId, String position) {
            this.bookId = bookId;
            this.position = position;
        }

        public String getBookId() {
            return bookId;
        }

        public String getPosition() {
            return position;
        }

        public String getPeopleId() {
            return peopleId;
        }

        public long getDate() {
            return date;
        }

        public long getPeriod() {
            return period;
        }
    }

    boolean setPermission(String oldPermission, String newPermission);

    SimpleCatalog.Card searchByTitle(String permission, String bookName);

    SimpleCatalog.Card searchByAuthor(String permission, String authorName);

    List<Card> searchByPeopleId(String permission, String peopleId);
}
