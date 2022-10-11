import { Image,StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React from 'react'
import Title from '../components/title'

const Home = ({navigation}) => {
  return (
    <View style={styles.container}>
    <Title titleText='QUIZZARD' />
    <View style={styles.bannerContainer}>
    <Image source={{uri:'https://cdni.iconscout.com/illustration/premium/thumb/customer-survey-3428393-2910850.png'}}
         style={styles.banner}
         resizeMode="contain"
    />

   
    </View>
    <TouchableOpacity onPress={()=>navigation.navigate("Quiz")} style={styles.button}>
        <Text style={styles.buttonText}>Start</Text>
    </TouchableOpacity>
    </View>
  )
}

export default Home

const styles = StyleSheet.create({
    banner:{
        height:300,
        width:300,
    },
    bannerContainer:{
        justifyContent:'center',
        alignItems:"center",
        flex:1,
    },
    container:{
      padding:40,
      paddingHorizontal:20,
      height:"100%"

    },
    button:{
      width:"100%",
      backgroundColor:"#14746F",
      padding:16,
      borderRadius:20,
      alignItems:"center",
      marginBottom:35
    },
    buttonText:{
      fontSize:24,
      fontWeight:"600",
      color:"white",

    },
})