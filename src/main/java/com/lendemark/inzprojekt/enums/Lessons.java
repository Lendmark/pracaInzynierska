package com.lendemark.inzprojekt.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public enum Lessons {
    MATEMATYKA, POLSKI, ANGIELSKI, NIEMIECKI,
    GEOGRAFIA, FIZYKA, BIOLOGIA, CHEMIA,
    HISTORA, EDUKACJA_WCZESNOSZKOLNA, INFORMATYKA,
    WOS, WDZ, WF, RELIGIA, GODZINA_WYCHOWACZA;

    public static Optional<Lessons> parseString(String value){
        return Arrays.stream(values())
                .filter(ls -> StringUtils.equalsIgnoreCase(ls.name(),value))
                .findFirst();
    }


}
