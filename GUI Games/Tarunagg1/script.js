console.log('Enjoy game!!');

const totalScores = {
    computerScore: 0,
    playerScore: 0
}

function getComputerChoice() {
    const rpsChoice = ['Rock', 'Paper', 'Scissors'];
    const randomChoice = Math.floor(Math.random() * 3);
    return rpsChoice[randomChoice];
}

function getResults(playerChoice, computerChoice) {
    let score;

    if (playerChoice === computerChoice) {
        score = 0;
    } else if (playerChoice === 'Rock' && computerChoice === 'Scissors') {
        score = 1

    } else if (playerChoice === "Paper" && computerChoice === "Rock") {
        score = 1

    } else if (playerChoice === "Scissors" && computerChoice === "Paper") {
        score = 1
        // Otherwise human loses (aka set score to -1)
    } else {
        score = -1
    }

    // return score
    return score
}



function showResult(score, playerChoice, computerChoice) {
    switch (score) {
        case -1:
            result.innerText = `You Lose!`
            break;
        case 0:
            result.innerText = `It's a Draw!`
            break;
        case 1:
            result.innerText = `You Win!`
            break;
    }

    let playerScore = document.getElementById('player-score')
    let hands = document.getElementById('hands')
    playerScore.innerText = `${Number(playerScore.innerText) + score}`
    hands.innerText = `👱 ${playerChoice} vs 🤖 ${computerChoice}`
}

function onClickRPS(playerChoice) {
    // console.log(playerChoice);
    const compChoice = getComputerChoice();
    let score = getResults(playerChoice, compChoice);
    showResult(score, playerChoice, compChoice)
}

function playGame() {
    const rpsButtons = document.querySelectorAll('.rpsButton');

    rpsButtons.forEach((rpsButton) => {
        rpsButton.onclick = () => onClickRPS(rpsButton.value);
    })
}

function endGame() {
    let playerScore = document.getElementById('player-score')
    let hands = document.getElementById('hands')
    let result = document.getElementById('result')
    playerScore.innerText = ''
    hands.innerText = ''
    result.innerText = ''
}

endGameButton.addEventListener('click', endGame);

playGame();