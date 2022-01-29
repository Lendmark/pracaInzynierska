package com.lendemark.inzprojekt.teachers.aplication.port;

import com.lendemark.inzprojekt.teachers.domain.Teachers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Optional;

public interface TeachersUseCase {

    List<Teachers> findAll();

    Optional<Teachers> findById(Long id);

    List<Teachers> findBySecondName(String secondName);

    Teachers addTeacher(CreateTeacherCommand command);

    void removeById(Long id);

    void removeBySecondName(String secondName);

    @Value
    @Builder
    @AllArgsConstructor
    class UpdateTeacherCommand {
        Long id;
        String firstName;
        String secondName;

        public Teachers updateTeacher(Teachers teachers){
            if (firstName != null) {
                teachers.setFirstName(firstName);
            }
            if (secondName != null){
                teachers.setSecondName(secondName);
            }
            return teachers;
        }
    }

    @Value
    class CreateTeacherCommand{
        String firstName;
        String secondName;

        public Teachers toTeacher() {
            return new Teachers(firstName,secondName);
        }
    }
}
