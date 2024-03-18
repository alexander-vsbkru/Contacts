package org.contacts;

import java.util.HashMap;
import java.util.Map;

public interface ContactsMap {
    Map<String, Contact> contactsMap = new HashMap<>();
    Map<String, Contact> getMap();
}
