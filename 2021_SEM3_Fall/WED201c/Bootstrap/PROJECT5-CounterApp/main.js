// ex 01
var countEl = document.getElementById("count-el");
var saveOut = document.getElementById("save-el");
var count = 0;
var output = "";

function increment() {
    countEl.innerHTML = count;
    count++;
}

function save() {
    output = countEl.innerHTML + " - ";
    saveOut.innerHTML += output;
    count = 0;
}

// ex 02
let errorParagraph = document.getElementById("error");
console.log(errorParagraph);

function purchase() {
    console.log("button clicked");
    errorParagraph.textContent = "Something went wrong, please try again";
}

// ex 03
var num1 = 8;
var num2 = 2;
var n1 = document.getElementById("num1-el");
n1.innerHTML = num1;

var n2 = document.getElementById("num2-el");
n2.innerHTML = num2;

var res = document.getElementById("res-el");

function add() {
    var s = num1 + num2;
    res.innerHTML = "Result: " + s;
}

function subtract() {
    var d = num1 - num2;
    res.innerHTML = "Result: " + d;
}

function divide() {
    var r = num1 / num2;
    res.innerHTML = "Result: " + r;
}

function multiply() {
    var p = num1 * num2;
    res.innerHTML = "Result: " + p;
}

