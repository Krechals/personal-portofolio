package com.google.sps.servlets;

import com.google.gson.Gson;
import com.google.sps.data.ServerStats;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/server-dates")
public final class ServerDatesServlet extends HttpServlet {
  private final List<ServerStats> dateList = new ArrayList<>();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Add another entry to the list
    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();
    dateList.add(new ServerStats(date, time));
  
    // Convert the server dates to JSON
    String json = convertToJson(dateList);

    // Send the JSON as the response
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }

  private static String convertToJson(List<ServerStats> dateList) {
    Gson gson = new Gson();
    return gson.toJson(dateList);
  }
}
