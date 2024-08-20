package com.example.courseapi.repository;

import com.example.courseapi.model.CourseInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseInstanceRepository extends JpaRepository<CourseInstance, Long> {
    List<CourseInstance> findByYearAndSemester(int year, int semester);
}
