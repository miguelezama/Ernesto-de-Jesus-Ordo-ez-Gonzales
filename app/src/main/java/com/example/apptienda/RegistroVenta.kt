package com.example.apptienda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_registro_venta.*

class RegistroVenta : AppCompatActivity() {
    internal lateinit var spclie : Spinner
    internal lateinit var spprod : Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_venta)

        val db = FirebaseFirestore.getInstance()

        spclie = findViewById(R.id.spCliente)
        val clie = arrayOf("Angel Olivas","Miguel Soto","Miguel Barcenas","Ernesto Ordoñez")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, clie)
        spclie.adapter = adapter

        spprod = findViewById(R.id.spProducto)
        val prod = arrayOf("Short","Zapatos","Camisas","Bolsos","Faldas","Camisolas","Jeans")
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_list_item_1, prod)
        spprod.adapter = adapter2

        btGuardarVenta.setOnClickListener {
            val pro1 = HashMap<String,Any>()
            pro1.put("vcliente", spCliente.selectedItem.toString())
            pro1.put("vproducto", spProducto.selectedItem.toString())
            pro1.put("vmonto", MontoVenta.text.toString())

            db.collection("ventas")
                .add(pro1).
                    addOnSuccessListener(OnSuccessListener {
                        Toast.makeText(this,"Venta Insertado", Toast.LENGTH_SHORT).show()
                    })
                .addOnFailureListener(OnFailureListener {
                    Toast.makeText(this,"Error al insertar", Toast.LENGTH_SHORT).show()
                })

            MontoVenta.setText("")
        }

        btCancelarVenta.setOnClickListener {
            finish()
        }
    }
}
