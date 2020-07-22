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
    // Get & store the input from the comment section.
    String reviewerName = getParameter(request, "name", "Anonymous");
    String reviewerComment = getParameter(request, "comment", "");
    reviews.add(new ReviewerData(reviewerName, reviewerComment));
    
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

    // Send the JSON as the response
    response.setContentType("application/json;");
    response.getWriter().println(reviewJson);
  }

  private static String convertToJson(List<ReviewerData> reviews) {
    Gson gson = new Gson();
    return gson.toJson(reviews);
  }
}
