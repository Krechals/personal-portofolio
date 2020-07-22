package com.google.sps.servlets;

import com.google.gson.Gson;
import com.google.sps.data.ReviewerData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that processes text. */
@WebServlet("/comments")
public final class ServerCommentsServlet extends HttpServlet {

  private final List<ReviewerData> reviews = new ArrayList<>();
  /**
   * @return the request parameter, or the default value if the parameter
   *         was not specified by the client
   */
  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    String reviewerName = getParameter(request, "name", "Anonymous");
    String reviewerComment = getParameter(request, "comment", "");
    reviews.add(new ReviewerData(reviewerName, reviewerComment));
    
    // boolean upperCase = Boolean.parseBoolean(getParameter(request, "upper-case", "false"));
    // boolean sort = Boolean.parseBoolean(getParameter(request, "sort", "false"));

    // // Convert the text to upper case.
    // if (upperCase) {
    //   text = text.toUpperCase();
    // }

    // // Break the text into individual words.
    // String[] words = text.split("\\s*,\\s*");

    // // Sort the words.
    // if (sort) {
    //   Arrays.sort(words);
    // }
    // Convert the comments to JSON
    String reviewJson = convertToJson(reviews);
    System.out.println(reviewerName + ' ' + reviewerComment);

    // Respond with the result.
    response.setContentType("application/json;");
    response.getWriter().println(reviewJson);
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {  
    // Convert the server dates to JSON
    String reviewJson = convertToJson(reviews);
    System.out.println("hello");
    // Send the JSON as the response
    response.setContentType("application/json;");
    response.getWriter().println(reviewJson);
  }

  private static String convertToJson(List<ReviewerData> reviews) {
    Gson gson = new Gson();
    return gson.toJson(reviews);
  }
}
