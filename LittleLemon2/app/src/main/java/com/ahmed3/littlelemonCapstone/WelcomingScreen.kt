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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun welcomingScreen(navController: NavController){

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Hello There !",
            fontSize = 35.sp,
            color = MaterialTheme.colors.secondary
            )
        Text(
            text = "let's Get to Know You",
            fontSize = 30.sp,
            color = MaterialTheme.colors.secondary
        )

        Spacer(modifier = Modifier.height(600.dp))
        
        Button(
            onClick = {
                navController.navigate(OnboardingScreen.route)
                      },
            modifier = Modifier
                .width(350.dp)
                .height(46.dp),
            colors = ButtonDefaults.buttonColors( MaterialTheme.colors.primary),
            shape = RoundedCornerShape(10.dp),

        ) {
            Text(
                text = "Let's Go",
                fontSize = MaterialTheme.typography.button.fontSize,
                color = MaterialTheme.colors.secondary
            )
        }

    }

}