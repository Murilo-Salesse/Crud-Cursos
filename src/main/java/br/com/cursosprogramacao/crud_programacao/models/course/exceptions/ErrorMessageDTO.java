package br.com.cursosprogramacao.crud_programacao.models.course.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {
    

    private String message;
    private String field;
}
