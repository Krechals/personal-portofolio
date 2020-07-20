package com.google.sps.servlets;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/server-dates")
public final class ServerDatesServlet extends HttpServlet {
  private List<Date> dateList = new ArrayList<>();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Add another entry to the list
    Date currentTime = new Date();
    dateList.add(currentTime);

    // Convert the server dates to JSON
    String json = convertToJson(dateList);

    // Send the JSON as the response
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }

  /**
   * Converts list of dates into a JSON string using the Gson library.
   */
  private String convertToJson(List<Date> dateList) {
    Gson gson = new Gson();
    String json = gson.toJson(dateList);
    return json;
  }
}