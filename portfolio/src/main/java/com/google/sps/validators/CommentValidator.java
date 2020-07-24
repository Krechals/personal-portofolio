package com.google.sps.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CommentValidator implements TextValidator {
  // Allow only alphanumeric chars and few extra symbols.
  private static final int maxNoChars = 10000;

  private final String text;

  public CommentValidator(String text) {
    this.text = text;
  }

  @Override
  public Boolean validate() {
    Pattern pattern = Pattern.compile(validPattern);
    Matcher matcher = pattern.matcher(text);
    
    return matcher.find() && text.length() <= maxNoChars;
  }
}
