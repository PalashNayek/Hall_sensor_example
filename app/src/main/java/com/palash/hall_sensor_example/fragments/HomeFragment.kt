package com.palash.hall_sensor_example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.palash.hall_sensor_example.R
import com.palash.hall_sensor_example.databinding.FragmentHomeBinding
import com.palash.hall_sensor_example.view_models.HallSensorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding?=null
    private val binding get() = _binding!!
    private val hallSensorViewModel by viewModels<HallSensorViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hallSensorViewModel.hallSensorData.observe(viewLifecycleOwner, Observer {
            binding.txt.text = "Magnetic Field Strength: $it"
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}