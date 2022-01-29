package com.lendemark.inzprojekt.lesson.application;


import com.lendemark.inzprojekt.lesson.domain.LessonPlan;
import com.lendemark.inzprojekt.lesson.application.port.LessonPlanUseCase;
import com.lendemark.inzprojekt.lesson.infrstructure.port.LessonPlanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class LessonPlanService implements LessonPlanUseCase {
    private final LessonPlanRepository repository;

    @Override
    public List<LessonPlan> findAll() {
        return repository.findAll();
    }

    @Override
    public List<LessonPlan> findByLessons(String lesson) {
        return repository.findAll()
                 .stream()
                 .filter(lessonPlan -> lessonPlan.getLessons().equals(lesson))
                .collect(Collectors.toList());
    }

    @Override
    public List<LessonPlan> findByTeacher(String teachers) {
        return   repository.findAll()
                .stream()
                .filter(teacher -> teacher.getTeacher().getSecondName().toLowerCase().startsWith(teachers))
                .collect(Collectors.toList());
    }

    @Override
    public List<LessonPlan> findByClass(String vintageClass) {
        return repository.findAll()
                .stream()
                .filter(clas -> clas.getClass().equals(vintageClass))
                .collect(Collectors.toList());
    }

    @Override
    public LessonPlan addLesson(CreateCommandLesson createCommandLesson) {
        LessonPlan lessonPlan = createCommandLesson.toLessonPlan();
        return repository.save(lessonPlan);
    }

    @Override
    public void removeByTeacherAndLessonAndDay(String teachers, String lessons, String day) {
        List<LessonPlan> lplan= repository.findAll()
                .stream()
                .filter(teacher -> teacher.getTeacher().getSecondName().startsWith(teachers))
                .filter(lesson -> lesson.getLessons().equals(lesson))
                .filter(days -> days.getDay().equals(day))
                .collect(Collectors.toList());

        repository.remove((LessonPlan) lplan);

    }

    @Override
    public void removeById(Long id) {
        repository.removeById(id);
    }

    @Override
    public Optional<LessonPlanRepository> updatePlan(UpdateCommandLesson updateCommandLesson) {
        return repository.findById(updateCommandLesson.getId())
                .map(plan ->{
                    LessonPlan upadatePlan = updateCommandLesson.update(plan);
                    repository.save(upadatePlan);
                    return repository;
                });
    }


}
