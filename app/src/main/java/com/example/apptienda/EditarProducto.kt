package com.example.apptienda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_editar_producto.*
import kotlinx.android.synthetic.main.activity_registro_producto.Cantidad
import kotlinx.android.synthetic.main.activity_registro_producto.Precio
import kotlinx.android.synthetic.main.activity_registro_producto.Producto
import kotlinx.android.synthetic.main.activity_registro_producto.btGuardar

class EditarProducto : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_producto)

        val db = FirebaseFirestore.getInstance()

        CodigoProd.setText(datospublicos.producto!!.get("cod_prod").toString())
        Producto.setText(datospublicos.producto!!.get("producto").toString())
        Cantidad.setText(datospublicos.producto!!.get("cantidad").toString())
        Precio.setText(datospublicos.producto!!.get("precio").toString())
        Total.setText(datospublicos.producto!!.get("total").toString())

        btGuardar.setOnClickListener {
            val pro1 = HashMap<String,Any>()
            pro1.put("cod_prod", CodigoProd.text.toString())
            pro1.put("producto",Producto.text.toString())
            pro1.put("cantidad",Cantidad.text.toString().toInt())
            pro1.put("precio", Precio.text.toString().toFloat())
            pro1.put("total", Total.text.toString().toFloat())

            db.collection("productos").document(datospublicos.idproducto.toString()).update(pro1)
                . addOnSuccessListener(OnSuccessListener {
                    Toast.makeText(this,"Producto Insertado", Toast.LENGTH_SHORT).show()
                })
                .addOnFailureListener(OnFailureListener {
                    Toast.makeText(this,"Error al insertar", Toast.LENGTH_SHORT).show()
                })

            finish()
        }

    }
}
