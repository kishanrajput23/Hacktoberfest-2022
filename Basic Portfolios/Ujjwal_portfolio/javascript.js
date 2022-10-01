console.log("Is this js file working? yes or no");


let mybox1 = document.querySelector(".combined");
let mybox2 = document.querySelector(".skills");
let mybox3 = document.querySelector(".hobbies");


document.querySelector(".homeicon").addEventListener("click", function(){
    mybox1.style.display = 'flex';
    mybox2.style.display = 'none';
    mybox3.style.display = 'none';
    document.querySelector(".homeicon").style.borderBottom = "2px solid white";
    document.querySelector(".skillsicon").style.borderBottom = "0px";
    document.querySelector(".hobbiesicon").style.borderBottom = "0px";

    // console.log("1");
})

document.querySelector(".skillsicon").addEventListener("click", function(){
    mybox1.style.display = 'none';
    mybox2.style.display = 'flex';
    mybox3.style.display = 'none';
    document.querySelector(".skillsicon").style.borderBottom = "2px solid white";
    document.querySelector(".homeicon").style.borderBottom = "0px";
    document.querySelector(".hobbiesicon").style.borderBottom = "0px";



    // console.log("2");

})

document.querySelector(".hobbiesicon").addEventListener("click", function(){
    mybox1.style.display = 'none';
    mybox2.style.display = 'none';
    mybox3.style.display = 'inline';
    document.querySelector(".hobbiesicon").style.borderBottom = "2px solid white";
    document.querySelector(".skillsicon").style.borderBottom = "0px";
    document.querySelector(".homeicon").style.borderBottom = "0px";

    
    // console.log("3");

})

// console.log(mybox1.style.);
// console.log(mybox2);
// console.log(mybox3);
// mybox2.style.display = 'none';



