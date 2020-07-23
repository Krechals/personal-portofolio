package com.google.sps.servlets;

import com.google.gson.Gson;
import com.google.sps.data.CommentDetails;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/comments")
public final class ServerCommentsServlet extends HttpServlet {
  private final List<CommentDetails> comments = new ArrayList<>();
  
  /**
   * @return the request parameter, or the default value if the parameter
   *         was not specified by the client
   */
  private static String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get & store the input from the comment section.
    String userName = getParameter(request, "name", "Anonymous");
    String userComment = getParameter(request, "comment", "");
    comments.add(new CommentDetails(userName, userComment));
    
    // Convert the comments to JSON
    String commentJson = convertToJson(comments);

    // Respond with the result.
    response.setContentType("application/json;");
    response.getWriter().println(commentJson);
  }

  // Used to fetch data in frontend 
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {  
    String commentJson = convertToJson(comments);

    // Send the JSON as the response
    response.setContentType("application/json;");
    response.getWriter().println(commentJson);
  }

  private static String convertToJson(List<CommentDetails> comments) {
    Gson gson = new Gson();
    return gson.toJson(comments);
  }
}
