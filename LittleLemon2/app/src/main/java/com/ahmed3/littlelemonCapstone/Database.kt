package com.ahmed3.littlelemonCapstone

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface MenuItemDao{
    @Insert
    fun insertAll(vararg menuItem:MenuItemRoom)

    @Query("SELECT * FROM MenuItemRoom")
    fun getAll(): LiveData<List<MenuItemRoom>>

    @Query("SELECT (SELECT COUNT(*) FROM MenuItemRoom) == 0")
    fun isEmpty(): Boolean
}

@Database(
    entities = [MenuItemRoom::class],
    version = 1
)
abstract  class AppDatabase:RoomDatabase(){
    abstract fun menuItemDao():MenuItemDao
}


@Entity
data class MenuItemRoom(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String,
)
