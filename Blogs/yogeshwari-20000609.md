
Chapter 1: Let's Start With OpenCV

a. Opening an image

Now that OpenCV is installed, the first thing to do is to import the module and opening an image which is fairly simple.]
Additional informations:

    image will contain an iplimage object which is the most kind of image object used for image processing. The inheritance order is CvArr which is basically just an array then a CvMat which can be multi-dimensional array of some more informations attached like the number or rows columns.. And then come the IplImage wich basically contains a CvMat the number of channels(explained further in the tutorial) the encoding used for the image..
    the argument cv.CV_LOAD_IMAGE_COLOR specify the kind of image we want to load but this is facultative and done dynamically. There is also CV_LOAD_IMAGE_GRAYSCALE if we want to load a color image as a grayscale image or CV_LOAD_IMAGE_UNCHANGED which change nothing to the image when it open it.
    all the windows created and showed on the screen by OpenCV are indexed by their name, so NamedWindow will create a window with the given name, and ShowImage will put in the window the given image. If the window has not been declared ShowImage create it that’s way NameWindow is facultative.
    WaitKey wait for an user input, if you forget to put it the program will end up and you will never see the image. 0 is the timeout and 0 means wait indefinitely.

Note: The way to import cv can vary depending of the installation and the operating system. For instance it can be just import cv2 as cv. Just be careful to do not import the old syntax inherited from swig.

Additional informations:

    The y-axis position is set to image.height /2 to put it in the center of the image
    The x-axis position is set to image.width / 4 to make the text start a th first quarter of the image.
    PutText just put the given string at the given position using the specified font an color. Note that RGB set to 255 gives a white color.


b. Save and Resize Operations

The example below shows very basic operations which are resize and save image. Indeed all the modifications applied to the images does not take effect on the file on the filesystem itself but just in memory. That’s why we may want to keep the changes.

Additional informations: Instead of cv.Resize you can also use cv.PyrDown(im, thumb) which resize but applying a filter. The default filter is GAUSSIAN 5x5.

c. Conversion

This section is very important, because it will be used all the tutorial and th examples. The important thing to understand is the concept of channel. A channel is a sub-image. For instance a color image have 3 channels red, green blue. So we can only work on red working on the red channel. A grayscale picture have only one channel that represent for each pixels the amount of brightness from 0 to 255. The other concept to understand is the concept of basis used. Basically for a pixel the value for each color (RGB) is included from 0 to 255 and is coded on 8 bits unsigned, but it is not always the case and we may want to use another basis. The piece of code below show how to do it.
