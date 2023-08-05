package com.ahmed3.littlelemonCapstone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun homeScreen(navController: NavHostController) {

    val navController = navController

    Column() {

        logoImage()

        //Spacer(modifier = Modifier.height(250.dp))

        Box(
            modifier = Modifier.background(MaterialTheme.colors.background)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    navController.navigate(ProfileScreen.route)
                }, Modifier
                    .width(350.dp)
                    .height(46.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary,
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "go to profile screen",
                    fontSize = MaterialTheme.typography.button.fontSize,
                    color = MaterialTheme.colors.secondary
                )
            }
        }

    }
}
