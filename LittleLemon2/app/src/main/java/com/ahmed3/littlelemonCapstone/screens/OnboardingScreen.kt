package com.ahmed3.littlelemonCapstone.screens

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.navigation.NavController
import com.ahmed3.littlelemonCapstone.R
import kotlinx.coroutines.*


@Composable
    fun Onboard(navController: NavController) {

        Column (
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ){
            LogoImage()
            LetsGetTOKnowYouText()
            BodyOfThePage(navController)
        }
    }


    @Composable
    fun LogoImage() {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .background(color = Color.White),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "header logo",
                modifier = Modifier
                    .height(35.dp)
                    .fillMaxWidth()
            )
        }
    }


    @Composable
    fun LetsGetTOKnowYouText() {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .background(color = colorResource(id = R.color.darkGreen))
        ) {
            Text(
                text = "Let's get to know you",
                fontSize = 23.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.karla_regular)),
            )
        }
    }


    @OptIn(DelicateCoroutinesApi::class)
    @Composable
    fun BodyOfThePage(navController: NavController) {

        val context: Context = LocalContext.current
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var loginStatus by remember { mutableStateOf(false) }
        val sharedPreferences by lazy { context.getSharedPreferences("LittleLemon", MODE_PRIVATE) }



        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = "Personal Information",
                fontFamily = FontFamily(Font(R.font.karla_regular)),
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                color = MaterialTheme.colors.secondary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(20.dp, 40.dp, 0.dp, 40.dp)
                    .align(Alignment.Start)
            )

                    /** FIRST NAME TEXT FIELD **/
                    OutlinedTextField(
                        colors = TextFieldDefaults.textFieldColors(
                            MaterialTheme.colors.secondary
                        ),
                        value = firstName,
                        onValueChange = { value -> firstName = value },
                        label = {
                            Text(
                                text = "First Name",
                                fontFamily = FontFamily(Font(R.font.karla_regular)),
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
                        trailingIcon = {
                            IconButton(onClick = { firstName = "" }) {
                                if (firstName != "") {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = ""
                                    )
                                }
                            }
                        },
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    /** LAST NAME TEXT FIELD **/
                    OutlinedTextField(
                        colors = TextFieldDefaults.textFieldColors(
                            MaterialTheme.colors.secondary
                        ),
                        value = lastName,
                        onValueChange = { value -> lastName = value },
                        label = {
                            Text(
                                text = "Last Name",
                                fontFamily = FontFamily(Font(R.font.karla_regular)),
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
                        trailingIcon = {
                            IconButton(onClick = { lastName = "" }) {
                                if (lastName != "") {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = ""
                                    )
                                }
                            }
                        },
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    /** EMAIL TEXT FIELD **/
                    OutlinedTextField(
                        colors = TextFieldDefaults.textFieldColors(
                            MaterialTheme.colors.secondary
                        ),
                        value = email,
                        onValueChange = { value -> email = value },
                        label = {
                            Text(
                                text = "Email",
                                fontFamily = FontFamily(Font(R.font.karla_regular)),
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
                            Icon(imageVector = Icons.Default.Email, contentDescription = "")
                        },
                        trailingIcon = {
                            IconButton(onClick = { email = "" }) {
                                if (email != "") {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = ""
                                    )
                                }
                            }
                        },

                        )
                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(modifier = Modifier.weight(1f))

                    val failedToast =
                        toast("Registration unsuccessful. Please enter all data")
                    val successesToast = toast("Registration successful!")
                    val emailToast = toast("email is not valid")
                    val nameValidation =
                        (firstName.isBlank() || lastName.isBlank() || email.isBlank())


                    Button(
                        onClick = {
                            if (nameValidation) {
                                failedToast.show()
                            } else if (!(email.contains("@gmail.com"))) {
                                emailToast.show()
                            } else {
                                loginStatus = true
                                GlobalScope.launch {
                                    withContext(Dispatchers.IO) {
                                        sharedPreferences.edit(commit = true) {
                                            putString("firstName", firstName)
                                            putString("lastName", lastName)
                                            putString("email", email)
                                            putBoolean("loginStatus", loginStatus)
                                            putBoolean("isNeedWelcoming", false)
                                        }

                                    }
                                }
                                successesToast.show()
                                navController.popBackStack()
                                navController.popBackStack()
                                navController.navigate(com.ahmed3.littlelemonCapstone.navigation.HomeScreen.route)
                            }
                        },
                        Modifier
                            .width(350.dp)
                            .height(46.dp)
                            .align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary,
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Register",
                            fontFamily = FontFamily(Font(R.font.karla_regular)),
                            fontSize = MaterialTheme.typography.button.fontSize,
                            color = MaterialTheme.colors.secondary
                        )
                    }
               Spacer(modifier = Modifier.height(20.dp))
        }
    }

    @Composable
    fun toast(string: String): Toast {
        return Toast.makeText(LocalContext.current, string, Toast.LENGTH_SHORT)
    }