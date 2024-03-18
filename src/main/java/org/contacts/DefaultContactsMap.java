package org.contacts;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DefaultContactsMap implements ContactsMap{
    Map<String, Contact> contactMap = new HashMap<>();
    @Override
    public Map<String, Contact> getMap() {
        return contactMap;
    }
}
