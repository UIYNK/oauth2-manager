package ir.nft.security.oauth2manager.validation;

public final class ValidationPolicy {
  public static final String PASSWORD_REGEX = "^(?=.{4,64}$)(?!.*('|\\s|\"|--|;|=|\\(|\\))).+$";
  public static final String ENGLISH_NAME_REGEX = "^(?!.*--)[a-zA-Z'-]+$";
  public static final String PERSIAN_NAME_REGEX =
      "^(?!(.*\\s{2,}|\\s))[\\u0621-\\u0624\\u0626-\\u063A\\u0641\\u0642\\u0644-\\u0648\\u064B-\\u064D\\u0651\\u0654\\u067E\\u0686\\u0698\\u06A9\\u06AF\\u06CC\\s]+$";
  public static final String GROUP_TITLE_REGEX = "^(?!.*[-_]{2,})[\\w'-]+$";
  public static final String USERNAME_REGEX =
      "^(?=.{4,30}$)(?=[a-zA-Z])(?!.*[\\._]{2,})[\\w\\.]+(?<![\\._])$";

  private ValidationPolicy() {}
}
