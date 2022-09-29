from os import system
board = [" " for x in range(9)]

def print_board():
    print("""
    {}|{}|{}
    -----
    {}|{}|{}
    -----
    {}|{}|{}
    """.format(board[0],board[1],board[2],board[3],board[4],board[5],board[6],board[7],board[8])
    )

def print_board_position_num():
    print("""
    {}|{}|{}
    -----
    {}|{}|{}
    -----
    {}|{}|{}
    """.format(1,2,3,4,5,6,7,8,9)
    )

def clear():
    _ = system("cls")

def player_move(icon):
    if icon == "X":
        number = 1
    else:
        number = 2
    print("Your turn player {}.".format(number))
    player_choice = int(input("Enter pos between [1-9]:"))
    if board[player_choice-1] == " ":
        board[player_choice-1] = icon
    else:
        print("Place is taken!")
    print_board()

def is_victory(icon):
    if (board[0]==icon and board[1]==icon and board[2] == icon) or \
       (board[3]==icon and board[4]==icon and board[5] == icon) or \
       (board[6]==icon and board[7]==icon and board[8] == icon) or \
       (board[0]==icon and board[3]==icon and board[6] == icon) or \
       (board[1]==icon and board[4]==icon and board[7] == icon) or \
       (board[2]==icon and board[5]==icon and board[8] == icon) or \
       (board[0]==icon and board[4]==icon and board[8] == icon) or \
       (board[2]==icon and board[4]==icon and board[6] == icon):
        return True
    else:
        return False
def is_draw():
    if " " not in board:
        return True
    else:
        return False


def tic_tac_toe():
    global board
    board = [" " for x in range(9)]
    clear()
    print("Board with position number")
    print_board_position_num()
    while True:
        player_move("X")
        if is_victory("X"):
            print("Congratulation Player 1 won!")
            break
        if is_draw():
            print("Oops Game Draw")
            break
        player_move("O")
        if is_victory("O"):
            print("Congratulation Player 2 won!")
            break
        
    

def main():
    clear()
    continue_game = True
    while continue_game:
        tic_tac_toe()
        should_continue= input("Would you like to play again? (y/n) ").lower()
        if should_continue == "n":
            continue_game = False

if __name__=="__main__":
    main()
