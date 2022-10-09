function GetTime() {
    var now = new Date();
    document.getElementById('times').innerHTML = now.toLocaleTimeString();
}
function f1() {
    setInterval(GetTime, 1000);
}

function hrtbtn() {
    document.getElementById('hrtdiv').style.visibility = "visible";
    document.getElementById('times').style.visibility = "hidden";
}
function timebtn() {
    document.getElementById('times').style.visibility = "visible";
    document.getElementById('hrtdiv').style.visibility = "hidden";
}


function black() {
    document.getElementById("pic1").src = "black.png";
}

function red() {
    document.getElementById("pic1").src = "red.png";
}

function blue() {
    document.getElementById("pic1").src = "blue.png";
}

function pink() {
    document.getElementById("pic1").src = "pink.png";
}

function purple() {
    document.getElementById("pic1").src = "purple.png";
}