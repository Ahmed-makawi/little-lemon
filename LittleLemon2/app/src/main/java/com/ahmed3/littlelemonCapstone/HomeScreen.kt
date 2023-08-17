package com.ahmed3.littlelemonCapstone

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Room
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.ahmed3.littlelemonCapstone.ui.theme.primDark
import com.ahmed3.littlelemonCapstone.ui.theme.primLight
import com.ahmed3.littlelemonCapstone.ui.theme.lightGray


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(navController: NavController) {

    val context = LocalContext.current
    val database by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }
    val allMenuLiveDava by database.menuItemDao().getAll().observeAsState(emptyList())

    var searchPhrase = rememberSaveable { mutableStateOf("") }
    val filteredMenu = rememberSaveable { mutableStateOf(allMenuLiveDava) }


    Column(
        Modifier.fillMaxSize()
    ) {

        apperPanalHome(navController)
        heroSection(allMenuLiveDava,searchPhrase,filteredMenu)
        weeklySpetialCard()
        menuBreakDown(allMenuLiveDava, filteredMenu)
        lowerSection(filteredMenu)
        println(allMenuLiveDava)
    }
}

@Composable
fun apperPanalHome(navController: NavController) {

    Row(
        //  horizontalArrangement = Arrangement.SpaceEvenly,
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
                painter = painterResource(id = R.drawable.profile),
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
            modifier = Modifier
                .size(45.dp)
            //.fillMaxWidth()
        )

    }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun heroSection(
    allMenuLiveDava2: List<MenuItemRoom>,
    searchPhrase: MutableState<String>,
    filteredMenu: MutableState<List<MenuItemRoom>>
) {
    Column(
        Modifier
            .height(320.dp)
            .fillMaxWidth()
            .background(primDark)
            .padding(13.dp)
    ) {

        Text(
            text = stringResource(id = R.string.heroSectionTitle),
            fontSize = 37.sp,
            color = primLight
        )

        Text(
            text = stringResource(id = R.string.heroSectionSecondTitle),
            fontSize = 20.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(9.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .height(130.dp)
        ) {

            Column(Modifier.width(210.dp)) {
                Text(
                    text = stringResource(id = R.string.heroSectionDescription),
                    color = Color.White,
                    style = MaterialTheme.typography.body2
                )
            }

            Image(
                painter = rememberImagePainter(
                    R.drawable.hero_image,
                    builder = {
                        transformations(
                            RoundedCornersTransformation(30f),
                        )
                    }
                ),
                contentDescription = "hero image",
                Modifier.height(130.dp)
            )


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
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            },
            placeholder = { Text(text = "Enter search phrase") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search icon",
                    modifier = Modifier.clickable {}
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = { }
                )
                {
                    if (searchPhrase.value.isNotBlank()) {
                        Icon(imageVector = Icons.Default.Clear,
                            contentDescription = "search icon",
                            modifier = Modifier.clickable { searchPhrase.value = "" }
                        )
                    }
                }

            },
        )
    }
    FilteringByTitle(allMenuLiveDava2, filteredMenu, searchPhrase)
}

