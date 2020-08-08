package com.google.sps.validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** */
@RunWith(JUnit4.class)
public final class TextValidatorTest {

  private static final TextValidator validator = TextValidator.getInstance();

  private static final String VALID1 = "This is a common type of text.";

  private static final String VALID2 = "This test uses 3 numbers: 1, 2, 3";

  private static final String VALID3 = "This test contains special chars! : , ( ? .";

  private static final String INVALID1 = "<script> alert(1); </script>";

  private static final String INVALID2 = "<img=x onerror=alert(1)>";

  private static final String INVALID3 = "; return 0;";

  private static final String INVALID4 = "name=Andrei or 1=1 --;";

  @Test
  public void commonValidComment() {
    boolean actual = validator.validate(VALID1, TextValidator.InputType.COMMENT);
    boolean expected = true;
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void numbersComment() {
    boolean actual = validator.validate(VALID2, TextValidator.InputType.COMMENT);
    boolean expected = true;
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void symbolsComment() {
    boolean actual = validator.validate(VALID3, TextValidator.InputType.COMMENT);
    boolean expected = true;
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void firstXssInjection() {
    boolean actual = validator.validate(INVALID1, TextValidator.InputType.COMMENT);;
    boolean expected = false;
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void secondXssInjection() {
    boolean actual = validator.validate(INVALID2, TextValidator.InputType.COMMENT);
    boolean expected = false;
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void otherInjection() {
    boolean actual = validator.validate(INVALID3, TextValidator.InputType.COMMENT);
    boolean expected = false;
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void sqlInjection() {
    boolean actual = validator.validate(INVALID4, TextValidator.InputType.COMMENT);
    boolean expected = false;
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void longComment() {
    String comment = new String();
    for (int i = 0; i <= TextValidator.COMMENT_LENGTH_LIMIT + 1; ++i) {
      comment = comment + 'a';
    }
    System.out.println(comment.length());
    boolean actual = validator.validate(comment, TextValidator.InputType.COMMENT);
    boolean expected = false;
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void longName() {
    String name = new String();
    for (int i = 0; i <= TextValidator.NAME_LENGTH_LIMIT + 1; ++i) {
      name = name + 'a';
    }
    boolean actual = validator.validate(name, TextValidator.InputType.NAME);
    boolean expected = false;
    Assert.assertEquals(expected, actual);
  }
}
