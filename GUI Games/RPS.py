import random
import tkinter as tk
from tkinter import *
import os
from PIL import Image, ImageTk

choice = ['stone', 'paper', 'scissors']

win = tk.Tk()
win.configure(bg="light blue")
win.geometry("650x550")

result=StringVar()
score_com=IntVar()
score_you=IntVar()

#------------------------ FUNCTIONS----------------------
def draw(x):
  out="                     Its a Draw"
  result.set(out)

def player_win(x):
  out = "                     You Won"
  result.set(out)
  score_you.set(score_you.get() +1)

def computer_wins(x):
  out = "                     You Lost"
  result.set(out)
  score_com.set(score_com.get() + 1)

def user_choice_stone():
  random_choice = random.choice(choice)

  if random_choice=='stone':
    draw(random_choice)

  elif random_choice=='paper':
    player_win(random_choice)
  
  else:
    computer_wins(random_choice)
    
def user_choice_paper():
  random_choice= random.choice(choice)

  if random_choice=='paper':
    draw(random_choice)

  elif random_choice=='scissors':
    player_win(random_choice)
  
  else:
    computer_wins(random_choice)

def user_choice_scissors():
  random_choice= random.choice(choice)

  if random_choice=='scissors':
    draw(random_choice)

  elif random_choice=='stone':
    player_win(random_choice)
  
  else:
    computer_wins(random_choice)  

# ------------------------IMAGE DATA----------------------

stone_image1 = Image.open("C:\\Users\\Paras\\Desktop\\py\\data\\stone.jpg")
stone_image = ImageTk.PhotoImage(stone_image1)

paper_image1 = Image.open("C:\\Users\\Paras\\Desktop\\py\\data\\paper.jpg")
paper_image = ImageTk.PhotoImage(paper_image1)

scissors_image1 = Image.open("C:\\Users\\Paras\\Desktop\\py\\data\\scissors.jpg")
scissors_image = ImageTk.PhotoImage(scissors_image1)
# ---------------------------------------------------------

msg=Label(win, text='Make Your Choice',font=("Courier",25),relief=GROOVE)
msg.place(relx=0.5, rely=0.09, anchor =CENTER)

user_input_stone = Button(win, width=150, height=150, image=stone_image, command=user_choice_stone, relief=GROOVE)
user_input_stone.place(relx=0.2, rely=0.35, anchor=CENTER)

user_input_paper = Button(win, width=150, height=150, image=paper_image, command=user_choice_paper, relief=GROOVE)
user_input_paper.place(relx=0.5, rely=0.35, anchor=CENTER)

user_input_scissors = Button(win, width=150, height=150, command=user_choice_scissors,image=scissors_image, relief=GROOVE)
user_input_scissors.place(relx=0.8, rely=0.35, anchor=CENTER)

ent1 = Entry(win, textvariable=result, width=27, font=('Ubuntu', 24), relief = GROOVE)
ent1.place(relx=0.5, rely=0.65, anchor=CENTER) 

ent2 = Entry(win, textvariable=score_you, width=2, font=('Ubuntu', 24), relief = GROOVE)
ent2.place(relx=0.3, rely=0.85, anchor=CENTER) 

msg2 = Label(win, text=' You ', font=("Courier", 8), relief=GROOVE)
msg2.place(relx=0.3, rely=0.79, anchor=CENTER)

msg3 = Label(win, text=' Com ', font=("Courier", 8), relief=GROOVE)
msg3.place(relx=0.7, rely=0.79, anchor=CENTER)

msg4 = Label(win, text=' Score Board ', font=("Courier", 18), relief=GROOVE)
msg4.place(relx=0.5, rely=0.85, anchor=CENTER)

ent3 = Entry(win, textvariable=score_com, width=2,
             font=('Ubuntu', 24), relief=GROOVE)
ent3.place(relx=0.7, rely=0.85, anchor=CENTER)

stop = tk.Button(win, text='stop', width=91, command=win.destroy,
                 bg="red", activebackground="red", relief=GROOVE)
stop.place(relx=0.5, rely=1, anchor=S)

win.mainloop()
