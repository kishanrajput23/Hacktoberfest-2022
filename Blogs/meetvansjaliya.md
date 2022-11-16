## Understanding Confusion Matrix

![image](https://user-images.githubusercontent.com/61739799/193335705-f0d1c5bc-652b-4bd2-917b-38d0b4419ce1.png)

- When we get the data, after data cleaning, pre-processing and wrangling, the first step we do is to feed it to an outstanding model and of course, get output in probabilities. But hold on! How in the hell can we measure the effectiveness of our model. Better the effectiveness, better the performance and that’s exactly what we want. And it is where the Confusion matrix comes into the limelight. Confusion Matrix is a performance measurement for machine learning classification.

### This blog aims to answer following questions:
- What the confusion matrix is and why you need it?
- How to calculate Confusion Matrix for a 2-class classification problem?

Today, let’s understand the confusion matrix once and for all.

### What is Confusion Matrix and why you need it?

- Well, it is a performance measurement for machine learning classification problem where output can be two or more classes. It is a table with 4 different combinations of predicted and actual values.

![image](https://user-images.githubusercontent.com/61739799/193336243-58a15071-d8d6-43a0-b30b-f0344f418c44.png)


- It is extremely useful for measuring Recall, Precision, Specificity, Accuracy and most importantly AUC-ROC Curve.

- Let’s understand TP, FP, FN, TN in terms of pregnancy analogy.

![image](https://user-images.githubusercontent.com/61739799/193336347-c0001f55-e297-4afc-afcc-ce0f891f653d.png)

### True Positive:

- Interpretation: You predicted positive and it’s true.

- You predicted that a woman is pregnant and she actually is.

### True Negative:

- Interpretation: You predicted negative and it’s true.

- You predicted that a man is not pregnant and he actually is not.

### False Positive: (Type 1 Error)

- Interpretation: You predicted positive and it’s false.

- You predicted that a man is pregnant but he actually is not.

### False Negative: (Type 2 Error)

- Interpretation: You predicted negative and it’s false.

- You predicted that a woman is not pregnant but she actually is.

- Just Remember, We describe predicted values as Positive and Negative and actual values as True and False.

![image](https://user-images.githubusercontent.com/61739799/193336728-91381d6c-66c8-48b6-a5c3-4c5053c64519.png)


### How to Calculate Confusion Matrix for a 2-class classification problem?

Let’s understand confusion matrix through math.

![image](https://user-images.githubusercontent.com/61739799/193336810-a80a7ac9-9d21-4228-9681-8d5567d0af34.png)


### Recall

![image](https://user-images.githubusercontent.com/61739799/193336846-25df7f79-d4d6-4561-944a-62fc63d588b7.png)

- Out of all the positive classes, how much we predicted correctly. It should be high as possible.

### Precision

![image](https://user-images.githubusercontent.com/61739799/193336908-390cfbbf-23c6-4a4a-916a-e8cb842edcfd.png)


- Out of all the positive classes we have predicted correctly, how many are actually positive.

### Accuracy

![image](https://user-images.githubusercontent.com/61739799/193336962-a2bf225c-fc4d-4013-8612-ab62565ea27e.png)

- Out of all the classes, how much we predicted correctly, which will be, in this case 4/7. It should be high as possible.

### F-measure

![image](https://user-images.githubusercontent.com/61739799/193337029-0aa80c91-6257-4cdb-9d9b-c2b8281b137f.png)

- It is difficult to compare two models with low precision and high recall or vice versa. So to make them comparable, we use F-Score. F-score helps to measure Recall and Precision at the same time. It uses Harmonic Mean in place of Arithmetic Mean by punishing the extreme values more.

---------
```
I hope I’ve given you some basic understanding on what exactly is confusing matrix. If you like this post, a tad of extra motivation will be helpful by giving this post some claps 👏. I am always open for your questions and suggestions. You can share this on Facebook, Twitter, Linkedin, so someone in need might stumble upon this.

You can reach me at:

LinkedIn : https://www.linkedin.com/in/meet-vansjaliya/

Github : https://github.com/meetvansjaliya

Thanks for Reading!
```
