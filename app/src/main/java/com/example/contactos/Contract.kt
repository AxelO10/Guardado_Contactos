package com.example.contactos

import android.provider.BaseColumns

class Contract {
    //Campos de la tabla
    class Contacto:BaseColumns{
        companion object{
            val ID = "id"
            val NOMBRE = "nombre"
            val NUMERO = "numero"
        }
    }
}