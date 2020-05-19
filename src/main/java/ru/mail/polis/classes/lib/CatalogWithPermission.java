package ru.mail.polis.classes.lib;

import java.util.List;

public class CatalogWithPermission implements ICatalog {

    private String permission;

    @Override
    public boolean setPermission(String oldPermission, String newPermission) {
        if (permission.equals(oldPermission)) {
            permission = newPermission;
            return true;
        }
        return false;
    }

    @Override
    public Card searchByTitle(String permission, String bookName) {
        if (!this.permission.equals(permission)) {
            return null;
        }
        return null;
    }

    @Override
    public Card searchByAuthor(String permission, String authorName) {
        if (!this.permission.equals(permission)) {
            return null;
        }
        return null;
    }

    @Override
    public List<Card> searchByPeopleId(String permission, String peopleId) {
        if (!this.permission.equals(permission)) {
            return null;
        }
        return null;
    }
}
