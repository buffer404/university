var activeX=null;
var isX=false;
var activeR = null;
var globalR = 0;
var context;
var Mathimg;

function clearHistory() {
    activeX.style.border = '0px solid red';
    test+=1;
}

function newR(id){
    let x = document.getElementById(id);
    if(activeX!=null){
        activeX.style.border='0px solid blue';
    }
    activeX=x;
    isX=true;
    x.style.border='1px solid green';
}

window.addEventListener("load", ()=>{
    document.getElementById("input-form:forreset").onclick = clearHistory;
    load();
});

function load(){
    document.querySelector(".numberY").onkeyup = onlyDigitsY;
    document.querySelector(".numberR").onkeyup = onlyDigitsR;
    activeX=null;
    let canvas= document.getElementById('canvasMath');
    let r = document.getElementById("input-form:r");
    context = canvas.getContext('2d');

    r.addEventListener(Event, function(e) {
        if(activeR!==r.value){
            console.log("Новое р = "+r.value);
            console.log("Старое р = "+r.value);
            let rr = r.value-activeR*20;
            drawPoints(rr);
        }
        activeR=r.value

    })
    document.getElementById("input-form:checkButton").addEventListener('mousedown', function(e) {
        document.getElementById("errors").innerHTML="";
        if(checkValue()){
            drawPoints();
        }
    })
    canvas.addEventListener('mousedown', function(e) {
        newPoint(e);
    })
    {
        let canvas =document.getElementById('canvasMath');
        let context = canvas.getContext('2d');
        let img = new Image();
        img.onload = function() {
            context.drawImage(img, 0, 0);
            Mathimg=img;
        };
        img.src = "../resources/style/math.jpg";
    }
}

