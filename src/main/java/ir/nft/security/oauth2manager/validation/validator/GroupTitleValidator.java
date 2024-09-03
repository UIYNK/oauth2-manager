package ir.nft.security.oauth2manager.validation.validator;

import ir.nft.security.oauth2manager.validation.ValidationPolicy;
import ir.nft.security.oauth2manager.validation.annotation.GroupTitle;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class GroupTitleValidator implements ConstraintValidator<GroupTitle, String> {
  private static final Pattern VALID_GROUP_TITLE_PATTERN =
      Pattern.compile(ValidationPolicy.GROUP_TITLE_REGEX);

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    return VALID_GROUP_TITLE_PATTERN.matcher(value).matches();
  }
}
