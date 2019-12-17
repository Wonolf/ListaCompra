package com.example.listacompra

import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

class AdaptadorAlimentos(var lista: ArrayList<Alimentos>): RecyclerView.Adapter<AdaptadorAlimentos.MiViewHolder>(){


    class MiViewHolder(view: View):RecyclerView.ViewHolder(view){
        fun enlazaItems(datos:Alimentos){

            val imagen: ImageView = itemView.findViewById(R.id.imagen)
            val nombre: TextView = itemView.findViewById(R.id.nombre)
            val categoria: TextView = itemView.findViewById(R.id.categoria)
            val fecha: TextView = itemView.findViewById(R.id.fecha)
            val precio: TextView = itemView.findViewById(R.id.precio)
            val boton: Button = itemView.findViewById(R.id.boton)
            val pagar: Button = itemView.findViewById(R.id.pagar)
            var totalprecio = 0.00

            nombre.text = datos.Nombre
            categoria.text = datos.Categoria
            fecha.text = datos.Caducidad
            precio.text = datos.Precio.toString()

            Glide.with(itemView.context).load(datos.Foto).into(imagen)

            boton.setOnClickListener(View.OnClickListener {
                totalprecio += datos.Precio
                Toast.makeText(itemView.context,"Ha añadido ${datos.Nombre} a la cesta. Importe total = $totalprecio",
                    Toast.LENGTH_LONG).show()
            })

            pagar.setOnClickListener(View.OnClickListener {
                if (totalprecio == 0.00) {
                    Toast.makeText(itemView.context,"No ha añadido ningún artículo a la lista",
                        Toast.LENGTH_LONG).show()
                }

                else {
                    val builder = AlertDialog.Builder(itemView.context)
                    builder.setTitle(("<font color='#3F6FEA'>Pagar App</font>"))
                    builder.setMessage(("<font color='#FF0000'>El importe total es de $totalprecio.</font>"))
                    builder.setNeutralButton("PAGAR") { dialog, which ->
                        Toast.makeText(itemView.context,"¡Gracias por su compra!", Toast.LENGTH_SHORT).show()
                    }
                    builder.show()
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.contenido_item,parent,false)
        return MiViewHolder(v)
    }

    override fun getItemCount(): Int {
        return lista.size
    }
    ////////////MiViewHolder.ViewHolder
    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        holder.enlazaItems(lista[position])
    }

}