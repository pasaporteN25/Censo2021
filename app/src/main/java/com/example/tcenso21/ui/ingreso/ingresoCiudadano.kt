package com.example.tcenso21.ui.ingreso

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.tcenso21.R
import com.google.android.material.switchmaterial.SwitchMaterial


class ingresoCiudadano : Fragment() {

    companion object {
        fun newInstance() = ingresoCiudadano()
    }

    private lateinit var viewModel: IngresoCiudadanoViewModel

    lateinit var dni:EditText
    lateinit var nomyap:EditText
    lateinit var nacim:EditText
    lateinit var sexorg:RadioGroup
    lateinit var dir:EditText
    lateinit var tel:EditText
    lateinit var ocup_sw:Switch
    lateinit var ocup_sp:Spinner
    lateinit var ingreso:EditText
    lateinit var guardar:Button
    lateinit var limpiar:Button
    val ocupacion = arrayOf("Desempleado", "Medicina","Profesiones Liberales","Docencia","Ingenieria","Agroindustria","Estudiante","Gobierno","Otras")
    val desocupado = arrayOf("Desempleado")
    var ocup_sel:String = ""

    private var ctx: Context? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater!!.inflate(R.layout.ingreso_ciudadano_fragment, container, false)
        ctx = container?.context
        inicializar(view)
        ocup_sp.setEnabled(false)
        ocup_sp.adapter = ctx?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item,ocupacion) }


        ocup_sp.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                ocup_sel=ocupacion[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?){
                ocup_sel = "Desempleado"
            }
        }

        ocup_sw.setOnCheckedChangeListener(object: CompoundButton.OnCheckedChangeListener{

            override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean): Unit{
                if(isChecked){
                    ocup_sp.adapter = ctx?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item,ocupacion) }
                    ocup_sp.setEnabled(true)
                    ocup_sp.setSelection(0,false)

                }else{
                    ocup_sp.setEnabled(false)
                    ocup_sp.adapter = ctx?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item,desocupado) }
                }
            }
        })

        guardar.setOnClickListener(
            View.OnClickListener {

                val sexo:String = getsrb(view)

                if(viewModel.guardarCiudadano(dni.text.toString().toInt(),nomyap.text.toString(),nacim.text.toString().toInt(),sexo,dir.text.toString(),tel.text.toString().toInt(),ocup_sel,ingreso.text.toString().toInt(),it.context))
                    Toast.makeText(ctx,"Guardado",Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(ctx,"Error",Toast.LENGTH_SHORT).show()
            }
        )

        limpiarCampos()

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IngresoCiudadanoViewModel::class.java)

    }

    private fun inicializar(view:View){
        dni = view.findViewById(R.id.dni_in)
        nomyap = view.findViewById(R.id.nomyap_in)
        nacim = view.findViewById(R.id.nacim_in)
        sexorg = view.findViewById(R.id.sexrg)
        dir = view.findViewById(R.id.dir_in)
        tel = view.findViewById(R.id.tel_in)
        ocup_sw = view.findViewById(R.id.ocupa_sw)
        ocup_sp = view.findViewById(R.id.ocup_sp)
        ingreso = view.findViewById(R.id.ingreso_in)
        guardar = view.findViewById(R.id.guardar_b)
        limpiar = view.findViewById(R.id.limpiar_b)
    }

    private fun getsrb(view:View):String{
        val sel: Int = sexorg!!.checkedRadioButtonId
        var sexo:RadioButton = view.findViewById(sel)
        return sexo!!.text.toString()
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
                ingreso.setText("")
            }
        )
    }


}