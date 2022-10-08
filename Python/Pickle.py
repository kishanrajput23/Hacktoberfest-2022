import pickle
  
# Create a variable
myvar = [{'This': 'is', 'Example': 2}, 'of',
         'serialisation', ['using', 'pickle']]
  
# Open a file and use dump()
with open('file.pkl', 'wb') as file:
      
    # A new file will be created
    pickle.dump(myvar, file)
    
    
    
import pickle
  
# Open the file in binary mode
with open('file.pkl', 'rb') as file:
      
    # Call load method to deserialze
    myvar = pickle.load(file)
  
    print(myvar)

    
    
    
# Dump Load Multiple Objects 

import pickle
with open('file.pkl', 'wb') as file:
    pickle.dump((myvar1,myvar2), file)
    
with open('file.pkl', 'rb') as file:
    myvar1,myvar2 = pickle.load(file)
