package com.ahmed3.littlelemonCapstone

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.ahmed3.littlelemonCapstone.ui.theme.LittleLemonTheme
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : ComponentActivity() {

    private val httpClient = HttpClient{
        install(ContentNegotiation){
            json()
        }
    }


    private val database by lazy{
        Room.databaseBuilder(applicationContext,AppDatabase::class.java,"database")
            .fallbackToDestructiveMigration()
            .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                LittleLemonTheme {
                    Navigation()
                }

        }



        lifecycleScope.launch{
            withContext(Dispatchers.IO){
                if (database.menuItemDao().isEmpty()){
                    runOnUiThread() {
                        Toast.makeText(applicationContext, "fetchData : database empty", Toast.LENGTH_SHORT).show()
                    }
                    val menu = fetchData()
                println("------------------------------------------")
                    println(menu)
                    saveMenuToDatabase(menu)
                }
            }
        }
    }


/*

    private suspend fun fetchData(): List<MenuItemNetwork> {
        val url =
            "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"

        return try {
           httpClient.get(url)
                .body<MenuNetwork>()
                .menu
        }catch (e:Exception){
            runOnUiThread() {
                Toast.makeText(applicationContext, "fetchData : $e.message", Toast.LENGTH_LONG).show()
                println(e.message)
            }
            emptyList()
        }
    }
*/



    private suspend fun fetchData(): List<MenuItemNetwork> {

        return try {
            val url =
                "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
            val jsonRespones = httpClient.get(url).bodyAsText()

            val gson = Gson()
            val menuNetwork = gson.fromJson(jsonRespones, MenuNetwork::class.java)
            menuNetwork.menu

        } catch (e:Exception) {
            runOnUiThread {
                println(e.message)
                Toast.makeText(applicationContext, "main fecth : ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
            emptyList()
        }

    }



    private fun saveMenuToDatabase(menu:List<MenuItemNetwork>){
        GlobalScope.launch {
            withContext(Dispatchers.IO){
                val menuItemRoom = menu.map { it.toMenuItemRoom() }
                database.menuItemDao().insertAll(*menuItemRoom.toTypedArray())
            }
        }
    }

}


