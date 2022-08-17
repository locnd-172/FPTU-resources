function Show(){
    var x = document.getElementById('myform');
    var displayValue = window.getComputedStyle(x).display;
    if(displayValue==="none"){
        document.getElementById('myform').style.display = "block";
    }else{
        document.getElementById('myform').style.display = "none";
    }
}
