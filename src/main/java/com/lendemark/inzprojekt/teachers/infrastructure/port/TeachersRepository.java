package com.lendemark.inzprojekt.teachers.infrastructure.port;

import com.lendemark.inzprojekt.teachers.domain.Teachers;

import java.util.List;
import java.util.Optional;

public interface TeachersRepository {

    List<Teachers> findAll();

    Teachers save (Teachers teacher);

    Optional<Teachers> findById(Long id);

    void removeById(Long id);
}
