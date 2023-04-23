package com.example.project_kotlin.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project_kotlin.database.testdata.MealPlanMock
import com.example.project_kotlin.databinding.FragmentPlanningBinding

class Planning : Fragment() {

    private lateinit var binding: FragmentPlanningBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlanningBinding.inflate(inflater, container, false)

        displayData()

        return binding.root
    }

    private fun displayData() {
        try {
            var output = MealPlanMock.getLogsMocks()
            if (output.trim().isEmpty()) output = "Nog geen data!"
            binding.txtOutputMealplan.text = output
        } catch (e: Exception) {
            binding.txtOutputMealplan.text = "Databank nog niet aangemaakt!"
        }
    }

}