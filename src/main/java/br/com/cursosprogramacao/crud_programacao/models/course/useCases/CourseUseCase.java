package br.com.cursosprogramacao.crud_programacao.models.course.useCases;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursosprogramacao.crud_programacao.models.course.dto.CourseResponseDTO;
import br.com.cursosprogramacao.crud_programacao.models.course.entity.CourseEntity;
import br.com.cursosprogramacao.crud_programacao.models.course.exceptions.CourseFoundException;
import br.com.cursosprogramacao.crud_programacao.models.course.repositories.CourseRepository;

@Service
public class CourseUseCase {
    
    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity execute(CourseEntity courseEntity) {
            this.courseRepository.findByName(courseEntity.getName())
            .ifPresent((course) -> {
                throw new CourseFoundException("Curso já existente");
            });

        return this.courseRepository.save(courseEntity);
    }

    public CourseEntity update(UUID courseId, CourseEntity courseBody) {
        var course = this.courseRepository.findById(courseId).orElseThrow(() -> {
            throw new CourseFoundException("Curso não encontrado.");
        });

        course.setName(courseBody.getName());
        course.setCategory(courseBody.getCategory());
        course.setActive(courseBody.isActive());

        return this.courseRepository.save(course);
    }

    public void delete(UUID courseId) {
         this.courseRepository.findById(courseId)
            .orElseThrow(() -> new CourseFoundException("Curso não encontrado."));
        
        courseRepository.deleteById(courseId);
    }

    public List<CourseResponseDTO> getAllCourses() {
    var courses = this.courseRepository.findAll();
    
    if (courses.isEmpty()) {
        throw new CourseFoundException("Nenhum curso encontrado.");
    }
    
    return courses.stream().map(course -> 
        CourseResponseDTO.builder()
            .category(course.getCategory())
            .name(course.getName())
            .id(course.getId())
            .build()
    ).collect(Collectors.toList());
    }

    public List<CourseResponseDTO> searchByName(String name){
        var courseFound = this.courseRepository.findByNameContainingIgnoreCase(name);

        if (courseFound.isEmpty()){
            throw new CourseFoundException("Curso não encontrado.");
        }

        return courseFound.stream().map(course -> 
        CourseResponseDTO.builder()
            .category(course.getCategory())
            .name(course.getName())
            .id(course.getId())
            .build()
        ).collect(Collectors.toList());
    }

    public List<CourseResponseDTO> searchByCategory(String category){
        var categoryFound = this.courseRepository.findByCategoryContainingIgnoreCase(category);

        if (categoryFound.isEmpty()){
            throw new CourseFoundException("Curso não encontrado.");
        }

        return categoryFound.stream().map(course -> 
        CourseResponseDTO.builder()
            .category(course.getCategory())
            .name(course.getName())
            .id(course.getId())
            .build()
        ).collect(Collectors.toList());
    }
}
