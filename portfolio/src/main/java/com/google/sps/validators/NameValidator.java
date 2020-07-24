package com.google.sps.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class NameValidator implements TextValidator {
  private static final int maxNoChars = 20;

  private final String text;

  public NameValidator(String text) {
    this.text = text;
  }

  @Override
  public Boolean validate() {
    Pattern pattern = Pattern.compile(validPattern);
    Matcher matcher = pattern.matcher(text);
    
    return matcher.find() && text.length() <= maxNoChars;
  }
}
