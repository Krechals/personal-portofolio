package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("login")
public final class ServerLoginServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    UserService userService = UserServiceFactory.getUserService();

    if (!userService.isUserLoggedIn()) {
      String loginUrl = userService.createLoginURL("/contact.html");
      out.println("<a href=\"" + loginUrl + "\">Log in </a> to add a comment.");
      return;
    }

    String logoutUrl = userService.createLogoutURL("/contact.html");
    out.println("Your are logged in as " + userService.getCurrentUser().getEmail() + ".");
    out.println("If you want to change the accout, you can <a href=\"" + logoutUrl + "\">logout</a>.");
  }
}