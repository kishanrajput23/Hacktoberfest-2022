import tkinter as tk
from random import randint

from PIL import Image, ImageTk

# Universal constant ..is the dimension of the image being moved
MOVE_INCREMENT = 20
MOVE_PER_SECOND = 15
# Universal constant ..is the time delay between each movement of snake
GAME_SPEED = 1000 // MOVE_PER_SECOND


class Snake(tk.Canvas):
    def __init__(self):
        super().__init__(height=620, width=600, background='black', highlightthickness=0)

        """ each position is(x,y) and has 20 difference on x as image is 20 pixel wide """
        self.snake_position = [(100, 100), (80, 100), (60, 100)]  # first element head and other are body
        self.food_position = self.set_new_food_position()
        self.score = 0

        # initial direction of the movement
        self.direction = 'Right'

        # binds any key to call the on_key press method
        self.bind_all('<Key>', self.on_key_press)

        self.load_assets()
        self.create_objects()
        self.after(GAME_SPEED, self.perform_actions)  # calling initially

    def load_assets(self):
        try:
            self.snake_body_image = Image.open('./assets/snake.png')
            self.snake_body = ImageTk.PhotoImage(self.snake_body_image)

            self.food_image = Image.open('./assets/food.png')
            self.food = ImageTk.PhotoImage(self.food_image)
        except IOError as error:
            print(error)
            root.destroy()

    def create_objects(self):
        """Places objects of the game in the canvas"""
        # Create score board
        self.create_text(
            45, 12, text=f'Score:{self.score}', tag='score', fill='#fff', font=('TkDefaultFont', 14)
        )

        # Create snake
        for x_position, y_position in self.snake_position:
            self.create_image(x_position, y_position, image=self.snake_body, tag='snake')

        # Create food
        # *self.food_position de structures tuple and yields self.food_position[0], self.food_position[1]
        self.create_image(*self.food_position, image=self.food, tag='food')

        # Create Boundary
        self.create_rectangle(7, 27, 593, 613, outline='#525d69')

    def move_snake(self):
        # load the first element of the snake body
        head_x_position, head_y_position = self.snake_position[0]

        if self.direction == 'Left':
            # move the head one place backward...towards left i.e 20 pixel forward
            new_head_position = head_x_position - MOVE_INCREMENT, head_y_position
        elif self.direction == 'Right':
            # move the head one place forward...towards right i.e 20 pixel forward
            new_head_position = head_x_position + MOVE_INCREMENT, head_y_position
        elif self.direction == 'Down':
            # move the head one place downward...towards bottom i.e 20 pixel forward
            new_head_position = head_x_position, head_y_position + MOVE_INCREMENT
        elif self.direction == 'Up':
            # move the head one place upward...top i.e 20 pixel forward
            new_head_position = head_x_position, head_y_position - MOVE_INCREMENT

        """As the length of snake constant without eating food remove the end of snake 
          body to make it constant"""
        self.snake_position = [new_head_position] + self.snake_position[:-1]

        """ As the image is static in canvas so to update the position of the image body 
           dynamically into the canvas we have to use this method"""
        """This takes the new position of the head and body according to the above written logic
            using the zip method and update it into the canvas using coords method"""
        for segment, position in zip(self.find_withtag('snake'), self.snake_position):
            self.coords(segment, position)

    def perform_actions(self):
        """calling return stops the game and self.after is not called"""
        if self.check_collisions():
            self.end_game()
            return

        self.check_food_collision()
        self.move_snake()

        """argument not passed with function because we want to call the function after 75 milli second
           not the value which function returns after 75 milli second"""
        self.after(GAME_SPEED, self.perform_actions)

    def check_collisions(self):
        """check_collision returns True if any of the given conditions are true"""
        head_x_position, head_y_position = self.snake_position[0]
        """(head_x_position, head_y_position) in self.snake_position[1:] check whether the head of the snake
          is in the body of the snake i.e snake eaten itself"""
        return (
                head_x_position in (0, 600)  # check the x axis part touched boundary
                or head_y_position in (20, 620)  # check the  axis part touched boundary
                or (head_x_position, head_y_position) in self.snake_position[1:]
        )

    def on_key_press(self, e):
        """it gives the symbol associated with the pressed key"""
        new_direction = e.keysym

        """avoiding snake to eat itself on press of two opposite keys"""
        all_directions = ('Up', 'Down', 'Left', 'Right')
        opposites = ({'Up', 'Down'}, {'Right', 'Left'})
        if (new_direction in all_directions) and {new_direction, self.direction} not in opposites:
            self.direction = new_direction

    def check_food_collision(self):
        if self.snake_position[0] == self.food_position:
            self.score = self.score + 1
            self.snake_position.append(self.snake_position[-1])

            # Adds the image of snake body
            self.create_image(
                *self.snake_position[-1], image=self.snake_body, tag='snake'
            )

            self.food_position = self.set_new_food_position()
            self.coords(self.find_withtag('food'), self.food_position)

            # Updates the value of the score variable
            score = self.find_withtag('score')
            self.itemconfigure(score, text='Score: {self.score}')

    def set_new_food_position(self):
        """generates random food position unless we collide"""
        while True:
            x_position = randint(1, 29) * MOVE_INCREMENT
            y_position = randint(3, 30) * MOVE_INCREMENT
            food_position = (x_position, y_position)

            """Checks whether the randomly generated snake position and random generated food position
               are not on the same place and if happens so it generates a new random position for snakes food"""
            if food_position not in self.snake_position:
                return food_position

    def end_game(self):
        self.delete('all'),
        self.create_text(
            self.winfo_width() / 2,
            self.winfo_height() / 2,
            text='Game Over! You Scored {self.score}',
            fill='#fff',
            font=('TkDefaultFont', 24)
        )


if __name__ == '__main__':
    root = tk.Tk()
    root.title('Snake')
    root.resizable(False, False)

    board = Snake()
    board.pack()

    root.mainloop()
