package br.com.cursosprogramacao.crud_programacao.models.course.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDTO {
    
    private String name;
    private String category;
    private UUID id;
}
