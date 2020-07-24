package com.google.sps.data; 

public final class CommentDetails {
  private final String name;
  private final String comment;

  public CommentDetails(String name, String comment) {
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
