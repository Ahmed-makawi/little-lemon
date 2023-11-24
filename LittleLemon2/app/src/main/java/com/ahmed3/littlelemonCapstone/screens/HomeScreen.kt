package com.ahmed3.littlelemonCapstone.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.Room
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.ahmed3.littlelemonCapstone.R
import com.ahmed3.littlelemonCapstone.data.AppDatabase
import com.ahmed3.littlelemonCapstone.data.MenuItemRoom
import com.ahmed3.littlelemonCapstone.data.MyViewModel
import com.ahmed3.littlelemonCapstone.navigation.DishDetails
import com.ahmed3.littlelemonCapstone.navigation.ProfileScreen
import com.ahmed3.littlelemonCapstone.ui.theme.lightGray
import com.ahmed3.littlelemonCapstone.ui.theme.primDark
import com.ahmed3.littlelemonCapstone.ui.theme.primLight


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(navController: NavController) {

    val viewModel: MyViewModel = viewModel()
    val context = LocalContext.current
    val database by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }
    val allMenuLiveDava by database.menuItemDao().getAll().observeAsState(emptyList())
    val searchPhrase = rememberSaveable { mutableStateOf("") }
    val selectedCategory = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.fetchMenuDataIfNeeded()
    }


    Column(
        Modifier
            //.verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        AppearPanelHome(navController)
        HeroSection(searchPhrase)
        WeeklySpecialCard()
        MenuBreakDown(allMenuLiveDava, selectedCategory)
        LowerSection(allMenuLiveDava, searchPhrase, selectedCategory, navController)
        println(allMenuLiveDava)
    }
}


@Composable
fun AppearPanelHome(navController: NavController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = {
            navController.navigate(ProfileScreen.route)
        }) {
            Image(
                painter = painterResource(id = R.drawable.account_avatar),
                contentDescription = "profile Icon",
                modifier = Modifier
                    .size(39.dp)
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

        Spacer(modifier = Modifier.width(65.dp))

        Image(
            painter = painterResource(id = R.drawable.deliveryvan),
            contentDescription = "van logo",
            modifier = Modifier.size(45.dp)
            //.fillMaxWidth()
        )

    }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun HeroSection(searchPhrase: MutableState<String>) {

    Column(
        Modifier
            .height(330.dp)
            .fillMaxWidth()
            .background(primDark)
            .padding(13.dp)
    ) {

        Text(
            text = stringResource(id = R.string.heroSectionTitle),
            fontSize = 40.sp,
            color = primLight,
            fontFamily =  FontFamily(Font(R.font.markazi_text_regular)),
            fontWeight = FontWeight.Bold
        )

        Text(
            text = stringResource(id = R.string.heroSectionSecondTitle),
            fontSize = 20.sp,
            color = Color.White,
            fontFamily =  FontFamily(Font(R.font.karla_regular)),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )

       // Spacer(modifier = Modifier.height(9.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .height(135.dp)
        ) {

            Column(
                Modifier
                    .width(215.dp)
                    .height(135.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.heroSectionDescription),
                    color = Color.White,
                    style = MaterialTheme.typography.body2,
                    fontFamily = FontFamily(Font(R.font.karla_regular))
                )
            }

            Image(painter = rememberImagePainter(R.drawable.hero_image, builder = {
                transformations(
                    RoundedCornersTransformation(30f),
                )
            }), contentDescription = "hero image", Modifier.height(130.dp))


        }

        Spacer(modifier = Modifier.weight(1f))

        OutlinedTextField(
            modifier = Modifier.size(width = 367.dp, height = 62.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                placeholderColor = Color.White.copy(alpha = 0.4f),
                cursorColor = Color.White,
                backgroundColor = Color.White.copy(0.12f),
                focusedBorderColor = primLight
            ),
            value = searchPhrase.value,
            onValueChange = { searchPhrase.value = it },
            shape = RoundedCornerShape(10.dp),
            label = {
                Text(
                    text = "Search",
                    fontFamily = FontFamily(Font(R.font.karla_regular)),
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            },
            placeholder = {
                Text(
                    text = "Enter search phrase", fontSize = 12.sp
                )
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = "search icon",
                    modifier = Modifier.clickable {})
            },
            trailingIcon = {
                IconButton(onClick = { }) {
                    if (searchPhrase.value.isNotBlank()) {
                        Icon(imageVector = Icons.Default.Clear,
                            contentDescription = "search icon",
                            modifier = Modifier.clickable { searchPhrase.value = "" })
                    }
                }

            },
        )
    }
}


