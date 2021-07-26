package com.example.tcenso21.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tcenso21.Communicator
import com.example.tcenso21.R
import com.example.tcenso21.databinding.FragmentDashboardBinding
import com.example.tcenso21.recyclerview.CiudadanoAdapter

class DashboardFragment : Fragment() {

    private lateinit var buscarViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    //private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        buscarViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ciudrv:RecyclerView = view.findViewById(R.id.ciud_rv)
        ciudrv.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        ciudrv.adapter = CiudadanoAdapter(buscarViewModel.getCiudadano(requireActivity()))

        ciudrv.adapter?.notifyDataSetChanged()
        ciudrv.adapter = CiudadanoAdapter(buscarViewModel.getCiudadano(requireActivity()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}