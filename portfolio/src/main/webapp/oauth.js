
function signIn(googleUser) {
  const profile = googleUser.getBasicProfile();
  const imageUrl = profile.getImageUrl();
  const name = profile.getName();
  const email =profile.getEmail();

  document.getElementById("myImg").src = imageUrl;
  document.getElementById("name").innerHTML = name;
  document.getElementById("myP").style.visibility = "hidden";
}

function signOut() {
  gapi.auth2.getAuthInstance().disconnect();
  location.reload();
}