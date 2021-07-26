package com.example.tcenso21.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.tcenso21.model.Ciudadano
import java.lang.Exception

class DBGestor(context:Context, factory:SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context,DB_NOMBRE, factory, DB_VERS) {

    companion object{
        private val DB_NOMBRE = "censoar.db"
        private val DB_VERS = 1
        val TABLA_NOMB = "ciudadanos"

        val COLUMN_DNI = "documento"
        val COLUMN_NOMYAP = "nombre_apellido"
        val COLUMN_NACIM = "fecha_nacimiento"
        val COLUMN_SEXO = "sexo"
        val COLUMN_DIR = "direccion"
        val COLUMN_TEL = "telefono"
        val COLUMN_OCUPAC = "ocupacion"
        val COLUMN_INGRESO = "ingreso_mensual"

    }

    override fun onCreate(db: SQLiteDatabase?){
        val crearTabla =
            ("CREATE TABLE "+ TABLA_NOMB + " ( "+ COLUMN_DNI + " INTEGER PRIMARY KEY, " +
                    COLUMN_NOMYAP+ " TEXT NOT NULL, " +
                    COLUMN_NACIM +  " INTEGER NOT NULL, " +
                    COLUMN_SEXO + " TEXT NOT NULL, " +
                    COLUMN_DIR + " TEXT, " +
                    COLUMN_TEL + " INTEGER, " +
                    COLUMN_OCUPAC + " TEXT, " +
                    COLUMN_INGRESO + " INTEGER NOT NULL ) ")

        db?.execSQL(crearTabla)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVers: Int, newVers: Int) {
        db?.execSQL("DROP TABLE IF EXIST "+ TABLA_NOMB)
        onCreate(db)
    }

    fun guardarCiudadano(ciudadano : Ciudadano):Boolean{
        try{
            val db = this.writableDatabase
            val values = ContentValues()

            values.put(COLUMN_DNI ,ciudadano.dni)
            values.put(COLUMN_NOMYAP ,ciudadano.nomyap)
            values.put(COLUMN_NACIM ,ciudadano.nacim)
            values.put(COLUMN_SEXO ,ciudadano.sexo)
            values.put(COLUMN_DIR ,ciudadano.dir)
            values.put(COLUMN_TEL ,ciudadano.tel)
            values.put(COLUMN_OCUPAC ,ciudadano.ocupac)
            values.put(COLUMN_INGRESO ,ciudadano.ingreso)

            db.insert(TABLA_NOMB, null,values)
            return true
        } catch (e: Exception){
            Log.e("ERROR AL INSERTAR: ", e.message.toString())
            return false
        }
    }

    fun getCiudadano(): ArrayList<Ciudadano> {
        val db = this.readableDatabase
        val Ciudadanos: ArrayList<Ciudadano> = ArrayList<Ciudadano>()
        val query = "SELECT * FROM $TABLA_NOMB ORDER BY $COLUMN_NOMYAP ASC"
        val cursor = db.rawQuery(query,null)

        if (cursor.moveToFirst()){
            do {
                val dni = cursor.getInt(cursor.getColumnIndex(COLUMN_DNI))
                val nomyap = cursor.getString(cursor.getColumnIndex(COLUMN_NOMYAP))
                val nacim = cursor.getInt(cursor.getColumnIndex(COLUMN_NACIM))
                val sexo = cursor.getString(cursor.getColumnIndex(COLUMN_SEXO))
                val dir = cursor.getString(cursor.getColumnIndex(COLUMN_DIR))
                val tel = cursor.getInt(cursor.getColumnIndex(COLUMN_TEL))
                val ocupac = cursor.getString(cursor.getColumnIndex(COLUMN_OCUPAC))
                val ingreso = cursor.getInt(cursor.getColumnIndex(COLUMN_INGRESO))


                Ciudadanos.add(Ciudadano(dni, nomyap, nacim, sexo, dir, tel, ocupac, ingreso))
            } while(cursor.moveToNext())
        }
        //cerrar el cursor!
        return Ciudadanos
    }

    fun modificarCiudadano(ciudadano: Ciudadano): Boolean {
        var bol: Boolean = false
        try{
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(COLUMN_DNI ,ciudadano.dni)
            values.put(COLUMN_NOMYAP ,ciudadano.nomyap)
            values.put(COLUMN_NACIM ,ciudadano.nacim)
            values.put(COLUMN_SEXO ,ciudadano.sexo)
            values.put(COLUMN_DIR ,ciudadano.dir)
            values.put(COLUMN_TEL ,ciudadano.tel)
            values.put(COLUMN_OCUPAC ,ciudadano.ocupac)
            values.put(COLUMN_INGRESO ,ciudadano.ingreso)

            val where = "$COLUMN_DNI=?"
            val whereargs = arrayOf(ciudadano.dni.toString())
            db.update(TABLA_NOMB, values,where,whereargs)
            bol = true
        } catch (e: Exception){
            Log.e("Error al modificar", e.message.toString())
        }
        return bol
    }

    fun eliminarCiudadano(dni: Int): Boolean{
        var bol:Boolean = false
        try{
            val db = this.writableDatabase
            val where = "$COLUMN_DNI=?"
            val whereargs = arrayOf(dni.toString())
            db.delete(TABLA_NOMB,where, whereargs)
            return bol
        }catch (e: Exception){
            Log.e("Error al borrar", e.message.toString())
        }
        return bol
    }



}