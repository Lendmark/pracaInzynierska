package com.lendemark.inzprojekt.enums;


import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public enum VintageClass {
    ZEROWKA,     KLASA_1,
    KLASA_2,     KLASA_3,
    KLASA_4,     KLASA_5,
    KLASA_6,     KLASA_7,
    KLASA_8;

    public static Optional<VintageClass> parseString(String value) {
        return Arrays.stream(values())
                .filter(ls -> StringUtils.equalsIgnoreCase(ls.name(), value))
                .findFirst();
    }
}
