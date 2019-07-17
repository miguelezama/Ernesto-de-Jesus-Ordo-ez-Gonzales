package com.example.apptienda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_editar_cliente.*

class EditarCliente : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_cliente)

        val db = FirebaseFirestore.getInstance()

        CodigoClie.setText(datospublicos_clie.nom_clie!!.get("cod_clie").toString())
        NombreCliente.setText(datospublicos_clie.nom_clie!!.get("nomb_clie").toString())
        ApellidoCliente.setText(datospublicos_clie.nom_clie!!.get("apel_clie").toString())
        Telefono.setText(datospublicos_clie.nom_clie!!.get("telefono").toString())
        Direccion.setText(datospublicos_clie.nom_clie!!.get("direccion").toString())

        btGuardarClie.setOnClickListener {
            val pro1 = HashMap<String,Any>()
            pro1.put("cod_clie", CodigoClie.text.toString())
            pro1.put("nomb_clie",NombreCliente.text.toString())
            pro1.put("apel_clie",ApellidoCliente.text.toString())
            pro1.put("telefono", Telefono.text.toString().toInt())
            pro1.put("direccion", Direccion.text.toString())

            db.collection("clientes").document(datospublicos_clie.idcliente.toString()).update(pro1)
                . addOnSuccessListener(OnSuccessListener {
                    Toast.makeText(this,"Cliente Insertado", Toast.LENGTH_SHORT).show()
                })
                .addOnFailureListener(OnFailureListener {
                    Toast.makeText(this,"Error al insertar", Toast.LENGTH_SHORT).show()
                })

            finish()
        }

    }
}
