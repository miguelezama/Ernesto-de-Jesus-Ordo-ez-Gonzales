package com.example.apptienda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_mostrar_inventario.*

class MostrarInventario : AppCompatActivity() {

    lateinit var listaproducto: MutableList<DocumentSnapshot>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_inventario)

        val db = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        db.firestoreSettings=settings

        db.collection("productos").
            addSnapshotListener(EventListener<QuerySnapshot>{ snapshots, e ->

                if (e != null) {
                    Log.w("Realtime", "listen:error", e)
                    return@EventListener
                }
                listaproducto= snapshots!!.documents
                //Toast.makeText(this,listaproducto.size.toString(),Toast.LENGTH_SHORT).show()
                cargardatos()

            })

        btnSalir.setOnClickListener {
            finish()
        }
    }

    fun cargardatos()
    {
        recycleproducto.layoutManager= LinearLayoutManager(applicationContext, LinearLayout.VERTICAL,false)
        // if(listaproducto.size>0) {
        var adapter = adapterproducto(listaproducto)
        recycleproducto.adapter = adapter
        //}
    }
    override fun onRestart() {
        super.onRestart()


    }
}
