package com.lendemark.inzprojekt.lesson.infrstructure.port;

import com.lendemark.inzprojekt.lesson.domain.LessonPlan;

import java.util.List;
import java.util.Optional;

public interface LessonPlanRepository {

    List<LessonPlan> findAll();

    LessonPlan save(LessonPlan lessonPlan);

    Optional<LessonPlan> findById(Long id);

    void removeById(Long id);

    void remove(LessonPlan lessonPlan);
}
