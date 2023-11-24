package com.ahmed3.littlelemonCapstone.screens

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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmed3.littlelemonCapstone.R
import com.ahmed3.littlelemonCapstone.navigation.OnboardScreen

@Composable
fun WelcomingScreen(navController: NavController) {

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "HI",
                fontSize = MaterialTheme.typography.h4.fontSize,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(10.dp),
                fontFamily =  FontFamily(Font(R.font.karla_regular)),
            )

            Text(
                text = "Let's Get To Know You",
                fontSize = MaterialTheme.typography.h5.fontSize,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(10.dp),
                fontFamily =  FontFamily(Font(R.font.karla_regular)),
            )
        }

        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(0.15f)
        ){
            Button(
                onClick = {
                    navController.navigate(OnboardScreen.route)
                },
                modifier = Modifier
                    .width(350.dp)
                    .height(46.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primary),
                shape = RoundedCornerShape(10.dp),

                ) {
                Text(
                    text = "Let's Go",
                    fontFamily = FontFamily(Font(R.font.karla_regular)),
                    fontSize = MaterialTheme.typography.button.fontSize,
                    color = MaterialTheme.colors.secondary
                )
            }

        }
    }
}