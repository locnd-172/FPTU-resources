
function present(piccccccc) {
  ///
  document.getElementById("image").innerHTML = piccccccc.alt;

  //
  document.getElementById('image').style.backgroundImage = "url(" + piccccccc.src + ")";
}

function hide() {
  //
  document.getElementById('image').innerHTML = 'Hover over to show picture.';

  //
  document.getElementById('image').style.backgroundImage = "url('')";
}

