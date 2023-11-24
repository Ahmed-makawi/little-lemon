package com.ahmed3.littlelemonCapstone.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ahmed3.littlelemonCapstone.R
import com.ahmed3.littlelemonCapstone.navigation.OnboardScreen


@Composable
fun ProfileScreen(navController: NavController) {

    Column(Modifier.fillMaxSize()) {
        Header(navController)
        BodyOfTheProfile(navController)
    }
}


@Composable
fun Header(navController: NavController?) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = {
            navController?.popBackStack()
        }) {
            Image(
                painter = rememberVectorPainter(Icons.Default.ArrowBack),
                contentDescription = "profile Icon",
                modifier = Modifier
                    .size(30.dp)
                    .clip(shape = RoundedCornerShape(19.dp)),
            )
        }

        Spacer(modifier = Modifier.width(65.dp))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "header logo",
            modifier = Modifier
                .size(150.dp)
                .padding(horizontal = 20.dp)
        )
    }
}


@SuppressLint("CoroutineCreationDuringComposition", "SuspiciousIndentation")
@Composable
fun BodyOfTheProfile(navController: NavController) {


    val context: Context = LocalContext.current
    val sharedPreferences by lazy { context.getSharedPreferences("LittleLemon", MODE_PRIVATE) }
    val showFirstName = sharedPreferences.getString("firstName", "").toString()
    val showLastName = sharedPreferences.getString("lastName", "").toString()
    val showEmail = sharedPreferences.getString("email", "").toString()



        Column(
            Modifier.background(MaterialTheme.colors.background)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(id = R.drawable.account_avatar),
                contentDescription = "profile Icon",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
            )

            Text(
                text = "Personal Information",
                fontFamily = FontFamily(Font(R.font.karla_regular)),
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                color = MaterialTheme.colors.secondary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 40.dp, bottom = 20.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                /** FIRST NAME TEXT FIELD **/
                OutlinedTextField(
                    colors = TextFieldDefaults.textFieldColors(
                        MaterialTheme.colors.secondary
                    ),
                    value = showFirstName,
                    onValueChange = { },
                    label = {
                        Text(
                            text = "First Name",
                            fontFamily = FontFamily(Font(R.font.karla_regular)),
                            fontSize = 13.sp,
                            color = MaterialTheme.colors.secondary
                        )
                    },
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .width(350.dp),
                    shape = RoundedCornerShape(10.dp),
                    maxLines = 1,
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Person, contentDescription = "")
                    },
                    enabled = false
                )

                Spacer(modifier = Modifier.height(20.dp))

                /** LAST NAME TEXT FIELD **/
                OutlinedTextField(
                    colors = TextFieldDefaults.textFieldColors(
                        MaterialTheme.colors.secondary
                    ),
                    value = showLastName,
                    onValueChange = { },
                    label = {
                        Text(
                            text = "Last Name",
                            fontFamily = FontFamily(Font(R.font.karla_regular)),
                            fontSize = 13.sp,
                            color = MaterialTheme.colors.secondary
                        )
                    },
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .width(350.dp),
                    shape = RoundedCornerShape(10.dp),
                    maxLines = 1,
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Person, contentDescription = "")
                    },
                    enabled = false
                )

                Spacer(modifier = Modifier.height(20.dp))

                /** EMAIL TEXT FIELD **/
                OutlinedTextField(
                    colors = TextFieldDefaults.textFieldColors(
                        MaterialTheme.colors.secondary
                    ),
                    value = showEmail,
                    onValueChange = { },
                    label = {
                        Text(
                            text = "Email",
                            fontFamily = FontFamily(Font(R.font.karla_regular)),
                            fontSize = 13.sp,
                            color = MaterialTheme.colors.secondary
                        )
                    },
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .width(350.dp),
                    shape = RoundedCornerShape(10.dp),
                    maxLines = 1,
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Email, contentDescription = "")
                    },
                    enabled = false
                )

                Spacer(modifier = Modifier.height(20.dp))
                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        sharedPreferences.edit()
                            .putString("firstName", "")
                            .putString("lastName", "")
                            .putString("email", "")
                            .putBoolean("loginStatus", false)
                            .apply()

                        navController.popBackStack()
                        navController.popBackStack()
                        navController.popBackStack()
                        navController.popBackStack()
                        navController.navigate(OnboardScreen.route)
                    },
                    Modifier
                        .width(350.dp)
                        .height(46.dp), colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                    ), shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Log Out",
                        fontFamily = FontFamily(Font(R.font.karla_regular)),
                        fontSize = MaterialTheme.typography.button.fontSize,
                        color = MaterialTheme.colors.secondary
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
}