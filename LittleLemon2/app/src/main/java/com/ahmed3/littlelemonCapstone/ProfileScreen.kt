package com.ahmed3.littlelemonCapstone

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun profileScreen(navController: NavController) {

    Column() {
        logoImage()
        bodyOfTheProfile(navController)
    }
}





@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun bodyOfTheProfile(navController: NavController) {



    val context: Context = LocalContext.current
    val sharedPreferences by lazy {context.getSharedPreferences("LittleLemon", MODE_PRIVATE)}
    var showFirstName = ""
    var showLastName = ""
    var showEmail = ""

    GlobalScope.launch{
        withContext(Dispatchers.IO){
            showFirstName = sharedPreferences.getString("firstName" , "").toString()
            showLastName = sharedPreferences.getString("lastName" , "").toString()
            showEmail = sharedPreferences.getString("email" , "").toString()
        }
    }



    Column(Modifier.background(MaterialTheme.colors.background)) {

        Text(
            text = "Personal Information",
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            color = MaterialTheme.colors.secondary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(20.dp, 40.dp, 0.dp, 40.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colors.background)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                /** FIRST NAME TEXT FIELD **/
                OutlinedTextField(
                    colors = TextFieldDefaults.textFieldColors(
                        MaterialTheme.colors.secondary
                    ),
                    value = showFirstName,
                    onValueChange = { "" },
                    label = {
                        Text(
                            text = "First Name",
                            fontSize = 12.sp,
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
                    onValueChange = { showLastName},
                    label = {
                        Text(
                            text = "Last Name",
                            fontSize = 12.sp,
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
                    onValueChange = { showEmail },
                    label = {
                        Text(
                            text = "Email",
                            fontSize = 12.sp,
                            color = MaterialTheme.colors.secondary
                        )
                    },
                    modifier = Modifier
                        /*.padding(0.dp, 10.dp, 0.dp, 170.dp)*/
                        .padding(horizontal = 20.dp)
                        .width(350.dp),
                    shape = RoundedCornerShape(10.dp),
                    maxLines = 1,
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Email, contentDescription = "")
                    },
                    enabled = false
                )

                Spacer(modifier = Modifier.height(230.dp))

                Button(
                    onClick = {
                        GlobalScope.launch {
                            withContext(Dispatchers.IO) {
                                sharedPreferences.edit(commit = true){
                                    putString("firstName" , "")
                                    putString("lastName" , "")
                                    putString("email" , "")
                                    putBoolean("loginStatus" , false)
                                }
                            }
                        }
                        navController.popBackStack()
                        navController.popBackStack()
                        navController.navigate(OnboardingScreen.route)
                              }
                           ,
                    Modifier
                        .width(350.dp)
                        .height(46.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Log Out",
                        fontSize = MaterialTheme.typography.button.fontSize,
                        color = MaterialTheme.colors.secondary
                    )
                }

            }
        }
    }
}