package com.pri.ai.springai.error;

import com.theokanning.openai.OpenAiHttpException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@RestControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

    public static final String OPEN_AI_CLIENT_RAISED_EXCEPTION = "Open AI client raised exception: ";

    @ExceptionHandler(OpenAiHttpException.class)
    public String handleOpenAiHttpException(OpenAiHttpException ex) {
        HttpStatus status = Optional
                .ofNullable(HttpStatus.resolve(ex.statusCode))
                .orElse(HttpStatus.BAD_REQUEST);

        return "Http Status error: " + status + OPEN_AI_CLIENT_RAISED_EXCEPTION + ex.getMessage();
    }
}
