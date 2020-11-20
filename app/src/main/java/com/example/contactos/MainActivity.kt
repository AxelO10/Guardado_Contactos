package com.example.contactos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val contactoAdmin = AdminContacto()
    lateinit var nombres: ArrayList<String>
    lateinit var numeros: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        eliminar()

    }

    override fun onResume(){
        super.onResume()
        crearLista()
    }


    fun crearLista() {
        //val nombres = contactoAdmin.getAllNames()
        //val adaptador = ArrayAdapter(AppContactos.CONTEXT, android.R.layout.simple_expandable_list_item_1, nombres)
        //val adaptador = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1,nombres)
        //listContactos.adapter = adaptador

        numeros = contactoAdmin.getAllNumbers()!!
        var adaptador1 = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,numeros!!)
        listContactos.adapter = adaptador1

        nombres = contactoAdmin.getAllNames()!!
        var adaptador = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,nombres!!)
        listContactos.adapter = adaptador



        /*//Seleccionar
        listContactos.onItemClickListener = AdapterView.OnItemClickListener{adapterView, view, i, l ->
            val item = nombres!!.get(i)
            Toast.makeText(AppContactos.CONTEXT, item, Toast.LENGTH_SHORT).show()
        }*/


       listContactos.onItemClickListener = AdapterView.OnItemClickListener{adapterView, view, i, l ->
            val item = numeros!!.get(i)
            Toast.makeText(AppContactos.CONTEXT, item, Toast.LENGTH_SHORT).show()
        }

    }


    fun eliminar(){
        listContactos.onItemLongClickListener = AdapterView.OnItemLongClickListener { adapterView, view, i, l ->

            val nombre = nombres.get(i)

            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Eliminar")
            dialog.setMessage("¿Desea eliminar este contacto?")
            dialog.setPositiveButton("ELIMINAR"){ dialogInterface, i ->
                contactoAdmin.deleteContacto(nombre)
                crearLista()
            }
            dialog.setNegativeButton("NO"){dialogInterface, i ->
                dialogInterface.dismiss()
            }
            dialog.show()
            true
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_add-> {
                val intentAdd = Intent(applicationContext,AgregarContacto::class.java)
                startActivity(intentAdd)
                return true
            }else -> return super.onOptionsItemSelected(item)
        }
    }
}