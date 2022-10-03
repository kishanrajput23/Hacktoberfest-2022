import {React, useState, useEffect} from "react";
import "./SortingVisualizer.css";
import { Button, FloatingLabel ,Form  } from 'react-bootstrap';
import {getMergeSortAnimations, 
        getBubbleSortAnimations, 
        getInsertionSortAnimations,
        getQuickSortAnimations,
        getSelectionSortAnimations} from "./SortingAnimation.js";


const NUMBER_OF_ARRAY_BARS = 100;

const PRIMARY_COLOR = 'turquoise';

const GREEN_COLOR = 'green';

const SECONDARY_COLOR = 'red';


const SortingVisualizer = () =>{
    const [array, setArray] = useState([]);

    function componentDidMount(){
        setArray(generateRandomArray(NUMBER_OF_ARRAY_BARS));
    };

    function resetArray(){
        setArray(generateRandomArray(NUMBER_OF_ARRAY_BARS));
    };

    function generateRandomArray(size) {
        var array = [];
        for (var i = 0; i < size; i++) {
            array.push(randomIntFromInterval(5, 750));
        }
        return array;
    };

    function randomIntFromInterval(min, max){
        return Math.floor(Math.random() * (max - min + 1) + min);
    }

    function makeItGreen(arrayBars){
        for (let i = 0; i < arrayBars.length; i++) {
            const arrayBarStyle = arrayBars[i].style;
            arrayBarStyle.backgroundColor = GREEN_COLOR;
        }
    }

    function doBubbleSortAnimation(array) {
    
        const animations = getBubbleSortAnimations(array);
        const arrayBars = document.getElementsByClassName('array-bar');

        for(let i = 0; i < animations.length; i++){

            const isColorChange = i % 3 !== 2;
            if(isColorChange && animations[i][1] < array.length){
                const barOneIdx = animations[i][0];
                const barTwoIdx = animations[i][1];
                const barOneStyle = arrayBars[barOneIdx].style;
                var barTwoStyle;

                if(barOneIdx+1 < array.length){
                    barTwoStyle = arrayBars[barOneIdx+1].style;
                }else{
                    barTwoStyle = arrayBars[barOneIdx].style;
                }
                const color = i % 3 === 0? SECONDARY_COLOR : PRIMARY_COLOR;
                //change the color of the bars
                setTimeout(() => {
                    barOneStyle.backgroundColor = color;
                    barTwoStyle.backgroundColor = color;
                }, i*.95);

            } else {
                //swap
                setTimeout(() => {
                    const [barOneIdx, newHeight] = animations[i];
                    const barOneStyle = arrayBars[barOneIdx].style;
                    barOneStyle.height = `${newHeight}px`;
                }, i*.95);
            }
        }
      
}
        
function doInsertionSortAnimation(array) {
        
        const animations = getInsertionSortAnimations(array);
        const arrayBars = document.getElementsByClassName('array-bar');

        for(let i = 0; i < animations.length; i++){

            const isColorChange = i % 3 !== 2;
            if(isColorChange && animations[i][1] < array.length){
             
                const barOneIdx = animations[i][0];
                const barTwoIdx = animations[i][1];
                const barOneStyle = arrayBars[barOneIdx].style;
                var barTwoStyle;

                if(barOneIdx+1 < array.length){
                    barTwoStyle = arrayBars[barOneIdx+1].style;
                }else{
                    barTwoStyle = arrayBars[barOneIdx].style;
                }
                const color = i % 3 === 0? SECONDARY_COLOR : PRIMARY_COLOR;
                //change the color of the bars

                setTimeout(() => {
                    barOneStyle.backgroundColor = color;
                    barTwoStyle.backgroundColor = color;
                }, i*50 );

            } else {
            
                //swap
                setTimeout(() => {
                    const [barOneIdx, newHeight] = animations[i];
                    const barOneStyle = arrayBars[barOneIdx].style;
                    barOneStyle.height = `${newHeight}px`;
                }, i*50);
            }
        }
  
}

    function doMergeSortAnimation(array) {
        const animations = getMergeSortAnimations(array);
        const arrayBars = document.getElementsByClassName('array-bar');
        //var doitGreen = makeItGreen;

        for (let i = 0; i < animations.length; i++) {
          const isColorChange = i % 3 !== 2;
          if (isColorChange) {
            const [barOneIdx, barTwoIdx] = animations[i];
            const barOneStyle = arrayBars[barOneIdx].style;
            const barTwoStyle = arrayBars[barTwoIdx].style;
            const color = i % 3 === 0 ? SECONDARY_COLOR : PRIMARY_COLOR;
            setTimeout(() => {
              barOneStyle.backgroundColor = color;
              barTwoStyle.backgroundColor = color;
            }, i * 50);
          } else {
            setTimeout(() => {
              const [barOneIdx, newHeight] = animations[i];
              const barOneStyle = arrayBars[barOneIdx].style;
              barOneStyle.height = `${newHeight}px`;
            }, i * 50);
          }
        }
  
        //doitGreen(arrayBars);
      }

function doQuickSortAnimation(array) {
        const animations = getQuickSortAnimations(array);
        const arrayBars = document.getElementsByClassName('array-bar');
        //var doitGreen = makeItGreen;

        for (let i = 0; i < animations.length; i++) {
          const isColorChange = i % 3 !== 2;
          if (isColorChange) {
            const [barOneIdx, barTwoIdx] = animations[i];
            const barOneStyle = arrayBars[barOneIdx].style;
            const barTwoStyle = arrayBars[barTwoIdx].style;
            const color = i % 3 === 0 ? SECONDARY_COLOR : PRIMARY_COLOR;
            setTimeout(() => {
              barOneStyle.backgroundColor = color;
              barTwoStyle.backgroundColor = color;
            }, i * 3);
          } else {
            setTimeout(() => {
              const [barOneIdx, newHeight] = animations[i];
              const barOneStyle = arrayBars[barOneIdx].style;
              barOneStyle.height = `${newHeight}px`;
            }, i * 3);
          }
        }

   
        //doitGreen(arrayBars);
}
    

      function doSelectionSortAnimation(array) {
        const animations = getSelectionSortAnimations(array);
        const arrayBars = document.getElementsByClassName('array-bar');
        //var doItGreen = makeItGreen;

        for(let i = 0; i < animations.length; i++){

          const isColorChange = i % 3 !== 2;
          if(isColorChange && animations[i][1] < array.length){
           
              const barOneIdx = animations[i][0];
              const barTwoIdx = animations[i][1];
              const barOneStyle = arrayBars[barOneIdx].style;
              var barTwoStyle;

              if(barOneIdx+1 < array.length){
                  barTwoStyle = arrayBars[barOneIdx+1].style;
              }else{
                  barTwoStyle = arrayBars[barOneIdx].style;
              }
              const color = i % 3 === 0? SECONDARY_COLOR : PRIMARY_COLOR;
              //change the color of the bars

              setTimeout(() => {
                  barOneStyle.backgroundColor = color;
                  barTwoStyle.backgroundColor = color;
              }, i*1.5 );

          } else {
           
              //swap
              setTimeout(() => {
                  const [barOneIdx, newHeight] = animations[i];
                  const barOneStyle = arrayBars[barOneIdx].style;
                  barOneStyle.height = `${newHeight}px`;
              }, i*1.5);
          }
      }
        
      
        //
    }


    function DoSortAlgorithm (array){
        switch (document.getElementById("AlgoSelect").value) {
            case "Choose an algorithm":
                alert("Please choose an algorithm");
                break;
            case "1":
                doBubbleSortAnimation(array);
                break;
            case "2":
                doInsertionSortAnimation(array)
                break;
            case "3":
                doSelectionSortAnimation(array)
                break;
            case "4":
                doMergeSortAnimation(array)
                break;
            case "5":
                doQuickSortAnimation(array)
                break;
            default:
                alert("Please choose an algorithm");
          }
    }


    useEffect(() => {
        componentDidMount();
      }, []);




    return (
        <>  
        <br/>   

        <Button onClick={()=>resetArray()} variant="primary" >Generate Random Set!</Button>{' '}

        <select className="select-dropdown" id="AlgoSelect">
              <option value="">Choose an algorithm</option>
              <option value="1">BubbleSort</option>
              <option value="2">InsertionSort</option>
              <option value="3">SelectionSort</option>
              <option value="4">MergeSort</option>
              <option value="5">QuickSort</option>
        </select>
       
        <Button variant="primary" onClick={()=>DoSortAlgorithm(array)}>Start!</Button>{' '}
    
        

        <div className="array-container">
            {array.map((value, idx) => (
                <div
                    className="array-bar"
                    key={idx}
                    style={{ height: `${value}px` }}
                ></div>
            ))}
        </div>
        </>

    );
}

export default SortingVisualizer;