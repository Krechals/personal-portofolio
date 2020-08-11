// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    Collection<TimeRange> availableTimes = new ArrayList<>();
    Collection<String> attendees = request.getAttendees();
    int currentTime = TimeRange.START_OF_DAY;
    int latestEventTime = TimeRange.START_OF_DAY;

    for (Event event : events) {
      for (String attendee : attendees) {
        if (event.getAttendees().contains(attendee)) {
          if (currentTime != latestEventTime && latestEventTime > event.getWhen().start()) {
            if (latestEventTime < event.getWhen().end()) {
              latestEventTime = event.getWhen().end();
            }
            break;
          } else {
            currentTime = latestEventTime;
          }

          // Check if we have enough time for a potential meeting
          if (event.getWhen().start() - currentTime >= request.getDuration()) {
            latestEventTime = event.getWhen().end();
            availableTimes.add(TimeRange.fromStartEnd(currentTime, event.getWhen().start(), false));
          } else {
            // The meeting space isn't enough 
            latestEventTime = event.getWhen().end();
          }
          break;
        }
      }
    }

    // Check for meeting availability at the end of the day
    currentTime = latestEventTime;
    if (TimeRange.END_OF_DAY - currentTime >= request.getDuration()) {
      availableTimes.add(TimeRange.fromStartEnd(currentTime, TimeRange.END_OF_DAY, true));
    }

    return availableTimes;
  }
}
