
function upDate(showThePic) {
  // 
  var txt =  showThePic.alt;
  document.getElementById("image").innerHTML = txt;
  // 
  var txt2 = "url(" + showPic.src + ")";
  var ima = document.getElementById('image');
  ima.style.backgroundImage = txt2;
}

function unDo() {
  /////
  var thTeTDiss = document.getElementById('image');
  thTeTDiss.innerHTML = 'Move cursor to view photo.';

  var thMgTDiss = document.getElementById('image');
  ////
  var noThing = null
  thMgTDiss.style.backgroundImage = null;
}