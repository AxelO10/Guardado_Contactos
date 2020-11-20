package com.example.contactos

import android.app.Application
import android.content.Context

class AppContactos: Application() {

    companion object{
        lateinit var CONTEXT : Context
        lateinit var DB : InitDb//Acceso a la DB
        val DB_NAME ="ContactosDB"
        val VERSION = 1
        val TB_CONTACTOS = "tbContactos"
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        DB = InitDb()
    }
}