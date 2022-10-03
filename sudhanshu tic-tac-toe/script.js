// Variable Used :-)

let btn = document.getElementById("btn");
let bodydiv = document.getElementById("bodydiv");
let box = document.getElementsByClassName("box");
let logich = document.getElementById("logich");
let start = " ";
let box_div = document.querySelectorAll(".box");
let clear_checker = " ";
let winner_arr = [[1, 2, 3], [4, 5, 6], [7, 8, 9]];
let a, b, c, d = 1;
let turn = "X";

// console.log(box_div);

// Function Used :-)

function start_check() {
    start = "new";
    // console.log(start);
    if (clear_checker == "X" || clear_checker == "O") {

        clear_box();
        clear_checker = " ";
    }
    if (clear_checker == " ") {

        start_game();
    }
}

function clear_box() {
    for (var i = 0; i < 9; i++) {
        box_div[i].innerHTML = " ";
        clear_checker = " ";
    }
    if (turn = "O") {
        turn = "X";
    }
    else {
        turn = "O";
    }
    winner_arr = [[1, 2, 3], [4, 5, 6], [7, 8, 9]];
    d = 1;
}

function winner_check(winner_arr) {
    // console.log("winner_check");
    var div_no;
    for(var i=0; i<3; i++){
        for(var j=0; j<3; j++){
            if(a == i && b == j){
                div_no = 3*i + j;
            }
        }
    }



    for (var i = 0; i < 3; i++) {

        if (winner_arr[i][0] == winner_arr[i][1]) {
            if (winner_arr[i][1] == winner_arr[i][2]) {
                box_div[div_no].classList.add(".winner_box");
                // console.log(logich.innerHTML);
                logich.innerHTML = "WINNER IS " + winner_arr[i][0];
                d = 0;
                return (0);
            }
        }

        if (winner_arr[0][i] == winner_arr[1][i]) {
            if (winner_arr[1][i] == winner_arr[2][i]) {

                // console.log(logich.innerHTML);
                logich.innerHTML = "WINNER IS " + winner_arr[0][i];
                d = 0;
                return (0);
            }
        }

        if (winner_arr[0][0] == winner_arr[1][1] && winner_arr[1][1] == winner_arr[2][2]) {

            // console.log(logich.innerHTML);
            logich.innerHTML = "WINNER IS " + winner_arr[0][0];
            return (0);
        }

        if (winner_arr[0][2] == winner_arr[1][1] && winner_arr[1][1] == winner_arr[2][0]) {

            // console.log(logich.innerHTML);
            logich.innerHTML = "WINNER IS " + winner_arr[0][2];
            d = 0;
            return (0);
        }

    }

    for (var i = 0; i < 3; i++) {
        for (var j = 0; j < 3; j++) {
            if (winner_arr[i][j] == "X" || winner_arr[i][j] == "O") {
                c = true;
            }
            else {
                c = false;
                break;
            }
        }
        if(c == false){
            break;

        }
    }

    if (c == true) {
        logich.innerHTML = "MATCH IS DRAW";
        console.log(winner_arr);
        d = 0;
        return (0);

    }

    // console.log(winner_arr);
}


// Logic Used :-)

// alert("CLICK on START NEW GAME to start a game");
btn.addEventListener("click", start_check);

function start_game() {

    if (start === "new") {

        const box_arr = [];

        // console.log(box_arr);
        for (var i = 0; i < 9; i++) {
            box_arr[i] = box[i];
        }


        box_arr.forEach(add_event);
        logich.innerHTML = `It's Your Turn "` + turn + `"`;

        function add_event(item, index) {
            // console.log(index);
            let ind = index;
            box_arr[index].addEventListener("click", () => {
                // console.log(box_arr[index]);


                switch (index) {

                    case 0:
                        a = 0;
                        b = 0;
                        break;
                    case 1:
                        a = 0;
                        b = 1;
                        break;
                    case 2:
                        a = 0;
                        b = 2;
                        break;
                    case 3:
                        a = 1;
                        b = 0;
                        break;
                    case 4:
                        a = 1;
                        b = 1;
                        break;
                    case 5:
                        a = 1;
                        b = 2;
                        break;
                    case 6:
                        a = 2;
                        b = 0;
                        break;
                    case 7:
                        a = 2;
                        b = 1;
                        break;
                    case 8:
                        a = 2;
                        b = 2;
                        break;

                }
                console.log(winner_arr);

                if (box_arr[ind].innerHTML == "X") {

                    box_arr[ind].innerHTML = "X";

                }
                else if (box_arr[ind].innerHTML == "O") {

                    box_arr[ind].innerHTML = "O";
                }
                else {
                    if (d != 0) {

                        box_arr[ind].innerHTML = turn;
                    }
                    winner_arr[a][b] = turn;
                    if (winner_check(winner_arr) == 0) {

                    }
                    else {
                        if (turn == "X") {
                            turn = "O";
                        }
                        else {
                            turn = "X";
                        }
                        logich.innerHTML = `It's Your Turn "` + turn + `"`;
                        clear_checker = turn;
                    };
                }

            }
            )
        }


    }
}

