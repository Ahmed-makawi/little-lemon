package com.ahmed3.littlelemonCapstone.navigation

interface Destinations {
    val route: String
}


object OnboardScreen : Destinations {
    override val route: String = "OnboardScreen"
}

object HomeScreen : Destinations {
    override val route: String = "HomeScreen"
}

object ProfileScreen : Destinations {
    override val route: String = "ProfileScreen"
}

object WelcomingScreen : Destinations {
    override val route: String = "WelcomingScreen"
}

object DishDetails : Destinations {
    override val route: String = "DishDetails"
}

object SplashScreenAnimation : Destinations {
    override val route: String = "SplashScreenAnimation"
}