package com.ahmed3.littlelemonCapstone.screens

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmed3.littlelemonCapstone.R
import com.ahmed3.littlelemonCapstone.navigation.HomeScreen
import com.ahmed3.littlelemonCapstone.navigation.OnboardScreen
import com.ahmed3.littlelemonCapstone.navigation.WelcomingScreen
import kotlinx.coroutines.delay


@Composable 
fun SplashScreenAnimation(navController: NavController){

    val destination =
        if (needWelcoming()) {
            WelcomingScreen.route
        } else if (needLogin()) {
            HomeScreen.route
        } else {
            OnboardScreen.route
        }

    LaunchedEffect(key1 = true){
        delay(2000)
        navController.popBackStack()
        navController.navigate(destination)
    }
    SplashScreen()
}

@Composable
fun SplashScreen(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            modifier = Modifier.size(220.dp)
        )
    }
}



@Composable
fun needLogin(): Boolean {
    val context: Context = LocalContext.current
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)

    return sharedPreferences.getBoolean("loginStatus", false)
}


@Composable
fun needWelcoming(): Boolean {

    val context: Context = LocalContext.current
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)

    return sharedPreferences.getBoolean("isNeedWelcoming", true)
}

