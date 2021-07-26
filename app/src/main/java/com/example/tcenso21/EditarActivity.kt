package com.example.tcenso21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.tcenso21.model.Ciudadano

class EditarActivity : AppCompatActivity() {

    private lateinit var EditVM: EditarViewModel

    lateinit var dni: EditText
    lateinit var nomyap: EditText
    lateinit var nacim: EditText
    lateinit var sexorg: RadioGroup
    lateinit var dir: EditText
    lateinit var tel: EditText
    lateinit var ocupac: EditText
    lateinit var ingreso: EditText
    lateinit var limpiar: Button
    lateinit var eliminar: Button
    lateinit var guardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)

        EditVM = ViewModelProvider(this).get(EditarViewModel::class.java)

        inicializar()

        mappeo()
        saveEdicion()
        limpiarCampos()
        //terminaria rompiendo si lo que se pasa esta vacio, revisar!
        eliminarElem()


    }

    private fun inicializar(){
        dni = findViewById(R.id.dni_in)
        nomyap = findViewById(R.id.nomyap_in)
        nacim = findViewById(R.id.nacim_in)
        sexorg = findViewById(R.id.sexrg)
        dir = findViewById(R.id.dir_in)
        tel = findViewById(R.id.tel_in)
        ocupac = findViewById(R.id.ocupac_in)
        ingreso = findViewById(R.id.ingreso_in)
        limpiar = findViewById(R.id.limpiar_b)
        eliminar = findViewById(R.id.eliminar_b)
        guardar = findViewById(R.id.guardar_b)
    }

    private fun mappeo(){
        dni.setText(intent.getStringExtra("dni"))
        nomyap.setText(intent.getStringExtra("nomyap"))
        nacim.setText(intent.getStringExtra("nacim"))
        //sexo = intent.getStringExtra("sexo")
        dir.setText(intent.getStringExtra("dir"))
        tel.setText(intent.getStringExtra("tel"))
        ocupac.setText(intent.getStringExtra("ocupac"))
        ingreso.setText(intent.getStringExtra("ingreso"))
    }

    private fun saveEdicion(){
        guardar.setOnClickListener(
            View.OnClickListener {
                var sexo:String = "Masculino    "
                EditVM.editarCiudadano(Ciudadano(dni.text.toString().toInt(),nomyap.text.toString(),nacim.text.toString().toInt()
                    ,sexo,dir.text.toString(),tel.text.toString().toInt(),ocupac.text.toString(),ingreso.text.toString().toInt()),it.context)

                Toast.makeText(it.context,"Guardado",Toast.LENGTH_SHORT).show()


            }
        )
    }

    private fun limpiarCampos(){
        limpiar.setOnClickListener(
            View.OnClickListener {
                dni.setText("")
                nomyap.setText("")
                nacim.setText("")
                //sexo = intent.getStringExtra("sexo")
                dir.setText("")
                tel.setText("")
                ocupac.setText("")
                ingreso.setText("")
            }
        )
    }

    private fun eliminarElem(){
        eliminar.setOnClickListener(
            View.OnClickListener {
                EditVM.eliminarCiudadano(dni.text.toString().toInt(), it.context)
                limpiarCampos()
                Toast.makeText(this,"Datos eliminados",Toast.LENGTH_LONG).show()
            }
        )
    }

}