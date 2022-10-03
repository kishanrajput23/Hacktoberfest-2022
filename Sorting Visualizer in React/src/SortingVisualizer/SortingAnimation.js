//-----------------------MergeSort-----------------------//
export function getMergeSortAnimations(array) {
    const animations = [];
    if (array.length <= 1) return array;
    const auxiliaryArray = array.slice();
    mergeSortHelper(array, 0, array.length - 1, auxiliaryArray, animations);
    return animations;
  }
  
function mergeSortHelper(mainArray,startIdx,endIdx,auxiliaryArray,animations,) {
    if (startIdx === endIdx) return;
    const middleIdx = Math.floor((startIdx + endIdx) / 2);
    mergeSortHelper(auxiliaryArray, startIdx, middleIdx, mainArray, animations);
    mergeSortHelper(auxiliaryArray, middleIdx + 1, endIdx, mainArray, animations);
    doMerge(mainArray, startIdx, middleIdx, endIdx, auxiliaryArray, animations);
  }
  
function doMerge(
    mainArray,
    startIdx,
    middleIdx,
    endIdx,
    auxiliaryArray,
    animations,
  ) {
    let k = startIdx;
    let i = startIdx;
    let j = middleIdx + 1;

    while (i <= middleIdx && j <= endIdx) {
      // These are the values that we're comparing; we push them once
      // to change their color.
      animations.push([i, j]);
      // These are the values that we're comparing; we push them a second
      // time to revert their color.
      animations.push([i, j]);

      if (auxiliaryArray[i] <= auxiliaryArray[j]) {
        // We overwrite the value at index k in the original array with the
        // value at index i in the auxiliary array.
        animations.push([k, auxiliaryArray[i]]);
        mainArray[k++] = auxiliaryArray[i++];
      } else {
        // We overwrite the value at index k in the original array with the
        // value at index j in the auxiliary array.
        animations.push([k, auxiliaryArray[j]]);
        mainArray[k++] = auxiliaryArray[j++];
      }
    }

    while (i <= middleIdx) {
      // These are the values that we're comparing; we push them once
      // to change their color.
      animations.push([i, i]);
      // These are the values that we're comparing; we push them a second
      // time to revert their color.
      animations.push([i, i]);
      // We overwrite the value at index k in the original array with the
      // value at index i in the auxiliary array.
      animations.push([k, auxiliaryArray[i]]);
      mainArray[k++] = auxiliaryArray[i++];
    }
    while (j <= endIdx) {
      // These are the values that we're comparing; we push them once
      // to change their color.
      animations.push([j, j]);
      // These are the values that we're comparing; we push them a second
      // time to revert their color.
      animations.push([j, j]);
      // We overwrite the value at index k in the original array with the
      // value at index j in the auxiliary array.
      animations.push([k, auxiliaryArray[j]]);
      mainArray[k++] = auxiliaryArray[j++];
    }
  }


//--------------------BubbleSort--------------------
export function getBubbleSortAnimations(array) {
    const animations = [];
    if (array.length <= 1) return array;
    bubbleSortHelper(array, animations);
    return animations;
}

function bubbleSortHelper(mainArray,animations,) {
    doBubble(mainArray, animations);
}

