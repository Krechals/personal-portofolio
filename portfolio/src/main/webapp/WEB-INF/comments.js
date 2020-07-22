async function getComments() {
  const response = await fetch('/comments');
  const welcome = await response.json();
  document.getElementById('welcome-msg').innerText = welcome;
}