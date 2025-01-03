package br.com.cursosprogramacao.crud_programacao.models.course.repositories;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cursosprogramacao.crud_programacao.models.course.entity.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID>{
    Optional<CourseEntity> findByName(String courseName);
    List<CourseEntity> findByNameContainingIgnoreCase(String name);
    List<CourseEntity> findByCategoryContainingIgnoreCase(String category);
    List<CourseEntity> findAll();
    void deleteById(UUID courseId);
}
