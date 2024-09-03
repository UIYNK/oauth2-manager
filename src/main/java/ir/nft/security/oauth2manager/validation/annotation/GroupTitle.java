package ir.nft.security.oauth2manager.validation.annotation;

import ir.nft.security.oauth2manager.validation.validator.GroupTitleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GroupTitleValidator.class)
@Documented
public @interface GroupTitle {

  String message() default "Invalid Group Title";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
