package com.google.sps.data;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

public final class ServerStats {
  private final LocalDate date;
  private final LocalTime time;

  public ServerStats(LocalDate date, LocalTime time) {
    this.date = date;
    this.time = time;
  }

  public LocalDate getDate() {
    return date;
  }
  public LocalTime getTime() {
    return time;
  }
}
