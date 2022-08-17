function show(){
    if(document.getElementById('para2').style.display=="block"){
        document.getElementById('para2').style.display="none";
        document.getElementById('para3').innerHTML = "SHOW ALL";
    }else{
        document.getElementById('para2').style.display="block"
        document.getElementById('para3').innerHTML = "SHOW LESS";
    }
}
function Enroll(){
    var input1 = document.getElementById("t1").value;
    var input2 = document.getElementById("t2").value;
    var input3 = document.getElementById("t3").value;
    var input4 = document.getElementById("t4").value;
    if(input1 == "" || input2 =="" || input3==""||input4==""){
        alert("please fill all fields");
    }else{
        if(input3 != input4){
            alert("password/repassword is invalid");
        }else{
            var t5 = document.getElementById("t5");
            var t6 = document.getElementById("t6");
            var t7 = document.getElementById("t7");
            if(t5.checked || t6.checked || t7.checked){
                alert("start learning WED201c");
            }else{
                alert("would you like to choose options.");
            }
        }
    }
}