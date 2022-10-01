let computerGuess;
let userGuess = [];
let userGuessUpdate = document.getElementById('textOutput');
let userNumberUpdate = document.getElementById('inputBox');
let audio = new Audio('gamesound.mp3');
let winaudio = new Audio('winsound.mp3');
let overaudio = new Audio('gameoversound.mp3');


const init = () => {
    computerGuess = Math.floor(Math.random()*100);
    
    document.getElementById('newGameButton').style.display = "none";
    document.getElementById('gameArea').style.display = "none";
}

const newGameBegin = () =>{
    audio.play()
    window.location.reload();
}
const startGame = () => {
    document.getElementById('welcomeScreen').style.display = "none";
    document.getElementById('gameArea').style.display = "block";
}

const startNewGame = () => {
    audio.play()
    document.getElementById('newGameButton').style.display = "inline";
    userNumberUpdate.setAttribute('disabled', true);
}

const compareguess = () => {
    const userNumber = Number(document.getElementById('inputBox').value);
    userGuess = [...userGuess, userNumber];
    document.getElementById('guesses').innerHTML = userGuess;

    // check the value low or high
    if(userGuess.length < maxGuess) {
        audio.play()
        if(userNumber > computerGuess){
            userGuessUpdate.innerHTML = "Your guess is High😯";
            userNumberUpdate.value = "";
        }
        else if(userNumber < computerGuess){
            userGuessUpdate.innerHTML = "Your guess is Low 😔";
            userNumberUpdate.value = "";
        }
        else{
            userGuessUpdate.innerHTML = "It's Correct 🏆😃😄";
            winaudio.play()
            userNumberUpdate.value = "";
            startNewGame();
        }
    }
    else{
        if(userNumber > computerGuess){
            overaudio.play()
            userGuessUpdate.innerHTML = "Your Lose!! correct number was " +computerGuess;
            userNumberUpdate.value = "";
            startNewGame();
        }
        else if(userNumber < computerGuess){
            overaudio.play()
            userGuessUpdate.innerHTML = "Your Lose!! correct number was "+computerGuess;
            userNumberUpdate.value = "";
            startNewGame();
        }
        else{
            userGuessUpdate.innerHTML = "It's Correct 🏆😃😄";
            winaudio.play()
            userNumberUpdate.value = "";
            startNewGame();
        }
    }

    document.getElementById('attempts').innerHTML = userGuess.length;
}

const easyMode = () =>{
    audio.play()
    maxGuess = 10;
    startGame();
}

const hardMode = () =>{
    audio.play()
    maxGuess = 5;
    startGame();
}

