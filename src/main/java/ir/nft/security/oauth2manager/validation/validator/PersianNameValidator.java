package ir.nft.security.oauth2manager.validation.validator;

import ir.nft.security.oauth2manager.validation.ValidationPolicy;
import ir.nft.security.oauth2manager.validation.annotation.PersianName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PersianNameValidator implements ConstraintValidator<PersianName, String> {
  private static final Pattern VALID_PERSIAN_NAME_PATTERN =
      Pattern.compile(ValidationPolicy.PERSIAN_NAME_REGEX);

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    return VALID_PERSIAN_NAME_PATTERN.matcher(value).matches();
  }
}
