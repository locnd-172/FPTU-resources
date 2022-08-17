function show(){
    if(document.getElementById('team').style.display == "none"){
        document.getElementById('team').style.display = "block";
        document.getElementById('showmore').innerHTML = "SHOW LESS";
    }else{
        document.getElementById('team').style.display = "none";
        document.getElementById('showmore').innerHTML = "SHOW MORE";
    }

}
function check(){
    var usa = document.getElementById('USA');
    var uk = document.getElementById('UK');
    if(usa.checked || uk.checked){

    }else{
        alert("please check your options");
    }
    var input = document.getElementsByTagName('input');
    var capchar = input[3].value;
    if(capchar != "cVr12TY"){
        alert("please check your capchar");
    }
    console.log(input);
}