@Composable
fun weeklySpetialCard() {
    Card(
        Modifier
            .background(MaterialTheme.colors.background)
    ) {
        Column(
            Modifier
                .background(MaterialTheme.colors.background)
                .padding(start = 9.dp, top = 8.dp)
        ) {

            Text(
                text = "Weekly Special",
                color = MaterialTheme.colors.secondary,
                fontSize = 24.sp,
                modifier = Modifier.background(MaterialTheme.colors.background)
            )

            Spacer(modifier = Modifier.height(4.dp))
            Divider(color = Color.LightGray.copy(alpha = 0.4f))

        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
@Composable
fun MenuItems(menuItem: MenuItemRoom) {

    var isLoading by remember { mutableStateOf(true) }

    Spacer(modifier = Modifier.height(3.dp))

    Card(
        onClick = { /*TODO*/ },
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
            //horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.width(250.dp)) {
                Text(
                    text = menuItem.title,
                    style = MaterialTheme.typography.h6
                )
                if (isLoading) {
                    CircularProgressIndicator()
                    if (menuItem.description.isNotBlank()) {
                        isLoading = false
                    }
                } else {
                    Text(
                        text = menuItem.description,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .padding(5.dp),

                        )
                }
                Text(
                    text = " $${menuItem.price}",
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold
                )

            }

            val painter = rememberImagePainter(
                menuItem.image,
                builder = {
                    transformations(
                        RoundedCornersTransformation(10f)
                    )
                }
            )
            Image(
                painter = painter,
                contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .height(110.dp)
                    .width(140.dp)
            )
            if (painter.state is ImagePainter.State.Loading) {
                CircularProgressIndicator()
            }

        }
    }
    Spacer(modifier = Modifier.height(3.dp))

}


@Composable
fun menuBreakDown(
    allMenu: List<MenuItemRoom>?,
    filteredMenu: MutableState<List<MenuItemRoom>>
) {

    val listOfCategories:Set<String>? = allMenu?.map { menuItem ->
        menuItem.category
    }?.toSet()

    LazyRow(
        Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {

        listOfCategories?.forEach { category ->
            item {
                menuBreakDownCards(category, allMenu, filteredMenu)
            }
    }
    }
}

@Composable
fun menuBreakDownCards(
    category: String,
    allMenu: List<MenuItemRoom>,
    filteredMenu: MutableState<List<MenuItemRoom>>
) {

    var isClicked by remember { mutableStateOf(false) }
    var boxColor by remember { mutableStateOf(lightGray) }

    Spacer(modifier = Modifier.width(50.dp))
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(15.dp))
            .background(boxColor)
            .width(70.dp)
            .height(40.dp)
            .clickable {
                isClicked = !isClicked
                if (isClicked) {
                    filteringByCetogry(
                        allMenuLiveDava2 = allMenu,
                        filteredMenu = filteredMenu,
                        category = category
                    )
                    boxColor = primLight

                } else {
                    filteringByCetogry(
                        allMenuLiveDava2 = allMenu,
                        filteredMenu = filteredMenu,
                        category = "",
                    )
                    boxColor = lightGray
                }
            }
    ) {
        Text(
            text = category,
            fontSize = 12.sp,
            color = primDark,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun searchNotFoundIcon() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(100.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = null,
            Modifier
                .size(150.dp)
                .alpha(0.1f)
                .background(Color.LightGray, shape = CircleShape)
        )
    }
}

@Composable
fun button(searchPhrase: String) {

    val context: Context = LocalContext.current
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)


    Button(
        onClick = {
            /*GlobalScope.launch {
                withContext(Dispatchers.IO) {
                    sharedPreferences.edit(commit = true) {
                        putBoolean("isNeedWelcoming", true)
                    }
                }*/
            //Toast.makeText(context, searchPhrase, Toast.LENGTH_SHORT).show()
        },
        Modifier
            .width(40.dp)
            .height(40.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Red,
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = "Test",
            fontSize = MaterialTheme.typography.button.fontSize,
            color = MaterialTheme.colors.secondary
        )
    }
}


/*@Preview(showBackground = true)
@Composable
fun previewHeroSection(){
    heroSection()
}*/




@Composable
fun lowerSection(filteredMenu: MutableState<List<MenuItemRoom>>) {

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(15.dp, 10.dp)
    ) {
        if (filteredMenu.value.isEmpty()) {
            searchNotFoundIcon()
        }

        LazyColumn {
            items(filteredMenu.value!!) { menuItem ->
                MenuItems(menuItem)
            }
        }
    }
}

@Composable
fun FilteringByTitle(
    allMenuLiveDava2: List<MenuItemRoom>,
    filteredMenu: MutableState<List<MenuItemRoom>>,
    searchPhrase: MutableState<String>
) {

    filteredMenu.value =
        if (searchPhrase.value.isBlank()) {
            allMenuLiveDava2
        } else if (!allMenuLiveDava2.any { menuItem ->
                menuItem.title.contains(searchPhrase.value)
            }) {
            emptyList()
        } else {
            val lowercaseSearchPhrase = searchPhrase.value.lowercase()
            allMenuLiveDava2.filter { menuItem ->
                menuItem.title.lowercase().contains(lowercaseSearchPhrase)

            }
        }
}

fun filteringByCetogry(
    allMenuLiveDava2: List<MenuItemRoom>,
    filteredMenu: MutableState<List<MenuItemRoom>>,
    category: String
){

    filteredMenu.value =
    if (category != "") {
        allMenuLiveDava2.filter { menuItem ->
                menuItem.category.contains(category)
            }
    }else
    {
        allMenuLiveDava2
    }
}
