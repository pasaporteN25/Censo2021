package com.example.tcenso21.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tcenso21.R
import com.example.tcenso21.databinding.FragmentNotificationsBinding
import com.example.tcenso21.model.Ciudadano

class NotificationsFragment : Fragment() {

    private lateinit var reportVM: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        reportVM =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.datoIngresos



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ciud:ArrayList<Ciudadano> = reportVM.getDatos(requireActivity())
        var totpobr:Int = 0
        var hombres: Int = 0
        var mujeres: Int = 0
        var juvdesoc:Int = 0
        for (ciudadan in ciud){
            if (ciudadan.ingreso.toInt()<5000) {
                totpobr+=1
            }

            if(ciudadan.sexo.equals("Masculino    ")){
                hombres+=1
            }else if(ciudadan.sexo.equals("Femenino    ")){
                mujeres+=1
            }

            //Incorporar datepicker hasta entonces tomo la fecha por la edad
            //No llegue a incluir el datepicker en el proyecto
            if(ciudadan.nacim < 18 && ciudadan.ocupac.equals("desocupado")){
                juvdesoc+=1
            }


            //mostrar.setText(total.toString())
        binding.datoIngresos.setText("Censados bajo la linea de pobreza $totpobr")
        binding.datoDesemp.setText("Desempleados jovenes: $juvdesoc")
        binding.datoSexos.setText("total hombres $hombres / total mujeres $mujeres")


        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}