package com.masq.util;

import java.util.UUID;

public class UIDGenerator {

    public static String generate() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

}
