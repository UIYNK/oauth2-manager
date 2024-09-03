package ir.nft.security.oauth2manager.exception;

import ir.nft.security.oauth2manager.dto.ErrorResponseDTO;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(DomainException.class)
  public ResponseEntity<ErrorResponseDTO> handleDomainException(DomainException exception) {
    ErrorResponseDTO errorResponseDTO =
        ErrorResponseDTO.builder()
            .title(exception.getTitle())
            .detail(exception.getMessage())
            .status(exception.getStatus().toString())
            .properties(exception.getProperties())
            .build();

    return ResponseEntity.status(exception.getStatus()).body(errorResponseDTO);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDTO> handleUncaughtException() {
    ErrorResponseDTO errorResponseDTO =
        ErrorResponseDTO.builder()
            .title("Unexpected Issue in the Server")
            .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
            .build();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDTO);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
    List<ErrorResponseDTO.InvalidParameter> invalidParameters =
        fieldErrors.stream()
            .map(
                fieldError ->
                    ErrorResponseDTO.InvalidParameter.builder()
                        .field(fieldError.getField())
                        .cause(fieldError.getDefaultMessage())
                        .build())
            .toList();

    ErrorResponseDTO errorResponseDTO =
        ErrorResponseDTO.builder()
            .title("Parameter Validation Failed")
            .detail("Some parameters in the request's body violated our validation policies")
            .status(HttpStatus.BAD_REQUEST.toString())
            .build();
    errorResponseDTO.setInvalidParameters(invalidParameters);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
  }
}
