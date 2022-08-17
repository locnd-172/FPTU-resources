
function upDate(showPic) {
  //
  var txt =  showPic.alt;
  document.getElementById("image").innerHTML = txt;
  //
  document.getElementById('image').style.backgroundImage = "url(" + showPic.src + ")";
}

function unDo() {
  document.getElementById('image').innerHTML = 'Hover over an image below to display here.';
  document.getElementById('image').style.backgroundImage = "url('')";
}