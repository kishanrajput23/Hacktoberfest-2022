import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import Home from './screens/home'
import Quiz from './screens/quiz'
import Result from './screens/result'
import { NavigationContainer } from '@react-navigation/native'
import MyStack from './navigation'

const App = () => {
  return (
    
      // {/* <Home/> */}
      // {/* <Quiz/> */}
      <NavigationContainer>
        <MyStack/>
      </NavigationContainer>
      // {/* <Result/> */}
       
    
  )
}

export default App

const styles = StyleSheet.create({
  container:{
    padding:40,
    paddingHorizontal:16,
  }
})