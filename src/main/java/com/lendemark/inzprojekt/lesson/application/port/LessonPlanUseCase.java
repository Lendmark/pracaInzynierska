package com.lendemark.inzprojekt.lesson.application.port;

import com.lendemark.inzprojekt.lesson.infrstructure.port.LessonPlanRepository;
import com.lendemark.inzprojekt.enums.*;
import com.lendemark.inzprojekt.lesson.domain.LessonPlan;
import com.lendemark.inzprojekt.enums.Lessons;
import com.lendemark.inzprojekt.teachers.domain.Teachers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Optional;

public interface LessonPlanUseCase {

    List<LessonPlan> findAll();

    List<LessonPlan> findByLessons(String lesson);

    List<LessonPlan> findByTeacher(String teachers);

    List<LessonPlan> findByClass(String vintageClass);

    LessonPlan addLesson(CreateCommandLesson createCommandLesson);

    void removeByTeacherAndLessonAndDay(String teachers, String lessons, String day);

    void removeById(Long id);

    Optional<LessonPlanRepository> updatePlan(UpdateCommandLesson updateCommandLesson);

    @Value
    class CreateCommandLesson{
        Teachers teacher;
        Day day;
        Lessons lessons;
        ClassRooms classRooms;
        VintageClass vintageClass;

        public LessonPlan toLessonPlan(){
            return new LessonPlan(teacher, day, lessons, classRooms,vintageClass);
        }
    }

    @Value
    @Builder
    @AllArgsConstructor
    class UpdateCommandLesson {
        Long id;
        Teachers teacher;
        Day day;
        Lessons lessons;
        ClassRooms classRooms;
        VintageClass vintageClass;

        public LessonPlan update(LessonPlan lessonPlan) {
            if (teacher != null){
                lessonPlan.setTeacher(teacher);
            }
            if (day != null){
                lessonPlan.setDay(day);
            }
            if (lessons != null){
                lessonPlan.setLessons(lessons);
            }
            if (classRooms != null){
                lessonPlan.setClassRooms(classRooms);
            }
            if (vintageClass != null){
                lessonPlan.setVintageClass(vintageClass);
            }
            return lessonPlan;
        }
    }
}
