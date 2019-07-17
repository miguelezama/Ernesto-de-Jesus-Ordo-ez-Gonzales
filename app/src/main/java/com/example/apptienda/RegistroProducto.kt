package com.example.apptienda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_registro_producto.*

class RegistroProducto : AppCompatActivity(), TextWatcher {
    var cantidad = 0
    var precio = 0
    var total = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_producto)

        val db = FirebaseFirestore.getInstance() //nuevo

        var EditPrecio : EditText
        EditPrecio = findViewById(R.id.Precio) as EditText
        EditPrecio?.addTextChangedListener(this)

        btGuardar.setOnClickListener {
            val pro1 = HashMap<String,Any>()  //nuevo
            if (EditCodProd.text.length == 0) {
                EditCodProd.setError("Campo Obligatorio")
                return@setOnClickListener
            }
            if (Producto.text.length == 0) {
                Producto.setError("Campo Obligatorio")
                return@setOnClickListener
            }
            if (Cantidad.text.length == 0) {
                Cantidad.setError("Campo Obligatorio")
                return@setOnClickListener
            }
            if (Precio.text.length == 0) {
                Precio.setError("Campo Obligatorio")
                return@setOnClickListener
            }

            //Toast.makeText(this, "Se Guardó el registro correctamete", Toast.LENGTH_SHORT).show()
            //nuevo
            pro1.put("cod_prod", EditCodProd.text.toString())
            pro1.put("producto", Producto.text.toString())
            pro1.put("cantidad",Cantidad.text.toString().toInt())
            pro1.put("precio",Precio.text.toString().toFloat())
            pro1.put("total", txtTotal.text.toString().toFloat())

            db.collection("productos")
                .add(pro1).
                    addOnSuccessListener(OnSuccessListener {
                        Toast.makeText(this,"Producto Insertado", Toast.LENGTH_SHORT).show()
                    })
                .addOnFailureListener(OnFailureListener {
                    Toast.makeText(this,"Error al insertar", Toast.LENGTH_SHORT).show()
                })


            EditCodProd.setText("")
            Producto.setText("")
            Cantidad.setText("")
            Precio.setText("")
            txtTotal.setText("00.00")
        }

        btCancelar.setOnClickListener {
            finish()
        }
    }

    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (Precio.text.length != 0){
            /*cantidad = Cantidad.text.toString().toInt()
            precio = Precio.text.toString().toInt()
            total = precio * cantidad
            txtTotal.setText(total.toString())*/
            calcular()
        }else {
            txtTotal.setText("00.00")
        }

    }

    fun calcular(){
        cantidad = Cantidad.text.toString().toInt()
        precio = Precio.text.toString().toInt()
        total = precio * cantidad
        txtTotal.setText(total.toString())
    }

}
