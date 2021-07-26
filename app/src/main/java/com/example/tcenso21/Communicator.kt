package com.example.tcenso21

import com.example.tcenso21.model.Ciudadano
import java.io.DataInput

interface Communicator {

    fun recibirDatos(editIntInput: Int)
}