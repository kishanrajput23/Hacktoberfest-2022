const buttons = document.querySelectorAll('.btn');
const popup = document.querySelector('.popup');
const newGame = document.getElementById("newGame");
const restart = document.getElementById("restart");
const message = document.getElementById("message");
const scoreX = document.getElementById("scoreX");
const scoreO = document.getElementById("scoreO");

// SCORE
let scoreXVal=0,scoreOVal=0;


// Array Win Indexes
const win = [
   [0,1,2],
   [0,3,6],
   [2,5,8],
   [6,7,8],
   [3,4,5],
   [1,4,7],
   [0,4,8],
   [2,4,6], 
];

// O Moves first
let Omove = true;
let counter=0;

const disableButtons = ()=>{
    buttons.forEach((move)=>{
          move.disabled = true;
    });
    // Show Popup
    popup.classList.remove('hide');
}

// When a player wins enable all buttons and clear
const enableButtons = ()=>{
    buttons.forEach((move)=>{
        move.innerHTML = "";
        move.disabled = false;
    });
    // disable popup
    popup.classList.add('hide');
}

// New Game
newGame.addEventListener('click' , ()=>{
    counter = 0;
    enableButtons(); 
 });

// Restart Game
restart.addEventListener('click' , ()=>{
   counter = 0;
   enableButtons(); 
});

const playerDraw = ()=>{
    disableButtons();
    message.innerHTML = "GAME DRAW";
}

const playerWins = (player)=>{
     disableButtons();
     if(player == 'O'){
        message.innerHTML = "O WINS";
        scoreOVal++;
        scoreO.innerHTML = scoreOVal;

     }else{
        message.innerHTML = "X WINS";
        scoreXVal++;
        scoreX.innerHTML = scoreXVal;
     }
}

// Check Win Function
const checkWin = () => {
    // go through the array win
    for(let i of win){

        let [move1,move2,move3] = [
            buttons[i[0]].innerHTML, 
            buttons[i[1]].innerHTML, 
            buttons[i[2]].innerHTML 
        ];

        // Move should not be empty
        if(move1 != "" && move2 != "" && move3 !=""){
            if(move1 == move2 && move2 == move3){
                    //   player wins
                    playerWins(move1);
            }
        }
    }
}


// Display move on screen
buttons.forEach((move)=>{
    move.addEventListener('click',()=>{
         if(Omove){
            Omove = false;
            move.innerHTML ="O";
            // user canno change this again
            move.disabled = true;
         }else{
            Omove = true;
            move.innerHTML ="X";
            // user canno change this again
            move.disabled = true;
         }
        //  Increment Counter for total moves
        counter++;

        if(counter == 9){
            // Draw if counter reaches 9
            playerDraw();
        }

        // Check Win Combination
        checkWin();
    });
});

// Enable Buttons on Load
window.onload = enableButtons();
score = 0;