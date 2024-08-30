package ir.nft.security.oauth2manager.validation.annotation;

import ir.nft.security.oauth2manager.validation.validator.GroupNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GroupNameValidator.class)
@Documented
public @interface ValidGroupName {
  String INVALID_GROUP_NAME_MESSAGE = "Invalid group name";

  String message() default INVALID_GROUP_NAME_MESSAGE;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
