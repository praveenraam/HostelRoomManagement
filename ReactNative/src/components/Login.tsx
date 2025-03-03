import React, { useEffect } from "react";
import { View, Text, TouchableOpacity,Image } from "react-native";
import { s } from "react-native-wind";
import { GoogleSignin, GoogleSigninButton } from "@react-native-google-signin/google-signin";
import AsyncStorage from "@react-native-async-storage/async-storage";

const Login = () => {

  useEffect(()=>{
    GoogleSignin.configure({
        webClientId:"641347745380-sj9n98h8ideu9i0oid97dj4nhnckfb6a.apps.googleusercontent.com",
        offlineAccess:true
    });
  },[]);

  const handleGoogleSignIn = async () =>{

    try{

        await GoogleSignin.hasPlayServices();
        const userInfo = await GoogleSignin.signIn();

        const response = await fetch("",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body: JSON.stringify({ token: userInfo.idToken }),
        });

        const data = await response.json();
        console.log("Backend Response:", data);
    }
    catch(error){
      console.log(error);
    }
  };

  return (
    <View style={s`flex-1 justify-center items-center bg-white`}>
      <Text style={s`text-3xl font-black m-10`}> Hostel Management </Text>
      <View style={s`flex-2 items-center justify-center`}>
        <TouchableOpacity
          onPress={handleGoogleSignIn}
          style={s`flex-row items-center bg-white px-4 py-3 rounded-full shadow-lg border border-gray-300`}
        >
          <Image
            source={require("../imgs/google.png")}
            style={s`w-6 h-6 mr-3`}
          />
          <Text style={s`italic `}>Sign in to continue</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
};

export default Login;
