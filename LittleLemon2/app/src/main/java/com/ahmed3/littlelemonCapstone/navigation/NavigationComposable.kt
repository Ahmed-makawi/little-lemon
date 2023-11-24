package com.ahmed3.littlelemonCapstone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahmed3.littlelemonCapstone.screens.*


@Composable
fun Navigation() {

    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = SplashScreenAnimation.route
    ) {
        composable(OnboardScreen.route) {
            Onboard(navController)
        }
        composable(HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(ProfileScreen.route) {
            ProfileScreen(navController)
        }
        composable(WelcomingScreen.route) {
            WelcomingScreen(navController)
        }
        composable(DishDetails.route) {
            DishDetails(navController)
        }
        composable(SplashScreenAnimation.route) {
           SplashScreenAnimation(navController)
        }
    }
}




