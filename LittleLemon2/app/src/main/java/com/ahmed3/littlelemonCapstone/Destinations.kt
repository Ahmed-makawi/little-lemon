package com.ahmed3.littlelemonCapstone

interface Destinations {
    val route:String
}



object OnboardingScreen:Destinations{
    override val route: String = "OnboardingScreen"
}

object HomeScreen:Destinations{
    override val route: String = "HomeScreen"
}

object ProfileScreen:Destinations{
    override val route: String = "ProfileScreen"
}

