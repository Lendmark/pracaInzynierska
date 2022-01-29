package com.lendemark.inzprojekt.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public enum Day {
    PONIEDZIALEK, WTOREK, SRODA, CZWARTEK, PIATEK;

    public static Optional<Day> parseString(String value) {
        return Arrays.stream(values())
                .filter(ls -> StringUtils.equalsIgnoreCase(ls.name(), value))
                .findFirst();
    }
}
