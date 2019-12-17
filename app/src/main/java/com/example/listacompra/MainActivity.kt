package com.example.listacompra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tarjetaAdapter : AdaptadorAlimentos
        val url = "http://iesayala.ddns.net/bonoso96/php/alimentos.php"
        val request = Request.Builder().url(url).build()
        val cliente = OkHttpClient()

        cliente.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: okhttp3.Call, response: Response) {
                val json = response.body()?.string()
                println(json)

                val gson = GsonBuilder().create()

                var lista = gson.fromJson(json, AlimentosArray::class.java)
                println(lista.alimentos)

                runOnUiThread {
                    recyclerId.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        tarjetaAdapter = AdaptadorAlimentos(lista.alimentos!!)
                        adapter = tarjetaAdapter
                    }
                }
            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                println("Error")
            }


        })
    }
}