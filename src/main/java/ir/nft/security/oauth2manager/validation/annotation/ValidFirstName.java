package ir.nft.security.oauth2manager.validation.annotation;

import ir.nft.security.oauth2manager.validation.validator.FirstNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FirstNameValidator.class)
@Documented
public @interface ValidFirstName {
  String INVALID_FIRST_NAME_MESSAGE = "Invalid first name";

  String message() default INVALID_FIRST_NAME_MESSAGE;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
