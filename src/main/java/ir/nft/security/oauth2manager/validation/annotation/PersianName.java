package ir.nft.security.oauth2manager.validation.annotation;

import ir.nft.security.oauth2manager.validation.validator.PersianNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PersianNameValidator.class)
@Documented
public @interface PersianName {

  String message() default "Invalid Name";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
