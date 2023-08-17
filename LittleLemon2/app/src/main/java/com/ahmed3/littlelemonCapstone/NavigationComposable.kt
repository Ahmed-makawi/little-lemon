package com.ahmed3.littlelemonCapstone

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.*


@Composable
fun Navigation(){

    val navController = rememberNavController()

    val startDestination =
        if (isNeedWelcoming()){
            WelcomingScreen.route
        }
        else if(userLoginData()){
            HomeScreen.route
        } else{
            OnboardingScreen.route
        }


    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(OnboardingScreen.route){
            Onboarding(navController)
        }
        composable(HomeScreen.route){
            HomeScreen(navController)
        }
        composable(ProfileScreen.route){
            profileScreen(navController)
        }
        composable(WelcomingScreen.route){
            welcomingScreen(navController)
        }
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun userLoginData(): Boolean {
    val context: Context = LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)

    return sharedPreferences.getBoolean("loginStatus", false)
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun isNeedWelcoming():Boolean{

    val context: Context = LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)


    return  sharedPreferences.getBoolean("isNeedWelcoming", true)
}




