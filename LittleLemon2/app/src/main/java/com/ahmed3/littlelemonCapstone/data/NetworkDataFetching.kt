package com.ahmed3.littlelemonCapstone.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }


    private val database by lazy {
        Room.databaseBuilder(application, AppDatabase::class.java, "database")
            .fallbackToDestructiveMigration().build()
    }


    fun fetchMenuDataIfNeeded() {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (database.menuItemDao().isEmpty()) {
                    val menu = fetchData()
                    println("------------------------------------------")
                    println(menu)
                    saveMenuToDatabase(menu)
                }
            }
        }
    }


    private suspend fun fetchData(): List<MenuItemNetwork> {

        return try {
            val url =
                "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
            val jsonResponse = httpClient.get(url).bodyAsText()

            val gson = Gson()
            val menuNetwork = gson.fromJson(jsonResponse, MenuNetwork::class.java)
            menuNetwork.menu

        } catch (e: Exception) {
            emptyList()
        }

    }


    private fun saveMenuToDatabase(menu: List<MenuItemNetwork>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val menuItemRoom = menu.map { it.toMenuItemRoom() }
                database.menuItemDao().insertAll(*menuItemRoom.toTypedArray())
            }
        }
    }
}