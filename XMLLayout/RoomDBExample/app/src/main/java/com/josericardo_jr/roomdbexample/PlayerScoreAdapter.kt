package com.josericardo_jr.roomdbexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PlayerScoreAdapter(private val playerScoreList: List<PlayerScore>) : RecyclerView.Adapter<PlayerScoreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerScoreViewHolder {
        // Aqui estamos criando uma view a partir do xml
        val view = LayoutInflater.from(parent.context).inflate(R.layout.playerscore_item, parent, false)
        return PlayerScoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerScoreViewHolder, position: Int) {
        // Aqui estamos associando os dados a um viewholder que foi recebido como parâmetro
        val playerScore = playerScoreList[position]
        holder.bind(playerScore)
    }

    // Metodo usado para retornar a quantidade de itens da lista
    override fun getItemCount(): Int {
        return playerScoreList.size
    }
}