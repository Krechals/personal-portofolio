package com.google.sps.data; 

public final class ReviewerData {
  private final String name;
  private final String comment;

  public ReviewerData(String name, String comment) {
    this.name = name;
    this.comment = comment;
  }

  public String getName() {
    return name;
  }
  public String getComment() {
    return comment;
  }
}
