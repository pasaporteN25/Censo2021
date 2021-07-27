package com.example.tcenso21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
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
    lateinit var ocup_sw: Switch
    lateinit var ocup_sp: Spinner
    lateinit var ingreso: EditText
    lateinit var limpiar: Button
    lateinit var eliminar: Button
    lateinit var guardar: Button
    val ocupacion = arrayOf("Desempleado", "Medicina","Profesiones Liberales","Docencia","Ingenieria","Agroindustria","Estudiante","Gobierno","Jubilado","Otras")
    //val desocupado = arrayOf("Desempleado")
    var ocupacion_sel:String? = null
    var sexo:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)

        EditVM = ViewModelProvider(this).get(EditarViewModel::class.java)

        inicializar()
        initSp()
        mappeo()

        ocup_sp.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,ocupacion)
        if (ocupacion_sel.equals("Desempleado")){
            ocup_sp.setEnabled(false)
            ocup_sw.setChecked(false)
        }else{
            ocup_sp.setEnabled(true)
            ocup_sw.setChecked(true)
            var x:Int=0
            for (e in ocupacion){
                if(ocupacion_sel.equals(e)){
                    ocup_sp.setSelection(x)

                }else{
                    x++
                }
            }
        }

        limpiarCampos()

        try{
            saveEdicion()
        }catch (e: Exception){
            Toast.makeText(applicationContext,"Error",Toast.LENGTH_SHORT).show()
        }

        eliminarElem()


    }

    private fun inicializar(){
        dni = findViewById(R.id.dni_in)
        nomyap = findViewById(R.id.nomyap_in)
        nacim = findViewById(R.id.nacim_in)
        sexorg = findViewById(R.id.sexrg)
        dir = findViewById(R.id.dir_in)
        tel = findViewById(R.id.tel_in)
        ocup_sw = findViewById(R.id.ocupa_sw)
        ocup_sp = findViewById(R.id.ocup_sp)
        ingreso = findViewById(R.id.ingreso_in)
        limpiar = findViewById(R.id.limpiar_b)
        eliminar = findViewById(R.id.eliminar_b)
        guardar = findViewById(R.id.guardar_b)
    }

    private fun mappeo(){
        dni.setText(intent.getStringExtra("dni"))
        nomyap.setText(intent.getStringExtra("nomyap"))
        nacim.setText(intent.getStringExtra("nacim"))
        sexo = intent.getStringExtra("sexo")
        //colocar el sexo correcto con un if
        dir.setText(intent.getStringExtra("dir"))
        tel.setText(intent.getStringExtra("tel"))
        ocupacion_sel = intent.getStringExtra("ocupac")
        ingreso.setText(intent.getStringExtra("ingreso"))
    }

    private fun saveEdicion(){
        guardar.setOnClickListener(
            View.OnClickListener {
                sexo = "Masculino    "
                //Este sexo esta forzado

                EditVM.editarCiudadano(Ciudadano(dni.text.toString().toInt(),nomyap.text.toString(),nacim.text.toString().toInt()
                    ,sexo.toString(),dir.text.toString(),tel.text.toString().toInt(),ocupacion_sel!!,ingreso.text.toString().toInt()),it.context)

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
                sexorg.clearCheck()
                dir.setText("")
                tel.setText("")
                ocup_sp.setSelection(0)
                ocup_sp.setEnabled(false)
                ocup_sw.setEnabled(false)
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

    private fun initSp(){
        ocup_sp.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                ocupacion_sel=ocupacion[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                ocupacion_sel = "Desempleado"
                //esta no se si es necesaria, revisar
            }

        }

        ocup_sw.setOnCheckedChangeListener(object: CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
               if(isChecked){
                   //ocup_sp.adapter = ArrayAdapter(applicationContext,android.R.layout.simple_spinner_item,ocupacion)
                   ocup_sp.setEnabled(true)
                   ocup_sp.setSelection(0,false)
               }else{
                   ocup_sp.setSelection(0)
                   ocup_sp.setEnabled(false)
                   //ocup_sp.adapter = ArrayAdapter(applicationContext,android.R.layout.simple_spinner_item,desocupado)
                   //Solo faltaria setear el array de arriba o ver la forma de mejorar eso en todo el sistema!
               }
            }


        })

    }

}