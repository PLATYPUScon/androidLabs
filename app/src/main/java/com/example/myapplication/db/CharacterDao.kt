package com.example.myapplication.db

import android.database.sqlite.SQLiteException
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CharacterDao {

    @Insert( onConflict = OnConflictStrategy.ABORT )
    @Throws(SQLiteException::class)
    fun insertCharacter(characterEntity: com.example.myapplication.Character)

    @Update
    fun updateCharacter(characterEntity: com.example.myapplication.Character)

    @Delete
    fun deleteCharacter(characterEntity: com.example.myapplication.Character)

    @Query("SELECT * FROM character_data_table")
    fun getAllCharacters() : List<com.example.myapplication.Character>
//
//    @Query("SELECT * FROM employee WHERE id = :id")
//    fun getById(_id : Int) : com.example.myapplication.Character
}