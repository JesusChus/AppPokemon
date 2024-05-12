package com.chusdevil.mypokemonapi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chusdevil.mypokemonapi.PokemonDetalles.Companion.ID
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val listaPokemon = mutableListOf<ElementoLista>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lista = findViewById<RecyclerView>(R.id.rvLista)
        val adapter = ListaAdapter(listaPokemon) { onClick(it) }
        lista.layoutManager = LinearLayoutManager(this)
        lista.adapter = adapter

        val retrofit = iniciaRetrofit()
        val api = retrofit.create(ApiPokemon::class.java)
        api.traerPokemon().enqueue(object : Callback<DatosPokemon> {
            override fun onResponse(p0: Call<DatosPokemon>, p1: Response<DatosPokemon>) {
                val todo = p1.body()!!
                todo.results.forEach {
                    listaPokemon.add(ElementoLista(it.name, it.url))
                }
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(p0: Call<DatosPokemon>, p1: Throwable) {

            }

        })
    }


    fun iniciaRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun onClick(posicion: String) {
        val intent = Intent(this, PokemonDetalles::class.java)
        intent.putExtra(ID, posicion)
        startActivity(intent)
    }
}