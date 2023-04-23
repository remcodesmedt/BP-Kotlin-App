package com.example.project_kotlin.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project_kotlin.database.testdata.IngredientMock
import com.example.project_kotlin.databinding.FragmentIngredientenBinding

class Ingredienten : Fragment() {

    private lateinit var binding: FragmentIngredientenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIngredientenBinding.inflate(inflater, container, false)

        displayData()

        return binding.root
    }

    private fun displayData() {
        try {
            var output = IngredientMock.getLogsMocks()
            if (output.trim().isEmpty()) output = "Nog geen data!"
            binding.txtOutputIngredienten.text = output
        } catch (e: Exception) {
            binding.txtOutputIngredienten.text = "Databank nog niet aangemaakt!"
        }
    }

}