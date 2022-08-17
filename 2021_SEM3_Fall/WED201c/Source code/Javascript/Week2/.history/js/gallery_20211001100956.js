
function present(piccccccc) {
  document.getElementById("image").innerHTML = piccccccc.alt;
  document.getElementById('image').style.backgroundImage = "url(" + piccccccc.src + ")";
}

function hide() {
  document.getElementById('image').innerHTML = 'Hover over to show picture.';
  document.getElementById('image').style.backgroundImage = "url('')";
}

function showPic(pic)
{
    document.getElementById('image').innerHTML = pic.alt;
    document.querySelector('#image').style.backgroundImage = "url(" + pic.src + ")";  
}

function unShowPic()
{
    document.querySelector('#image').style.backgroundImage = null;
    document.getElementById('image').innerHTML = "Hover your mouse to below pics to display the image here.";
}