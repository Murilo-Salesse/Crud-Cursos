package br.com.cursosprogramacao.crud_programacao.models.course.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursosprogramacao.crud_programacao.models.course.dto.CourseRequestDTO;
import br.com.cursosprogramacao.crud_programacao.models.course.entity.CourseEntity;
import br.com.cursosprogramacao.crud_programacao.models.course.exceptions.CourseFoundException;
import br.com.cursosprogramacao.crud_programacao.models.course.useCases.CourseUseCase;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/course")
public class CourseController {
    
    @Autowired
    private CourseUseCase courseUseCase;

    @PostMapping("/new")
    public ResponseEntity<Object> create(@Valid @RequestBody CourseEntity courseEntity) {
        try {
            var result = this.courseUseCase.execute(courseEntity);
            return ResponseEntity.ok().body(result);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAll") 
    public ResponseEntity<Object> getAllCourses() {
        try {
            var courses = this.courseUseCase.getAllCourses();
            return ResponseEntity.ok().body(courses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }    

    @GetMapping("/getByName") 
    public ResponseEntity<Object> getCourseByName(@RequestParam String name) {
        try {
            var courses = this.courseUseCase.searchByName(name);
            return ResponseEntity.ok().body(courses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }   

    @GetMapping("/getByCategory") 
    public ResponseEntity<Object> getCourseByCategory(@RequestParam String category) {
        try {
            var courses = this.courseUseCase.searchByCategory(category);
            return ResponseEntity.ok().body(courses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }  

    @PutMapping("/updateCourse/{id}")
    public ResponseEntity<CourseEntity> updateCourse(@PathVariable UUID id, @RequestBody CourseEntity courseDetails) {
        try {
            CourseEntity updatedCourse = courseUseCase.update(id, courseDetails);
            return ResponseEntity.ok(updatedCourse);  
        } catch (CourseFoundException e) {
            return ResponseEntity.badRequest().body(null); 
        }
    }

    @DeleteMapping("/deleteCourse")
    public ResponseEntity<Object> deleteCourse(@RequestBody CourseRequestDTO requestId) {
        try {
            this.courseUseCase.delete(requestId.getId());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
