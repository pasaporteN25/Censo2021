package com.example.tcenso21.ui.ingreso

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.tcenso21.dao.DBGestor
import com.example.tcenso21.model.Ciudadano

class IngresoCiudadanoViewModel : ViewModel() {

    fun guardarCiudadano(
        dni:Int,
        nomyap:String,
        nacim:Int,
        sexo:String,
        dir:String,
        tel:Int,
        ocupac:String,
        ingreso:Int, context:Context):Boolean{

        val db: DBGestor = DBGestor(context,null)
        return db.guardarCiudadano(Ciudadano(dni,nomyap,nacim,sexo,dir,tel,ocupac,ingreso))
    }


}