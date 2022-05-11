/*
package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteException
import android.os.Handler
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.db.CharacterDatabase
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class OkHttpRequest(client: OkHttpClient, ENDPOINT: String, context: Context){
    val Handler = Handler()
    private fun createHttpRequest(client: OkHttpClient){
        val request = Request.Builder()
            .url(ENDPOINT)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, error: IOException) {
                Log.d("myDebug", "onFailure")
                try {
                    val charactersLiveData =
                        CharacterDatabase.instance.characterDao().getAllCharacters()
                    Log.d("myDebug", "getAllCharacters done")
                    val adapter = CharacterAdapter(this.context, charactersLiveData)
                    Log.d("myDebug", "adapter attached")
                    binding.apply {
                        characterRecyclerView.layoutManager =
                            GridLayoutManager(MainActivity.this, 2)
                        characterRecyclerView.adapter = adapter
                    }
                }
                catch (onFailureError: SQLiteException){
                    onFailureError.printStackTrace()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d("myDebug", "onResponse")
                val responseData = response.body?.string()
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    try {
                        val json = JSONObject(responseData)
                        println("Request Successful!!")
                        println(json)
//                        val count = json.getString("count")

                        val data = json.getJSONArray("data")


                        val characterList1 = mutableListOf<Character>()

                        for(i in 0 until data.length()){
                            val json_character = data.getJSONObject(i)
                            val _id = json_character.getInt("_id")
                            val name = json_character.getString("name")
                            val imageUrl = json_character.getString("imageUrl")
                            val url = json_character.getString("url")

                            fun jsonForArray(jsonArray: JSONArray): MutableList<String>{
                                val bufferArray = mutableListOf<String>()

                                if (jsonArray.length() == 0) return bufferArray

                                for (i in 0 until jsonArray.length()) {
                                    val tempStr = jsonArray.getString(i)
                                    bufferArray.add(tempStr)
                                }
                                return bufferArray
                            }

                            val json_films = json_character.getJSONArray("films")
                            val json_shortFilms = json_character.getJSONArray("shortFilms")
                            val json_tvShows = json_character.getJSONArray("tvShows")
                            val json_videoGames = json_character.getJSONArray("videoGames")
                            val json_attraction = json_character.getJSONArray("parkAttractions")
                            val json_allies = json_character.getJSONArray("allies")
                            val json_enemies = json_character.getJSONArray("enemies")

                            val films = jsonForArray(json_films)
                            val shortFilms = jsonForArray(json_shortFilms)
                            val tvShows = jsonForArray(json_tvShows)
                            val videoGames = jsonForArray(json_videoGames)
                            val parkAttraction = jsonForArray(json_attraction)
                            val allies = jsonForArray(json_allies)
                            val enemies = jsonForArray(json_enemies)

                            val characterEntity = Character(_id, url, imageUrl, name, films.toString(), shortFilms.toString(), tvShows.toString(), videoGames.toString(), parkAttraction.toString(), allies.toString(), enemies.toString())


                            characterList1.add(characterEntity)

                            try {
                                CharacterDatabase.instance.characterDao().insertCharacter(characterEntity)
                                println("character added" + i)
                            }
                            catch (bde: SQLiteException){
                                bde.printStackTrace()
                            }

                        }
                        Handler.post{
                            val adapter = CharacterAdapter(this@MainActivity, characterList1)
                            binding.apply {
                                characterRecyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)
                                characterRecyclerView.adapter = adapter
                            }
                        }
                    }catch (e: JSONException){
                        e.printStackTrace()
                    }
                }
            }
        })
    }
}*/
