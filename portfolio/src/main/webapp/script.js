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

/**
 * Adds a random welcome to the page.
 */
async function getRandomWelcome() {
  const response = await fetch('/random-welcome-msg');
  const welcome = await response.text();
  document.getElementById('welcome-msg').innerText = welcome;
}

async function getServerDates() {
  const response = await fetch('/server-dates');
  const dates = await response.json();
  const body = document.getElementsByTagName('body')[0];
  body.innerHTML = '';
  dates.forEach(function(stats) {
    body.appendChild(
        createListElement('Server was accessed on: ' + stats.date.day + '/' 
                                                     + stats.date.month + '/' 
                                                     + stats.date.year + ' at ' 
                                                     + stats.time.hour + ':' 
                                                     + stats.time.minute));
  });
}

/** Creates an <li> element containing text. */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}
(function ($) {
  "use strict";
  
  var navbarCollapse = function() {
    var mainNav = $("#main-nav");
    if (mainNav.offset().top > 100) {
      mainNav.addClass("navbar-shrink");
    } else {
      mainNav.removeClass("navbar-shrink");
    }
  };

  // Collapse the navbar when page is scrolled
  $(window).scroll(navbarCollapse);
})(jQuery);
