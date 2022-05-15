package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CharacterItemBinding
import com.squareup.picasso.Picasso

class CharacterAdapter(private val listener: Listener, private var characterList: List<Character>): RecyclerView.Adapter<CharacterAdapter.CharacterHolder>(){

    inner class CharacterHolder(item: View): RecyclerView.ViewHolder(item){
        private val binding = CharacterItemBinding.bind(item)

        fun bind(character: Character, listener: Listener){
            Log.d("myDebug", "holder binding")
            Picasso.get().load(character.imageUrl).into(binding.charIcon)
            binding.charName.text = character.name

            itemView.setOnClickListener{
                listener.onClick(character)
            }
            Log.d("myDebug", "holder was bind")
        }

    }

    fun setCharacters(characterList: List<Character>){
        this.characterList = characterList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        Log.d("myDebug", "viewHolder created")
        return CharacterHolder(view)

    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        holder.bind(characterList[position], listener)
        Log.d("myDebug", "view is bind")
    }

    override fun getItemCount(): Int {
        Log.d("myDebug", "getItemCount done")
        return characterList.size

    }

    interface Listener{
        fun onClick(character: Character)
    }
}