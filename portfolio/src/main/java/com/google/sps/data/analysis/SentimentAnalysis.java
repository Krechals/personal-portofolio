package com.google.sps.data.analysis;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.google.sps.common.Constants;
import java.io.IOException;

public final class SentimentAnalysis {

  public enum SentimentCategory { POSITIVE, NEUTRAL, NEGATIVE; }

  /**
   * @return the feeling detected by Cloud Natural Language API,
   *         when querying a comment from the website.
   */
  public static SentimentCategory evaluate(String text) throws IOException {
    Document doc =
        Document.newBuilder().setContent(text).setType(Document.Type.PLAIN_TEXT).build();
    LanguageServiceClient languageService = LanguageServiceClient.create();
    Sentiment sentiment = languageService.analyzeSentiment(doc).getDocumentSentiment();
    float score = sentiment.getScore();
    languageService.close();

    if (score >= Constants.MIN_POSITIVE_SCORE) {
      return SentimentCategory.POSITIVE;
    } else if (score <= Constants.MIN_NEGATIVE_SCORE) {
      return SentimentCategory.NEGATIVE;
    }
    return SentimentCategory.NEUTRAL;
  }
}
