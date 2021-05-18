package com.mercadolibre.varela_agustina.config;

import com.mercadolibre.varela_agustina.exceptions.*;
import com.newrelic.api.agent.NewRelic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(FechaExceptions.class)
    public ResponseEntity handlerFechaException(FechaExceptions ex) {
        int intCode = 500;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;


        switch (ex.getCode()) {
            case FechaExceptions.FECHA_FORMATO_NO_CORRECTO:
                intCode = 400;
                status = HttpStatus.BAD_REQUEST;
                break;
        }

        ApiError apiError = new ApiError(ex.getCode(), ex.getMessage(), intCode);
        return new ResponseEntity(apiError, status);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiError> noHandlerFoundException(HttpServletRequest req, NoHandlerFoundException ex) {
        ApiError apiError = new ApiError("route_not_found", String.format("Route %s not found", req.getRequestURI()), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(apiError.getStatus())
                .body(apiError);
    }

    @ExceptionHandler(value = {ApiException.class})
    protected ResponseEntity<ApiError> handleApiException(ApiException e) {
        Integer statusCode = e.getStatusCode();
        boolean expected = HttpStatus.INTERNAL_SERVER_ERROR.value() > statusCode;
        NewRelic.noticeError(e, expected);
        if (expected) {
            LOGGER.warn("Internal Api warn. Status Code: " + statusCode, e);
        } else {
            LOGGER.error("Internal Api error. Status Code: " + statusCode, e);
        }

        ApiError apiError = new ApiError(e.getCode(), e.getDescription(), statusCode);
        return ResponseEntity.status(apiError.getStatus())
                .body(apiError);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ApiError> handleUnknownException(Exception e) {
        LOGGER.error("Internal error", e);
        NewRelic.noticeError(e);

        ApiError apiError = new ApiError();

        switch (e.getClass().getSimpleName()) {
            case "DataIntegrityViolationException":
                apiError.setError(ApiExceptionMessages.INCONSISTENT_DATA);
                apiError.setMessage(ApiExceptionMessages.INCONSISTENT_DATA_MSG);
                apiError.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
                break;
            case "HttpRequestMethodNotSupportedException":
                apiError.setError(ApiExceptionMessages.METHOD_NOT_ALLOWED);
                apiError.setMessage(ApiExceptionMessages.METHOD_NOT_ALLOWED_MSG);
                apiError.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
                break;
            case "HttpMessageNotReadableException":
                apiError.setError(ApiExceptionMessages.INVALID_INPUT);
                apiError.setMessage(ApiExceptionMessages.INVALID_INPUT_MSG);
                apiError.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
                break;
            default:
                apiError.setError(ApiExceptionMessages.INTERNAL_SERVER_ERROR);
                apiError.setMessage(ApiExceptionMessages.INTERNAL_SERVER_ERROR_MSG);
                apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return ResponseEntity.status(apiError.getStatus())
                .body(apiError);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity handlerNumberFormatException(NumberFormatException ex) {
        ApiError apiError = new ApiError(ApiExceptionMessages.NUMBER_FORMAT, ApiExceptionMessages.NUMBER_FORMAT_MSG, HttpStatus.UNPROCESSABLE_ENTITY.value());
        return new ResponseEntity(apiError, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity handlerParseException(ParseException ex) {
        ApiError apiError = new ApiError(ApiExceptionMessages.DATE_FORMAT, ApiExceptionMessages.DATE_FORMAT_MSG, HttpStatus.UNPROCESSABLE_ENTITY.value());
        return new ResponseEntity(apiError, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handlerNullPointerException(NullPointerException ex) {
        ApiError apiError = new ApiError(ApiExceptionMessages.INTERNAL_SERVER_ERROR, ApiExceptionMessages.INTERNAL_SERVER_ERROR_MSG, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity handlerDisabledException(DisabledException ex) {
        ApiError apiError = new ApiError(ApiExceptionMessages.USER_DISABLED, ApiExceptionMessages.USER_DISABLED_MSG, HttpStatus.FORBIDDEN.value());
        return new ResponseEntity(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handlerBadCredentialsException(BadCredentialsException ex) {
        ApiError apiError = new ApiError(ApiExceptionMessages.INVALID_CREDENTIALS, ApiExceptionMessages.INVALID_CREDENTIALS_MSG, HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ValidationError> handleException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors()
                .stream()
                .map(this::mapError)
                .collect(Collectors.toList());
    }

    private ValidationError mapError(ObjectError objectError) {
        if (objectError instanceof FieldError) {
            return new ValidationError(((FieldError) objectError).getField(), objectError.getDefaultMessage());
        }
        return new ValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
    }
}