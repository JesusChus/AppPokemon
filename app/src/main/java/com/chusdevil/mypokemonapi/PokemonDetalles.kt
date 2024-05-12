package com.chusdevil.mypokemonapi

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonDetalles : AppCompatActivity() {

    companion object {
        const val ID = "nombre"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detalles)

        val nombre = findViewById<ImageView>(R.id.laUrl)
        val otro = findViewById<TextView>(R.id.lol)
        val id = intent.getStringExtra(ID)
        val retrofit = incioRetrofit()

        val api = retrofit.create(ApiPokemon::class.java)
        api.traerUnPokemon(id!!).enqueue(object : Callback<UnPokemon> {
            override fun onResponse(p0: Call<UnPokemon>, p1: Response<UnPokemon>) {
                val respuesta = p1.body()!!

                Picasso.get().load(respuesta.sprites.front_default).into(nombre)
                otro.text = respuesta.name


            }

            override fun onFailure(p0: Call<UnPokemon>, p1: Throwable) {

            }

        })

    }

    fun incioRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(intent.getStringExtra(ID))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}