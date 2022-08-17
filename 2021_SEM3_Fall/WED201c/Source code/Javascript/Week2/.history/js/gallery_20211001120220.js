
function upDate(showThePic) {
  //
  var txt =  showPic.alt;
  document.getElementById("image").innerHTML = txt;
  //
  var txt2 = "url(" + showPic.src + ")";
  var ima = document.getElementById('image');
  ima.style.backgroundImage = txt2;
}

function unDo() {
  document.getElementById('image').innerHTML = 'Hover over an image below to display here.';
  document.getElementById('image').style.backgroundImage = "url('')";
}