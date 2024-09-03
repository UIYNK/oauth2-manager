package ir.nft.security.oauth2manager.validation.validator;

import ir.nft.security.oauth2manager.validation.ValidationPolicy;
import ir.nft.security.oauth2manager.validation.annotation.ValidFirstName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class FirstNameValidator implements ConstraintValidator<ValidFirstName, String> {
  private static final Pattern VALID_FIRST_NAME_PATTERN =
      Pattern.compile(ValidationPolicy.FIRST_NAME_REGEX);

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    return VALID_FIRST_NAME_PATTERN.matcher(value).matches();
  }
}
