import { StyleSheet, Text, Touchable, TouchableOpacity, View } from 'react-native'
import React, { useEffect, useState } from 'react'


const shuffleArray=(array)=> {
  for (let i = array.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [array[i], array[j]] = [array[j], array[i]];
  }
}

const Quiz = ({navigation}) => {
  const [questions,setQuestions]=useState();
  const [ques,setQues]=useState(0);
  const [options, setOptions]= useState([]);
  const [score, setScore]= useState(0)
  const [isLoading, setIsLoading]= useState(false)
  const getQuiz=async()=>{
    setIsLoading(true)
    const url='https://opentdb.com/api.php?amount=20&category=18&type=multiple&encode=url3986';
    const res=await fetch(url);
    const data =await res.json();
    setQuestions(data.results);
    

    setOptions(generateOptionsAndShuffle(data.results[0]))
    setIsLoading(false)
     
  }
  useEffect(()=>{
    getQuiz();
  },[]);

  const handleNextPress =()=>{
    setQues(ques+1);
    setOptions(generateOptionsAndShuffle(questions[ques+1]));
  }
  const generateOptionsAndShuffle=(_question)=>{
    const options= [..._question.incorrect_answers]
    options.push(_question.correct_answer)
 
    shuffleArray(options)
    
    return options
  }

  const handlSelectedOption=(_option)=>{
    if(_option===questions[ques].correct_answer){
      setScore(score+10)
    }
    if(ques!==19){
      setQues(ques+1)
      setOptions(generateOptionsAndShuffle(questions[ques+1]))
    }
    if(ques===19){
      handleShowResult()
    }
  }

  const handleShowResult=()=>{
    navigation.navigate('Result', {
      score: score
    })
  }

  
  return (
    <View style={styles.container}>
      {isLoading ? <View style={{display:'flex', justifyContent:'center', alignItems:'center', height:'100%'}}>
        <Text style={{fontSize:32, fontWeight:'700'}}>LOADING...</Text>
      </View> : questions && (
        <View style={styles.parrent}>
          <View style={styles.top}>
            <Text style={styles.question}>Q. {decodeURIComponent(questions[ques].question)}</Text>
          </View>
          <View style={styles.options}>
              
            <TouchableOpacity style={styles.optionButton} onPress={()=>handlSelectedOption(options[0])}>
              <Text style={styles.option}>{decodeURIComponent(options[0])}</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.optionButton} onPress={()=>handlSelectedOption(options[1])}>
              <Text style={styles.option}>{decodeURIComponent(options[1])}</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.optionButton} onPress={()=>handlSelectedOption(options[2])}>
              <Text style={styles.option}>{decodeURIComponent(options[2])}</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.optionButton} onPress={()=>handlSelectedOption(options[3])}>
              <Text style={styles.option}>{decodeURIComponent(options[3])}</Text>
            </TouchableOpacity>
          </View>
          <View style={styles.bottom}>
            {/* <TouchableOpacity style={styles.button}>
              <Text style={styles.buttonText}>PREV</Text>
            </TouchableOpacity> */}

{ques!==19 &&<TouchableOpacity style={styles.button} onPress={handleNextPress}>
              <Text style={styles.buttonText}>SKIP</Text>
            </TouchableOpacity> }

{ques===19 &&<TouchableOpacity style={styles.button} onPress={handleShowResult}>
              <Text style={styles.buttonText}>SHOW RESULTS</Text>
            </TouchableOpacity> }
            
          </View>
        </View>
      )}
    </View>
  );
};


export default Quiz 

const styles = StyleSheet.create({
  container:{
    padding:40,
    paddingHorizontal:20,
    height:"100%"
  },
  top:{
    marginVertical:16,

  },
  options:{
    marginVertical:16,
    flex:1,

  },
  bottom:{
    margin: 12,
    paddingVertical:16,
    justifyContent:'space-between',
    flexDirection:'row',
  },
  button:{
    backgroundColor:"#14746F",
    padding:12,
    paddingHorizontal:16,
    borderRadius:20,
    alignItems:"center",
    marginBottom:35
  },
  buttonText:{
    fontSize:18,
      fontWeight:"600",
      color:"white",
  
  },
  question:{
    fontSize:28,
  },
  option:{
    fontSize:18,
    fontWeight:"500",
    color:"white"

  },
  optionButton:{
    paddingVertical:12,
    marginVertical:6,
    backgroundColor:"#56AB91",
    paddingHorizontal:12,
    borderRadius:12,

  },
  parrent:{
    height:"100%",
  }
})