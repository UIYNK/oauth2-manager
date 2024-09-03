package ir.nft.security.oauth2manager.validation.validator;

import ir.nft.security.oauth2manager.validation.ValidationPolicy;
import ir.nft.security.oauth2manager.validation.annotation.EnglishName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class EnglishNameValidator implements ConstraintValidator<EnglishName, String> {
  private static final Pattern VALID_ENGLISH_NAME_PATTERN =
      Pattern.compile(ValidationPolicy.ENGLISH_NAME_REGEX);

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    return VALID_ENGLISH_NAME_PATTERN.matcher(value).matches();
  }
}
