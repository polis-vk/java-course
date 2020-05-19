package ru.mail.polis.classes.lib;


import java.util.List;

public class SimpleCatalog implements ICatalog {

//    @Override
//    public Card searchByTitle(String bookName) {
//        return null;
//    }
//
//    @Override
//    public Card searchByAuthor(String authorName) {
//        return null;
//    }
//
//    @Override
//    public Card searchByPeopleId(String peopleId) {
//        return null;
//    }

    @Override
    public boolean setPermission(String oldPermission, String newPermission) {
        return false;
    }

    @Override
    public Card searchByTitle(String permission, String bookName) {
        return null;
    }

    @Override
    public Card searchByAuthor(String permission, String authorName) {
        return null;
    }

    @Override
    public List<Card> searchByPeopleId(String permission, String peopleId) {
        return null;
    }
}
