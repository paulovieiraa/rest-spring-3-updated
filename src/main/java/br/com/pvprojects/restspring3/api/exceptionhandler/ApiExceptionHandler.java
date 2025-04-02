package br.com.pvprojects.restspring3.api.exceptionhandler;

import br.com.pvprojects.restspring3.domain.exception.BusinessException;
import br.com.pvprojects.restspring3.domain.exception.EntityNotFoundException;
import br.com.pvprojects.restspring3.domain.exception.InternalErrorException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler  extends ResponseEntityExceptionHandler {
    public static final String MSG_GENERIC_ERROR_FOR_FINAL_USER = "An unexpected internal system error has occurred. " +
            "Try again and if the problem persists, contact your system administrator.";

    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.SYSTEM_ERROR;
        String exception = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, MSG_GENERIC_ERROR_FOR_FINAL_USER)
                .exception(exception)
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handlerBusiness(BusinessException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.BUSINESS_ERROR;
        String detail = ex.getMessage();
        String exception = ex.getCause() != null ? ex.getCause().getMessage() : null;

        Problem problem = createProblemBuilder(status, problemType, detail)
                .exception(exception)
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<?> handlerInternalError(InternalErrorException ex, WebRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.SYSTEM_ERROR;
        String detail = ex.getMessage();
        String exception = ex.getCause() != null ? ex.getCause().getMessage() : null;

        Problem problem = createProblemBuilder(status, problemType, detail)
                .exception(exception)
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNot(EntityNotFoundException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
        String detail = ex.getMessage();
        String exception = ex.getCause() != null ? ex.getCause().getMessage() : null;

        Problem problem = createProblemBuilder(status, problemType, detail)
                .exception(exception)
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NotNull MethodArgumentNotValidException ex,
                                                                  @NotNull HttpHeaders headers,
                                                                  @NotNull HttpStatusCode status,
                                                                  @NotNull WebRequest request) {
        return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(@NotNull Exception ex, Object body,
                                                             @NotNull HttpHeaders headers,
                                                             @NotNull HttpStatusCode statusCode,
                                                             @NotNull WebRequest request) {
        if (isNull(body)) {
            body = Problem.builder()
                    .timestamp(OffsetDateTime.now())
                    .status(statusCode.value())
                    .title(HttpStatus.valueOf(statusCode.value()).getReasonPhrase())
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .timestamp(OffsetDateTime.now())
                    .title((String) body)
                    .status(statusCode.value())
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex, HttpHeaders headers,
                                                            HttpStatusCode status, WebRequest request,
                                                            BindingResult bindingResult) {

        List<Problem.Field> fields = bindingResult.getFieldErrors().stream()
                .map(fieldError -> {
                    String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
                    return Problem.Field.builder()
                            .name(fieldError.getField())
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());

        ProblemType problemType = ProblemType.INVALID_DATA;
        String detail = "One or more fields are invalid. Fill in correctly and try again.";

        Problem problem = createProblemBuilder(status, problemType, detail)
                .fields(fields)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatusCode status, ProblemType problemType, String detail) {
        return Problem.builder()
                .status(status.value())
                .title(problemType.getTitle())
                .detail(detail)
                .timestamp(OffsetDateTime.now());
    }
}
