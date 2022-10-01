import pickle
from pathlib import Path

import streamlit_authenticator as stauth

names = ["Ujjwal Kumar", "Lokesh Yadav", "Saroj Kumari"] #This is the list of users who are allowed to access the application
usernames = ["Ujjwal", "Lokesh", "Ankit"]
passwords = ["Ujjwal@123", "Lokesh!123", "Ankit#123"]

hashed_passwords = stauth.Hasher(passwords).generate() #Converting the plane text passwords into hashed passwords for security using bcrypt

file_path = Path(__file__).parent / "safe_passwords.pkl"  #storing hashed passwords in .pkl file
with file_path.open("wb") as file:  #opening file in "wb" write binary mode and dump the passwords in .pkl file
    pickle.dump(hashed_passwords, file)