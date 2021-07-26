package com.example.tcenso21.model

import java.io.Serializable

data class Ciudadano (
    val dni: Int,
    val nomyap: String,
    val nacim: Int,
    val sexo: String,
    val dir: String,
    val tel: Int,
    val ocupac: String,
    val ingreso: Int
):Serializable