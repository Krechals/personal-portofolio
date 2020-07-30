package com.google.sps.data; 

public final class CommentDetails {
  private final String name;
  private final String comment;
  private final String sentiment;

  public CommentDetails(String name, String comment, String sentiment) {
    this.name = name;
    this.comment = comment;
    this.sentiment = sentiment;
  }

  public String getName() {
    return name;
  }

  public String getComment() {
    return comment;
  }

  public String getSentiment() {
    return sentiment;
  }
}
