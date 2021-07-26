package com.example.tcenso21.recyclerview

import android.view.View
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.tcenso21.Communicator
import com.example.tcenso21.EditarActivity
import com.example.tcenso21.R
import com.example.tcenso21.model.Ciudadano
import com.example.tcenso21.ui.ingreso.IngresoCiudadanoViewModel
import org.w3c.dom.Text
import java.lang.Exception

class CiudadanoAdapter(val lista:ArrayList<Ciudadano>) :
        RecyclerView.Adapter<CiudadanoAdapter.ViewHolder>(){

    private lateinit var IngresoVM: IngresoCiudadanoViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ciudadano_layout, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dni.text = lista[position].dni.toString()
        holder.nomyap.text = lista[position].nomyap
        holder.nacim.text = lista[position].nacim.toString()
        holder.sexo.text = lista[position].sexo
        holder.dir.text = lista[position].dir
        holder.tel.text = lista[position].tel.toString()
        holder.ocupac.text = lista[position].ocupac
        holder.ingreso.text = lista[position].ingreso.toString()



        holder.editar.setOnClickListener(
            View.OnClickListener {
                try{
                    val intent: Intent = Intent(it.context,EditarActivity::class.java)
                    intent.putExtra("dni",lista[position].dni.toString())
                    intent.putExtra("nomyap",lista[position].nomyap)
                    intent.putExtra("nacim",lista[position].nacim.toString())
                    //intent.putExtra("sexo",lista[position].sexo)
                    intent.putExtra("dir",lista[position].dir)
                    intent.putExtra("tel",lista[position].tel.toString())
                   intent.putExtra("ocupac",lista[position].ocupac)
                    intent.putExtra("ingreso",lista[position].ingreso.toString())

                    it.context.startActivity(intent)
                } catch (e: Exception){
                    Log.e("se rompioooao!?", e.message.toString())
                }



            }
        )

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val dni: TextView
        var nomyap: TextView
        var nacim: TextView
        var sexo:TextView
        var dir:TextView
        var tel:TextView
        var ocupac:TextView
        var ingreso:TextView
        var editar:Button


        init {
            dni =  view.findViewById(R.id.dni_r)
            nomyap = view.findViewById(R.id.nom_r)
            nacim = view.findViewById(R.id.nacim_r)
            sexo = view.findViewById(R.id.sexo_r)
            dir = view.findViewById(R.id.dir_r)
            tel = view.findViewById(R.id.tel_r)
            ocupac = view.findViewById(R.id.ocupac_r)
            ingreso = view.findViewById(R.id.ingreso_r)
            editar = view.findViewById(R.id.editar_r)
        }
    }

}