function newPoint(event){
    document.getElementById("errors").innerHTML="";
    let canvas = document.getElementById('canvasMath')
    let rect = canvas.getBoundingClientRect();
    let x=round((((event.clientX - rect.left)/60).toFixed(3)-2), 0.5);
    let y=(((event.clientY - rect.top)/-24)+5).toFixed(5);
    let r = document.getElementById("input-form:r").value;
    if(r!==null && r!=="" && r>2 && r<5){
        let inputY = document.getElementById("input-form:y");
        inputY.value = y;
        let x1 = document.getElementById("input-form:x1");
        let x2 = document.getElementById("input-form:x2");
        let x3 = document.getElementById("input-form:x3");
        let x4 = document.getElementById("input-form:x4");
        let x5 = document.getElementById("input-form:x5");
        let x6 = document.getElementById("input-form:x6");
        let x7 = document.getElementById("input-form:x7");
        let x8 = document.getElementById("input-form:x8");
        let x9 = document.getElementById("input-form:x9");
        let ButtonsR = [x1, x2,x3, x4, x5, x6, x7, x8, x9];
        ButtonsR.forEach(button => {
            if(button.title === x.toString()){
                button.click();
            }
        });
        console.log("новая точка "+x, y, r);
        document.getElementById("input-form:checkButton").click();
        drawPoints();
        }
    else {
        checkValue();
    }
}
function drawPoints(){
    let $table_rows = document.querySelector("#ResultTable").tBodies[0].rows;
    let points = [];
    let newR = document.getElementById("input-form:r").value;
    for (let i = 0; i < $table_rows.length; ++i){
        let point = {};
        point.x = parseFloat($table_rows[i].cells[0].innerText.replace(",","."));
        point.y = parseFloat($table_rows[i].cells[1].innerText.replace(",","."));
        point.hit = $table_rows[i].cells[3].innerText.trim();
        points.push(point);
    }
    let point = {};
    point.x=parseFloat(activeX.title);
    point.y=document.getElementById("input-form:y").value;
    point.hit=hirRes(parseFloat(activeX.title),document.getElementById("input-form:y").value,document.getElementById("input-form:r").value);
    points.push(point);
    let rr = checkR(newR);
    console.log("rr = "+rr);
    if(rr!==0){
        newCanvas(points, rr);
    }
    else {
        points.forEach(point => { getCursorPosition(point, rr); });
    }
}
function getCursorPosition(point) {
    let x1 = CoordX ((point.x+2)*60 -globalR) ;
    let y1 = Coordy (((-point.y)+5)*24 +globalR) ;
    console.log("draw "+x1, y1 +" -> "+point.x, point.y, point.hit);
    let ctx = context;
    if(point.hit==="true"){
        ctx.fillStyle="green";
    }
    else {
        ctx.fillStyle="red";
    }
    ctx.fillRect(x1, y1, 3, 3);
    ctx.save();
}
function round(value, step) {
    step || (step = 1.0);
    var inv = 1.0 / step;
    return Math.round(value * inv) / inv;
}
function hirRes(x, y, r){
    let hit = "false";
    if(x<=0 && y>=0){
        if (y*y<=r*r-x*x){
            hit= "true";
        }
    }
    else if(x>=0 && y>=0){
        if (y<=(-2*x)+(r)){
            hit= "true";
        }
    }
    else if (x>=0 && y<=0){
        if(x<=r && Math.abs(y)<=r){
            hit= "true";
        }
    }
    return hit;
}
function checkR(newR){
    if (activeR!==newR && activeR!==null){
        let r = (newR-activeR)*5
        activeR = newR;
        globalR=r;
        console.log(globalR);
        return r;
    }
    activeR = newR;
    return 0;
}
function newCanvas(points, rr){
    let canvas = document.getElementById('canvasMath');
    let ctx = context;
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.drawImage(Mathimg, 0, 0);
    points.forEach(point => { getCursorPosition(point, rr); });
    console.log("Canvas new")
}
function CoordX(x){
    if(x>=120){
        return x+globalR;
    }
    return x-globalR;
}
function Coordy(y){
    if(y>=120){
        return y+globalR;
    }
    return y-globalR;
}
function checkValue(){
    if (document.getElementById("input-form:y").value>-5 && document.getElementById("input-form:y").value<5 && document.getElementById("input-form:r").value>2 && document.getElementById("input-form:r").value<5){
        document.getElementById("errors").innerHTML = "";
        return true;
    }
    else {
        let err = "";
        if(document.getElementById("input-form:y").value<=-5 || document.getElementById("input-form:y").value>=5 || document.getElementById("input-form:y").value==null || document.getElementById("input-form:y").value===""){
            err+="y=(-5, 5) ";
        }
        if(document.getElementById("input-form:r").value<=2 || document.getElementById("input-form:r").value>=5) {
            err+="r=(2, 5) ";
        }
        if(activeX ===  null){
            err+="enter x"
        }
        err+="!";
        document.getElementById("errors").innerHTML=err;
        return false;
    }
}
function onlyDigitsY() {
    let separator = ".";
    let replaced = new RegExp('[^\\d\\'+separator+'\\-]', "g");
    let regex = new RegExp('\\'+separator, "g");
    this.value = this.value.replace(replaced, "");

    let minValue = -4.9999;
    let maxValue = 4.99999;
    let val = parseFloat(separator == "." ? this.value : this.value.replace(new RegExp(separator, "g"), "."));
    if (minValue <= maxValue) {
        if (this.value[0] == "-") {
            if (this.value.length > 8)
                this.value = this.value.substr(0, 8);
        } else {
            if (this.value.length > 7)
                this.value = this.value.substr(0, 7);
        }

        if (minValue < 0 && maxValue < 0) {
            if (this.value[0] != "-")
                this.value = "-" + this.value[0];
        } else if (minValue >= 0 && maxValue >= 0) {
            if (this.value[0] == "-")
                this.value = this.value.substr(0, 0);
        }

        if (val < minValue || val > maxValue)
            this.value = this.value.substr(0, 0);

        if (this.value.match(regex))
            if (this.value.match(regex).length > 1)
                this.value = this.value.substr(0, 0);

        if (this.value.match(/\-/g))
            if (this.value.match(/\-/g).length > 1)
                this.value = this.value.substr(0, 0);
    }
}
function onlyDigitsR() {
    let separator = ".";
    let replaced = new RegExp('[^\\d\\'+separator+'\\-]', "g");
    let regex = new RegExp('\\'+separator, "g");
    this.value = this.value.replace(replaced, "");

    let minValue = 1.99999;
    let maxValue = 4.99999;
    let val = parseFloat(separator == "." ? this.value : this.value.replace(new RegExp(separator, "g"), "."));
    if (minValue <= maxValue) {
        if (this.value[0] == "-") {
            if (this.value.length > 8)
                this.value = this.value.substr(0, 8);
        } else {
            if (this.value.length > 7)
                this.value = this.value.substr(0, 7);
        }

        if (minValue < 0 && maxValue < 0) {
            if (this.value[0] != "-")
                this.value = "-" + this.value[0];
        } else if (minValue >= 0 && maxValue >= 0) {
            if (this.value[0] == "-")
                this.value = this.value.substr(0, 0);
        }

        if (val < minValue || val > maxValue)
            this.value = this.value.substr(0, 0);

        if (this.value.match(regex))
            if (this.value.match(regex).length > 1)
                this.value = this.value.substr(0, 0);

        if (this.value.match(/\-/g))
            if (this.value.match(/\-/g).length > 1)
                this.value = this.value.substr(0, 0);
    }
}


