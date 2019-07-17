package com.example.apptienda

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.itemrecycleproductos.view.*


class adapterproducto(val listaproducto:MutableList<DocumentSnapshot>):RecyclerView.Adapter<adapterproducto.Viewholder>()
{
  //  private var db: room? = null


    val db = FirebaseFirestore.getInstance()


    class Viewholder(itemview: View):RecyclerView.ViewHolder(itemview)
    {
        val tvnombre:TextView=itemview.tvnombre
        val tvprecio:TextView=itemview.tvtelefono
        val tvcantidad:TextView=itemview.tvapellido
        val tvtotal:TextView=itemview.tvdireccion
        var delete:ImageView=itemview.imagedelete
        var edit:ImageView=itemview.imageedit
       // private var db: room? = null

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): adapterproducto.Viewholder
    {
        val v=LayoutInflater.from(p0.context).inflate(R.layout.itemrecycleproductos,p0,false)
       // Toast.makeText(v.context,"Dentro del adapter=="+listaproducto.size.toString(),Toast.LENGTH_SHORT).show()

        return Viewholder(v)
    }
    override fun getItemCount(): Int {
       return listaproducto.size
    }
    override fun onBindViewHolder(p0: adapterproducto.Viewholder, p1: Int) {

        p0.tvnombre.setText(listaproducto[p1].get("producto").toString())
        p0.tvcantidad.setText(listaproducto[p1].get("cantidad").toString())
        p0.tvprecio.setText(listaproducto[p1].get("precio").toString())
        p0.tvtotal.setText(listaproducto[p1].get("total").toString())


        p0.edit.setOnClickListener {
            var intent = Intent(p0.itemView.context,EditarProducto::class.java)
            datospublicos.idproducto=listaproducto[p1].id
            datospublicos.producto=listaproducto[p1]
            p0.itemView.context.startActivity(intent)


        }


        p0.delete.setOnClickListener {

            db.collection("productos").document(listaproducto[p1].id).delete()
                .addOnSuccessListener(OnSuccessListener {
                       Toast.makeText(p0.itemView.context,"Producto Eliminado Correctamente",Toast.LENGTH_SHORT).show()
                    })
                .addOnFailureListener(OnFailureListener {
                    Toast.makeText(p0.itemView.context,"Error al Eliminar",Toast.LENGTH_SHORT).show()
                })



        }

        }



     }
