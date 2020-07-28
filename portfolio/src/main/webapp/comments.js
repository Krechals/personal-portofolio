
function getAvatarImage(src, className) {
  const image = document.createElement("img");
  image.className = className;
  image.setAttribute("src", src);
  
  return image;
}

function getUserAvatar() {
  const avatar = document.createElement("a");
  avatar.className = "pull-left";
  avatar.setAttribute("href", "http://google.com");
  avatar.appendChild(getAvatarImage("images/default_user.jpg", "rounded"));

  return avatar;
}

function getUserComment(userName, userComment) {
  const comment = document.createElement("div");
  comment.className = "media-body";

  // Hardcoded for now
  function getCommentDate() {
    const date = document.createElement("span");
    date.className = "text-muted pull-right";

    const dateContent = document.createElement("small");
    dateContent.className = "text-muted";
    dateContent.appendChild(document.createTextNode("2 min ago"));
    date.appendChild(dateContent);

    return date
  }

  function getAuthorName(userName) {
    const name = document.createElement("strong");
    name.className = "text-success";
    name.appendChild(document.createTextNode("@" + userName));

    return name;
  }

  function getCommentText(userComment) {
    const text = document.createElement("p");
    text.className = "comment-text";
    
    /**  --> This make XSS possible
     * Ex: <img src="" onerror="alert('xxs')">
     *
     * text.innerHTML = userComment; 
     */
    
    // This prevents XSS
    text.appendChild(document.createTextNode(userComment));
    
    return text;
  }
  
  comment.appendChild(getCommentDate());
  comment.appendChild(getAuthorName(userName));
  comment.appendChild(getCommentText(userComment));

  return comment;
}

// Creates an <li> element containing text.
function createListElement(review) {
  const liElement = document.createElement('li');
  liElement.className = "media";
  liElement.appendChild(getUserAvatar());
  liElement.appendChild(getUserComment(review.name, review.comment));
  return liElement;
}

async function getComments() {
  const response = await fetch('/comments');
  const reviews = await response.json();
  const displayList = document.getElementsByClassName('media-list')[0];
  displayList.innerHTML = '';
  reviews.forEach(function(review) {
    displayList.appendChild(createListElement(review));
  });
}

async function getLoginStatus() {
  const response = await fetch('/login');
  const loginStatus = await response.text();
  const loginStatusClass = document.querySelectorAll('.page-section .container .text-center')[0];
  
  const pElement = document.createElement('p');
  pElement.className = "text-center";
  pElement.innerHTML = loginStatus;
  loginStatusClass.appendChild(pElement);
}

// Display any potential error that comes from backend
function displayErrors() {
  const url_string = window.location.href; //window.location.href
  const url = new URL(url_string);
  const error = url.searchParams.get("error");

  if (error === "incorrect-name") {
    Swal.fire({
        icon: 'error',
        title: 'Name Error',
        text: 'Your name contains characters that are not allowed.',
        footer: '<a href="contact.html">Try with another name! </a> Symbols allowed: ?!,.("'
      });
    return;
  } else if (error === "incorrect-comment") {
    Swal.fire({
        icon: 'error',
        title: 'Comment Error',
        text: 'Your comment contains characters that are not allowed.',
        footer: '<a href="contact.html">Try again! </a> Symbols allowed: ?!,.("'
      });
    return;
  } else if (error === "login-required") {
    Swal.fire({
        icon: 'error',
        title: 'You are not logged in!',
        text: 'You must log in to use the comment section.',
        footer: 'Log in <a href="login">here</a>.'
      });
    return;
  }
}

function displayContent() {
  displayErrors();
  getLoginStatus();
  getComments();
}