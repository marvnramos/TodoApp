package com.example.tasker

import RetrofitInstance
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasker.data.models.Get
import com.example.tasker.ui.view.screens.HomeScreen
import com.example.tasker.ui.view.theme.TaskerTheme
import retrofit2.Callback

class MainActivity : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
    @Preview
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreen() {
        val context = LocalContext.current
        var get: Get? = null

        Scaffold(
            topBar = {
                TopAppBar(
//                    backgroundColor = Purple700,
                    title = {
                        Text(
                            text = "Simple API Request",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = Color.Blue
                        )
                    }
                )
            },
            content = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val id = remember {
                        mutableStateOf(TextFieldValue())
                    }

                    val profile = remember {
                        mutableStateOf(Get(
                            message = ""
                        ))
                    }

                    Text(
                        text="API Sample",
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 25.sp
                        )

//                            style = TextStyle(
//                                fontSize = 40.sp,
//                                fontFamily = FontFamily.Cursive
//                            )
                    )


                    Spacer(modifier = Modifier.height(15.dp))

                    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                        Button(
                            onClick = {
                                try{

                                    val data = RetrofitInstance.ApiClient.getHello()

                                    data.enqueue(object : Callback<Get> {
                                        override fun onResponse(
                                            call: retrofit2.Call<Get>,
                                            response: retrofit2.Response<Get>
                                        ) {
                                            if(response.isSuccessful){
                                                get = response.body()!!
                                                get?.let {
                                                    Log.d("Get: ", it.toString())
                                                }
                                                Toast.makeText(context, "Listo ✅", Toast.LENGTH_SHORT).show()
                                            }else{
                                                Toast.makeText(context, "no furula", Toast.LENGTH_SHORT).show()
                                            }


                                        }

                                        override fun onFailure(
                                            call: retrofit2.Call<Get>,
                                            t: Throwable
                                        ) {
                                            Log.e("API Request", "Failed", t)
                                            Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                                        }

                                    })
//                                val data = sendRequest(
//                                    id = id.value.text,
//                                    profileState = profile
//

//                                    Log.d("Main Activity", )
//                                    Toast.makeText(context, "asd", Toast.LENGTH_SHORT).show()
                                }catch (e: Exception) {
                                    Log.e("Error", "Failed to fetch data", e)
                                    Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                                }
                            }
                        ) {
                            Text(text = "Get Data")
                        }
                    }

                    Spacer(modifier = Modifier.height(15.dp))

//                    Text(text = profile.component1().toString(), fontSize = 20.sp)

                }
            }
        )
    }
}

//    fun sendRequest(
//        id: String,
//        profileState: MutableState<Post>
//    ) {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://192.168.0.109:3000")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val api = retrofit.create(ApiClient::class.java)
//
//        val call: Call<Post> = api.getUserById(id);
//
//        call!!.enqueue(object: WindowInsetsAnimation.Callback<Post?> {
//            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
//                if(response.isSuccessful) {
//                    Log.d("Main", "success!" + response.body().toString())
//                    profileState.value = response.body()!!.profile
//                }
//            }
//
//            override fun onFailure(call: Call<UserModel?>, t: Throwable) {
//                Log.e("Main", "Failed mate " + t.message.toString())
//            }
//        })
//    }
//
//}
