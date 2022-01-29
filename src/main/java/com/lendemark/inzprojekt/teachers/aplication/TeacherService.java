package com.lendemark.inzprojekt.teachers.aplication;

import com.lendemark.inzprojekt.teachers.aplication.port.TeachersUseCase;
import com.lendemark.inzprojekt.teachers.domain.Teachers;
import com.lendemark.inzprojekt.teachers.infrastructure.port.TeachersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class TeacherService implements TeachersUseCase {
    private final TeachersRepository repository;

    @Override
    public List<Teachers> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Teachers> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Teachers> findBySecondName(String secondName) {
        return repository.findAll()
                .stream()
                .filter(teachers ->
                        teachers.getSecondName().toLowerCase().startsWith(secondName))
                .collect(Collectors.toList());
    }

    @Override
    public Teachers addTeacher(CreateTeacherCommand command) {
        Teachers teacher = command.toTeacher();
        return repository.save(teacher);
    }

    @Override
    public void removeById(Long id) {
        repository.removeById(id);

    }

    @Override
    public void removeBySecondName(String secondName) {
        List<Teachers> teachers = repository.findAll()
                .stream()
                .filter(teacher ->
                        teacher.getSecondName().toLowerCase().startsWith(secondName))
                .collect(Collectors.toList());
        Long id = teachers.stream().findFirst().get().getId();
        repository.removeById(id);

    }
}
