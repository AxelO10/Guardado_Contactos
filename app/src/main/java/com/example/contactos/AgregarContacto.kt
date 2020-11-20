package com.example.contactos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_agregar_contacto.*
import kotlinx.android.synthetic.main.activity_main.*

class AgregarContacto : AppCompatActivity() {

    val contactoAdmin= AdminContacto()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_contacto)
        guardarContacto_Click()
    }


    fun guardarContacto_Click(){
        btnGuardar.setOnClickListener(){
            val contacto = Contacto(0,txtNombre.text.toString(), txtNumero.text.toString())
            contactoAdmin.addContacto(contacto)
            finish()
        }
    }
}