package com.example.apptienda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_registro_cliente.*

class RegistroCliente : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_cliente)

        val db = FirebaseFirestore.getInstance() //nuevo

        btGuardarClie.setOnClickListener {
            val cli1 = HashMap<String,Any>()  //nuevo
            if (CodigoClie.text.length == 0){
                CodigoClie.setError("Campo Obligatorio")
                return@setOnClickListener
            }
            if (NombreCliente.text.length == 0){
                NombreCliente.setError("Campo Obligatorio")
                return@setOnClickListener
            }
            if (ApellidoCliente.text.length == 0){
                ApellidoCliente.setError("Campo Obligatorio")
                return@setOnClickListener
            }
            if (Telefono.text.length == 0){
                Telefono.setError("Campo Obligatorio")
                return@setOnClickListener
            }
            if (Direccion.text.length == 0){
                Direccion.setError("Campo Obligatorio")
                return@setOnClickListener
            }

            //Toast.makeText(this, "Registro ingresado con éxito", Toast.LENGTH_SHORT).show()

            cli1.put("cod_clie", CodigoClie.text.toString())
            cli1.put("nomb_clie", NombreCliente.text.toString())
            cli1.put("apel_clie",ApellidoCliente.text.toString())
            cli1.put("telefono",Telefono.text.toString().toInt())
            cli1.put("direccion", Direccion.text.toString())

            db.collection("clientes")
                .add(cli1).
                    addOnSuccessListener(OnSuccessListener {
                        Toast.makeText(this,"Cliente Insertado", Toast.LENGTH_SHORT).show()
                    })
                .addOnFailureListener(OnFailureListener {
                    Toast.makeText(this,"Error al insertar", Toast.LENGTH_SHORT).show()
                })

            CodigoClie.setText("")
            NombreCliente.setText("")
            ApellidoCliente.setText("")
            Telefono.setText("")
            Direccion.setText("")

        }

        btCancelarClie.setOnClickListener {
            finish()
        }

    }
}
