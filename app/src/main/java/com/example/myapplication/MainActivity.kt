package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.db.CharacterDatabase


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CharacterDatabase.buildDatabase(this)
        Log.d("myDebug", "dataBase created")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val characterList_fragment = supportFragmentManager.findFragmentById(R.id.CharacterList_fragment)

        Log.d("myDebug", "ActivityMain setContented")
//        binding.characterRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//
//
//            }
//        })
//        var request = OkHttpRequest(client, ENDPOINT, context)
    }


}