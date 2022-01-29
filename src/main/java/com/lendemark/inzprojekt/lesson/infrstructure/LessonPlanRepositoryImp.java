package com.lendemark.inzprojekt.lesson.infrstructure;

import com.lendemark.inzprojekt.lesson.domain.LessonPlan;
import com.lendemark.inzprojekt.lesson.infrstructure.port.LessonPlanRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
class LessonPlanRepositoryImp implements LessonPlanRepository {
    private final Map<Long, LessonPlan> storage = new ConcurrentHashMap<>();
    private final AtomicLong ID_NEXT_VALUE = new AtomicLong(0L);

    @Override
    public List<LessonPlan> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public LessonPlan save(LessonPlan plan) {
        if(plan.getId() != null){
            storage.put(plan.getId(), plan);
        } else{
            long nextId = nextId();
            plan.setId(nextId);
            storage.put(nextId,plan);
        }
        return plan;
    }

    @Override
    public Optional<LessonPlan> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void removeById(Long id) {
        storage.remove(id);
    }

    @Override
    public void remove(LessonPlan lessonPlan) {
        storage.remove(lessonPlan);
    }

    private long nextId() {
        return ID_NEXT_VALUE.getAndIncrement();
    }

}
