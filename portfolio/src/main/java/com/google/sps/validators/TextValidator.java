package com.google.sps.validators;

public interface TextValidator {
  // Allow only alphanumeric chars and few extra symbols.
  public static final String validPattern = "^([A-z]|[0-9]|[\\?!,.(\" ])*$";

  public Boolean validate();
}
