package com.example.project_kotlin.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project_kotlin.database.testdata.ShoppingListMock
import com.example.project_kotlin.databinding.FragmentShoppingListBinding

class ShoppingList : Fragment() {

    private lateinit var binding: FragmentShoppingListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingListBinding.inflate(inflater, container, false)

        displayData()

        return binding.root
    }

    private fun displayData() {
        try {
            var output = ShoppingListMock.getLogsMocks()
            if (output.trim().isEmpty()) output = "Nog geen data!"
            binding.txtOutputShoppingList.text = output
        } catch (e: Exception) {
            binding.txtOutputShoppingList.text = "Databank nog niet aangemaakt!"
        }
    }

}