function warning(){
    var name = document.getElementById('yourname').value;
    var email = document.getElementById('email').value;
    var content = document.getElementById('content').value;
    if(name == ""|| email ==""||content == ""){
        alert("please fill out all fields");
    }else{
        var check = confirm("are you sure about your information");
        if(check == true){
            alert("message sent");
        }
    }
}
function zoomout(image){
    var src = image.src;
    document.getElementById('frame').style.backgroundImage = "url("+src+")";
   
}
