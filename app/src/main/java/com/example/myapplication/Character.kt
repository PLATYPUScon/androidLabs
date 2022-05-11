package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import java.io.Serializable

@Entity(tableName = "character_data_table")
data class Character(
    @PrimaryKey val _id: Int, // номер персонажа

    @ColumnInfo(name = "url") val url: String, // ссылка на endpoint
    @ColumnInfo(name = "imageUrl") val imageUrl: String, // ссылка на endpoint
    @ColumnInfo(name = "character_name") val name: String, // имя
    @ColumnInfo(name = "films") val films: String, // полнометражные фильмы
    @ColumnInfo(name = "shortFilms") val shortFilms: String, // короткометражные фильмы
    @ColumnInfo(name = "tvShows") val tvShows: String, // сериалы
    @ColumnInfo(name = "videoGames") val videoGames: String, // видео игры
    @ColumnInfo(name = "parkAttractions") val parkAttractions: String, // парк атракционов
    @ColumnInfo(name = "allies") val allies: String, // союзники
    @ColumnInfo(name = "enemies") val enemies: String, // враги

) : Serializable

/*import java.io.Serializable

data class Character(
    val _id: Int? = null, // номер персонажа
    val url: String? = null, // ссылка на endpoint
    val imageUrl: String? = null, // ссылка на endpoint
    val name: String? = null, // имя
    val films: List<String>? = null, // полнометражные фильмы
    val shortFilms: List<String>? = null, // короткометражные фильмы
    val tvShows: List<String>? = null, // сериалы
    val videoGames: List<String>? = null, // видео игры
    val parkAttractions: List<String>? = null, // парк атракционов
    val allies: List<String>? = null, // союзники
    val enemies: List<String>? = null, // враги

) : Serializable*/
