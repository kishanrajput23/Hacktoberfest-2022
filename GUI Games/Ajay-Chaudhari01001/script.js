 const game = () => {
	let pScore = 0;
	let cScore = 0;


	// start the game 
	const startGame = () => {
		const playBtn = document.querySelector(".intro .btn");
		const introScreen = document.querySelector(".intro");
		const match = document.querySelector(".match");

		playBtn.addEventListener("click", () => {
			introScreen.classList.add("fadeOut");
			match.classList.add("fadeIn");
		});
	};

	// play game 
	const playMatch = () =>{
		const options = document.querySelectorAll(".options .btn");
		const pHand = document.querySelector(".player-hand");
		const cHand = document.querySelector(".computer-hand");
		const hands = document.querySelectorAll(".hands img");

		hands.forEach(hand => {
		  hand.addEventListener("animationend", function() {
			this.style.animation = "";
		  });
		});
		//C
		
		// computer options
		const computerOptions = ["Rock", "Paper", "Scissor"];

		options.forEach(option => {
			option.addEventListener("click", function() {
				// computer choice
				const computerNumber = Math.floor(Math.random()*3);
				const computerChoice = computerOptions[computerNumber];
				// here is where we call compare hands

				setTimeout (() =>{
					compareHands(this.textContent, computerChoice);
				// update images
				// console.log(this.textContent);
				// console.log(computerChoice);
				pHand.src = `./images/${this.textContent}.png`;
				cHand.src = `./images/${computerChoice}.png`;
				}, 2000);

				pHand.style.animation = "shakePlayer 2s ease";
				cHand.style.animation = "shakeComputer 2s ease";
			});
		});
	};

	const updateScore = () => {
		const playerScore = document.querySelector(".player-score p");
		const computerScore = document.querySelector(".computer-score p");
		playerScore.textContent = pScore;
		computerScore.textContent = cScore;
	  };

	const compareHands = (playerChoice, computerChoice) =>{
		const winner = document.querySelector(".winner");
		// for a tie game
		if(playerChoice === computerChoice){
			winner.textContent = "Game Tie !!";
			return;
		}
		// check for rock
		if(playerChoice === 'Rock'){
			if(computerChoice === "Scissor"){
				winner.textContent = "Player Win 😊😊";
				pScore++;
				updateScore();
				return;
			}else{
				winner.textContent = "Computer Win 💻💻";
				cScore++;
				updateScore();
				return;
			}
		}

		//check for the paper
		if(playerChoice === 'Paper'){
			if(computerChoice === "Scissor"){
				winner.textContent = "Computer Win 💻💻";
				cScore++;
				updateScore();
				return;
			}else{
				winner.textContent = "Player Win 😊😊";
				pScore++;
				updateScore();
				return;
			}
		}

		//check for the scissor
		if(playerChoice === 'Scissor'){
			if(computerChoice === "Rock"){
				winner.textContent = "Computer Win 💻💻";
				cScore++;
				updateScore();
				return;
			}else{
				winner.textContent = "Player Win 😊😊";
				pScore++;
				updateScore();
				return;
			}
		}
	}

	// call the all inner function
	startGame();
	// updateScore();
	playMatch();
 }


 // call the game function first time 
 game();