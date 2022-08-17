function show() {
    const formEl = document.querySelector("#myform");
    const textEl = document.querySelector("#mytext");
    if (formEl.style.display == "none") {
        formEl.style.display = "block";
        textEl.innerHTML = "HIDE REGISTER FORM";
    } else {
        formEl.style.display = "none";
        textEl.innerHTML = "HIDE REGISTER FORM";
    }
}