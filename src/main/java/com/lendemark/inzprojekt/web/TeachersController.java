package com.lendemark.inzprojekt.web;

import com.lendemark.inzprojekt.teachers.aplication.port.TeachersUseCase;
import com.lendemark.inzprojekt.teachers.aplication.port.TeachersUseCase.CreateTeacherCommand;
import com.lendemark.inzprojekt.teachers.domain.Teachers;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("/teachers")
@RestController
@AllArgsConstructor
class TeachersController {
    private final TeachersUseCase teachersUseCase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Teachers> getAll(){
        return teachersUseCase.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Teachers> findById(@PathVariable Long id){
        return teachersUseCase.findById(id);
    }

    @GetMapping("{secondName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Teachers> findBySecondName(@PathVariable String secondName){
        return teachersUseCase.findBySecondName(secondName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addTeacher(
            @Valid @RequestBody RestTeacherCommand command){
        Teachers teacher = teachersUseCase.addTeacher(command.toCreateCommand());
        return ResponseEntity.created(CreateUri(teacher)).build();

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeacherById(@PathVariable Long id){
        teachersUseCase.removeById(id);
    }

    @DeleteMapping("{secondName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeacherBySecondName(@PathVariable String secondName){
        teachersUseCase.removeBySecondName(secondName);
    }

    private URI CreateUri(Teachers uriTeacher){
        return new CreatedURI("/" + uriTeacher.getId().toString()).uri();
    }

    @AllArgsConstructor
    public class CreatedURI {
        private final String path;
        public URI uri() {
            return ServletUriComponentsBuilder.fromCurrentRequestUri().path(path).build().toUri();
        }
    }

    @Data
    private static class RestTeacherCommand{

        @NotBlank (message = "prosze podac imie")
        private String firstName;

        @NotBlank (message = "prosze podac nazwisko")
        private String secondName;

        CreateTeacherCommand toCreateCommand() {
            return new CreateTeacherCommand(firstName, secondName);
        }
    }
}
