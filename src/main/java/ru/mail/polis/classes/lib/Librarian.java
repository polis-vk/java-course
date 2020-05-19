package ru.mail.polis.classes.lib;

import java.util.List;

public class Librarian extends People {

    private String permission;

    public Librarian(String name, int age) {
        super(name, age);
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return null;
    }

    public int checkBookCount(List<ICatalog.Card> cards) {
        int badBookCount = 0;
        for (ICatalog.Card card : cards) {
            if (card.getDate() + card.getPeriod() < System.currentTimeMillis()) {
                badBookCount++;
            }
        }
        if (badBookCount == 0) {
            return 10;
        } else if ((double)badBookCount / cards.size() < 0.1) {
            return 1;
        }
        return 0;
    }
}
