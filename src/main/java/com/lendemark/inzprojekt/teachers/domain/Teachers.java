package com.lendemark.inzprojekt.teachers.domain;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Teachers {
    private Long id;
    private String firstName;
    private String secondName;

    public Teachers(String firstName, String secondName) {
    }
}
