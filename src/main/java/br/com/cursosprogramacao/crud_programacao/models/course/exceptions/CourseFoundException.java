package br.com.cursosprogramacao.crud_programacao.models.course.exceptions;

public class CourseFoundException extends RuntimeException {
    public CourseFoundException(String message) {
        super(message);
    }
}
