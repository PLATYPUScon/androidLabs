package com.example.myapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [com.example.myapplication.Character::class],
    version = 2,
    exportSchema = false
)
abstract class CharacterDatabase : RoomDatabase(){
    abstract fun characterDao():CharacterDao
    companion object{
        lateinit var instance: CharacterDatabase
            private set

        fun buildDatabase(context: Context): CharacterDatabase {
            val db = Room.databaseBuilder(context,
                CharacterDatabase::class.java, "character_database.db")
                .build()
            instance = db
            return db
        }
    }
}