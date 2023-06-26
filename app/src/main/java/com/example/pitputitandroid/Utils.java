package com.example.pitputitandroid;

import java.util.UUID;

public class Utils {
    public static String uniqueIdGenerator() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
