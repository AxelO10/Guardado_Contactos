package com.example.contactos

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//Heredar clase SQLIte
class InitDb:SQLiteOpenHelper(AppContactos.CONTEXT, AppContactos.DB_NAME,null,AppContactos.VERSION) {

    //Query - Crear tabla
    val qryCreaTabka = "CREATE TABLE ${AppContactos.TB_CONTACTOS}(" +
            "${Contract.Contacto.ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
            "${Contract.Contacto.NOMBRE} TEXT NOT NULL," +
            "${Contract.Contacto.NUMERO} TEXT NOT NULL); "

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(qryCreaTabka)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}