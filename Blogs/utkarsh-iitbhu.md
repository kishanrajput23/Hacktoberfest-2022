# U-Net-Semantic-Segmentation

### How to proceed with a task of Semantic Segmentation using U-Nets:

## **1. Get our data**

Download data from: [Here](https://www.kaggle.com/code/oluwatobiojekanmi/carla-image-semantic-segmentation-with-u-net/data)



## **2. Data Preproccesing**

<a class="anchor" id="2-1" name="2-1"></a>
### **2.1. Load the images and masks from their directories**

In this data preparation step, we will:
1. Create 2 lists containing the paths of images and masks
2. Split the lists into training, validation and test sets

#### **2.2. Create a function to read image and mask paths and return equivalent arrays**

The **read_image** function will
1. Read an image and its mask from their paths
2. Convert the digital image and its mask to image arrays 
3. Normalize the datasets
4. Resize the image and its masks to a desired dimension

<a class="anchor" id="3" name="3"></a>
## **3. Model Architecture and Training**
We will using a the **U-Net architecture** to train our semantic segmentation model. The U-Net model was named for its U-shape architecture, and it was originally created for biomedical image segmentation tasks in 2015. However, the model has become a very popular choice for other semantic segmentation tasks. 

U-Net builds on a previous architecture called the Fully Convolutional Network, or FCN, which replaces the dense layers found in a typical CNN with a transposed convolution layer that upsamples the feature map back to the size of the original input image, while preserving the spatial information. This is necessary because the dense layers destroy spatial information (the "where" of the image), which is an essential part of image segmentation tasks. An added bonus of using transpose convolutions is that the input size no longer needs to be fixed, as it does when dense layers are used. 

The U-Net architecture consists of:

**1. Contracting path (Encoder containing downsampling steps)**:

The contracting path follows a regular CNN architecture, with convolutional layers, their activations, and pooling layers to downsample the image and extract its features. Images are first fed through several convolutional layers which reduce height and width, while growing the number of channels. 

In detail, it consists of the repeated application of two 3 x 3 unpadded convolutions, each followed by a rectified linear unit (ReLU) and a 2 x 2 max pooling operation with stride 2 for downsampling. At each downsampling step, the number of feature channels is doubled.

During the contracting process, convolution outputs are stored in a separate variable before size reduction (pooling of features). This is passed to the expanding blocks during the decoding process as feature map.


**2. Expanding path (Decoder containing upsampling steps)**:

The expanding path performs the opposite operation of the contracting path, growing the image back to its original size, while shrinking the channels gradually. 

In detail, each step in the expanding path upsamples the feature map, followed by a 2 x 2 convolution (the transposed convolution or upsampling). This transposed convolution halves the number of feature channels, while growing the height and width of the image.

<a class="anchor" id="3-1" name="3-1"></a>

### **3.1. U-Net Model Design**

To design our model, we will carry out the following steps
1. Define a function for an encoding block. The function will return the next layer output and the skip connection output for the corresponding block in the model
2. Define a function for a decoding block. This function will merge the skip-connection input with the previous layer, process it, and return an output
3. Develop a model using both the encoding and decoding blocks output


Next is a concatenation with the correspondingly cropped feature map from the contracting path, and two 3 x 3 convolutions, each followed by a ReLU.


**3. Final Feature Mapping Block**: In the final layer, a 1x1 convolution is used to map each 64-component feature vector to the desired number of classes. The channel dimensions from the previous layer correspond to the number of filters used, so when you use 1x1 convolutions, you can transform that dimension by choosing an appropriate number of 1x1 filters. When this idea is applied to the last layer, you can reduce the channel dimensions to have one layer per class. 

The U-Net network has 23 convolutional layers in total. 
<center><img src="https://i.ibb.co/0287bZ1/U-Net.webp" alt="U-Net" border="0"></center>

<a class="anchor" id="4" name="4"></a>
## **4. Model Evaluation**

Model Evaluation is an integral part of the model development process. It helps to find the best model that represents our data and how well the chosen model will work in the future. For classification tasks, precision and recall are the popular choice metrics used in addition with model accuracy to evaluate model performance since model accuracy is not always sufficient to judge if a model is optimal or not (especially if our dataset is skewed). The same rule applies to most dense prediction tasks like image segmentation where the goal is to simplify and/or change the representation of an image into classes that is more meaningful and easier to analyze.
 

Since the goal of our model is to partition an input image into various classes, it is often difficult to know if our model struggles to optimally partition one or more classes since it doesn't always reflect in the model accuracy, neither can it easily detected by the eyes. Hence, there is a need for supplementary metrics to evaluate model performance.

We will be using recall,precision, Intersection over Union (IoU), and F1-score as supplementary metrics to evaluate our model performance. These metrics were computed by identifying the variables true positive (TP), true negative (TN), false positive (FP), and false-negative (FN) by calculating the confusion matrix between the predicted segmentations and the ground truth segmentations. The expressions for these metrics are defined as:

1. Precision = TP/(TP + FP)
2. Recall/Sensitivity = TP/(TP + FN)
3. Intersection over Union (IoU)/Jaccard Similarity  = TP/(TP + FP + FN)
4. F1-score(JS)/Dice coefficient = 2 * ((Precision * Recall)/(Precision + Recall))

<br/>

To carry out these evaluations, we will:
1. Create segmentations/masks of images in our dataset
2. Evaluate predicted segmentations

<a class="anchor" id="5" name="5"></a>
## **5. Predict image segmentations using the trained Model**

Though, our model have pretty decent accuracies and IoUs on our training, validation, and test datasets, visualizing how it performs on these datasets could give us additional gains.

Hence, we will

1. Create a function to preprocess selected images and display their true state, true mask and predicted mask
2. Predict and compare masks of images in the training set
3. Predict and compare masks of images in the validation set
4. Predict and compare masks of images in the test set

## **6. Segmented image**
![image](https://user-images.githubusercontent.com/84759422/177004225-256b1ae9-b31e-47e5-bd8d-6ca5216d70cf.png)


##### Github repo: [Link](https://github.com/utkarsh-iitbhu?tab=repositories)
##### Project link: [Link](https://github.com/utkarsh-iitbhu/U-Net-Segmentaion-for-Self-driving-cars)


#### Predicted mask shows the segmented image that our model has predicted, we can compare our results with True mask
