package com.chusdevil.mypokemonapi


import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ElementosViewHolder(itemView: View) : ViewHolder(itemView) {

    private val nombre = itemView.findViewById<TextView>(R.id.nombre)


    fun datosElemento(name: String, url: String, onClick: (String) -> Unit) {
        nombre.text = name
        itemView.setOnClickListener {
            onClick(url)

        }

    }
}