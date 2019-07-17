package com.example.apptienda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.activity_mostrar_clientes.*
import kotlinx.android.synthetic.main.activity_mostrar_inventario.*
import java.util.*

class MostrarClientes : AppCompatActivity() {

    lateinit var listacliente: MutableList<DocumentSnapshot>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_clientes)

        val db = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        db.firestoreSettings=settings

        db.collection("clientes").
            addSnapshotListener(EventListener<QuerySnapshot>{ snapshots, e ->

                if (e != null) {
                    Log.w("Realtime", "listen:error", e)
                    return@EventListener
                }
                listacliente= snapshots!!.documents
                //Toast.makeText(this,listaproducto.size.toString(),Toast.LENGTH_SHORT).show()
                cargardatos()

            })

        btSalir.setOnClickListener {
            finish()
        }
    }

    fun cargardatos()
    {
        recyclecliente.layoutManager= LinearLayoutManager(applicationContext, LinearLayout.VERTICAL,false)
        // if(listacliente.size>0) {
        var adapter = adaptercliente(listacliente)
        recyclecliente.adapter = adapter
        //}
    }
    override fun onRestart() {
        super.onRestart()


    }
}
