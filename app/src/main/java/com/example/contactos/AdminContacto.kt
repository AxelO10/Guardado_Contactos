package com.example.contactos

import android.database.DatabaseUtils
import android.widget.Toast
import java.lang.Exception
import java.util.ArrayList

data class Contacto(var id:Int, var nombre:String, var numero:String)//mapeo de datos
class AdminContacto {

    //QUERY Regresa nombre de contactos
    fun getAllNames(): ArrayList<String>? {
        try{
            val nombres = arrayListOf<String>() //Respuesta a la funcion
            val db = AppContactos.DB.readableDatabase
            //Revisar datos guardados
            val numDatos = DatabaseUtils.queryNumEntries(db,AppContactos.TB_CONTACTOS).toInt()
            if (numDatos > 0){
                val qry = "SELECT ${Contract.Contacto.NOMBRE} , ${Contract.Contacto.NUMERO} FROM ${AppContactos.TB_CONTACTOS}"
                val c = db.rawQuery(qry,null)
                //Inicio de la tabla
                c.moveToFirst()
                do{
                    //LLenar el arreglo
                    nombres.add(c.getString(c.getColumnIndex(Contract.Contacto.NOMBRE)))
                }while (c.moveToNext())

            }else{
                Toast.makeText(AppContactos.CONTEXT, "NO HAY CONTACTOS GUARDADOS", Toast.LENGTH_SHORT).show()
            }

            db.close()
            return nombres
        }catch (ex:Exception){
            Toast.makeText(AppContactos.CONTEXT, "Lista NO Recuperada", Toast.LENGTH_SHORT).show()
            return null
        }
    }
    fun getAllNumbers(): ArrayList<String>? {
        try{
            val numeros = arrayListOf<String>() //Respuesta a la funcion
            val db = AppContactos.DB.readableDatabase
            //Revisar datos guardados
            val numDatos = DatabaseUtils.queryNumEntries(db,AppContactos.TB_CONTACTOS).toInt()
            if (numDatos > 0){
                val qry = "SELECT  ${Contract.Contacto.NUMERO} FROM ${AppContactos.TB_CONTACTOS}"
                val c = db.rawQuery(qry,null)
                //Inicio de la tabla
                c.moveToFirst()
                do{
                    //LLenar el arreglo
                    numeros.add(c.getString(c.getColumnIndex(Contract.Contacto.NUMERO)))
                }while (c.moveToNext())

            }else{
                Toast.makeText(AppContactos.CONTEXT, "NO HAY CONTACTOS GUARDADOS", Toast.LENGTH_SHORT).show()
            }

            db.close()
            return numeros
        }catch (ex:Exception){
            Toast.makeText(AppContactos.CONTEXT, "Lista NO Recuperada", Toast.LENGTH_SHORT).show()
            return null
        }
    }

    //QUERY INSERTAR contactos
    fun addContacto(contacto: Contacto){
        try{
            val db = AppContactos.DB.writableDatabase
            var qry = "INSERT INTO ${AppContactos.TB_CONTACTOS} (" +
                    "${Contract.Contacto.NOMBRE}, ${Contract.Contacto.NUMERO}) " +
                    "VALUES('${contacto.nombre}','${contacto.numero}');"
            db.execSQL(qry)
            db.close()
        }catch (ex:Exception){
            Toast.makeText(AppContactos.CONTEXT, "Contacto NO Guardado", Toast.LENGTH_SHORT).show()
        }
    }

    //Eliminar un contacto
    fun deleteContacto(nombre:String){
        try{
            val db = AppContactos.DB.writableDatabase
            var qry = "DELETE FROM ${AppContactos.TB_CONTACTOS} WHERE ${Contract.Contacto.NOMBRE} = '$nombre';"
            db.execSQL(qry)
            db.close()
        }catch(ex:Exception){
            Toast.makeText(AppContactos.CONTEXT, "Contacto NO Eliminado", Toast.LENGTH_SHORT).show()
        }
    }
}