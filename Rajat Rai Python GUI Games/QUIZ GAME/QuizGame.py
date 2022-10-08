from tkinter import *
from tkinter import messagebox as mb
import json

class Quiz:
	def __init__(self):
		self.qno=0
		self.disp_title()
		self.disp_ques()
		self.opt_sel=IntVar()
		self.opts=self.radio_buttons()
		self.disp_opt()
		self.buttons()
		self.total_size=len(question)
		self.correct=0

	def disp_res(self):
		
		wrong_count = self.total_size - self.correct
		correct = f"Correct: {self.correct}"
		wrong = f"Wrong: {wrong_count}"
		
		score = int(self.correct / self.total_size * 100)
		result = f"Score: {score}%"
		
		mb.showinfo("Result", f"{result}\n{correct}\n{wrong}")


	def check_ans(self, qno):
		
		if self.opt_sel.get() == answer[qno]:
			return True


	def next_btn(self):
		
		if self.check_ans(self.qno):
			self.correct += 1
		
		self.qno += 1
		
		if self.qno==self.total_size:
			self.disp_res()
			ws.destroy()
		else:
			self.disp_ques()
			self.disp_opt()


	def buttons(self):
		
		next_button = Button(
            ws, 
            text="Next",
            command=self.next_btn,
            width=10,
            bg="#F2780C",
            fg="white",
            font=("ariel",16,"bold")
            )
		
		next_button.place(x=350,y=380)
		
		quit_button = Button(
            ws, 
            text="Quit", 
            command=ws.destroy,
            width=5,
            bg="black", 
            fg="white",
            font=("ariel",16," bold")
            )
		
		quit_button.place(x=700,y=50)


	def disp_opt(self):
		val=0
		self.opt_sel.set(0)
		
		for option in options[self.qno]:
			self.opts[val]['text']=option
			val+=1

	def disp_ques(self):
		
		qno = Label(
            ws, 
            text=question[self.qno], 
            width=60,
            font=( 'ariel' ,16, 'bold' ), 
            anchor= 'w',
			wraplength=700,
			justify='center'
            )
		
		qno.place(x=70, y=100)


	def disp_title(self):
		
		title = Label(
            ws, 
            text="QUIZ GAME",
            width=50, 
            bg="#F2A30F",
            fg="white", 
            font=("ariel", 20, "bold")
            )
		
		title.place(x=0, y=2)


	def radio_buttons(self):
		
		q_list = []
		
		y_pos = 150
		
		while len(q_list) < 4:
			
			radio_btn = Radiobutton(
                ws,
                text=" ",
                variable=self.opt_sel,
                value = len(q_list)+1,
                font = ("ariel",14)
                )
			q_list.append(radio_btn)
			
			radio_btn.place(x = 100, y = y_pos)
			
			y_pos += 40
		
		return q_list

ws = Tk()

ws.geometry("800x450")

ws.title("Quiz Game")

with open('data.json') as f:
	data = json.load(f)

question = (data['question'])
options = (data['options'])
answer = (data[ 'answer'])

quiz = Quiz()

ws.mainloop()
