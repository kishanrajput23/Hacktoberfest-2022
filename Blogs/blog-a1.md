# Brain Tumor Detection Using CNN
## Introduction
- The brain is one of the most important and vital part of a human body and it is responsible for thinking. We cannot think of a like without the brain. It has various parts like cerebrum, cerebellum and brainstem. In the mdical field the abnormal growth of brain cells which results in brain failure is considered as a brain tumor. 

- It is primarly of two types benign and malignant. The traditional method to detect brain tumors is through magnetic resonance imaging(MRI) and computed tomography(CT). In many researches, brain tumor has been detected using machine learning which helps to detect the brain tumor in early stages. Convolution Neural Network can be used detect tumors and classify them. Image processing and neural network are used to improve the performance of detecting and classifying brain tumor.

## Objective
- To create a good software which will help doctors not only to identify tumor but also to classify the types of tumor at the early stage which will save both doctor and patient time and the appropriate treatment can be done. Convolutional Neural Network Algorithm can be used for detecting the tumor.

## Convolutional Neural Network
- CNN is a deep learning algorithm used for classification and processing of data which has grid pattern
like images or patterns. CNN is composed of three layers: convolution, pooling and fully connected layers. The first two layers are for feature extraction and the third layer maps the extracted features into final output i.e. classification. 

- CNN is highly efficient for image processing as one layer becomes input for next layer, extracted features can become more complex. The process of optimizing parameters such as kernels is called training, which is done to reduce the difference between original output and predicted output.

## Workflow
- This workflow has five modules namely, dataset, pre-processing, split data, model building and classification. For dataset significant number of tumor images can be used or a sample dataset for kaggle.com can be used as input for the model. 
- For pre-processing the images taken are endcoded and resized. Then we will split the dataset into 80% training data and 20% testing data, (70–30)% split can also be used. After this CNN model is built and trained for epochs. The model gives the output by classifying the image as positive or negative tumor.

## Tools & Technologies Used
### Google Colab:
- It is a free notebook environment that runs entirely in the cloud. It is a web IDE for python. It lets users edit documents. It supports many machine learning libraries which can be easily loaded. It is primarly used for machine learning, data analysis.

### Jupyter Notebook:
- It is an open-source web application that allows you to create and share documents that contain live code, equations, visualizations, and narrative text. It is used for machine learning, data cleaning, data visualization and so on.

## Conclusion
- In this project one can learn about CNN algorithm and how it works. A good accuracy can be obtained using this model but it also depends on other factors like the dataset used. In future this model can be further imporved or more complex model can be used for prediction.
