
function present(previewPic) {
  document.getElementById("image").innerHTML = previewPic.alt;
  document.getElementById('image').style.backgroundImage = "url(" + previewPic.src + ")";
}

function hide() {
  document.getElementById('image').innerHTML = 'Hover over an image below to display here.';
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