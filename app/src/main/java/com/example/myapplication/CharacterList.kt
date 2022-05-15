package com.example.myapplication

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.FragmentCharacterListBinding
import com.example.myapplication.db.CharacterDatabase
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

private const val ENDPOINT = "https://api.disneyapi.dev/characters"

class CharacterList : Fragment(), CharacterAdapter.Listener {

    interface CharacterListListener {
        fun openCharacterInfo(character: Character)
    }

    private val adapter = CharacterAdapter(this@CharacterList, emptyList())

    lateinit var fragmentBinding: FragmentCharacterListBinding


    private fun createHttpRequest(client: OkHttpClient, handler: Handler){
        val request = Request.Builder()
            .url(ENDPOINT)
            .build()

        client.newCall(request).enqueue(object : Callback {
            @SuppressLint("NotifyDataSetChanged")
            override fun onFailure(call: Call, error: IOException) {
                Log.d("myDebug", "onFailure")
                try {
                    val charactersLiveData =
                        CharacterDatabase.instance.characterDao().getAllCharacters()
                    handler.post {
                        adapter.setCharacters(charactersLiveData)
                        adapter.notifyDataSetChanged()
                    }
                }
                catch (onFailureError: SQLiteException){
                    onFailureError.printStackTrace()
                }
            }

            @SuppressLint("NotifyDataSetChanged")
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
                            catch (DBerr: SQLiteException){
                                DBerr.printStackTrace()
                            }

                        }
                        handler.post{
                            Log.d("myDebug", "bfr Adapter set")
                            adapter.setCharacters(characterList1)
                            adapter.notifyDataSetChanged()
                            Log.d("myDebug", "aft Adapter set")
                        }
                    }catch (JSerr: JSONException){
                        JSerr.printStackTrace()
                    }
                }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("myDebug", "onCreate_characterList")
        super.onCreate(savedInstanceState)
        fragmentBinding = FragmentCharacterListBinding.inflate(requireActivity().layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return fragmentBinding.root
    }

    override fun onStart() {
        super.onStart()

        Log.d("myDebug", "onStart_CharacterList")
        val client = OkHttpClient()
        val handler = Handler()

        fragmentBinding.apply {
            characterRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            characterRecyclerView.adapter = adapter
        }
        createHttpRequest(client, handler)
    }

    override fun onClick(character: Character) {
        (activity as? CharacterListListener)?.openCharacterInfo(character)
    }

    companion object {
        @JvmStatic
        fun newInstance() = CharacterList()
    }
}