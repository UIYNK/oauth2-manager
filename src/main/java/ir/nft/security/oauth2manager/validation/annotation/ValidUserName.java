package ir.nft.security.oauth2manager.validation.annotation;

import ir.nft.security.oauth2manager.validation.validator.UserNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserNameValidator.class)
@Documented
public @interface ValidUserName {

  String INVALID_USERNAME_MESSAGE = "Invalid username";

  String message() default INVALID_USERNAME_MESSAGE;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
