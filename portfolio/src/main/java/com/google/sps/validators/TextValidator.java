package com.google.sps.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TextValidator {
  // Singleton Design Pattern
  private static TextValidator validator; 

  public enum InputType { NAME, COMMENT; }

  public static final int NAME_LENGTH_LIMIT = 20;

  public static final int COMMENT_LENGTH_LIMIT = 10000;

  private static final int DEFAULT_LENGTH_LIMIT = 5000;

  private static final String TEXT_VALID_REGEX = "^([A-z]|[0-9]|[\\?!,.:(\" ])*$";

  // Allow only alphanumeric chars and few extra symbols.
  private static final Pattern validPattern = Pattern.compile(TEXT_VALID_REGEX);

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
        return text.length() <= NAME_LENGTH_LIMIT && matcher.find();
      case COMMENT:
        return text.length() <= COMMENT_LENGTH_LIMIT && matcher.find();
    }

    return matcher.find() && text.length() <= DEFAULT_LENGTH_LIMIT;
  }
}
