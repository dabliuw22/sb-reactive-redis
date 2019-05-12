
package com.leyton.util;

import java.util.UUID;

public class CacheUtil {

    private CacheUtil() {
    }

    public static String generateKey() {
        return UUID.randomUUID().toString();
    }
}
