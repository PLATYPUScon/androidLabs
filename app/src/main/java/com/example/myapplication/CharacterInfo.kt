package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentCharacterInfoBinding
import com.squareup.picasso.Picasso

class CharacterInfo : Fragment() {
    interface CharacterInfoListener {
        fun addFragment()
        fun destroyFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character_info, container, false)
        val viewBinding = FragmentCharacterInfoBinding.bind(view)

        val item = arguments?.getSerializable("character") as Character
        Log.d("myDebug", "CharacterInfo character get")
        viewBinding.apply {
            Picasso.get().load(item.imageUrl).into(iconExtend)
            charNameExtend.text = item.name

            extendedContent1.text = item.films
            extendedContent2.text = item.shortFilms
            extendedContent3.text = item.tvShows
            extendedContent4.text = item.videoGames
            extendedContent5.text = item.parkAttractions
            extendedContent6.text = item.allies
            extendedContent7.text = item.enemies
        }
        (activity as? CharacterInfoListener)?.addFragment()
        return viewBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as? CharacterInfoListener)?.destroyFragment()
    }
}