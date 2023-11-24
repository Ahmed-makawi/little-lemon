package com.ahmed3.littlelemonCapstone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.ahmed3.littlelemonCapstone.R
import com.ahmed3.littlelemonCapstone.data.MenuItemRoom

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DishDetails(navController: NavController) {

    val dish = navController.previousBackStackEntry?.savedStateHandle?.get<MenuItemRoom>("menu")

    Column(Modifier.background(MaterialTheme.colors.background)) {

        Header(navController)

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .height(260.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
        ) {

            val painter = rememberImagePainter(dish?.image)

            if (painter.state is ImagePainter.State.Loading) {
                CircularProgressIndicator()
            }
            Image(
                painter = painter,
                contentDescription = "Dish image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp),
                contentScale = ContentScale.FillWidth
            )

        }


        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.height(290.dp)
                .background(MaterialTheme.colors.background)
        ) {
            Text(
                text = dish?.title ?: "",
                fontFamily = FontFamily(Font(R.font.karla_regular)),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.secondary
            )
            Text(
                text =  dish?.description ?: "",
                style = MaterialTheme.typography.body1,
                fontFamily = FontFamily(Font(R.font.karla_regular)),
                modifier = Modifier.padding(start = 13.dp, top = 10.dp , end = 10.dp),
                color = MaterialTheme.colors.secondary
            )
        }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)
                    .background(MaterialTheme.colors.background)
                    .fillMaxHeight()
            ) {

                Counter()

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {},
                    Modifier
                        .width(350.dp)
                        .height(46.dp), colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary,
                    ), shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Add for " + " $${dish?.price}",
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.karla_regular)),
                        style = MaterialTheme.typography.button,
                        color = MaterialTheme.colors.secondary,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .fillMaxWidth()
                    )
                }

            }
    }
}

@Composable
fun Counter() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(),
    ) {
        var counter by rememberSaveable {
            mutableStateOf(1)
        }
        TextButton(

            modifier = Modifier.background(MaterialTheme.colors.background)
                .border(1.dp, MaterialTheme.colors.primary, RoundedCornerShape(10.dp)),
            onClick = {
                if (counter > 0)
                    counter--
            },
        ) {
            Text(
                text = "-",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.karla_regular)),
                color = MaterialTheme.colors.secondary
            )
        }
        Text(
            text = counter.toString(),
            fontFamily = FontFamily(Font(R.font.karla_regular)),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colors.secondary
        )
        TextButton(
            modifier = Modifier.background(MaterialTheme.colors.background)
                .border(1.dp , MaterialTheme.colors.primary, RoundedCornerShape(10.dp)),
            onClick = {
                if (counter >= 0)
                    counter++
            },
        ) {
            Text(
                text = "+",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.karla_regular)),
                color = MaterialTheme.colors.secondary
            )
        }
    }
}
