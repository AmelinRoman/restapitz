package com.amelinroman.restapitz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Amelin Roman
 * Исключение, выбрасываемое при некорректных входных данных.
 * @see RuntimeException
 */


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException {

    /**
     * Конструктор класса InvalidInputException.
     *
     * @param message сообщение об ошибке
     */
    public InvalidInputException(String message) {
        super(message);
    }

}
