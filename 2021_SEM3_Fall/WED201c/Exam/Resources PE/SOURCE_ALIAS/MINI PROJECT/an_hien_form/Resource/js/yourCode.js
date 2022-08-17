
function Show(){
	if(document.getElementById('myform').style.display === "none"){
		document.getElementById('myform').style.display = "block";
		document.getElementById('mytext').innerHTML = "Hide Register Form";
	}else{
		document.getElementById('myform').style.display = "none";
		document.getElementById('mytext').innerHTML = "Show Register Form";
	}
}
function checkForm(){
			var t1 = document.getElementById('t1').value;//string
			var t2 = document.getElementById('t2').value;
			if(t1===1 || t2===""){
				alert("must not empty");
			}else{
				alert("ok");
			}
		}
		