package com.example.courseapi.controller;

import com.example.courseapi.model.CourseInstance;
import com.example.courseapi.repository.CourseInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instances")
public class CourseInstanceController {

    @Autowired
    private CourseInstanceRepository courseInstanceRepository;

    @PostMapping
    public ResponseEntity<CourseInstance> createInstance(@RequestBody CourseInstance instance) {
        CourseInstance createdInstance = courseInstanceRepository.save(instance);
        return new ResponseEntity<>(createdInstance, HttpStatus.CREATED);
    }

    @GetMapping("/{year}/{semester}")
    public List<CourseInstance> getInstances(@PathVariable int year, @PathVariable int semester) {
        return courseInstanceRepository.findByYearAndSemester(year, semester);
    }

    @GetMapping("/{year}/{semester}/{id}")
    public ResponseEntity<CourseInstance> getInstanceById(@PathVariable int year,
                                                           @PathVariable int semester,
                                                           @PathVariable Long id) {
        return courseInstanceRepository.findById(id)
            .map(instance -> new ResponseEntity<>(instance, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{year}/{semester}/{id}")
    public ResponseEntity<Void> deleteInstance(@PathVariable int year,
                                               @PathVariable int semester,
                                               @PathVariable Long id) {
        if (courseInstanceRepository.existsById(id)) {
            courseInstanceRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
