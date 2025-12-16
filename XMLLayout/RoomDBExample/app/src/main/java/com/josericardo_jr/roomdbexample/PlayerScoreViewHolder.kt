package com.josericardo_jr.roomdbexample

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlayerScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(playerScore: PlayerScore){
        itemView.findViewById<TextView>(R.id.lblName).text = playerScore.name
        itemView.findViewById<TextView>(R.id.lblScore).text = playerScore.score.toString()
    }

}