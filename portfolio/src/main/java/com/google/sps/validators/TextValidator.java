package com.google.sps.validators;

import com.google.sps.common.Constants;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TextValidator {
  // Singleton Design Pattern
  private static TextValidator validator; 

  // Allow only alphanumeric chars and few extra symbols.
  private static final Pattern validPattern = Pattern.compile(Constants.TEXT_VALID_REGEX);

  public enum InputType { NAME, COMMENT; }

  private TextValidator() {}

  public static TextValidator getInstance() {
    if (validator == null) {
      validator = new TextValidator(); 
    }
    return validator; 
  }

  public boolean validate(String text, InputType type) {
    Matcher matcher = validPattern.matcher(text);

    switch(type) {
      case NAME:
        return matcher.find() && text.length() <= Constants.NAME_LENGTH_LIMIT;
      case COMMENT:
        return matcher.find() && text.length() <= Constants.COMMENT_LENGTH_LIMIT;
    }

    return matcher.find() && text.length() <= Constants.DEFAULT_LENGTH_LIMIT;
  }
}
