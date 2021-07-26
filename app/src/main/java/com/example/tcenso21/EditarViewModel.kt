package com.example.tcenso21

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.tcenso21.dao.DBGestor
import com.example.tcenso21.model.Ciudadano

class EditarViewModel : ViewModel(){

    fun editarCiudadano(ciudadano: Ciudadano, context:Context){
        val db: DBGestor = DBGestor(context, null)
        db.modificarCiudadano(ciudadano)
    }

    fun eliminarCiudadano(dni: Int, context: Context){
        val db: DBGestor = DBGestor(context, null)
        db.eliminarCiudadano(dni)
    }

}