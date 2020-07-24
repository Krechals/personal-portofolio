package com.google.sps.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TextValidator {
  // Allow only alphanumeric chars and few extra symbols.
  private static final String validPattern = "^([A-z]|[0-9]|[\\?!,.(\" ])*$";

  private final int lengthLimit;

  private final String text;

  public TextValidator(String text, int lengthLimit) {
    this.text = text;
    this.lengthLimit = lengthLimit;
  }

  public boolean validate() {
    Pattern pattern = Pattern.compile(validPattern);
    Matcher matcher = pattern.matcher(text);

    return matcher.find() && text.length() <= lengthLimit;
  }
}
