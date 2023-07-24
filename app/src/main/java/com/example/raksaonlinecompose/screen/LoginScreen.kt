package com.example.raksaonlinecompose.screen

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavHostController
import com.example.raksaonlinecompose.R
import com.example.raksaonlinecompose.route.ScreenRoute
import com.example.raksaonlinecompose.ui.theme.headerGen
import com.example.raksaonlinecompose.ui.theme.revamp_main


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    context: Context,
    navController: NavHostController,
    exitApp:(Boolean)->Unit
) {
    var clickExit by remember { mutableStateOf(0) }
    BackHandler(enabled = true){
        clickExit++
        if (clickExit == 2){
            exitApp(true)
        }else{
            val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                vibrator.vibrate(200)
            }
            Toast.makeText(context, "Click Back to Exit", Toast.LENGTH_SHORT).show()
        }
    }

    var text by remember { mutableStateOf("") }
    var textPassword by remember { mutableStateOf("") }
    var textRePassword by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var screenLogin by remember { mutableStateOf(true) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .aspectRatio(1f)
                    .clip(RectangleShape),
                painter = painterResource(R.drawable.miss_raksa),
                contentDescription = "miss_raksa",
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier.weight(0.5f)
                    .fillMaxWidth()
                    .height(0.dp)
                    .background(color = headerGen)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
            ) {

            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f)
                    .padding(10.dp),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(6.dp),
                shape = RoundedCornerShape(4.dp)
            ){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .size(30.dp),
                        painter = painterResource(R.drawable.main_menu),
                        contentDescription = "main_menu",
                    )
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                TextField(
                                    value = text,
                                    onValueChange = { newText -> text = newText },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .padding(horizontal = 10.dp),
                                    enabled = true,
                                    textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        keyboardType = KeyboardType.Email,
                                        imeAction = ImeAction.Done
                                    ),
                                    label = { Text(
                                        "Alamat Email",
                                        fontSize = 14.sp,
                                        modifier = Modifier.fillMaxWidth()
                                    ) },
                                    singleLine = true,
                                    colors = TextFieldDefaults.textFieldColors(
                                        containerColor = Color.White,
                                        textColor = Color.Black
                                    )
                                )
                                Spacer(
                                    modifier = Modifier.height(25.dp)
                                )
                                TextField(
                                    value = textPassword,
                                    onValueChange = { newText -> textPassword = newText },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .padding(horizontal = 10.dp),
                                    enabled = true,
                                    textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
                                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        keyboardType = KeyboardType.Email,
                                        imeAction = ImeAction.Done
                                    ),
                                    label = { Text(
                                        "Password",
                                        fontSize = 14.sp,
                                        modifier = Modifier.fillMaxWidth()
                                    ) },
                                    trailingIcon = {
                                        IconButton(
                                            onClick = { passwordVisibility = !passwordVisibility }
                                        ) {
                                            Image(
                                                modifier = Modifier.size(20.dp),
                                                painter = if (passwordVisibility) painterResource(R.drawable.close) else painterResource(R.drawable.open),
                                                contentDescription = "Toggle Password Visibility",
                                            )
                                        }
                                    },
                                    singleLine = true,
                                    colors = TextFieldDefaults.textFieldColors(
                                        containerColor = Color.White,
                                        textColor = Color.Black
                                    ),
                                )
                                if (!screenLogin){
                                    Spacer(
                                        modifier = Modifier.height(25.dp)
                                    )
                                    TextField(
                                        value = textRePassword,
                                        onValueChange = { newText -> textRePassword = newText },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentHeight()
                                            .padding(horizontal = 10.dp),
                                        enabled = true,
                                        textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
                                        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                                        keyboardOptions = KeyboardOptions.Default.copy(
                                            keyboardType = KeyboardType.Email,
                                            imeAction = ImeAction.Done
                                        ),
                                        label = { Text(
                                            "Retype Password",
                                            fontSize = 14.sp,
                                            modifier = Modifier.fillMaxWidth()
                                        ) },
                                        trailingIcon = {
                                            IconButton(
                                                onClick = { passwordVisibility = !passwordVisibility }
                                            ) {
                                                Image(
                                                    modifier = Modifier.size(20.dp),
                                                    painter = if (passwordVisibility) painterResource(R.drawable.close) else painterResource(R.drawable.open),
                                                    contentDescription = "Toggle Password Visibility",
                                                )
                                            }
                                        },
                                        singleLine = true,
                                        colors = TextFieldDefaults.textFieldColors(
                                            containerColor = Color.White,
                                            textColor = Color.Black
                                        ),
                                    )
                                }
                                if (screenLogin){
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        "Lupa Kata Sandi",
                                        textAlign = TextAlign.End,
                                        color = Color.Blue,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp),
                                        fontSize = 11.sp
                                    )
                                    Spacer(modifier = Modifier.height(14.dp))
                                }
                                if(screenLogin){
                                    Row(
                                        modifier = Modifier.fillMaxWidth().padding(10.dp)
                                    ){
                                        Image(
                                            painterResource(R.drawable.uk_unselected),
                                            contentDescription = null,
                                            modifier = Modifier.size(24.dp)
                                        )
                                        Text("English")
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Image(
                                            painterResource(R.drawable.ina_selected),
                                            contentDescription = null,
                                            modifier = Modifier.size(24.dp)
                                        )
                                        Text("Indonesia")
                                    }
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Button(
                                        modifier = Modifier.weight(0.45f).border(
                                            6.dp,
                                            revamp_main,
                                            RoundedCornerShape(16.dp)
                                        ),
                                        onClick = {
                                            screenLogin = !screenLogin
                                            text = ""
                                            textPassword = ""
                                            textRePassword = ""
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Transparent
                                        ),
                                    ){
                                        Text(
                                            if(screenLogin) "Signup" else "Back",
                                            color = revamp_main
                                            )
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Button(
                                        modifier = Modifier.weight(0.45f).border(
                                            6.dp,
                                            revamp_main,
                                            RoundedCornerShape(16.dp)
                                        ),
                                        onClick = {
                                            if(screenLogin){
                                                if (text.isBlank()&&textPassword.isBlank()){
                                                    Toast.makeText(context, "Harap Masukan Data Anda", Toast.LENGTH_SHORT).show()
                                                }else{
                                                    if(text == "admin" && textPassword == "admin"){
                                                        navController.navigate(ScreenRoute.HomeScreen.route)
                                                    }else{
                                                        Toast.makeText(context, "Password Salah", Toast.LENGTH_SHORT).show()
                                                    }
                                                }
                                            }else{
                                                if (text.isBlank()&&textPassword.isBlank()){
                                                    Toast.makeText(context, "Harap Masukan Data Anda", Toast.LENGTH_SHORT).show()
                                                }else{
                                                    if (textPassword == textRePassword){
                                                        Toast.makeText(context, "Action Signup", Toast.LENGTH_SHORT).show()
                                                    }else{
                                                        Toast.makeText(context, "Password Tidak Sama", Toast.LENGTH_SHORT).show()
                                                    }
                                                }
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = revamp_main
                                        ),
                                    ){
                                        Text(
                                            if(screenLogin) "Login" else "Signup",
                                            color = Color.White
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}