package com.lendemark.inzprojekt.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public enum ClassRooms {
    SALA_1, SALA_2,
    SALA_3, SALA_4,
    SALA_5, SALA_6,
    SALA_7, SALA_8,
    SALA_9, SALA_INFORMATYCZNA,
    HALA, SWIETLICA;

    public static Optional<ClassRooms> parseString(String value) {
        return Arrays.stream(values())
                .filter(ls -> StringUtils.equalsIgnoreCase(ls.name(), value))
                .findFirst();
    }
}
