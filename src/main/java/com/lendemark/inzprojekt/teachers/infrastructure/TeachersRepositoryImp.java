package com.lendemark.inzprojekt.teachers.infrastructure;


import com.lendemark.inzprojekt.teachers.domain.Teachers;
import com.lendemark.inzprojekt.teachers.infrastructure.port.TeachersRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
class TeachersRepositoryImp implements TeachersRepository {
    private final Map<Long, Teachers> storage = new ConcurrentHashMap<>();
    private final AtomicLong ID_NEXT_VALUE = new AtomicLong(0L);

    @Override
    public List<Teachers> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Teachers save(Teachers teacher) {
        if(teacher.getId() != null){
            storage.put(teacher.getId(), teacher);
        } else {
            long nextId = nextId();
            teacher.setId(nextId);
            storage.put(nextId, teacher);
        }
        return teacher;
    }

    @Override
    public Optional<Teachers> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void removeById(Long id) {
        storage.remove(id);
    }

    private long nextId() {
        return ID_NEXT_VALUE.getAndIncrement();
    }
}
