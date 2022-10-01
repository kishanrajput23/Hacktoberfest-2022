//TIC-TAC-TOE GAME IN C

#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>


void PrintPlayBoard(); //prints board in play
int CheckPlay(); //checks win/lose and draw conditions
void SetBoard(); //resets the board for a new game
int TTT(); // the actual play code 
void Scoreboard(); //shows the final scores of players
void Menu(); //choose to play or exit game


char TTT_index[10] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}; //array of grid values of Tic-Tac-Toe Board 
int p1=0,p2=0; //p1 and p2 are player1 and player2 scores


int main()
{
    printf("Hello Players !\nWelcome to the game of Tic-Tac-Toe!\n");
    Menu();
    return 0;
}


void Menu(){
    int opt;    //this is a menu option and allows us to choose play or exit
    do
    {
        printf("\n\nGAME MENU\n");
        printf("\n(1)Play Tic-Tac-Toe\n(0)Exit Game\nEnter Option : ");
        scanf("%d", &opt);
        switch(opt){
            case 0:
            {
                printf("\n\nGame Over!!\nYou have Exited the Game!!\n");
                sleep(1);
                exit(0);
                break;
            }
            case 1:
            {
                SetBoard();
                TTT();
                break;
            }
            default:
            {
                printf("Wrong Option!! Please enter a valid option.\n\n");
                break;
            }
        }
    }while(opt != 0); 
    printf("\nThank you for playing Tic-Tac-Toe!\n");  //End game
}


int TTT(){
    int player = 1,result,choice; 
    char symbol, ch;
    do
    {
    printf("\n\n");
   
        PrintPlayBoard();

        player = (player % 2 !=0) ? 1 : 2;
        printf("\nIt's your turn, Player %d\nEnter your choice : ", player);
        scanf("%d", &choice);

        symbol = (player == 1) ? 'X' : 'O';

        if(choice < 1 || choice > 9){   //if the choices do not match TTT_index values
            printf("Invalid move! Want to move to main menu(m) or continue game(c) ? : "); //continue means the current game in play will resume
            scanf(" %c",&ch);
            if(ch == 'c'){
               printf("\nOkay! Let's continue.\n");
               player++;
            }
            else{
               printf("\n\nGame Over!!\nGame progress will be lost!!\n");
               Menu();
            }
            player--;
        }
        else{
            if((TTT_index[1]=='1' && choice == 1)||(TTT_index[2]=='2' && choice == 2)||(TTT_index[3]=='3' && choice == 3)||(TTT_index[4]=='4' && choice == 4)||(TTT_index[5]=='5' && choice == 5)||(TTT_index[6]=='6' && choice == 6)||(TTT_index[7]=='7' && choice == 7)||(TTT_index[8]=='8' && choice == 8)||(TTT_index[9]=='9' && choice == 9)){
                TTT_index[choice] = symbol;
                player++;   //only update a players turn when it is in unique position
            }  
        }
        result = CheckPlay(); 

    }while(result == -1);  

    PrintPlayBoard(); //Shows updated grid

    // To declare result (Win/Lose/Draw)
    if(result == 1)
    {
        printf("And the winner is .....\n");
        sleep(1);
        printf("Player %d {%c}\n", player-1, symbol);
        if((player-1)==1 ){
            p1=p1+1;  //if player 1 wins , increment the score
            Scoreboard(p1,p2);  // Display Scores
        }
        else{
            p2=p2+1;  //if player 2 wins , increment the score
            Scoreboard(p1,p2);
        }
    }
    else
    {
        printf("The Game is a Draw!\n");
        Scoreboard(p1,p2);
    }
}


void Scoreboard(int p1, int p2){
    printf("\nScoreboard\n");
    printf("\n  Players   |  Score");
    printf("\n____________|________");
    printf("\n            |      ");
    printf("\n  Player 1  |    %d", p1);
    printf("\n  Player 2  |    %d", p2);
}


int CheckPlay()
{
    // There are 8 winning conditions. So, the first 8 statements check for a win, either with X or O. 
    //The 9th statement checks for a draw
    //The 10th statement means the game is still in progress
    if (TTT_index[1] == TTT_index[2] && TTT_index[2] == TTT_index[3]){  	//(1,2,3)
        return 1;
    }
    else if (TTT_index[4] == TTT_index[5] && TTT_index[5] == TTT_index[6]){	//(4,5,6)
        return 1;
    }
    else if (TTT_index[7] == TTT_index[8] && TTT_index[8] == TTT_index[9]){	//(7,8,9)
        return 1;
    }
    else if (TTT_index[1] == TTT_index[5] && TTT_index[5] == TTT_index[9]){	//(1,5,9)
        return 1;
    }
    else if (TTT_index[3] == TTT_index[5] && TTT_index[5] == TTT_index[7]){	//(3,5,7)
        return 1;
    }
    else if (TTT_index[1] == TTT_index[4] && TTT_index[4] == TTT_index[7]){	//(1,4,7)
        return 1;
    }
    else if (TTT_index[2] == TTT_index[5] && TTT_index[5] == TTT_index[8]){	//(2,5,8)
        return 1;
    }
    else if (TTT_index[3] == TTT_index[6] && TTT_index[6] == TTT_index[9]){	//(3,6,9)
        return 1;
    }
    else if (TTT_index[1] != '1' && TTT_index[2] != '2' && TTT_index[3] != '3' &&
        TTT_index[4] != '4' && TTT_index[5] != '5' && TTT_index[6] != '6' && TTT_index[7]
        != '7' && TTT_index[8] != '8' && TTT_index[9] != '9'){
        return 0;
    }
    else{
        return  -1;
    }
}


void PrintPlayBoard()
{
    //This is how the board will look on the output screen
    printf("Player 1: {X}   vs.  Player 2: {O}\n");
    printf(" _____ _____ _____\n");
    printf("|     |     |     |\n");
    printf("|  %c  |  %c  |  %c  |\n", TTT_index[1], TTT_index[2], TTT_index[3]);
    printf("|_____|_____|_____|\n");
    printf("|     |     |     |\n");
    printf("|  %c  |  %c  |  %c  |\n", TTT_index[4], TTT_index[5], TTT_index[6]);
    printf("|_____|_____|_____|\n");
    printf("|     |     |     |\n");
    printf("|  %c  |  %c  |  %c  |\n", TTT_index[7], TTT_index[8], TTT_index[9]);
    printf("|_____|_____|_____|\n\n");
}


void SetBoard()
{
    for(int i = 0; i <= 9; i++)
    {
        TTT_index[i] = i + '0';
    }
}