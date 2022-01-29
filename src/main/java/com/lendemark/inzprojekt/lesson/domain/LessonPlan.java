package com.lendemark.inzprojekt.lesson.domain;


import com.lendemark.inzprojekt.enums.*;
import com.lendemark.inzprojekt.teachers.domain.Teachers;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public
class LessonPlan {
    private Long id;
    private Teachers teacher;
    private Day day;
    private Lessons lessons;
    private ClassRooms classRooms;
    private VintageClass vintageClass;

    public LessonPlan(Teachers teachers, Day day, Lessons lessons, ClassRooms classRooms, VintageClass vintageClass){
        this.teacher = teachers;
        this.day = day;
        this.lessons = lessons;
        this.classRooms = classRooms;
        this.vintageClass = vintageClass;
    }

}
