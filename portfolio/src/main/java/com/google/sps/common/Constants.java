package com.google.sps.common;
 
public final class Constants {
  private Constants() {
    // NOT CALLED
  }

  public static final int NAME_LENGTH_LIMIT = 20;
  public static final int COMMENT_LENGTH_LIMIT = 10000;
  public static final int DEFAULT_LENGTH_LIMIT = 5000;
  public static final String TEXT_VALID_REGEX = "^([A-z]|[0-9]|[\\?!,.(\" ])*$";
}
