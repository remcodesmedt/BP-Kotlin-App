package com.example.project_kotlin.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project_kotlin.database.testdata.DishMock
import com.example.project_kotlin.databinding.FragmentGerechtenBinding

class Gerechten : Fragment() {

    private lateinit var binding: FragmentGerechtenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGerechtenBinding.inflate(inflater, container, false)

        displayData()

        return binding.root
    }

    private fun displayData() {
        try {
            var output = DishMock(requireContext()).getLogsMocks()
            if (output.trim().isEmpty()) output = "Nog geen data!"
            binding.txtOutputGerechten.text = output
        } catch (e: Exception) {
            binding.txtOutputGerechten.text = "Databank nog niet aangemaakt!"
        }
    }

}