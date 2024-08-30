package ir.nft.security.oauth2manager.validation.validator;

import ir.nft.security.oauth2manager.validation.ValidationPolicy;
import ir.nft.security.oauth2manager.validation.annotation.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
  private static final Pattern VALID_PASSWORD_PATTERN =
      Pattern.compile(ValidationPolicy.PASSWORD_REGEX);

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    return VALID_PASSWORD_PATTERN.matcher(value).matches();
  }
}
