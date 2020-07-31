function onSignIn(googleUser) {
  var profile = googleUser.getBasicProfile();
  var imagurl=profile.getImageUrl();
  var name=profile.getName();
  var email=profile.getEmail();
  document.getElementById("myImg").src = imagurl;
  document.getElementById("name").innerHTML = name;
  document.getElementById("myP").style.visibility = "hidden";
  document.getElementById("status").innerHTML = 'Welcome '+name+'!<a href=success.jsp?email='+email+'&name='+name+'/>Continue with Google login</a></p>';
}
function myFunction() {
  gapi.auth2.getAuthInstance().disconnect();
  location.reload();
}