package ir.nft.security.oauth2manager.validation.validator;

import ir.nft.security.oauth2manager.validation.ValidationPolicy;
import ir.nft.security.oauth2manager.validation.annotation.ValidGroupName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class GroupNameValidator implements ConstraintValidator<ValidGroupName, String> {
  private static final Pattern VALID_GROUP_NAME_PATTERN =
      Pattern.compile(ValidationPolicy.GROUP_NAME_REGEX);

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    return VALID_GROUP_NAME_PATTERN.matcher(value).matches();
  }
}
