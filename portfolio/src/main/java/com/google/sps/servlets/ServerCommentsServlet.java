package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;
import com.google.sps.common.Constants;
import com.google.sps.data.CommentDetails;
import com.google.sps.validators.TextValidator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/comments")
public final class ServerCommentsServlet extends HttpServlet {
  
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
    UserService userService = UserServiceFactory.getUserService();

    // Only logged-in users can comment
    if (!userService.isUserLoggedIn()) {
      response.sendRedirect("/contact.html?error=login-required");
      return;
    }
    
    // Get & store the input from the comment section.
    String userName = getParameter(request, "name", "Anonymous");
    String userComment = getParameter(request, "comment", "");

    if (!TextValidator.getInstance().validate(userName, TextValidator.InputType.NAME)) {
      response.sendRedirect("/contact.html?error=incorrect-name");
      return;
    }
    if (!TextValidator.getInstance().validate(userComment,TextValidator.InputType.COMMENT)) {
      response.sendRedirect("/contact.html?error=incorrect-comment");
      return;
    }

    Entity commentEntity = new Entity("CommentDetails");
    commentEntity.setProperty("name", userName);
    commentEntity.setProperty("comment", userComment);

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(commentEntity);
    
    response.sendRedirect("/contact.html");
  }

  // Used to fetch data in frontend 
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {  
    List<CommentDetails> comments = extractComments();
    String commentJson = convertToJson(comments);

    // Send the JSON as the response
    response.setContentType("application/json;");
    response.getWriter().println(commentJson);
  }

  private static List<CommentDetails> extractComments() {
    Query query = new Query("CommentDetails");

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);

    // Add each comment from Datastore to the array.
    List<CommentDetails> comments = new ArrayList<>();
    for (Entity entity : results.asIterable()) {
      String userName = (String) entity.getProperty("name");
      String userComment = (String) entity.getProperty("comment");

      CommentDetails comment = new CommentDetails(userName, userComment);
      comments.add(comment);
    }

    return comments;
  }

  private static String convertToJson(List<CommentDetails> comments) {
    Gson gson = new Gson();
    return gson.toJson(comments);
  }
}
