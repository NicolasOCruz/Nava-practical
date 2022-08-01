package com.example.api.util;

import java.util.Collection;

public class FunctionsUtil {

    public static boolean isEmpty(Collection list) {
        return (list == null || list.isEmpty() || list.size() <= 0);
    }

    public static boolean isEmpty(String text) {
        return (text == null || text.trim().equals(""));
    }
}