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


class adaptercliente(val listacliente:MutableList<DocumentSnapshot>):RecyclerView.Adapter<adaptercliente.Viewholder>()
{
    val db = FirebaseFirestore.getInstance()

    class Viewholder(itemview: View):RecyclerView.ViewHolder(itemview)
    {
        val tvnombre:TextView=itemview.tvnombre
        val tvapellido:TextView=itemview.tvapellido
        val tvtelefono:TextView=itemview.tvtelefono
        val tvdireccion:TextView=itemview.tvdireccion
        var delete:ImageView=itemview.imagedelete
        var edit:ImageView=itemview.imageedit
       // private var db: room? = null

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): adaptercliente.Viewholder
    {
        val v=LayoutInflater.from(p0.context).inflate(R.layout.itemrecycleclientes,p0,false)
       // Toast.makeText(v.context,"Dentro del adapter=="+listaproducto.size.toString(),Toast.LENGTH_SHORT).show()

        return Viewholder(v)
    }
    override fun getItemCount(): Int {
       return listacliente.size
    }
    override fun onBindViewHolder(p0: adaptercliente.Viewholder, p1: Int) {

        p0.tvnombre.setText(listacliente[p1].get("nomb_clie").toString())
        p0.tvapellido.setText(listacliente[p1].get("apel_clie").toString())
        p0.tvtelefono.setText(listacliente[p1].get("telefono").toString())
        p0.tvdireccion.setText(listacliente[p1].get("direccion").toString())

        p0.edit.setOnClickListener {
            var intent = Intent(p0.itemView.context,EditarCliente::class.java)
            datospublicos_clie.idcliente=listacliente[p1].id
            datospublicos_clie.nom_clie=listacliente[p1]
            p0.itemView.context.startActivity(intent)


        }


        p0.delete.setOnClickListener {

            db.collection("clientes").document(listacliente[p1].id).delete()
                .addOnSuccessListener(OnSuccessListener {
                       Toast.makeText(p0.itemView.context,"Producto Eliminado Correctamente",Toast.LENGTH_SHORT).show()
                    })
                .addOnFailureListener(OnFailureListener {
                    Toast.makeText(p0.itemView.context,"Error al Eliminar",Toast.LENGTH_SHORT).show()
                })



        }

    }
}
