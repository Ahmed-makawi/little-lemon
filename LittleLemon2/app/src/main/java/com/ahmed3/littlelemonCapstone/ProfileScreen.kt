package com.ahmed3.littlelemonCapstone

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
        header(navController)
        bodyOfTheProfile(navController)
    }
}


@Composable
fun header(navController: NavController) {

    Row(
        //  horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = {
            navController.popBackStack()
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
                //.fillMaxWidth(0.5F)
                .size(150.dp)
                .padding(horizontal = 20.dp)
            //.fillMaxWidth()
        )

        //Spacer(modifier = Modifier.width(65.dp))

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



    Column(
        Modifier.background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "profile Icon",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape),
        )

        Text(
            text = "Personal Information",
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            color = MaterialTheme.colors.secondary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(20.dp, 40.dp, 0.dp, 20.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

       /* Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colors.background)
        ) {*/
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

                Spacer(modifier = Modifier.height(270.dp))

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
        //}
    }
}