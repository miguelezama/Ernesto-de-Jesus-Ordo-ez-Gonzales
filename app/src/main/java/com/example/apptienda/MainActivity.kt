package com.example.apptienda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(Toolbar)

        AgregarProd.setOnClickListener {
            var prod = Intent(this, RegistroProducto::class.java)
            startActivity(prod)
        }

        NuevoCliente.setOnClickListener {
            var clien = Intent(this, RegistroCliente::class.java)
            startActivity(clien)
        }

        Inventario.setOnClickListener {
            var inv = Intent(this, MostrarInventario::class.java)
            startActivity(inv)
        }
        AgregarVenta.setOnClickListener {
            var ventas = Intent(this, RegistroVenta::class.java)
            startActivity(ventas)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.items,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == R.id.item_help) {
            var info = Intent(this, Ayuda::class.java)
            startActivity(info)
        }
        if (id == R.id.item_info) {
            var acercade = Intent(this, AcercaDe::class.java)
            startActivity(acercade)
        }
        if (id == R.id.item_clientes) {
            var clien = Intent(this, MostrarClientes::class.java)
            startActivity(clien)
        }
        return super.onOptionsItemSelected(item)
    }

    }
