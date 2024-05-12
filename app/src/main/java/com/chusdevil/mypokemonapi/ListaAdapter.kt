package com.chusdevil.mypokemonapi


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter


class ListaAdapter(
    val listaPokemon: MutableList<ElementoLista>,
    private val onclick: (String) -> Unit
) : Adapter<ElementosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementosViewHolder {
        val elementoXml =
            LayoutInflater.from(parent.context).inflate(R.layout.elemento_lista, parent, false)
        val elemento = ElementosViewHolder(elementoXml)
        return elemento
    }

    override fun getItemCount(): Int {
        return listaPokemon.size
    }

    override fun onBindViewHolder(holder: ElementosViewHolder, position: Int) {
        val datos: ElementoLista = listaPokemon[position]
        holder.datosElemento(datos.pokemon, datos.url, onclick)

    }
}