package ir.nft.security.oauth2manager.validation.validator;

import ir.nft.security.oauth2manager.validation.ValidationPolicy;
import ir.nft.security.oauth2manager.validation.annotation.UserName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class UserNameValidator implements ConstraintValidator<UserName, String> {
  private static final Pattern VALID_USERNAME_PATTERN =
      Pattern.compile(ValidationPolicy.USERNAME_REGEX);

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    return VALID_USERNAME_PATTERN.matcher(value).matches();
  }
}
