from turtle import Turtle 
ALIGNMENT="center"
FONT=("Courier",24,"normal")
with "Snake Game\data.txt" as DATA:
    HS=DATA.read()

class Scoreboard(Turtle):

    def __init__(self):
        super().__init__()
        self.score=0
        with open("Snake Game\data.txt") as data:
            self.highscore=int(data.read())
        self.penup()
        self.goto(0,265)
        self.color("white")
        self.hideturtle()
        self.update_scoreboard()
    
    def update_scoreboard(self):
        self.clear()
        self.write(f"Score: {self.score} High Score: {self.highscore}",align=ALIGNMENT,font=FONT)


    def increase_scoreboard(self):
        self.score+=1
        self.update_scoreboard()

    def reset(self):
        if self.score > self.highscore:
            self.highscore = self.score
            with open("Snake Game\data.txt", mode="w") as data:
                data.write(f"{self.highscore}")
        self.score = 0
        self.update_scoreboard()

    """def game_over(self):
        self.goto(0,0)
        self.write(f"GAME OVER",align=ALIGNMENT,font=FONT)"""