@Composable
fun WeeklySpecialCard() {
    Card(
        Modifier.background(MaterialTheme.colors.background)
    ) {
        Column(
            Modifier
                .background(MaterialTheme.colors.background)
                .padding(start = 9.dp, top = 8.dp)
        ) {

            Text(
                text = "Weekly Special",
                color = MaterialTheme.colors.secondary,
                fontFamily = FontFamily(Font(R.font.karla_regular)),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.background(MaterialTheme.colors.background)
            )

            Spacer(modifier = Modifier.height(4.dp))
            Divider(color = Color.LightGray.copy(alpha = 0.4f))

        }
    }
}


@Composable
fun LowerSection(
    allMenuLiveDava: List<MenuItemRoom>,
    search: MutableState<String>,
    selectedCategory: MutableState<String>,
    navController: NavController
) {

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(15.dp, 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        val items = if (search.value.isBlank()) {
            allMenuLiveDava
        } else {
            allMenuLiveDava.filter {
                it.title.contains(search.value, ignoreCase = true)
            }
        }
        if (items.isEmpty()) {
            SearchNotFoundIcon()
        }

        val filteredMenu = if (selectedCategory.value == "" || selectedCategory.value == "all") {
            items
        } else {
            items.filter {
                it.category.contains(selectedCategory.value, ignoreCase = true)
            }
        }

        LazyColumn {
            items(filteredMenu) { menuItem ->
                MenuItems(menuItem, navController)
            }
        }
    }
}


@Composable
fun MenuBreakDown(
    allMenu: List<MenuItemRoom>?, selectedCategory: MutableState<String>
) {

    val listOfCategories: Set<String>? = allMenu?.map { menuItem ->
        menuItem.category
    }?.toSet()

    Row(
        Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {

        MenuBreakDownCards(category = "all", selectedCategory)

        for (category in listOfCategories!!) {
            MenuBreakDownCards(category, selectedCategory)
        }

    }
}

@Composable
fun MenuBreakDownCards(
    category: String, selectedCategory: MutableState<String>
) {

    val boxColor by remember { mutableStateOf(lightGray) }

    Spacer(modifier = Modifier.width(23.dp))
    Box(contentAlignment = Alignment.Center, modifier = Modifier
        // .fillMaxWidth()
        .clip(shape = RoundedCornerShape(15.dp))
        .background(boxColor)
        .width(70.dp)
        .height(40.dp)
        .clickable {
            selectedCategory.value = category
        }) {
        Text(
            text = category,
            fontFamily = FontFamily(Font(R.font.karla_regular)),
            fontSize = 12.sp,
            color = primDark,
            fontWeight = FontWeight.Bold
        )
    }
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
@Composable
fun MenuItems(menuItem: MenuItemRoom, navController: NavController) {


    Spacer(modifier = Modifier.height(3.dp))

    Card(
        onClick = {
            navController.currentBackStackEntry?.savedStateHandle?.set(
                key = "menu",
                value = menuItem
            )
            navController.navigate(DishDetails.route)
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(9.dp, RectangleShape)
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxWidth()
                .padding(8.dp)
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.width(250.dp)) {
                Text(
                    text = menuItem.title,
                    fontFamily = FontFamily(Font(R.font.karla_regular)),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = menuItem.description,
                    style = MaterialTheme.typography.body2,
                    fontFamily = FontFamily(Font(R.font.karla_regular)),
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(5.dp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3,

                    )
                Text(
                    text = " $${menuItem.price}",
                    style = MaterialTheme.typography.caption,
                    fontFamily = FontFamily(Font(R.font.karla_regular)),
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                )

            }

            val painter = rememberImagePainter(menuItem.image, builder = {
                transformations(
                    RoundedCornersTransformation(10f),
                )
            })
            if (painter.state is ImagePainter.State.Loading) {
                CircularProgressIndicator()
            }
            Image(
                painter = painter,
                contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .height(110.dp)
                    .width(140.dp)
            )
        }
    }
    Spacer(modifier = Modifier.height(3.dp))

}


@Composable
fun SearchNotFoundIcon() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.background(Color.LightGray.copy(alpha = 0.2f), shape = CircleShape)

        //.padding(100.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = "",
            Modifier
                .size(170.dp)
                .alpha(0.2f)
        )
    }
}



