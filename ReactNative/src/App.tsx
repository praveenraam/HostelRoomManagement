import React from "react";
import { View, Text, TouchableOpacity,Image } from "react-native";
import { s } from "react-native-wind";


const App = () => {

    return(
        <View style={s`flex-1 justify-center items-center bg-white`}>


            <Text style={s`text-3xl font-black m-10`}> Hostel Management </Text>
            <View style={s`flex-2 items-center justify-center`}>

                <TouchableOpacity style={s`flex-row items-center bg-white px-4 py-3 rounded-full shadow-lg border border-gray-300`}>
                    <Image
                        source={require("./imgs/google.png")}
                        style={s`w-6 h-6 mr-3`}
                    />
                    <Text style={s`italic `}>Sign in to continue</Text>
                </TouchableOpacity>
            </View>

        </View>
    );
};

export default App;
