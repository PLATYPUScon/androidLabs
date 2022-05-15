package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.db.CharacterDatabase


class MainActivity : AppCompatActivity(), CharacterList.CharacterListListener, CharacterInfo.CharacterInfoListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var fragment : CharacterInfo

    override fun openCharacterInfo(character: Character) {
        if (this::fragment.isInitialized){
            supportFragmentManager
                .popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            supportFragmentManager
                .beginTransaction()
                .remove(fragment)
                .commit() }

        fragment = CharacterInfo()
        val bundle = Bundle()
        bundle.putSerializable("character", character)
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .add(R.id.character_info_fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun addFragment() {
        binding.characterInfoFragmentContainer.visibility = View.VISIBLE
    }

    override fun destroyFragment() {
        binding.characterInfoFragmentContainer.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CharacterDatabase.buildDatabase(this)
        Log.d("myDebug", "dataBase created")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}





//        binding.characterRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//
//
//            }
//        })