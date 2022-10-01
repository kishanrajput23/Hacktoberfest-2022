# BINARY SEARCH
Binary search works on sorted arrays. Binary search 
begins by comparing an element in the middle of the 
array with the target value. If the target value 
matches the element, its position in the array is 
returned. 
- Index(Middle)=index(low)+index(high-low)/2
- <img width="266" alt="image" src="https://user-images.githubusercontent.com/91652171/193401220-0a2c9edf-d720-4c40-aed6-5203ed6caeb3.png">

## Steps to Implement Binary Search
1. Read the search element from the user.
2. Find middle element of the ar
3. 3. Compare the search element with the middle element in the sorted list.
4. If both are matched, then display "Given element is found!!!" and terminate the function.
5. If both are not matched, then check whether the search element is smaller or larger than the middle
element.
6. If the search element is smaller than middle element, repeat steps 2, 3, 4 and 5 for the left sublist of the
middle element.
7. If the search element is larger than middle element, repeat steps 2, 3, 4 and 5 for the right sublist of the
middle element.
8. Repeat the same process until we find the search element in the list or until sublist contains only one
element.

## Thank You