function doBubble(arr, animations) {
    for(let i = 0; i < arr.length; i++){

        for(let j = 0; j < arr.length - i - 1; j++){
            animations.push([j, j+1]);
            animations.push([j, j+1]);
            animations.push([j, arr[j]]);

            if(arr[j] > arr[j+1]){
                animations.push([j, j]);
                animations.push([j, j]);
                animations.push([j, arr[j+1]]);

                animations.push([j+1, j+2]);
                animations.push([j+1, arr.length-j+2]);
                animations.push([j+1, arr[j]]);

                let temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
    }
}

//--------------------------------Insertion Sort----------------

export function getInsertionSortAnimations(array) {
    const animations = [];
    if (array.length <= 1) return array;
    insertionSortHelper(array, animations);
    return animations ;
 
}

function insertionSortHelper(array,animations,) {
    doInsertion(array, animations);
}

function doInsertion(arr, animations) {

    for (let i = 1; i < arr.length; i++) {


        let j = i - 1
        let temp = arr[i]
        let doSwap = false;
        while (j >= 0 && arr[j] > temp) {
            animations.push([j, j]);
            animations.push([j, j]);
            animations.push([j+1, arr[j]]);

            arr[j + 1] = arr[j]
            doSwap = true;
            j--
        }
        if (doSwap) {
            animations.push([j+1, j+2]);
            animations.push([j+1, j+2]);
            animations.push([j+1, temp]);
        }

        arr[j + 1] = temp;
      }
    };


//-----------------------------Quick Sort------------------------

export function getQuickSortAnimations(array) {
    const animations = [];
    if (array.length <= 1) return array;
    quickSortHelper(array, 0, array.length - 1, animations);
    return animations;
}


function quickSortHelper(mainArray, startIdx, endIdx, animations) {
    if (startIdx >= endIdx) return;
    const pivotIdx = startIdx;
    const newPivotIdx = doQuickSortAnimation(mainArray, startIdx, endIdx, pivotIdx, animations);
    quickSortHelper(mainArray, startIdx, newPivotIdx - 1, animations);
    quickSortHelper(mainArray, newPivotIdx + 1, endIdx, animations);
}


function doQuickSortAnimation(mainArray, startIdx, endIdx, pivotIdx, animations) {
    const pivotValue = mainArray[pivotIdx];
    let newPivotIdx = startIdx;
    for (let i = startIdx + 1; i <= endIdx; i++) {
        animations.push([i, pivotIdx]);
        animations.push([i, pivotIdx]);
        animations.push([i, mainArray[i]]);

        if (mainArray[i] < pivotValue) {
            newPivotIdx++;
            animations.push([newPivotIdx, newPivotIdx]);
            animations.push([newPivotIdx, newPivotIdx]);
            animations.push([newPivotIdx, mainArray[i]]);

            animations.push([i, i]);
            animations.push([i, i]);
            animations.push([i, mainArray[newPivotIdx]]);

            swap(mainArray, i, newPivotIdx);
        }
    }
    animations.push([newPivotIdx, newPivotIdx]);
    animations.push([newPivotIdx, newPivotIdx]);
    animations.push([newPivotIdx, mainArray[pivotIdx]]);

    animations.push([pivotIdx, pivotIdx]);
    animations.push([pivotIdx, pivotIdx]);
    animations.push([pivotIdx, mainArray[newPivotIdx]]);

    swap(mainArray, pivotIdx, newPivotIdx);
    return newPivotIdx;
}

function swap(mainArray, a, b) {
    const temp = mainArray[a];
    mainArray[a] = mainArray[b];
    mainArray[b] = temp;
}



//-----------------------------SelectionSort------------------------

export function getSelectionSortAnimations(array) {
    const animations = [];
    if (array.length <= 1) return array;
    selectionSortHelper(array, animations);
    return animations;
}

function selectionSortHelper(mainArray, animations) {
    doSelection(mainArray, animations);
}

function doSelection(arr, animations) {
    for (let i = 0; i < arr.length; i++) {
        let min = i;
        for (let j = i + 1; j < arr.length; j++) {
            animations.push([j, min]);
            animations.push([j, min]);
            animations.push([j, arr[j]]);

            if (arr[j] < arr[min]) {
                min = j;
            }
        }
        animations.push([min, min]);
        animations.push([min, min]);
        animations.push([min, arr[i]]);

        animations.push([i, i]);
        animations.push([i, i]);
        animations.push([i, arr[min]]);

        let temp = arr[i];
        arr[i] = arr[min];
        arr[min] = temp;
    }
}