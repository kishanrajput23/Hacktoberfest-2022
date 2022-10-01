
# Securevote

This project makes use of opencv library to authenticate the faces of people and let them go for vote, adding one more layer of security




# You need to have these installed
### Python (3.10.4)
### pip (22.1)
### streamlit-authenticator 
### opencv (4.5.5)
### numpy (1.22.3)
### dlib 
pip install https://github.com/jloh02/dlib/releases/download/v19.22/dlib-19.22.99-cp310-cp310-win_amd64.whl
### face_recognition
### streamlit (1.9.0)
### install streamlit-lottie 
                pip install streamlit-lottie
### pip install requests






## Take care about

#### To run the project write command
open index.py file in vs code and write the below command
```
streamlit run index.py
```
#### In faces.py in line no. 93 
```camera = cv2.VideoCapture(1)``` 
This 1 should be 0 ->if you use your primary webcam of your computer.
If you use external webcam then use 1.

#### In line no. 168 
``` <form action="https://formsubmit.co/capitalgabru@gmail.com```
This is my email id, you can use your email Id to receive the form's data.



## Screenshots

![Login Page](https://github.com/Ujjwal990/securevote/blob/master/LoginPageSS.jpg)
![Welcome Page](https://github.com/Ujjwal990/securevote/blob/master/WelcomePageSS1.jpg)
![Welcome Page 2](https://github.com/Ujjwal990/securevote/blob/master/WelcomePageSS2.jpg)
![Face Recognised](https://github.com/Ujjwal990/securevote/blob/master/FaceRecognisedSS.jpg)
![Contact Us Form](https://github.com/Ujjwal990/securevote/blob/master/ContactUsFormSS.jpg)
![Form Received by developer](https://github.com/Ujjwal990/securevote/blob/master/ContactUsFormReceivedByDeveloper.jpg)
![Details of Recognised Voters](https://github.com/Ujjwal990/securevote/blob/master/recordsCSVss.jpg)
![Details of Recognised Voters](https://github.com/Ujjwal990/securevote/blob/master/all%20users%20with%20usernames.jpg)


## 🚀 About Me
I'm a full stack developer...

email-Id : ujjwalkumar080302@gmail.com

Machine learning enthusiast

Blockchain enthusiast
