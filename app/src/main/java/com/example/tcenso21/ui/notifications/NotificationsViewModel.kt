package com.example.tcenso21.ui.notifications

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tcenso21.dao.DBGestor
import com.example.tcenso21.model.Ciudadano

class NotificationsViewModel : ViewModel() {

    fun getDatos(context: Context):ArrayList<Ciudadano>{
        val db: DBGestor = DBGestor(context,null)
        return db.getCiudadano()
    }
}