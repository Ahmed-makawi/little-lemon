package com.ahmed3.littlelemonCapstone

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


@Composable
fun Navigation(){

    val navController = rememberNavController()

    val startDestination =
        if(userLoginData()){
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
            homeScreen(navController)
        }
        composable(ProfileScreen.route){
            profileScreen(navController)
        }
    }
}


@Composable
fun userLoginData(): Boolean {
    val context: Context = LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)

    var loginStatus:Boolean

    runBlocking {
        withContext(Dispatchers.IO) {
            loginStatus = sharedPreferences.getBoolean("loginStatus", false)
        }
    }

    return loginStatus
}






