package ir.nft.security.oauth2manager.validation;

public class ValidationPolicy {
  public static final String PASSWORD_REGEX = "^(?=.{4,64}$)(?!.*('|\\s|\"|--|;|=|\\(|\\))).+$";
  public static final String FIRST_NAME_REGEX = "^(?!.*--)[a-zA-Z'-]+$";
  public static final String LAST_NAME_REGEX = "^(?!.*--)[a-zA-Z'-]+$";
  public static final String GROUP_NAME_REGEX = "^(?!.*[-_]{2,})[\\w'-]+$";
  public static final String USERNAME_REGEX =
      "^(?=.{4,30}$)(?=[a-zA-Z])(?!.*[\\._]{2,})[\\w\\.]+(?<![\\._])$";
  public static final String SQL_INJECTION_PREVENTION_LOOKAHEAD_REGEX =
      "(?!.*('|\\?|\"|--|;|=|\\(|\\)))";

  private ValidationPolicy() {}
}
