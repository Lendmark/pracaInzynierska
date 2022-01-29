package com.lendemark.inzprojekt.lesson.web;

import com.lendemark.inzprojekt.lesson.application.port.LessonPlanUseCase;
import com.lendemark.inzprojekt.lesson.application.port.LessonPlanUseCase.CreateCommandLesson;
import com.lendemark.inzprojekt.lesson.domain.LessonPlan;
import com.lendemark.inzprojekt.enums.Lessons;
import com.lendemark.inzprojekt.enums.ClassRooms;
import com.lendemark.inzprojekt.enums.Day;
import com.lendemark.inzprojekt.enums.VintageClass;
import com.lendemark.inzprojekt.teachers.domain.Teachers;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RequestMapping("/lesson")
@RestController
@AllArgsConstructor
class LessonPlanController {
    private final LessonPlanUseCase planUseCase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LessonPlan> getAll(){
        return planUseCase.findAll();
    }

    @GetMapping("/{lesson}")
    @ResponseStatus(HttpStatus.OK)
    public List<LessonPlan> getByLessons(@PathVariable String lesson){
        return planUseCase.findByLessons(lesson);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addLesson(@Valid @ModelAttribute RestLessonPlanCommand command){
        LessonPlan plan = planUseCase.addLesson(command.toCreateCommand());
        return ResponseEntity.created(CreateUri(plan)).build();

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        planUseCase.removeById(id);
    }



    private URI CreateUri(LessonPlan uriPlan){

        return new CreatedURI("/" + uriPlan.getId().toString()).uri();
    }

    @AllArgsConstructor
    public class CreatedURI {
        private final String path;
        public URI uri() {
            return ServletUriComponentsBuilder.fromCurrentRequestUri().path(path).build().toUri();
        }
    }


    @Data
    private static class RestLessonPlanCommand{

        @NotNull
        private Teachers teacher;

        @NotNull
        private Day day;

        @NotNull
        private Lessons lessons;

        @NotNull
        private ClassRooms classRooms;

        @NotNull
        private VintageClass vintageClass;

        CreateCommandLesson toCreateCommand(){
            return new CreateCommandLesson(teacher, day, lessons, classRooms, vintageClass);
        }
    }
}
