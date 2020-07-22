
function getUserAvatar() {
  const avatar = document.createElement("a");
  avatar.className = "pull-left";
  avatar.setAttribute("href", "http://google.com");

  function getAvatarImage() {
    const image = document.createElement("img");
    image.className = "rounded";
    image.setAttribute("src", "images/default_user.jpg");
    return image;
  }
  avatar.appendChild(getAvatarImage());
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
